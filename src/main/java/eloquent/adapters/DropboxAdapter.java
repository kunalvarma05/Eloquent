package eloquent.adapters;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import eloquent.exceptions.EloquentException;
import eloquent.models.File;

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
		return null;
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
