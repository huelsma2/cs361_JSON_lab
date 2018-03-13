

package Editor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

/**
 * 
 * @author Steven Messer
 *
 */
public class DirectoryEditor {
	private static DirectoryProxy _proxy = new DirectoryProxy();
	static Scanner stdin = new Scanner(System.in);
	private static final ArrayList<String> _VALIDCOMMANDS = new ArrayList<String>();
	private static ArrayList<String[]> _employees = new ArrayList<String[]>();
	private final static String _fileLocation = "commandFile";
	static {
		_VALIDCOMMANDS.add("ADD");
		_VALIDCOMMANDS.add("PRINT");
		_VALIDCOMMANDS.add("CLR");
	}
	
	public static void main(String[] args) {
		 simloop();
	}
	
	/** Simulation loop. Runs the simulation until exit is entered here or through the file or console input
	 * 
	 */
	private static void simloop() {
		 stdin = new Scanner(System.in);
		 System.out.println("[F]ile, [C]onsole, or [E]xit");
		 String input = stdin.nextLine();
		 if(input.toUpperCase().equals("F")) file();
		 if(input.toUpperCase().equals("C")) console();
		 if(input.toUpperCase().equals("E")) exitDirectory();
		 simloop();
	}
	
	/** File handler, sends commands read from a file to runCommand()
	 * 
	 */
	private static void file() {
		 InputStream in = DirectoryEditor.class.getResourceAsStream(_fileLocation);
		 String input;
		 
		 try {
				stdin = new Scanner(in);
			} catch (Exception e) {
					System.out.println("Failed at opening resource " + e);
					System.exit(0);
			}
		 while(stdin.hasNextLine()) {
			 input = stdin.nextLine();
			 runCommand(input, true);
		 }
		 try {
			in.close();
		} catch (IOException e) {
			System.out.println("Failed at closing resource " + e);
		}
		simloop();
	}
	
	/** Console handler, sends commands entered by the user to runCommand()
	 * 
	 */
	private static void console() {
		 System.out.println("Enter command or HELP");
		 String input = stdin.nextLine();
		 if(input.toUpperCase().equals("HELP")) printHelp();
		 else if(input.toUpperCase().equals("EXIT")) exitDirectory();
		 else runCommand(input.toUpperCase(), false);
		 console();
	}
	
	/** Prints the valid commands to the console for the user
	 * 
	 */
	private static void printHelp() {
		System.out.println("Commands are:\n"
								+ "exit - exits the program\n"
								+ "add - adds an employee to the directory\n"
								+ "print - prints the contents of the directory\n"
								+ "clr - clears the directory");
	}
	
	/** checks and executes incoming commands
	 * 
	 * @param command The command to be executed
	 * @param file boolean true if command came from file, false if came from console.
	 * 			If this is true, commands are echo'd to the console if they came from a file
	 */
	private static void runCommand(String command, boolean file) {
		if(file) System.out.println(command);
		if(!_VALIDCOMMANDS.contains(command)) return;
		if(command.equals("ADD")) runInput(file);
		else if(command.equals("PRINT")) _proxy.print("PRINT");
		else _proxy.clear("CLEAR");
	}
	
	/** Collects the stream of employee input data until the End command is passed
	 * 
	 * @param file boolean true if command came from file, false if came from console.
	 * 			If this is true, employee data is echo'd to the console if they came from a file
	 */
	private static void runInput(boolean file) {
		System.out.println("Enter employee (First Name, Last Name, Department, Phone #, Gender, Title):");
		String input = stdin.nextLine();
		if(file) System.out.println(input);
		if(input.toUpperCase().equals("END")) {
			sendAdd();
			return;
		}
		String[] inputArray = input.split(" ");
		if(inputArray.length != 6) {
			System.out.println("Invalid employee input");
			runInput(file);
			return;
		}
		_employees.add(inputArray);
		runInput(file);
	}
	
	/** Converts employee data array into a Json string
	 * 
	 */
	private static void sendAdd() {
		Gson g = new Gson();
		ArrayList<Employee> p = new ArrayList<Employee>();
		for(String[] eData: _employees) {
			p.add( new Employee(eData[0], eData[1], eData[2], eData[3], eData[4], eData[5]));
		}
		_employees.clear();
		String out = g.toJson(p);
		out = "ADD " + out;
		_proxy.add(out);
	}
	
	/** Exits directory editor
	 * 
	 */
	private static void exitDirectory() {
		stdin.close();
		System.exit(0);
	}

}
