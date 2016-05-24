package com.database;

import java.util.Date;

/**
 * Class of a product
 * @version 1.0 10 May 2016
 */
public class Product extends BaseObject {
	private String brand;
	private String type;
	private String color;
	private Date releaseDate;
	private int id;
	Product(int id, String brand, String type, String color, String date) {
		super();
		this.brand = brand;
		this.type= type;
		this.color = color;
		this.releaseDate = Common.convertStringToDate(date);
		this.id = id;
	}
	
	/** Returns product id 
	 * @return Id of a product
	 * */
	public int getId() {
		return this.id;
	}
	
	/** Returns product brand name as a string
	 * @return A string containing brand name
	 */
	public String getBrand() {
		return this.brand;
	}
	
	/** Returns product type 
	 * @return A string containing product type
	 * */
	public String getType() {
		return this.type;
	}
	
	/** Return product color 
	 * @return A string containing product color
	 * */
	public String getColor() {
		return this.color;
	}
	
	/** Return product release date as string 
	 * @return A string containing product release date
	 * */
	public String getReleaseDateAsString() {
		return Common.convertDateToString(this.releaseDate);
	}
	
	/** Returns product release date
	 * @return A reference to a Date object containing converted date
	 * */
	public Date getReleaseDate() {
		return this.releaseDate;
	}
	
	/**
	 * Prints a message when adding a new product
	 */
	public void printAddMessage() {
		System.out.println("Product was added: "+this.id+" "+this.brand+" "+
				this.type+" "+this.color+" "+Common.convertDateToString(this.releaseDate));
	}
	
	public void printData() {
		System.out.println(this.id+" "+this.brand+" "+
				this.type+" "+this.color+" "+Common.convertDateToString(this.releaseDate));
	}
}
