package eloquent.models;

import eloquent.FileComparator;

import java.util.Collections;
import java.util.List;

/**
 * @author Kunal
 */
public class Directory extends Metadata {

    protected List<Metadata> files;

    public final boolean isDir = true;

    public Directory(String path) {
        this.setPath(path);
    }

    public Directory(String path, List<Metadata> files) {
        this.setPath(path);
        this.setFiles(files);
    }

    public List<Metadata> getFiles() {
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
    public Directory setFiles(List<Metadata> files) {
        this.files = files;

        return this;
    }

    /**
     * Sort files inside the directory
     *
     * @return {@link Directory}
     */
    public Directory sort() {
        this.getFiles().sort(new FileComparator());

        return this;
    }

    /**
     * Sort files inside the directory based on a key
     *
     * @param sortBy Sorting key
     *
     * @return {@link Directory}
     */
    public Directory sort(String sortBy) {

        this.getFiles().sort(new FileComparator(sortBy));

        return this;
    }

    /**
     * Sort files inside the directory based on a key
     *
     * @param sortBy Sorting key
     * @param order Sorting order
     *
     * @return {@link Directory}
     */
    public Directory sort(String sortBy, String order) {
        switch (order) {
            case "desc":
                // Sort ascending
                this.sort(sortBy);
                // Reverse
                Collections.reverse(this.getFiles());

            default:
                this.sort(sortBy);
        }

        return this;
    }
}
