package Editor;

import java.util.Collection;
import Directory_Server.MainDirectory;

public class DirectoryProxy implements IDirectory{

	MainDirectory _dir;
	
	public DirectoryProxy(MainDirectory e)
	{
		_dir = e;
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
