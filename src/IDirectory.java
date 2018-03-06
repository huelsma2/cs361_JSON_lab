import java.util.Collection;

import Editor.Employee;


public interface IDirectory {
	void add(Collection<Employee> e);
	void print();
	void clear();
}
