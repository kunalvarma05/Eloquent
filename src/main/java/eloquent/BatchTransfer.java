package eloquent;

import eloquent.adapters.AdapterInterface;
import eloquent.exceptions.EloquentException;
import eloquent.models.BatchTransferFile;
import eloquent.models.File;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BatchTransfer {

	protected Queue<BatchTransferFile> paths;

	protected List<File> files;

	protected AdapterInterface toAdapter;

	protected AdapterInterface fromAdapter;

	public BatchTransfer() {

		this.paths = new LinkedList<BatchTransferFile>();
		this.files = new ArrayList<>();
	}

	public BatchTransfer(List<String> filePaths) {
		super();

		for (String path : filePaths) {
			this.add(path);
		}
	}

    /**
     * Add path of the file to transfer
     *
     * @param path Path of the file
     *
     * @return {@link BatchTransfer}
     */
	public BatchTransfer add(String path) {
		return this.add(path, path);
	}

    /**
     * Add path of the file and the new path
     * of the file to transfer to.
     *
     * @param path Path of the file
     * @param newPath New Path of the file
     *
     * @return {@link BatchTransfer}
     */
	public BatchTransfer add(String path, String newPath) {
		this.paths.add(new BatchTransferFile(path, newPath));

		return this;
	}

    /**
     * Set the adapter to transfer the files to
     *
     * @param toAdapter The adapter to transfer the files to
     *
     * @return {@link BatchTransfer}
     */
	public BatchTransfer to(AdapterInterface toAdapter) {
		this.toAdapter = toAdapter;

		return this;
	}

    /**
     * Set the adapter to transfer the files from
     *
     * @param fromAdapter The adapter to transfer the files from
     *
     * @return {@link BatchTransfer}
     */
	public BatchTransfer from(AdapterInterface fromAdapter) {
		this.fromAdapter = fromAdapter;

		return this;
	}

    /**
     * Execute the batch Request and get the transferred files
     *
     * @return {@link List}
     *
     * @throws EloquentException Eloquent Exception
     */
	public List<File> getFiles() throws EloquentException {

		while (!this.paths.isEmpty()) {

			// Retrieve first path | HEAD
			BatchTransferFile transferFile = this.paths.poll();

			Transfer transfer = new Transfer(transferFile.getPath());
			transfer.from(this.fromAdapter).to(this.toAdapter).at(transferFile.getNewPath());

			try {
				File file = transfer.getFile();
				this.files.add(file);
			} catch (Exception e) {
				throw new EloquentException(e.getMessage());
			}
		}

		return this.files;
	}

}
