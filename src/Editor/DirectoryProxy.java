package Editor;

import java.util.Collection;
import Directory_Server.MainDirectory;

public class DirectoryProxy implements IDirectory{

	MainDirectory _dir;
	
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
