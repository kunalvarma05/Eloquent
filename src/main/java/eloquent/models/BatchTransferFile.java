package eloquent.models;

/**
 * @author Kunal
 */
public class BatchTransferFile {

	protected String path;

	protected String newPath;
g
	public BatchTransferFile(String path, String newPath) {
		this.setPath(path).setNewPath(newPath);
	}

	/**
	 * Get Path of the file
	 *
	 * @return Path of the file
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Set path of the file
	 *
	 * @param path Path of the file
	 *
	 * @return {@link BatchTransferFile}
	 */
	public BatchTransferFile setPath(String path) {
		this.path = path;

		return this;
	}

	/**
	 * Get the path to transfer the file to
	 *
	 * @return New path of the file
	 */
	public String getNewPath() {
		return newPath;
	}

	/**
	 * Set path of the file
	 *
	 * @param newPath Set new path of the file
	 *
	 * @return {@link BatchTransferFile}
	 */
	public BatchTransferFile setNewPath(String newPath) {
		this.newPath = newPath;

		return this;
	}
}
