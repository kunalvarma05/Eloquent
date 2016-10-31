package com.eloquent;

import com.eloquent.adapters.AdapterInterface;
import com.eloquent.exceptions.EloquentException;
import com.eloquent.models.File;

public class Eloquent {

    protected AdapterInterface adapter;

    /**
     * Create a new Eloquent Instance
     *
     * @param adapter {@link AdapterInterface}
     */
    public Eloquent(AdapterInterface adapter) {
        this.setAdapater(adapter);
    }

    /**
     * Get the Adapter
     *
     * @return {@link AdapterInterface}
     */
    public AdapterInterface getAdapater() {
        return this.adapter;
    }

    /**
     * Set the Adapter
     *
     * @param adapter {@link AdapterInterface}
     */
    public void setAdapater(AdapterInterface adapter) {
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
        return this.getAdapater().read(path);
    }

    /**
     * Write a file
     *
     * @param path Path to file
     * @param contents Contents to write
     *
     * @return {@link File}
     */
    public File write(String path, String contents) throws EloquentException {
        return this.getAdapater().write(path, contents);
    }
}
