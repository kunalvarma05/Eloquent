package eloquent;

import eloquent.adapters.AdapterInterface;
import eloquent.exceptions.EloquentException;
import eloquent.models.Directory;
import eloquent.models.File;
import eloquent.models.Metadata;

public class Eloquent {

    protected AdapterInterface adapter;

    /**
     * Create a new Eloquent Instance
     *
     * @param adapter {@link AdapterInterface}
     */
    public Eloquent(AdapterInterface adapter) {
        this.setAdapter(adapter);
    }

    /**
     * Get the Adapter
     *
     * @return {@link AdapterInterface}
     */
    public AdapterInterface getAdapter() {
        return this.adapter;
    }

    /**
     * Set the Adapter
     *
     * @param adapter {@link AdapterInterface}
     */
    public void setAdapter(AdapterInterface adapter) {
        this.adapter = adapter;
    }

    /**
     * Read a file
     *
     * @param path Path to File
     *
     * @return {@link File}
     *
     * @throws EloquentException Eloquent Exception
     */
    public File read(String path) throws EloquentException {
        return this.getAdapter().read(path);
    }

    /**
     * Write a file
     *
     * @param path Path to file
     * @param contents Contents to write
     *
     * @return {@link File}
     *
     * @throws EloquentException Eloquent Exception
     */
    public File write(String path, String contents) throws EloquentException {
        return this.getAdapter().write(path, contents);
    }

    /**
     * Update a file.
     *
     * @param path File Path
     * @param contents File contents
     *
     * @return {@link File}
     *
     * @throws EloquentException Eloquent Exception
     */
    File update(String path, String contents) throws EloquentException {
        return this.getAdapter().update(path, contents);
    }

    /**
     * Create or Update a file.
     *
     * @param path File Path
     * @param contents File contents
     *
     * @return {@link File}
     *
     * @throws EloquentException Eloquent Exception
     */
    File put(String path, String contents) throws EloquentException {
        return this.getAdapter().put(path, contents);
    }

    /**
     * Check if a File exists or not
     *
     * @param path File Path
     *
     * @return boolean
     *
     * @throws EloquentException Eloquent Exception
     */
    boolean has(String path) throws EloquentException {
        return this.getAdapter().has(path);
    }

    /**
     * Rename a file
     *
     * @param path File Path
     * @param newPath New path
     *
     * @return boolean
     *
     * @throws EloquentException Eloquent Exception
     */
    boolean rename(String path, String newPath) throws EloquentException {
        return this.getAdapter().rename(path, newPath);
    }

    /**
     * Copy a file
     *
     * @param path File Path
     * @param newPath New path
     *
     * @return boolean
     *
     * @throws EloquentException Eloquent Exception
     */
    File copy(String path, String newPath) throws EloquentException {
        return this.getAdapter().copy(path, newPath);
    }

    /**
     * Delete a file
     *
     * @param path File Path
     *
     * @return boolean
     *
     * @throws EloquentException Eloquent Exception
     */
    boolean delete(String path) throws EloquentException {
        return this.getAdapter().delete(path);
    }

    /**
     * Delete a directory
     *
     * @param path File Path
     *
     * @return boolean
     *
     * @throws EloquentException Eloquent Exception
     */
    boolean deleteDir(String path) throws EloquentException {
        return this.getAdapter().deleteDir(path);
    }

    /**
     * Create a directory
     *
     * @param path File Path
     *
     * @return boolean
     *
     * @throws EloquentException Eloquent Exception
     */
    Directory createDir(String path) throws EloquentException {
        return this.getAdapter().createDir(path);
    }

    /**
     * Get metadata of a File or Directory
     *
     * @param path Path to file or folder
     *
     * @return {@link Metadata}
     *
     * @throws EloquentException
     */
    public Metadata getMetadata(String path) throws EloquentException {
        return this.getAdapter().getMetadata(path);
    }

    /**
     * Read / View a directory
     *
     * @param path File Path
     *
     * @return {@link Directory}
     *
     * @throws EloquentException Eloquent Exception
     */
    public Directory readDir(String path) throws EloquentException {
        return this.getAdapter().readDir(path);
    }


}
