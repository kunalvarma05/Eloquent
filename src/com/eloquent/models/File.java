package com.eloquent.models;

import java.util.Date;

public class File {

    protected String name;

    protected String path;

    protected String contents;

    protected Date timestamp;

    protected String mimeType;

    protected String size;

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
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
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
     * @param contents
     */
    public void setContents(String contents) {
        this.contents = contents;
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
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * @param timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get Mime Type (File Type)
     *
     * @return mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Set Mime Type (File Type)
     *
     * @param mimeType
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
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
     * @param size
     */
    public void setSize(String size) {
        this.size = size;
    }
}
