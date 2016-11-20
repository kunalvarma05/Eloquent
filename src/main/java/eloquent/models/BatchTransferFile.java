package eloquent.models;

/**
 * @author Kunal
 */
public class BatchTransferFile {

	protected String path;

	protected String newpath;

	public BatchTransferFile(String path, String newpath) {
		this.setPath(path).setNewpath(newpath);
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
	public String getNewpath() {
		return newpath;
	}

	/**
	 * Set path of the file
	 *
	 * @param newpath Set new path of the file
	 *
	 * @return {@link BatchTransferFile}
	 */
	public BatchTransferFile setNewpath(String newpath) {
		this.newpath = newpath;

		return this;
	}
}
