package com.comparators.Client;

import java.util.*;

import com.database.*;

/** Comparator class for comparing clients by last name */
public class CompareClientsByLastName implements Comparator<Client> {
	public int compare(Client client1, Client client2) {
		return client1.getLastName().compareTo(client2.getLastName());
	}
}
