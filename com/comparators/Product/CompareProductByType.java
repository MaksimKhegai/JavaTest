package com.comparators.Product;

import java.util.*;

import com.database.*;

/** Comparator class for comparing products by type */
public class CompareProductByType implements Comparator<Product> {
	public int compare(Product prod1, Product prod2) {
		return prod1.getType().compareTo(prod2.getType());
	}
}