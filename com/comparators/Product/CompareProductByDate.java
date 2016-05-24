package com.comparators.Product;

import java.util.*;

import com.database.*;

/** Comparator class for comparing products by date */
public class CompareProductByDate implements Comparator<Product> {
	public int compare(Product prod1, Product prod2) {
		Date date1 = prod1.getReleaseDate();
		Date date2 = prod2.getReleaseDate();
		return date1.compareTo(date2);
	}
}