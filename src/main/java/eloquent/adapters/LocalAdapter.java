package eloquent.adapters;

import eloquent.models.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.io.*;

import eloquent.exceptions.DirectoryAlreadyExistsException;
import eloquent.exceptions.FileAlreadyExistsException;
import eloquent.exceptions.DirectoryNotFoundException;
import eloquent.exceptions.EloquentException;
import eloquent.exceptions.FileNotFoundException;


/**
 * @author dhruvdutt
 */
public class LocalAdapter extends AbstractAdapter {

    /**
     * Default Character Set
     */
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * Local Filesystem Root
     */
    protected String root = "";

    public LocalAdapter(String root) {
        this.setRoot(root);
    }

    public String getRoot() {
        return this.root;
    }

    public void setRoot(String root) {

        // Add '/' at the end of
        // the root if not present.
        if (!root.endsWith("/")) {
            root = root + "/";
        }

        // Set the root
        this.root = root;
    }

    /**
     * Build the path
     *
     * @param path Path to a file or directory
     *
     * @return String
     */
    protected String buildPath(String path) {
        String root = this.getRoot();

        // The path to file/folder
        // must start with a '/'.
        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        return root + path;
    }

    @Override
    public File read(String path) throws EloquentException {

        path = this.buildPath(path);
        java.io.File file = new java.io.File(path);

        // File doesn't exist
        if (!file.exists()) {
            throw new FileNotFoundException(path);
        }

        String contents;
        String size;
        String name;
        Date timestamp;

        try {
            // Read the file into a string
            contents = new String(Files.readAllBytes(file.toPath()), DEFAULT_CHARSET);

            // Fetch the file size
            size = Long.toString(file.length());

            // Fetch the file name
            name = file.getName();

            // Fetch the file timestamp (last modified)
            timestamp = new Date(file.lastModified());
        } catch (Exception e) {
            throw new EloquentException(e.getMessage());
        }

        // Make the file model
        File fileModel = new File();
        fileModel.setPath(file.getAbsolutePath())
                .setContents(contents)
                .setSize(size)
                .setName(name)
                .setTimestamp(timestamp);

        // Return the file model
        return fileModel;
    }

    @Override
    public File write(String path, String contents) throws EloquentException {

        path = this.buildPath(path);

        java.io.File file = new java.io.File(path);

        // If file already exists
        if (file.exists()) {
            throw new FileAlreadyExistsException(path);
        }

        try {

            file.createNewFile();

            // creates a FileWriter Object
            FileWriter writer = new FileWriter(file);

            // Writes the content to the file
            writer.write(contents);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            throw new EloquentException(e.getMessage());
        }

        // Make the file model
        File fileModel = this.read(path);

        // Return the file model
        return fileModel;

    }

    @Override
    public File update(String path, String contents) throws EloquentException {

        File file;

        try {
            file = this.read(path);

            String oldContents;
            oldContents = file.getContents();

            String newContents;
            newContents = oldContents.concat(contents);

            file.setContents(newContents);

        } catch (Exception e) {
            throw new EloquentException(e.getMessage());
        }

        return file;
    }

    @Override
    public boolean has(String path) throws EloquentException {

        path = this.buildPath(path);
        java.io.File file = new java.io.File(path);

        boolean checkExists;
        checkExists = file.exists();

        return checkExists;
    }

    @Override
    public boolean rename(String oldPath, String newPath) throws EloquentException {

        oldPath = this.buildPath(oldPath);
        java.io.File file = new java.io.File(oldPath);

        // File doesn't exist
        if (!file.exists()) {
            throw new FileNotFoundException(oldPath);
        }

        boolean renameFlag;

        // Renaming file
        renameFlag = file.renameTo(new java.io.File(newPath));

        return renameFlag;
    }

    @Override
    public File copy(String path, String newPath) throws EloquentException {

        File file1 = this.read(path);

        String file1Contents = file1.getContents();

        File file2 = this.write(newPath, file1Contents);

        return file2;

    }

    @Override
    public boolean delete(String path) throws EloquentException {

        path = this.buildPath(path);
        java.io.File file = new java.io.File(path);

        // File doesn't exist
        if (!file.exists()) {
            throw new FileNotFoundException(path);
        }

        boolean deleteFlag;

        // Deleting file
        deleteFlag = file.delete();

        return deleteFlag;
    }

    @Override
    public boolean deleteDir(String path) throws EloquentException {

        path = this.buildPath(path);
        java.io.File file = new java.io.File(path);

        // Directory doesn't exist
        if (!file.isDirectory()) {
            throw new DirectoryNotFoundException(path);
        }

        boolean deleteFlag;

        // Deleting file
        deleteFlag = deleteDirRecursively(file);

        return deleteFlag;
    }

    private boolean deleteDirRecursively(java.io.File file) throws EloquentException {

        String[] children = file.list();

        boolean success = false;

        try {
            for (String aChildren : children) {
                success = deleteDirRecursively(new java.io.File(file, aChildren));
                if (!success) {
                    return false;
                }
            }
        } catch (Exception e) {
            throw new EloquentException(e.getMessage());
        }

        return success;
    }

    @Override
    public boolean createDir(String path) throws EloquentException {

        path = this.buildPath(path);
        java.io.File file = new java.io.File(path);

        boolean dirCreated;

        try {
            // Creates directory
            dirCreated = file.mkdir();
        } catch (Exception e) {
            throw new EloquentException(e.getMessage());
        }

        // Directory already exists
        if (!dirCreated) {
            throw new DirectoryAlreadyExistsException(path);
        }

        return dirCreated;
    }
}
