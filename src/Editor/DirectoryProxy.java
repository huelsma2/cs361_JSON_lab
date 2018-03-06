package Editor;


public class DirectoryProxy implements IDirectory{

	IDirectory _dir;
	
	public DirectoryProxy(IDirectory e)
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
