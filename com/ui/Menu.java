package com.ui;

import java.util.*;

import com.database.*;
import com.exceptions.ExceptionWithErrorCode;

/**
 * Main class of the program
 * @version 1.0 10 May 2016
 */
public class Menu {
	private Scanner scanner = new Scanner(System.in);
	private String lastCommand = "";
	private boolean working = true;
	private Base base = new Base();
	private Commands commands = new Commands(this.base);
	public Menu() {
		
	}
	
	/** 
	 * The main update method runs in loop
	 */
	public void update() {
		System.out.println("Type ? for help.");
		while (working) {
			lastCommand = scanner.nextLine();
			if (execute(lastCommand) == Common.errorCode.COMMANDNOTFOUND) {
				System.out.println("Command not found.");
			}
		}
	}
	
	/**
	 * Prints a message of an exception that was thrown
	 * @param e Exception with a message
	 */
	public void printException(Exception e) {
		System.out.println(e.getMessage());
	}
	
	/** 
	 * Executes a command 
	 * @param command A string that contains a command
	 * @return An exit code 0 if there were no errors
	 */
	public Common.errorCode execute(String command) {
		String[] parameters = command.split(" ");
		Common.errorCode result = Common.errorCode.SUCCESS;
		try {
			if (parameters[0].equals("?"))
				result = commands.showHelp(parameters);
			else if (parameters[0].equals("add"))
				result = commands.add(parameters);
			else if (parameters[0].equals("find")) 
				result = commands.find(parameters);
			else if (parameters[0].equals("show"))
				result = commands.show(parameters);
			else if (parameters[0].equals("sort")) 
				result = commands.sort(parameters);
			else if (parameters[0].equals("exit")) {
				working = false;
				System.out.println("Exiting...");
				result = Common.errorCode.EXIT;
			} else 
				result = Common.errorCode.COMMANDNOTFOUND;
		} catch (ExceptionWithErrorCode e) {
			result = e.GetErrorCode();
			printException(e);
		}
		return result;
	}
}
