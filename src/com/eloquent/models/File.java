package com.eloquent.models;

import java.util.Date;

public class File {

    protected String name;

    protected String path;

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
     * @return {@link File}
     */
    public File setPath(String path) {
        this.path = path;

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
     * @return {@link File}
     */
    public File setName(String name) {
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
