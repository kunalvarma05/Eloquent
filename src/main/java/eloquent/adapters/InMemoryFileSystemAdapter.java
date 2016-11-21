package eloquent.adapters;

import eloquent.exceptions.EloquentException;
import eloquent.filesystems.*;
import eloquent.models.Directory;
import eloquent.models.File;
import eloquent.models.Metadata;

/**
 * @author Kunal
 */
public class InMemoryFileSystemAdapter extends AbstractAdapter {

	protected final InMemoryFileSystem filesystem;

	public InMemoryFileSystemAdapter(InMemoryFileSystem fs) {
		this.filesystem = fs;
	}

	@Override
	public File read(String path) throws EloquentException {
		try {
			FSFile file = this.filesystem.read(path);
			File meta = new File();
			meta.setName(file.getName());
			meta.setSize(file.getSize());
			meta.setContents(file.getContents());
			meta.setTimestamp(file.getTimestamp());

			return meta;
		} catch (InMemoryFileSystemException e) {
			throw new EloquentException(e.getMessage());
		}
	}

	@Override
	public Metadata getMetadata(String path) throws EloquentException {
		try {
			if (!this.filesystem.has(path)) {
				throw new EloquentException("Path " + path + " doesn't exist!");
			}
			FSObject file = this.filesystem.getFiles().get(path);
			Metadata meta = new Metadata();
			meta.setName(file.getName());
			meta.setSize(file.getSize());
			meta.setTimestamp(file.getTimestamp());

			return meta;
		} catch (InMemoryFileSystemException e) {
			throw new EloquentException(e.getMessage());
		}
	}

	@Override
	public File write(String path, String contents) throws EloquentException {
        try {
            FSFile file = this.filesystem.write(path, contents);
            File meta = new File(path, contents);
            meta.setName(file.getName())
                    .setSize(file.getSize())
                    .setContents(file.getContents())
                    .setTimestamp(file.getTimestamp());

            return meta;
        } catch (InMemoryFileSystemException e) {
            throw new EloquentException(e.getMessage());
        }
	}

	@Override
	public File update(String path, String contents) throws EloquentException {
        try {
            FSFile file = this.filesystem.update(path, contents);
            File meta = new File();
            meta.setName(file.getName());
            meta.setSize(file.getSize());
            meta.setContents(file.getContents());
            meta.setTimestamp(file.getTimestamp());

            return meta;
        } catch (InMemoryFileSystemException e) {
            throw new EloquentException(e.getMessage());
        }
	}

	@Override
	public boolean has(String path) throws EloquentException {
        try {
            return this.filesystem.has(path);
        } catch (InMemoryFileSystemException e) {
            throw new EloquentException(e.getMessage());
        }
	}

	@Override
	public boolean rename(String path, String newPath) throws EloquentException {
        try {
            return this.filesystem.rename(path, newPath);
        } catch (InMemoryFileSystemException e) {
            throw new EloquentException(e.getMessage());
        }
	}

	@Override
	public File copy(String path, String newPath) throws EloquentException {
        try {
            FSFile file = this.filesystem.copy(path, newPath);
            File meta = new File();
            meta.setName(file.getName());
            meta.setSize(file.getSize());
            meta.setContents(file.getContents());
            meta.setTimestamp(file.getTimestamp());

            return meta;
        } catch (InMemoryFileSystemException e) {
            throw new EloquentException(e.getMessage());
        }
	}

	@Override
	public boolean delete(String path) throws EloquentException {
        try {
            return this.filesystem.delete(path);
        } catch (InMemoryFileSystemException e) {
            throw new EloquentException(e.getMessage());
        }
	}

	@Override
	public boolean deleteDir(String path) throws EloquentException {
        try {
            return this.filesystem.delete(path);
        } catch (InMemoryFileSystemException e) {
            throw new EloquentException(e.getMessage());
        }
	}

	@Override
	public Directory createDir(String path) throws EloquentException {
        try {
            FSDirectory file = this.filesystem.createDir(path);
            Directory meta = new Directory(path);
            meta.setName(file.getName());
            meta.setSize(file.getSize());
            meta.setTimestamp(file.getTimestamp());

            return meta;
        } catch (InMemoryFileSystemException e) {
            throw new EloquentException(e.getMessage());
        }
	}

	@Override
	public Directory readDir(String path) throws EloquentException {
        try {
            FSDirectory file = this.filesystem.readDir(path);
            Directory meta = new Directory(path);
            meta.setName(file.getName());
            meta.setSize(file.getSize());
            meta.setTimestamp(file.getTimestamp());

            return meta;
        } catch (InMemoryFileSystemException e) {
            throw new EloquentException(e.getMessage());
        }
	}
}
