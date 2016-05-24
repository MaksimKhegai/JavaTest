package com.database;

import java.util.*;
import com.exceptions.*;
import com.database.Common;

/** 
 * Main "database" class
 * @version 1.0 10 May 2016
 */
public class Base {
	private ArrayList<Client> clients;
	private ArrayList<Product> products;
	private ArrayList<Sale> sales;
	public Base() {
		this.clients = new ArrayList<Client>();
		this.products = new ArrayList<Product>();
		this.sales = new ArrayList<Sale>();
	}
	/** 
	 * Adds a new client 
	 * @param name Name of a client
	 * @param secondName Second name of a client
	 * @param lastName Last name of a client
	 * @param date Date of birth as a string in dd/mm/yyyy format
	 * @return A reference to a new client object or NULL if there was an error
	 * @throws WrongArgumentException if date is not in proper format
	 * */
	public Client addClient(String name, String secondName, String lastName, String date) throws WrongArgumentException {
		Client newClient = new Client(clients.size(), name, lastName, secondName, date);
		if (!Common.checkDate(date, true)) throw new WrongArgumentException("Wrong date format");
		if (clients.add(newClient))
			return newClient;
		else return null;
	}
	
	/** 
	 * Adds a new product
	 * @param brand Brand name
	 * @param type Product type
	 * @param color Product color
	 * @param date Release date as a string in dd/mm/yyyy format
	 * @return A reference to a new product object or NULL if there was an error
	 * @throws WrongArgumentException  if date is not in proper format
	 * */
	public Product addProduct(String brand, String type, String color, String date) throws WrongArgumentException {
		Product newProduct = new Product(products.size(), brand, type, color, date);
		if (!Common.checkDate(date, true)) throw new WrongArgumentException("Wrong date format");
		if (products.add(newProduct))
			return newProduct;
		else return null;
	}
	
	/** 
	 * Adds a new sale 
	 * @param date Date when a sale was made as a string in dd/mm/yyyy format
	 * @param product A reference to a product that was sold
	 * @param client A reference to a client that was served
	 * @return A reference to a new sale object or NULL if there was an error 
	 * @throws WrongArgumentsNumberException if arguments number is wrong
	 * @throws WrongArgumentException  if date is not in proper format
	 * */
	public Sale addSale(String date, Product product, Client client) throws WrongArgumentsNumberException, WrongArgumentException {
		if (!Common.checkDate(date, true)) throw new WrongArgumentException("Wrong date format");
		if (product == null || client == null) {
			throw new WrongArgumentsNumberException();
		}
		Sale newSale = new Sale(sales.size(), date, product, client);
		if (sales.add(newSale))
			return newSale;
		else return null;
	}
	
	/** 
	 * Sorts products by a value and returns a sorted array
	 * @param comparator A comparator to be used for sorting
	 * @return A new array with sorted values
	 */
	public ArrayList<Product> sortProducts(Comparator<Product> comparator) {
		ArrayList<Product> sortedProducts = new ArrayList<Product>(products);
		//sortedProducts.sort(comparator);
		Collections.sort(sortedProducts, comparator);
		return sortedProducts;
	}
	
	/** 
	 * Sorts clients by a value and returns a sorted array
	 * @param comparator A comparator to be used for sorting
	 * @return A new array with sorted values
	 */
	public ArrayList<Client> sortClients(Comparator<Client> comparator) {
		ArrayList<Client> sortedClients = new ArrayList<Client>(clients);
		//sortedClients.sort(comparator);
		Collections.sort(sortedClients, comparator);
		return sortedClients;
	}
	
	/** 
	 * Sorts sales by a value and returns a sorted array
	 * @param comparator A comparator to be used for sorting
	 * @return A new array with sorted values
	 */
	public ArrayList<Sale> sortSales(Comparator<Sale> comparator) {
		ArrayList<Sale> sortedSales = new ArrayList<Sale>(sales);
		//sortedSales.sort(comparator);
		Collections.sort(sortedSales, comparator);
		return sortedSales;
	}
	
	/** Returns all existing clients 
	 * @return An array containing all existing clients 
	 */
	public ArrayList<Client> getAllClients() {
		return this.clients;
	}
	
	/** 
	 * Returns all existing products 
	 * @return An array containing all existing clients 
	 */
	public ArrayList<Product> getAllProducts() {
		return this.products;
	}
	
	/** 
	 * Returns all existing sales 
	 * @return An array containing all existing clients 
	 */
	public ArrayList<Sale> getAllSales() {
		return this.sales;
	}
	
	/** 
	 * Finds a client by id 
	 * @param id An id to use for search
	 * @return Returns a reference to an object
	 * @throws ObjectNotFoundException if an object could not be found
	 */
	public Client findClientById(int id) throws ObjectNotFoundException {
		if (clients.size() == 0) throw new ObjectNotFoundException("There are no clients");
		for (Client cl : clients) {
			if (cl.getId() == id) return cl;
		}
		throw new ObjectNotFoundException("Client not found");
	}
	
	/** 
	 * Finds clients by full name 
	 * @param name First name to use for search
	 * @param secondName Second name to use for search
	 * @param lastName Last name to use for search
	 * @return Returns a reference to an object
	 * @throws ObjectNotFoundException if an object could not be found
	 * */
	public ArrayList<Client> findClientsByName(String name, String secondName, String lastName) throws ObjectNotFoundException {
		if (clients.size() == 0) throw new ObjectNotFoundException("There are no clients");
		ArrayList<Client> clientsList = new ArrayList<Client>();
		for (Client cl : clients) {
			String newName = name;
			String newSecondName = secondName;
			String newLastName = lastName;
			/* Check if there are empty fields */
			if (newName.equals("*")) newName = cl.getName();
			if (newSecondName.equals("*")) newSecondName = cl.getSecondName();
			if (newLastName.equals("*")) newLastName = cl.getLastName();
			if (cl.getName().toLowerCase().equals(newName.toLowerCase()) && 
					cl.getSecondName().toLowerCase().equals(newSecondName.toLowerCase()) &&
					cl.getLastName().toLowerCase().equals(newLastName.toLowerCase())) {
				clientsList.add(cl);
			}
		}
		if (clientsList.size() == 0) throw new ObjectNotFoundException("Clients not found"); 
		return clientsList;
	}
	
	/** 
	 * Finds clients by birth date 
	 * @param date Birth date to use for search as a string in dd/mm/yyyy format
	 * @return Returns a reference to an object
	 * @throws ObjectNotFoundException if an object could not be found
	 * @throws WrongArgumentException if date is not in proper format
	 * */
	public ArrayList<Client> findClientsByDate(String date) throws ObjectNotFoundException, WrongArgumentException {
		if (clients.size() == 0) throw new ObjectNotFoundException("There are no clients");
		if (!Common.checkDate(date, false)) throw new WrongArgumentException("Wrong date format");
		ArrayList<Client> clientsList = new ArrayList<Client>();
		for (Client cl : clients) {
			String[] splitDate = date.split("/", -1);
			String[] splitClDate = cl.getBirthDateAsString().split("/", -1);
			for (int i = 0;i < splitDate.length;i++) {
				if (splitDate[i].equals("*")) splitDate[i] = splitClDate[i];
			}
			if (splitDate[0].equals(splitClDate[0]) &&
					splitDate[1].equals(splitClDate[1]) &&
					splitDate[2].equals(splitClDate[2])) {
				clientsList.add(cl);
			}
		}
		if (clientsList.size() == 0) throw new ObjectNotFoundException("Clients not found"); 
		return clientsList;
	}
	
	/** 
	 * Finds a product by id 
	 * @param id An id to use for search
	 * @return Returns a reference to an object
	 * @throws ObjectNotFoundException if an object could not be found
	 */
	public Product findProductById(int id) throws ObjectNotFoundException {
		if (products.size() == 0) throw new ObjectNotFoundException("There are no products");
		for (Product pr : products) {
			if (pr.getId() == id) return pr;
		}
		throw new ObjectNotFoundException("Product not found");
	}
	
	/** 
	 * Finds a products by type 
	 * @param type Product type to use for search
	 * @return Returns a reference to an object
	 * @throws ObjectNotFoundException if an object could not be found
	 * */
	public ArrayList<Product> findProductsByType(String type) throws ObjectNotFoundException {
		if (products.size() == 0) throw new ObjectNotFoundException("There are no products");
		ArrayList<Product> productList = new ArrayList<Product>();
		for (Product pr : products) {
			if (pr.getType().toLowerCase().equals(type.toLowerCase())) productList.add(pr);
		}
		if (productList.size() == 0) throw new ObjectNotFoundException("Product not found"); 
		return productList;
	}
	
	/** 
	 * Finds products by brand 
	 * @param brand Brand name to use for search
	 * @return Returns a reference to an object
	 * @throws ObjectNotFoundException if an object could not be found
	 * */
	public ArrayList<Product> findProductsByBrand(String brand) throws ObjectNotFoundException {
		if (products.size() == 0) throw new ObjectNotFoundException("There are no products");
		ArrayList<Product> productList = new ArrayList<Product>();
		for (Product pr : products) {
			if (pr.getBrand().toLowerCase().equals(brand.toLowerCase())) productList.add(pr);
		}
		if (productList.size() == 0) throw new ObjectNotFoundException("Product not found"); 
		return productList;
	}
	
	/** 
	 * Finds products by release date 
	 * @param date Release date to use for search as a string in dd/mm/yyyy format
	 * @return Returns a reference to an object
	 * @throws ObjectNotFoundException if an object could not be found
	 * @throws WrongArgumentException if date is not in proper format
	 * */
	public ArrayList<Product> findProductsByDate(String date) throws ObjectNotFoundException, WrongArgumentException {
		if (products.size() == 0) throw new ObjectNotFoundException("There are no products");
		if (!Common.checkDate(date, false)) throw new WrongArgumentException("Wrong date format");
		ArrayList<Product> productList = new ArrayList<Product>();
		for (Product pr : products) {
			String[] splitDate = date.split("/", -1);
			String[] splitClDate = pr.getReleaseDateAsString().split("/", -1);
			for (int i = 0;i < splitDate.length;i++) {
				if (splitDate[i].equals("*")) splitDate[i] = splitClDate[i];
			}
			if (splitDate[0].equals(splitClDate[0]) &&
					splitDate[1].equals(splitClDate[1]) &&
					splitDate[2].equals(splitClDate[2])) {
				productList.add(pr);
			}
		}
		if (productList.size() == 0) throw new ObjectNotFoundException("Product not found"); 
		return productList;
	}
	
	/** Finds a sale by id 
	 * @param id An id to use for search
	 * @return Returns a reference to an object
	 * @throws ObjectNotFoundException if an object could not be found
	 * */
	public Sale findSaleById(int id) throws ObjectNotFoundException {
		if (sales.size() == 0) throw new ObjectNotFoundException("There are no sales");
		for (Sale sl : sales) {
			if (sl.getId() == id) return sl;
		}
		throw new ObjectNotFoundException("Sale not found"); 
	}
	
	/** Finds sales by date 
	 * @param date Sale date to use for search as a string in dd/mm/yyyy format
	 * @return Returns a reference to an object
	 * @throws ObjectNotFoundException if an object could not be found
	 * @throws WrongArgumentException if date is not in proper format
	 * */
	public ArrayList<Sale> findSalesByDate(String date) throws ObjectNotFoundException, WrongArgumentException {
		if (sales.size() == 0) throw new ObjectNotFoundException("There are no sales");
		if (!Common.checkDate(date, false)) throw new WrongArgumentException("Wrong date format");
		ArrayList<Sale> salesList = new ArrayList<Sale>();
		for (Sale sl : sales) {
			String[] splitDate = date.split("/", -1);
			String[] splitClDate = sl.getSellDateAsString().split("/", -1);
			for (int i = 0;i < splitDate.length;i++) {
				if (splitDate[i].equals("*")) splitDate[i] = splitClDate[i];
			}
			if (splitDate[0].equals(splitClDate[0]) &&
					splitDate[1].equals(splitClDate[1]) &&
					splitDate[2].equals(splitClDate[2])) {
				salesList.add(sl);
			}
		}
		if (salesList.size() == 0) throw new ObjectNotFoundException("Sales not found"); 
		return salesList;
	}
}
