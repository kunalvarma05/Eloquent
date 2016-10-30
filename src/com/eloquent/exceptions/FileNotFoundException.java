package com.eloquent.exceptions;

/**
 * @author Kunal
 */
public class FileNotFoundException extends EloquentException {

    public FileNotFoundException(String filePath) {
        super("File " + filePath + " was not found.");
    }

}
