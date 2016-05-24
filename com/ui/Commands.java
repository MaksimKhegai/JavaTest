package com.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import com.comparators.Client.*;
import com.comparators.Product.*;
import com.comparators.Sale.*;
import com.database.*;
import com.exceptions.*;

/**
 * Class that holds and processes all available commands for a console input
 * @version 1.0 10 May 2016
 */
public class Commands {
	private Base base;
	private HashMap<String, String> help = new HashMap<String, String>();
	private HashMap<String, Comparator> comparators = new HashMap<String, Comparator>();
	
	public Commands(Base base) {
		this.base = base;
		
		/* Adding help strings to a map */
		this.help.put("?", "Commands list:\n"
				+ " add\n"
				+ " show\n"
				+ " sort\n"
				+ " find\n");
		
		this.help.put("add", "List of parameters:\n"
				+ " add [client] name second_name last_name birth_date\n"
				+ " add [product] brand type color release_date\n"
				+ " add [sale] date client_id product_id\n");
		
		this.help.put("find", "List of parameters:\n"
				+ " find [client] search_type\n"
				+ "  Available types: id, name, date\n"
				+ " find [product] search_type\n"
				+ "  Available types: id, brand, type, date\n"
				+ " find [sale] search_type\n"
				+ "  Available types: id, date\n");
		
		this.help.put("show", "List of parameters:\n"
				+ " show [clients]\n"
				+ " show [products]\n"
				+ " show [sales]\n");
		
		this.help.put("sort", "List of parameters:\n"
				+ " sort [clients] sorting_type\n"
				+ "  Available types: first, second, last, date\n"
				+ " sort [products] sorting_type\n"
				+ "  Available types: brand, color, type, date\n"
				+ " sort [sales] sorting_type\n"
				+ "  Available types: date\n");
		
		this.comparators.put("clientfirst", new CompareClientsByName());
		this.comparators.put("clientsecond", new CompareClientsBySecondName());
		this.comparators.put("clientlast", new CompareClientsByLastName());
		this.comparators.put("clientdate", new CompareClientsByDate());
		
		this.comparators.put("productbrand", new CompareProductByBrand());
		this.comparators.put("productcolor", new CompareProductByColor());
		this.comparators.put("producttype", new CompareProductByType());
		this.comparators.put("productdate", new CompareProductByDate());
		
		this.comparators.put("saledate", new CompareSalesByDate());
		this.comparators.put("saleclient", new CompareSalesByClientId());
		this.comparators.put("saleproduct", new CompareSalesByProductId());
	}
	
	/** 
	 * Shows help 
	 * @param parameters Array of parameters
	 * @return void
	 * @throws ObjectNotFoundException if an object could not be found
	 * */
	public Common.errorCode showHelp(String[] parameters) throws ObjectNotFoundException {
		String helpString;
		try {
			helpString = help.get(parameters[parameters.length-2]);
			if (helpString == null) throw new ObjectNotFoundException("Help file was not found", Common.errorCode.OBJNOTFOUND);
		} catch (IndexOutOfBoundsException e) {
			helpString = help.get(parameters[0]);
		}
		System.out.println(helpString);
		return Common.errorCode.NOERROR;
	}
	
	/** 
	 * Adds client, product or sale to the base
	 * @param parameters Array of parameters
	 * @return Exit code 0 if there was no errors
	 * @throws WrongArgumentsNumberException if arguments number is wrong
	 * @throws ObjectNotFoundException if an object could not be found
	 * @throws WrongArgumentException if date is not in proper format
	 */
	public Common.errorCode add(String[] parameters) throws WrongArgumentsNumberException, 
															ObjectNotFoundException, WrongArgumentException {
		try {
			BaseObject newObject = null;

			if (parameters[1].equals("?")) {
				showHelp(parameters);
				return Common.errorCode.NOERROR;
			} else if (parameters[1].equals("client")) {
				newObject = base.addClient(parameters[2], parameters[3], parameters[4], parameters[5]);
			} else if (parameters[1].equals("product")) {
				newObject = base.addProduct(parameters[2], parameters[3], parameters[4], parameters[5]);
			} else if (parameters[1].equals("sale")) {
				Product newProduct = null;
				Client newClient = null;
				
				newProduct = base.findProductById(Integer.parseInt(parameters[3]));
				newClient = base.findClientById(Integer.parseInt(parameters[4]));
				newObject = base.addSale(parameters[2], newProduct, newClient);
			}
			if (newObject != null) {
				newObject.printAddMessage();
				return Common.errorCode.NOERROR;
			}
		} catch (IndexOutOfBoundsException e) {
			throw new WrongArgumentsNumberException("Wrong number of arguments", e, Common.errorCode.WRONGARGNUM);
		} catch (ObjectNotFoundException e) {
			throw e;
		} catch (WrongArgumentsNumberException e) {
			throw e;
		} catch (WrongArgumentException e) {
			throw e;
		}
		return Common.errorCode.COMMANDNOTFOUND;
	}
	
	/** 
	 * Finds clients, products or sales in the base and writes them on screen
	 * @param parameters Array of parameters
	 * @return Exit code 0 if there was no errors
	 * @throws WrongArgumentsNumberException if arguments number is wrong
	 * @throws ObjectNotFoundException if an object could not be found
	 * @throws WrongArgumentException if date is not in proper format
	 */
	public Common.errorCode find(String[] parameters) throws WrongArgumentsNumberException, 
															ObjectNotFoundException, WrongArgumentException {
		try {
			if (parameters[1].equals("?")) {
				showHelp(parameters);
				return Common.errorCode.NOERROR;
			} else if (parameters[1].equals("client")) {
				if (parameters[2].equals("id")) {
					Client client = base.findClientById(Integer.parseInt(parameters[3]));
					client.printData();
				} else {
					/* Find client if a lot of them fits */
					ArrayList<Client> clients = null;
					if (parameters[2].equals("name")) {
						clients = base.findClientsByName(parameters[3], parameters[4], parameters[5]);
					} else if (parameters[2].equals("date")) {
						clients = base.findClientsByDate(parameters[3]);
					}
					for (Client client : clients) {
						client.printData();
					}
				}
				return Common.errorCode.NOERROR;
			} else if (parameters[1].equals("product")) {
				if (parameters[2].equals("id")) {
					Product product = base.findProductById(Integer.parseInt(parameters[3]));
					product.printData();
				} else {
					ArrayList<Product> products = null;
					if (parameters[2].equals("brand"))
						products = base.findProductsByBrand(parameters[3]);
					else if (parameters[2].equals("type"))
						products = base.findProductsByType(parameters[3]);
					else if (parameters[2].equals("date"))
						products = base.findProductsByDate(parameters[3]);
					for (Product product : products) {
						product.printData();
					}
				}
				return Common.errorCode.NOERROR;
			} else if (parameters[1].equals("sale")) {
				if (parameters[2].equals("id")) {
					Sale sale = base.findSaleById(Integer.parseInt(parameters[3]));
					sale.printData();
				} else if (parameters[2].equals("date")) {
					ArrayList<Sale> sales = base.findSalesByDate(parameters[3]);
					for (Sale sale : sales) {
						sale.printData();
					}
				}
				return Common.errorCode.NOERROR;
			}
		} catch (IndexOutOfBoundsException e) {
			throw new WrongArgumentsNumberException("Wrong number of arguments", e, 
													Common.errorCode.WRONGARGNUM);
		} catch (ObjectNotFoundException e) {
			throw e;
		} catch (WrongArgumentException e) {
			throw e;
		}
		return Common.errorCode.COMMANDNOTFOUND;
	}
	
	/** 
	 * Writes all clients, products or sales on screen
	 * @param parameters Array of parameters
	 * @return Exit code 0 if there was no errors
	 * @throws WrongArgumentsNumberException if arguments number is wrong
	 * @throws ObjectNotFoundException if an object could not be found 
	 */
	public Common.errorCode show(String[] parameters) throws WrongArgumentsNumberException, 
																ObjectNotFoundException {
		try {
			if (parameters[1].equals("?")) {
				showHelp(parameters);
				return Common.errorCode.NOERROR;
			} else if (parameters[1].equals("clients")) {
				for (Client client : base.getAllClients()) {
					client.printData();
				}
				return Common.errorCode.NOERROR;
			} else if (parameters[1].equals("products")) {
				for (Product product : base.getAllProducts()) {
					product.printData();
				}
				return Common.errorCode.NOERROR;
			} else if (parameters[1].equals("sales")) {
				for (Sale sale : base.getAllSales()) {
					sale.printData();
				}
				return Common.errorCode.NOERROR;
			}
		} catch (IndexOutOfBoundsException e) {
			throw new WrongArgumentsNumberException("Wrong number of arguments", e, Common.errorCode.WRONGARGNUM);
		} catch (ObjectNotFoundException e) {
			throw e;
		}
		return Common.errorCode.COMMANDNOTFOUND;
	}
	
	/** 
	 * Sorts clients, products or sales in the base and writes them on screen
	 * @param parameters Array of parameters
	 * @return Exit code 0 if there was no errors
	 * @throws WrongArgumentsNumberException if arguments number is wrong
	 * @throws InvalidSearchTypeException if a search type could not be chosen
	 * @throws ObjectNotFoundException if an object could not be found
	 */
	public Common.errorCode sort(String[] parameters) throws WrongArgumentsNumberException, 
															InvalidSearchTypeException, ObjectNotFoundException {
		try {
			if (parameters[1].equals("?")) {
				showHelp(parameters);
				return Common.errorCode.NOERROR;
			} else if (parameters[1].equals("clients")) {
				Comparator<Client> comparator = ChooseClientComparator(parameters);
				ArrayList<Client> newClients = base.sortClients(comparator);
				for (Client client : newClients) {
					client.printData();
				}
				return Common.errorCode.NOERROR;
			} else if (parameters[1].equals("products")) {
				Comparator<Product> comparator = ChooseProductComparator(parameters);
				ArrayList<Product> newProducts = base.sortProducts(comparator);
				for (Product product : newProducts) {
					product.printData();
				}
				return Common.errorCode.NOERROR;
			} else if (parameters[1].equals("sales")) {
				Comparator<Sale> comparator = ChooseSaleComparator(parameters);
				ArrayList<Sale> newSales = base.sortSales(comparator);
				for (Sale sale : newSales) {
					sale.printData();
				}
				return Common.errorCode.NOERROR;
			}
		} catch (IndexOutOfBoundsException e) {
			throw new WrongArgumentsNumberException("Wrong number of arguments", e, 
													Common.errorCode.WRONGARGNUM);
		} catch (InvalidSearchTypeException e) {
			throw e;
		} catch (ObjectNotFoundException e) {
			throw e;
		}
		return Common.errorCode.COMMANDNOTFOUND;
	}
	
	/** 
	 * Chooses a proper comparator to search for matching clients
	 * @param parameters Array of parameters
	 * @return A reference to a Comparator object or NULL if there was an error
	 * @throws InvalidSearchTypeException if a search type could not be chosen
	 */
	public Comparator<Client> ChooseClientComparator(String[] parameters) throws InvalidSearchTypeException {
		Comparator<Client> comparator = comparators.get("client"+parameters[2]);
		if (comparator == null) {
			throw new InvalidSearchTypeException("Invalid search type", Common.errorCode.INVALIDSEARCHTYPE);
		}
		return comparator;
	}
	
	/** 
	 * Chooses a proper comparator to search for matching products
	 * @param parameters Array of parameters
	 * @return A reference to a Comparator object or NULL if there was an error
	 * @throws InvalidSearchTypeException if a search type could not be chosen
	 */
	public Comparator<Product> ChooseProductComparator(String[] parameters) throws InvalidSearchTypeException {
		Comparator<Product> comparator = comparators.get("product"+parameters[2]);
		if (comparator == null) {
			throw new InvalidSearchTypeException("Invalid search type", Common.errorCode.INVALIDSEARCHTYPE);
		}
		return comparator;
	}
	
	/** 
	 * Chooses a proper comparator to search for matching sales
	 * @param parameters Array of parameters
	 * @return A reference to a Comparator object or NULL if there was an error
	 * @throws InvalidSearchTypeException if a search type could not be chosen
	 */
	public Comparator<Sale> ChooseSaleComparator(String[] parameters) throws InvalidSearchTypeException {
		Comparator<Sale> comparator = comparators.get("sale"+parameters[2]);
		if (comparator == null) {
			throw new InvalidSearchTypeException("Invalid search type", Common.errorCode.INVALIDSEARCHTYPE);
		}
		return comparator;
	}
}
