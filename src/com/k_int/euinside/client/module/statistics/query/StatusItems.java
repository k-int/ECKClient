package com.k_int.euinside.client.module.statistics.query;

import java.util.ArrayList;

public class StatusItems extends ArrayList<StatusItem> {
	private static final long serialVersionUID = 1234500L;

	public StatusItems() {
	}

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: StatisticItems\n";
		for (StatusItem statusItem : this) {
			result += statusItem.toString();
		}
		return(result);
	}

}
