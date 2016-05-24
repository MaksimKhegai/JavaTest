package com.comparators.Client;

import java.util.*;

import com.database.*;

/** Comparator class for comparing clients by first name */
public class CompareClientsByName implements Comparator<Client> {
	public int compare(Client client1, Client client2) {
		return client1.getName().compareTo(client2.getName());
	}
}
