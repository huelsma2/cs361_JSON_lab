package Editor;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

public class DirectoryEditor {
	private static DirectoryProxy _proxy = new DirectoryProxy();
	static Scanner stdin = new Scanner(System.in);
	private static final ArrayList<String> _VALIDCOMMANDS = new ArrayList<String>();
	private static ArrayList<String[]> _employees = new ArrayList<String[]>();
	static {
		_VALIDCOMMANDS.add("ADD");
		_VALIDCOMMANDS.add("PRINT");
		_VALIDCOMMANDS.add("CLEAR");
	}
	
	public static void main(String[] args) {
		 System.out.println("Enter command or HELP");
		 String input = stdin.nextLine();
		 if(input.toUpperCase().equals("HELP")) printHelp();
		 else if(input.toUpperCase().equals("EXIT")) exitDirectory();
		 else runCommand(input.toUpperCase());
		 main(args);
	}
	
	
	
	private static void printHelp() {
		System.out.println("Commands are:\n"
								+ "exit - exits the program\n"
								+ "add - adds an employee to the directory\n"
								+ "print - prints the contents of the directory\n"
								+ "clear - clears the directory");
	}
	
	private static void runCommand(String command) {
		if(!_VALIDCOMMANDS.contains(command)) main(null);
		if(command.equals("ADD")) runInput();
		else if(command.equals("PRINT")) _proxy.print();
		else _proxy.clear();
	}
	
	private static void runInput() {
		System.out.println("Enter employee (First Name, Last Name, Department, Phone #):");
		String input = stdin.nextLine();
		if(input.toUpperCase().equals("END")) {
			sendAdd();
			return;
		}
		String[] inputArray = input.split(" ");
		if(inputArray.length != 4) {
			System.out.println("Invalid employee input");
			runInput();
		}
		_employees.add(inputArray);
		runInput();
	}
	
	private static void sendAdd() {
		Gson g = new Gson();
		ArrayList<Employee> p = new ArrayList<Employee>();
		for(String[] eData: _employees) {
			p.add( new Employee(eData[0], eData[1], eData[2], eData[3]));
		}
		_employees.clear();
		String out = g.toJson(p);
		_proxy.add(out);
	}
	
	private static void exitDirectory() {
		stdin.close();
		System.exit(0);
	}

}
