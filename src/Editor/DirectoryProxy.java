package Editor;

import Directory_Server.MainDirectory;

/**
 * Routes information from some source to a directory
 * @author Andrew Huelsman
 *
 */
public class DirectoryProxy implements IDirectory{

	IDirectory _dir;
	
	public DirectoryProxy()
	{
		_dir = new MainDirectory();
	}
	
	@Override
	public void print() {
		_dir.print();
	}

	@Override
	public void clear() {
		_dir.clear();
	}

	@Override
	public void add(String json) {
		_dir.add(json);
	}

}
