package eloquent.models;

import java.util.Date;

public class File extends Metadata {

    protected String contents;

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
        this.path = path;

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
        this.name = name;

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
