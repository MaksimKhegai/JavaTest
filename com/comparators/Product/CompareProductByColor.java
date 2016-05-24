package com.comparators.Product;

import java.util.*;

import com.database.*;

/** Comparator class for comparing products by color */
public class CompareProductByColor implements Comparator<Product> {
	public int compare(Product prod1, Product prod2) {
		return prod1.getColor().compareTo(prod2.getColor());
	}
}