package com.comparators.Sale;

import java.util.*;

import com.database.*;

/** Comparator class for comparing sales by product id */
public class CompareSalesByProductId implements Comparator<Sale> {
	public int compare(Sale sale1, Sale sale2) {
		if (sale1.getProduct().getId() == sale2.getProduct().getId()) return 0;
		return sale1.getProduct().getId() < sale2.getProduct().getId() ? -1 : 1;
	}
}
