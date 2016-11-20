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

	public BatchTransfer add(String path) {
		return this.add(path, path);
	}

	public BatchTransfer add(String path, String newPath) {
		this.paths.add(new BatchTransferFile(path, newPath));

		return this;
	}

	public BatchTransfer to(AdapterInterface toAdapter) {
		this.toAdapter = toAdapter;

		return this;
	}

	public BatchTransfer from(AdapterInterface fromAdapter) {
		this.fromAdapter = fromAdapter;

		return this;
	}

	public List<File> getFiles() throws EloquentException {

		while (!this.paths.isEmpty()) {

			// Retrieve first path | HEAD
			BatchTransferFile transferFile = this.paths.poll();

			Transfer transfer = new Transfer(transferFile.getPath());
			transfer.from(this.fromAdapter).to(this.toAdapter).at(transferFile.getNewpath());

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
