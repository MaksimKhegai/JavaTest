package com.database;

import java.util.Date;

/**
 * Class for keeping sells data
 * @version 1.0 10 May 2016
 */
public class Sale extends BaseObject {
	private Date sellDate;
	private Product product;
	private Client client;
	private int id;
	Sale(int id, String date, Product product, Client client) {
		super();
		this.sellDate = Common.convertStringToDate(date);
		this.product = product;
		this.client = client;
		this.id = id;
	}
	
	/** Returns sale date as string
	 * @return A string containing a sell date
	 */
	public String getSellDateAsString() {
		return Common.convertDateToString(this.sellDate);
	}
	
	/** Returns sale id
	 * @return A string containing a sell id
	 */
	public int getId() {
		return this.id;
	}
	
	/** Returns sale date
	 * @return A string containing a sell date as a Data object
	 */
	public Date getSellDate() {
		return this.sellDate;
	}
	
	/** Returns sold product
	 * @return A reference to a Product object
	 */
	public Product getProduct() {
		return this.product;
	}
	
	/** Returns served client
	 * @return A reference to a Client object
	 */
	public Client getClient() {
		return this.client;
	}
	
	/**
	 * Prints a message when adding a new product
	 */
	public void printAddMessage() {
		System.out.println("Product was added: "+this.id+" "+this.product.getId()+" "+this.client.getId());
	}
	
	public void printData() {
		System.out.println(this.id+" "+this.product.getId()+" "+this.client.getId());
	}
}
