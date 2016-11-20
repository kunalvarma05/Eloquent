package eloquent.models;

import java.util.Date;

public class File extends Metadata {

    protected String contents;

    protected Date timestamp;

    protected String size;

    public File() {
        super();
    }

    public File(String path, String contents) {
        this.path = path;
        this.contents = contents;
    }

    /**
     * Set file path
     *
     * @param path File path
     *
     * @return {@link File}
     */
    public File setPath(String path) {
        super.setPath(path);

        return this;
    }

    /**
     * Set file name
     *
     * @param name File name
     *
     * @return {@link File}
     */
    public File setName(String name) {
        super.setName(name);

        return this;
    }

    /**
     * Get file contents
     *
     * @return String
     */
    public String getContents() {
        return contents;
    }

    /**
     * Set file contents
     *
     * @param contents File contents
     *
     * @return {@link File}
     */
    public File setContents(String contents) {
        this.contents = contents;

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
     * @return {@link File}
     */
    public File setTimestamp(Date timestamp) {
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
     * @return {@link File}
     */
    public File setSize(String size) {
        this.size = size;

        return this;
    }
}
