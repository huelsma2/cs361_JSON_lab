/**
 * @author Andrew Yehle, Michael Davis
 */
package Directory_Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

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
		dir.sort(new LexCompare());
		for(int i = 0; i<dir.size(); i++){
			
			System.out.println(dir.get(i).toString());
			
		}
		
	}
	
	public void clear(){
		
		dir.clear();
		
	}
	
	private class LexCompare implements Comparator<Employee>
	{

		@Override
		public int compare(Employee arg0, Employee arg1) {
			int ret = arg0.get_lname().compareTo(arg1.get_lname());
			if (ret==0) ret = arg0.get_fname().compareTo(arg1.get_fname());
			return ret;
		}

		
	}
	
}
