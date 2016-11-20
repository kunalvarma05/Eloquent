package eloquent.models;

import java.util.List;

/**
 * @author Kunal
 */
public class Directory extends File {

    protected List<File> files;

    public Directory(String path) {
        this.setPath(path);
    }

    public Directory(String path, List<File> files) {
        this.setPath(path);
        this.setFiles(files);
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
