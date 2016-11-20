package eloquent.exceptions;

/**
 * @author dhruvdutt
 */
public class DirectoryAlreadyExistsException extends EloquentException {

	public DirectoryAlreadyExistsException(String filePath) {
		super("Directory " + filePath + " already exists.");
	}

}
