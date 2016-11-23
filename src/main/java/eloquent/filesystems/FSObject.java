package eloquent.filesystems;

import java.util.Date;

/**
 * @author Kunal
 */
public class FSObject {

    protected String name;

    protected String path;

    protected String size;

    protected Date timestamp;

    public String getName() {
        return this.name;
    }

    public FSObject setName(String name) {
        this.name = name;

        return this;
    }

    public String getPath() {
        return path;
    }

    public FSObject setPath(String path) {
        this.path = path;

        return this;
    }

    public String getSize() {
        return size;
    }

    public FSObject setSize(String size) {
        this.size = size;

        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public FSObject setTimestamp(Date timestamp) {
        this.timestamp = timestamp;

        return this;
    }
}
