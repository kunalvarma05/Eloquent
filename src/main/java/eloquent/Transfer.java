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

    Transfer(String path) {
        this.fromPath = path;
        this.toPath = path;
    }

    /**
     * Get source adapter for transfer
     *
     * @return {@link AdapterInterface}
     */
    public AdapterInterface getFromAdapter() {
        return fromAdapter;
    }

    /**
     * Set source | from adapter for transfer
     *
     * @param fromAdapter Source adapter
     *
     * @return {@link Transfer}
     */
    public Transfer from(AdapterInterface fromAdapter) {
        this.fromAdapter = fromAdapter;

        return this;
    }

    /**
     * Get destination adapter for transfer
     *
     * @return {@link AdapterInterface}
     */
    public AdapterInterface getToAdapter() {
        return toAdapter;
    }

    /**
     * Set destination | to adapter for transfer
     *
     * @param toAdapter Destination adapter
     *
     * @return {@link Transfer}
     */
    public Transfer to(AdapterInterface toAdapter) {
        this.toAdapter = toAdapter;

        return this;
    }

    /**
     * Get destination | to transfer path
     *
     * @return String Path
     */
    public String getToPath() {
        return toPath;
    }

    public Transfer at(String toPath) {
        this.toPath = toPath;

        return this;
    }

    /**
     * Get source | from transfer path
     *
     * @return String Path
     */
    public String getFromPath() {
        return fromPath;
    }

    /**
     * Set from | source path
     *
     * @param fromPath Source path
     *
     * @return {@link Transfer}
     */
    public Transfer fromPath(String fromPath) {
        this.fromPath = fromPath;

        return this;
    }

    /**
     * Transfer files based on path and get the new file
     *
     * @return {@link File} Transferred file
     *
     * @throws EloquentException Eloquent Exception
     */
    public File getFile() throws EloquentException {
        File f = this.getFromAdapter().read(this.getFromPath());

        return this.getToAdapter().write(this.getToPath(), f.getContents());
    }
}
