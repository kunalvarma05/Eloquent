package eloquent.models;

import eloquent.FileComparator;

import java.util.Collections;
import java.util.List;

/**
 * @author Kunal
 */
public class Directory extends Metadata {

    protected List<File> files;

    public Directory(String path) {
        this.setPath(path);
    }

    public Directory(String path, List<File> files) {
        this.setPath(path);
        this.setFiles(files);
    }

    /**
     * Get List of files inside the directory
     *
     * @return List
     */
    public List<File> getFiles() {
        return files;
    }

    /**
     * Set file path
     *
     * @param path File path
     *
     * @return {@link Directory}
     */
    public Directory setPath(String path) {
        super.setPath(path);

        return this;
    }

    /**
     * Set file name
     *
     * @param name File name File name
     *
     * @return {@link Directory}
     */
    public Directory setName(String name) {
        super.setName(name);

        return this;
    }

    /**
     * Set Files inside the directory
     *
     * @param files List of files
     *
     * @return {@link Directory}
     */
    public Directory setFiles(List<File> files) {
        this.files = files;

        return this;
    }

    /**
     * Sort files inside the directory based on a key
     *
     * @param sortBy Sorting key
     *
     * @return List
     */
    public List<File> sort(String sortBy) {

        Collections.sort(this.getFiles(), new FileComparator(sortBy));

        return this.getFiles();
    }

    /**
     * Sort files inside the directory based on a key
     *
     * @param sortBy Sorting key
     * @param order Sorting order
     *
     * @return List
     */
    public List<File> sort(String sortBy, String order) {

        Collections.sort(this.getFiles(), new FileComparator(sortBy, order));

        return this.getFiles();
    }
}
