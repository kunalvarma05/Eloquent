package eloquent.filesystems;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kunal
 */
public class FSDirectory extends FSObject {

    Map<String, FSObject> files;

    public FSDirectory() {
        super();
        this.files = new HashMap<>();
    }

    public Map<String, FSObject> getFiles() {
        return files;
    }

    public FSDirectory setFiles(Map<String, FSObject> files) {
        this.files = files;

        return this;
    }

    public FSDirectory setName(String name) {
        this.name = name;

        return this;
    }
    public FSDirectory setPath(String path) {
        this.path = path;

        return this;
    }
    public FSDirectory setSize(String size) {
        this.size = size;

        return this;
    }
    public FSDirectory setTimestamp(Date timestamp) {
        this.timestamp = timestamp;

        return this;
    }

}
