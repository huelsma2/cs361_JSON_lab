import java.util.Collection;

import Editor.Employee;


public interface Directory {
	void add(Collection<Employee> e);
	void print();
	void clear();
}
