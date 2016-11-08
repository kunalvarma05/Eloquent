package eloquent.adapters;

import eloquent.exceptions.EloquentException;
import eloquent.models.File;

public abstract class AbstractAdapter implements AdapterInterface {

    /**
     * Create or Update a file.
     *
     * @param path
     * @param contents
     *
     * @return {@link File}
     */
    public File put(String path, String contents) throws EloquentException {
        // File Exists
        if (this.has(path)) {
            return this.update(path, contents);
        }

        // File does not exist, create it
        return this.write(path, contents);
    }

}