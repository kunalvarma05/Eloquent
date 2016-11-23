package eloquent.filesystems;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kunal
 */
public class InMemoryFileSystem {

    protected static Map<String, FSObject> files;

    public InMemoryFileSystem() {
        files = new HashMap<>();
    }

    public Map<String, FSObject> getFiles() {
        return files;
    }

    public void setFiles(Map<String, FSObject> files) {
        InMemoryFileSystem.files = files;
    }

    /**
     * Read a file
     *
     * @param path
     *            File Path
     *
     * @return {@link FSFile}
     *
     * @throws InMemoryFileSystemException
     *             FileSystem Exception
     */
    public FSFile read(String path) throws InMemoryFileSystemException {
        if (this.getFiles().containsKey(path)) {
            return (FSFile) this.getFiles().get(path);
        } else {
            throw new InMemoryFileSystemException("Path " + path + " not found!");
        }
    }

    /**
     * Write a new file
     *
     * @param path File Path
     * @param contents File contents
     *
     * @return {@link FSFile}
     *
     * @throws InMemoryFileSystemException FileSystem Exception
     */
    public FSFile write(String path, String contents) throws InMemoryFileSystemException {
        if (!this.getFiles().containsKey(path)) {
            FSFile file = new FSFile();
            String size = Long.toString(contents.length());
            file.setName(extractName(path)).setPath(path).setSize(size).setTimestamp(new Date()).setContents(contents);
            this.getFiles().put(path, file);
            return file;
        } else {
            throw new InMemoryFileSystemException("Path " + path + " not found!");
        }
    }

    /**
     * Update a file
     *
     * @param path File Path
     * @param contents File contents
     *
     * @return {@link FSFile}
     *
     * @throws InMemoryFileSystemException FileSystem Exception
     */
    public FSFile update(String path, String contents) throws InMemoryFileSystemException {
        if (this.getFiles().containsKey(path)) {
            if (this.getFiles().get(path) instanceof FSFile) {
                FSFile file = (FSFile) this.getFiles().get(path);
                String size = Long.toString(contents.length());
                file.setName(extractName(path)).setPath(path).setSize(size).setTimestamp(new Date()).setContents(contents);
                return (FSFile) this.getFiles().put(path, file);
            } else {
                throw new InMemoryFileSystemException("Path " + path + " is not a file!");
            }
        } else {
            throw new InMemoryFileSystemException("Path " + path + " not found!");
        }
    }

    /**
     * Check if a File exists or not
     *
     * @param path File Path
     *
     * @return boolean
     *
     * @throws InMemoryFileSystemException FileSystem Exception
     */
    public boolean has(String path) throws InMemoryFileSystemException {
        return this.getFiles().containsKey(path);
    }

    /**
     * Rename a file
     *
     * @param path File Path
     * @param newPath New path
     *
     * @return boolean
     *
     * @throws InMemoryFileSystemException FileSystem Exception
     */
    public boolean rename(String path, String newPath) throws InMemoryFileSystemException {
        if (this.has(path)) {
            FSObject fs = this.read(path);
            this.getFiles().remove(path);
            fs.setPath(newPath);
            this.getFiles().put(newPath, fs);

            return true;
        } else {
            throw new InMemoryFileSystemException("Path " + path + " doesn't exist!");
        }
    }

    /**
     * Copy a file
     *
     * @param path File path
     * @param newPath New path
     *
     * @return {@link FSFile}
     *
     * @throws InMemoryFileSystemException FileSystem Exception
     */
    public FSFile copy(String path, String newPath) throws InMemoryFileSystemException {
        if (!this.has(path)) {
            throw new InMemoryFileSystemException("Path " + path + " doesn't exist!");
        }

        if (this.has(newPath)) {
            throw new InMemoryFileSystemException("Path " + newPath+ " already exist!");
        }

        FSFile fs = this.read(path);
        fs.setPath(newPath);
        this.getFiles().put(newPath, fs);

        return fs;
    }

    /**
     * Delete a file
     *
     * @param path File path
     *
     * @return boolean
     *
     * @throws InMemoryFileSystemException FileSystem Exception
     */
    public boolean delete(String path) throws InMemoryFileSystemException {
        if (!this.has(path)) {
            throw new InMemoryFileSystemException("Path " + path + " doesn't exist!");
        }

        if(this.getFiles().get(path) instanceof FSDirectory) {
            throw new InMemoryFileSystemException("Cannot delete directory. Use deleteDir");
        }

        this.getFiles().remove(path);
        return true;
    }

    /**
     * Delete a directory
     *
     * @param path File path
     *
     * @return boolean
     *
     * @throws InMemoryFileSystemException FileSystem Exception
     */
    public boolean deleteDir(String path) throws InMemoryFileSystemException {
        if (!this.has(path)) {
            throw new InMemoryFileSystemException("Path " + path + " doesn't exist!");
        }

        if(this.getFiles().get(path) instanceof FSFile) {
            throw new InMemoryFileSystemException("Cannot delete file. Use delete()");
        }

        this.getFiles().remove(path);
        return true;
    }

    /**
     * Create a directory
     *
     * @param path File path
     *
     * @return {@link FSDirectory}
     *
     * @throws InMemoryFileSystemException FileSystem Exception
     */
    public FSDirectory createDir(String path) throws InMemoryFileSystemException {
        if (this.has(path)) {
            throw new InMemoryFileSystemException("Path " + path + " already exist!");
        }

        FSDirectory dir = new FSDirectory();
        dir.setName(extractName(path)).setPath(path).setTimestamp(new Date());

        this.getFiles().put(path, dir);

        return dir;
    }

    /**
     * Read / View a directory
     *
     * @param path File Path
     *
     * @return {@link FSDirectory}
     *
     * @throws InMemoryFileSystemException FileSystem Exception
     */
    public FSDirectory readDir(String path) throws InMemoryFileSystemException {
        if (!this.has(path)) {
            throw new InMemoryFileSystemException("Path " + path + " doesn't exist!");
        }

        if (this.getFiles().get(path) instanceof FSDirectory) {
            return (FSDirectory) this.getFiles().get(path);
        } else {
            throw new InMemoryFileSystemException("Directory " + path + " doesn't exist!");
        }
    }

    /**
     * Extract file name from path
     *
     * @param path File path
     *
     * @return String
     */
    protected static String extractName(String path) {
        int index = path.lastIndexOf("/");

        return path.substring(index + 1);
    }

}
