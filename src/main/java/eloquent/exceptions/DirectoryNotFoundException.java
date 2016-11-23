package eloquent.exceptions;

/**
 * @author dhruvdutt
 */
public class DirectoryNotFoundException extends EloquentException {

    public DirectoryNotFoundException(String filePath) {
        super("Directory " + filePath + " not found.");
    }

}
