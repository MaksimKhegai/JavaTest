package com.comparators.Client;

import java.util.*;

import com.database.*;

/** Comparator class for comparing clients by date */
public class CompareClientsByDate implements Comparator<Client> {
	public int compare(Client client1, Client client2) {
		return client1.getBirthDate().compareTo(client2.getBirthDate());
	}
}
