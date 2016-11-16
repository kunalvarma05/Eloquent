package eloquent.adapters;

import eloquent.exceptions.EloquentException;
import eloquent.exceptions.FileNotFoundException;
import eloquent.models.File;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;

/**
 * @author Kunal
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
        return null;
    }

    @Override
    public File update(String path, String contents) throws EloquentException {
        return null;
    }

    @Override
    public boolean has(String path) throws EloquentException {
        return false;
    }

    @Override
    public boolean rename(String path, String newPath) throws EloquentException {
        return false;
    }

    @Override
    public boolean copy(String path, String newPath) throws EloquentException {
        return false;
    }

    @Override
    public boolean delete(String path) throws EloquentException {
        return false;
    }

    @Override
    public boolean deleteDir(String path) throws EloquentException {
        return false;
    }

    @Override
    public boolean createDir(String path) throws EloquentException {
        return false;
    }
}
