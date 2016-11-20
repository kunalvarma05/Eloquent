package eloquent;

import eloquent.adapters.AdapterInterface;
import eloquent.exceptions.EloquentException;
import eloquent.models.File;

public class Transfer {

    protected AdapterInterface fromAdapter;
    protected AdapterInterface toAdapter;
    protected String toPath;
    protected String fromPath;
    protected File file;

    Transfer(String path)
    {
        this.fromPath = path;
        this.toPath = path;
    }

    public AdapterInterface getFromAdapter() {
        return fromAdapter;
    }

    public Transfer from(AdapterInterface fromAdapter) {
        this.fromAdapter = fromAdapter;

        return this;
    }

    public AdapterInterface getToAdapter() {
        return toAdapter;
    }

    public Transfer to(AdapterInterface toAdapter) {
        this.toAdapter = toAdapter;

        return this;
    }

    public String getToPath() {
        return toPath;
    }

    public Transfer at(String toPath) {
        this.toPath = toPath;

        return this;
    }

    public String getFromPath() {
        return fromPath;
    }

    public Transfer fromPath(String fromPath) {
        this.fromPath = fromPath;

        return this;
    }

    public File getFile() throws EloquentException {
        File f = this.getFromAdapter().read(this.getFromPath());

        return this.getToAdapter().write(this.getToPath(), f.getContents());
    }
}
