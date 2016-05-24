package com.comparators.Sale;

import java.util.*;

import com.database.*;

/** Comparator class for comparing sales by date */
public class CompareSalesByDate implements Comparator<Sale> {
	public int compare(Sale sale1, Sale sale2) {
		return sale1.getSellDate().compareTo(sale2.getSellDate());
	}
}
