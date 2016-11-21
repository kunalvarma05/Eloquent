package eloquent.models;

import java.util.Date;

/**
 * @author Kunal
 */
public class Metadata {

    protected String name;

    protected String path;

    protected Date timestamp;

    protected String size;

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
        return this.name;
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

    /**
     * Get Timestamp
     *
     * @return Date
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Set Timestamp
     *
     * @param timestamp File timestamp
     *
     * @return {@link Metadata}
     */
    public Metadata setTimestamp(Date timestamp) {
        this.timestamp = timestamp;

        return this;
    }

    /**
     * Get file size
     *
     * @return String
     */
    public String getSize() {
        return size;
    }

    /**
     * Set file size
     *
     * @param size File size
     *
     * @return {@link Metadata}
     */
    public Metadata setSize(String size) {
        this.size = size;

        return this;
    }

}
