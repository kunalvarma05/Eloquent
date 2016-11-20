package eloquent.models;

import java.util.List;

/**
 * @author Kunal
 */
public class Directory extends Metadata {

    protected List<Metadata> files;

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

    public Directory setFiles(List<Metadata> files) {
        this.files = files;

        return this;
    }
}
