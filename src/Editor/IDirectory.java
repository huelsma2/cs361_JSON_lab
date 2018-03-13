package Editor;

/**
 * Interface to ensure connection between a proxy and directory
 * @author Andrew Huelsman
 *
 */
public interface IDirectory {
	
	void add(String json);
	void print(String json);
	void clear(String json);
}
