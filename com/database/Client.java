package com.database;

import java.util.Date;

/**
 * Class of a client
 * @version 1.0 10 May 2016
 */
public class Client extends BaseObject {
	private String name;
	private String lastName;
	private String secondName;
	private Date birthDate;
	private int id;
	Client(int id, String name, String lastName, String secondName, String date) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.secondName = secondName;
		this.birthDate = Common.convertStringToDate(date);
		this.id = id;
	}
	
	/** Returns client id 
	 * @return A client id
	 * */
	public int getId() {
		return this.id;
	}
	
	/** Returns client name 
	 * @return A string containing client name
	 * */
	public String getName() {
		return this.name;
	}
	
	/** Returns client second name 
	 * @return A string containing client second name
	 * */
	public String getSecondName() {
		return this.secondName;
	}
	
	/** Returns client last name 
	 * @return A string containing client last name
	 * */
	public String getLastName() {
		return this.lastName;
	}
	
	/** Returns client birth date as string 
	 * @return A string containing client birth date
	 * */
	public String getBirthDateAsString() {
		return Common.convertDateToString(this.birthDate);
	}
	
	/** Returns client birth date 
	 * @return A reference to a Date object containing client burth date
	 * */
	public Date getBirthDate() {
		return this.birthDate;
	}
	
	/**
	 * Prints a message when adding a new client
	 */
	public void printAddMessage() {
		System.out.println("Client was added: "+this.id+" "+this.name+" "+
				this.secondName+" "+this.lastName+" "+Common.convertDateToString(this.birthDate));
	}
	
	public void printData() {
		System.out.println(this.id+" "+this.name+" "+
				this.secondName+" "+this.lastName+" "+Common.convertDateToString(this.birthDate));
	}
}
