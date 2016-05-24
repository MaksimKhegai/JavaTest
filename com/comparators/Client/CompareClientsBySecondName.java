package com.comparators.Client;

import java.util.*;

import com.database.*;

/** Comparator class for comparing clients by second name */
public class CompareClientsBySecondName implements Comparator<Client> {
	public int compare(Client client1, Client client2) {
		return client1.getSecondName().compareTo(client2.getSecondName());
	}
}
