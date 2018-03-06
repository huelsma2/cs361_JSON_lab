package Editor;

import java.util.ArrayList;
import java.util.Scanner;

public class DirectoryEditor {
	private static DirectoryProxy _proxy = new DirectoryProxy();
	static Scanner stdin = new Scanner(System.in);
	private static final ArrayList<String> _VALIDCOMMANDS = new ArrayList<String>();
	static {
		_VALIDCOMMANDS.add("ADD");
		_VALIDCOMMANDS.add("PRINT");
		_VALIDCOMMANDS.add("CLEAR");
	}
	
	public static void main() {
		 System.out.println("Enter command or HELP");
		 String input = stdin.nextLine();
		 if(input.toUpperCase().equals("HELP")) printHelp();
		 else if(input.toUpperCase().equals("EXIT")) exitDirectory();
		 else runCommand(input.toUpperCase());
		 main();
	}
	
	private static void printHelp() {
		System.out.println("Commands are:\n"
								+ "exit - exits the program\n"
								+ "add - adds an employee to the directory\n"
								+ "print - prints the contents of the directory\n"
								+ "clear - clears the directory");
	}
	
	private static void runCommand(String command) {
		if(!_VALIDCOMMANDS.contains(command)) main();
		if(command.equals("ADD")) runInput();
		else if(command.equals("PRINT")) _proxy.print();
		else _proxy.clear();
	}
	
	private static void runInput() {
		
	}
	
	private static void exitDirectory() {
		stdin.close();
		System.exit(0);
	}

}
