package eloquent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

import eloquent.exceptions.EloquentException;
import eloquent.models.File;
import eloquent.adapters.AdapterInterface;

public class BatchTransfer {

	protected Queue<String> paths;

	protected List<File> files;

	protected AdapterInterface toAdapter;

	protected AdapterInterface fromAdapter;

	public BatchTransfer() {

		this.paths = new LinkedList<>();
		this.files = new ArrayList<>();
	}

	public BatchTransfer(List<String> filePaths) {
		super();

		for (String path : filePaths) {
			this.add(path);
		}
	}

	public BatchTransfer add(String path) {
		this.paths.add(path);

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
			String path = this.paths.poll();

			Transfer transfer = new Transfer(path);
			transfer.from(this.fromAdapter).to(this.toAdapter);

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
