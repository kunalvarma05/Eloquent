package eloquent.models;

/**
 * @author Kunal
 */
public class Metadata {

    protected String name;

    protected String path;

    /**
     * Get file path
     *
     * @return String
     */
    public String getPath() {
        return path;
    }

    /**
     * Set file path
     *
     * @param path File path
     *
     * @return {@link Metadata}
     */
    public Metadata setPath(String path) {
        this.path = path;

        return this;
    }

    /**
     * Get file name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set file name
     *
     * @param name File name File name
     *
     * @return {@link Metadata}
     */
    public Metadata setName(String name) {
        this.name = name;

        return this;
    }

}
