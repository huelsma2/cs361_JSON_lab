/**
 * @author Andrew Yehle, Michael Davis
 */
package Directory_Server;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Editor.Employee;
import Editor.IDirectory;


public class MainDirectory  implements IDirectory{
	
	ArrayList<Employee> dir = new ArrayList<Employee>();
	
	public void add(String json){
		
		ArrayList<Employee> list = new Gson().fromJson(json, new TypeToken<Collection<Employee>>(){}.getType());
		dir.addAll(list);
		
	}
	
	public void print(){
		
		if(dir.size()==0)
			{
			System.out.println("<empty directory>");
			return;
			}
		for(int i = 0; i<dir.size(); i++){
			
			System.out.println(dir.get(i).toString());
			
		}
		
	}
	
	public void clear(){
		
		dir.clear();
		
	}
	
}
