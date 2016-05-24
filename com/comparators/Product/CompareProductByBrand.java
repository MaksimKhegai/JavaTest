package com.comparators.Product;

import java.util.*;

import com.database.*;

/** Comparator class for comparing products by brand */
public class CompareProductByBrand implements Comparator<Product> {
	public int compare(Product prod1, Product prod2) {
		return prod1.getBrand().compareTo(prod2.getBrand());
	}
}