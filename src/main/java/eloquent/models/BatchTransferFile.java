package eloquent.models;

/**
 * @author Kunal
 */
public class BatchTransferFile {

    protected String path;

    protected String newpath;

    public BatchTransferFile(String path, String newpath) {
        this.setPath(path);
        this.setNewpath(newpath);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNewpath() {
        return newpath;
    }

    public void setNewpath(String newpath) {
        this.newpath = newpath;
    }
}
