package eloquent.adapters;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import eloquent.exceptions.EloquentException;
import eloquent.models.File;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Kunal
 */
public class DropboxAdapter extends AbstractAdapter {

	protected DbxClientV2 client;

	public DropboxAdapter(String access_token, String identifier, String locale) {
		// Create Dropbox client
		DbxRequestConfig config = DbxRequestConfig.newBuilder(identifier).withUserLocale(locale).build();

		this.client = new DbxClientV2(config, access_token);
	}

	@Override
	public File read(String path) throws EloquentException {
		DbxDownloader<FileMetadata> fileDownload = null;
		FileMetadata fileMetadata = null;
		try {
			fileDownload = this.client.files().download(path);
		} catch (DbxException e) {
			throw new EloquentException(e.getMessage());
		}

		String contents = "";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			// Copy the contents of the file
			// to the outputStream
			fileMetadata = fileDownload.download(outputStream);

			// outStream to String
			contents = outputStream.toString();
		} catch (DbxException | IOException e) {
			throw new EloquentException(e.getMessage());
		}

		// Create the Eloquent File Object
		File file = new File();
		file.setPath(fileMetadata.getPathLower());
		file.setName(fileMetadata.getName());
		file.setTimestamp(fileMetadata.getServerModified());
		file.setSize(Long.toString(fileMetadata.getSize()));
		file.setContents(contents);

		return file;
	}

	@Override
	public File write(String path, String contents) throws EloquentException {
		return null;
	}

	@Override
	public File update(String path, String contents) throws EloquentException {
		return null;
	}

	@Override
	public boolean has(String path) throws EloquentException {
		return false;
	}

	@Override
	public boolean rename(String path, String newPath) throws EloquentException {
		return false;
	}

	@Override
	public boolean copy(String path, String newPath) throws EloquentException {
		return false;
	}

	@Override
	public boolean delete(String path) throws EloquentException {
		return false;
	}

	@Override
	public boolean deleteDir(String path) throws EloquentException {
		return false;
	}

	@Override
	public boolean createDir(String path) throws EloquentException {
		return false;
	}
}
