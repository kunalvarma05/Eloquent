package eloquent.adapters;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import eloquent.exceptions.EloquentException;
import eloquent.models.Directory;
import eloquent.models.File;
import eloquent.models.Metadata;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public Metadata getMetadata(String path) throws EloquentException {
		try {
			com.dropbox.core.v2.files.Metadata metadata = this.client.files().getMetadata(path);

			Metadata meta = new Metadata();
			meta.setName(metadata.getName()).setPath(metadata.getPathLower());

			return meta;
		} catch (DbxException e) {
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
			throw new EloquentException(e.getMessage());
		} catch (DbxException e) {
			throw new EloquentException(e.getMessage());
		}
	}

	@Override
	public boolean rename(String path, String newPath) throws EloquentException {
		try {
			MoveBuilder moveBuilder = this.client.files().moveBuilder(path, newPath);
			moveBuilder.withAutorename(true);

			com.dropbox.core.v2.files.Metadata metadata = moveBuilder.start();

			return !metadata.getPathLower().equals(newPath);
		} catch (DbxException e) {
			throw new EloquentException(e.getMessage());
		}
	}

	@Override
	public File copy(String path, String newPath) throws EloquentException {
		try {
			CopyBuilder copyBuilder = this.client.files().copyBuilder(path, newPath);
			copyBuilder.withAutorename(true);

			FileMetadata fileMetadata = (FileMetadata) copyBuilder.start();

			// Create the Eloquent File Object
			File file = new File();
			file.setPath(fileMetadata.getPathLower()).setName(fileMetadata.getName())
					.setTimestamp(fileMetadata.getServerModified()).setSize(Long.toString(fileMetadata.getSize()));

			return file;
		} catch (DbxException e) {
			throw new EloquentException(e.getMessage());
		}
	}

	@Override
	public boolean delete(String path) throws EloquentException {
		try {
			FileMetadata metadata = (FileMetadata) this.client.files().delete(path);

			return !metadata.getName().isEmpty();
		} catch (DbxException e) {
			throw new EloquentException(e.getMessage());
		}
	}

	@Override
	public boolean deleteDir(String path) throws EloquentException {
		return this.deleteDir(path);
	}

	@Override
	public Directory createDir(String path) throws EloquentException {
		try {
			FolderMetadata metadata = this.client.files().createFolder(path, true);

			Directory directory = new Directory(path);
			directory.setPath(metadata.getPathLower()).setName(metadata.getName());

			return directory;
		} catch (DbxException e) {
			throw new EloquentException(e.getMessage());
		}
	}

	@Override
	public Directory readDir(String path) throws EloquentException {
		try {
			Metadata metadata = this.getMetadata(path);
			ListFolderBuilder listFolderBuilder = this.client.files().listFolderBuilder(path);

			ListFolderResult listFolderResult = listFolderBuilder.start();
			List<com.dropbox.core.v2.files.Metadata> items = listFolderResult.getEntries();
			List<Metadata> files = new ArrayList<>();

			for (com.dropbox.core.v2.files.Metadata meta : items) {
				if (meta instanceof FolderMetadata) {
					Directory f = new Directory(meta.getPathLower());
					f.setName(meta.getName());
					files.add(f);
				} else {
					File f = new File();
					long size = ((FileMetadata) meta).getSize();
					Date timestamp = ((FileMetadata) meta).getServerModified();
					f.setName(meta.getName()).setPath(meta.getPathLower()).setSize(Long.toString(size))
							.setTimestamp(timestamp);

					files.add(f);
				}
			}

			Directory directory = new Directory(path);
			directory.setPath(metadata.getPath()).setName(metadata.getName()).setFiles(files);

			return directory;
		} catch (DbxException e) {
			throw new EloquentException(e.getMessage());
		}
	}

}
