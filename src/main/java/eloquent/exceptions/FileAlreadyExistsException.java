package eloquent.exceptions;

/**
 * @author dhruvdutt
 */
public class FileAlreadyExistsException extends EloquentException {

    public FileAlreadyExistsException(String filePath) {
        super("File " + filePath + " already exists.");
    }

}
