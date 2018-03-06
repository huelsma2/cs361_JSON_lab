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
		
		
		
	}
	
	public void clear(){
		
	}
	
	
}
