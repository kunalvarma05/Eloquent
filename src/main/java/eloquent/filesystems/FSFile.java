package eloquent.filesystems;

import java.util.Date;

/**
 * @author Kunal
 */
public class FSFile extends FSObject {

    protected String contents;

    public String getContents() {
        return contents;
    }

    public FSFile setContents(String contents) {
        this.contents = contents;

        return this;
    }

    public FSFile setName(String name) {
        this.name = name;

        return this;
    }
    public FSFile setPath(String path) {
        this.path = path;

        return this;
    }
    public FSFile setSize(String size) {
        this.size = size;

        return this;
    }
    public FSFile setTimestamp(Date timestamp) {
        this.timestamp = timestamp;

        return this;
    }

}
