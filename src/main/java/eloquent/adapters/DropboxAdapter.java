package eloquent.adapters;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.GetMetadataErrorException;
import com.dropbox.core.v2.files.WriteMode;
import eloquent.exceptions.EloquentException;
import eloquent.models.File;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;

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
		try {
			DbxDownloader<FileMetadata> fileDownload = this.client.files().download(path);

			String contents = "";
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			// Copy the contents of the file
			// to the outputStream
			FileMetadata fileMetadata = fileDownload.download(outputStream);

			// outStream to String
			contents = outputStream.toString();

			// Create the Eloquent File Object
			File file = new File();
			file.setPath(fileMetadata.getPathLower());
			file.setName(fileMetadata.getName());
			file.setTimestamp(fileMetadata.getServerModified());
			file.setSize(Long.toString(fileMetadata.getSize()));
			file.setContents(contents);

			return file;
		} catch (DbxException | IOException e) {
			throw new EloquentException(e.getMessage());
		}
	}

	@Override
	public File write(String path, String contents) throws EloquentException {
		try {
			byte[] byteArray = contents.getBytes(Charset.defaultCharset());
			InputStream in = new ByteArrayInputStream(byteArray);

			FileMetadata fileMetadata = this.client.files().uploadBuilder(path).withMode(WriteMode.ADD)
					.withClientModified(new Date()).uploadAndFinish(in);

			// Create the Eloquent File Object
			File file = new File();
			file.setPath(fileMetadata.getPathLower()).setName(fileMetadata.getName())
					.setTimestamp(fileMetadata.getServerModified()).setSize(Long.toString(fileMetadata.getSize()))
					.setContents(contents);

			return file;
		} catch (IOException | DbxException ex) {
			throw new EloquentException(ex.getMessage());
		}

	}

	@Override
	public File update(String path, String contents) throws EloquentException {
		try {
			byte[] byteArray = contents.getBytes(Charset.defaultCharset());
			InputStream in = new ByteArrayInputStream(byteArray);

			FileMetadata fileMetadata = this.client.files().uploadBuilder(path).withMode(WriteMode.OVERWRITE)
					.withClientModified(new Date()).uploadAndFinish(in);

			// Create the Eloquent File Object
			File file = new File();
			file.setPath(fileMetadata.getPathLower()).setName(fileMetadata.getName())
					.setTimestamp(fileMetadata.getServerModified()).setSize(Long.toString(fileMetadata.getSize()))
					.setContents(contents);

			return file;
		} catch (IOException | DbxException ex) {
			throw new EloquentException(ex.getMessage());
		}
	}

	@Override
    public File put(String path, String contents) throws EloquentException {
        return this.update(path, contents);
    }

	@Override
	public boolean has(String path) throws EloquentException {
        try {
            FileMetadata metadata = (FileMetadata) this.client.files().getMetadata(path);

            return !metadata.getName().isEmpty();
        } catch (GetMetadataErrorException e) {
            // Path not found
            if (e.errorValue.isPath()) {
                return false;
            }
            throw new EloquentException(e.errorValue.isPath() ? "is path" : "false");
        } catch (DbxException e) {
            throw new EloquentException(e.getMessage());
        }
	}

	@Override
	public boolean rename(String path, String newPath) throws EloquentException {
		return false;
	}

	@Override
	public File copy(String path, String newPath) throws EloquentException {
		return null;
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
