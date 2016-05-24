package com.comparators.Sale;

import java.util.*;

import com.database.*;

/** Comparator class for comparing sales by client id */
public class CompareSalesByClientId implements Comparator<Sale> {
	public int compare(Sale sale1, Sale sale2) {
		if (sale1.getClient().getId() == sale2.getClient().getId()) return 0;
		return sale1.getClient().getId() < sale2.getClient().getId() ? -1 : 1;
	}
}
