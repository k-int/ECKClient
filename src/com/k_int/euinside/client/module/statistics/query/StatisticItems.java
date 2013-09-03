package com.k_int.euinside.client.module.statistics.query;

import java.util.ArrayList;

public class StatisticItems extends ArrayList<StatisticItem> {
	private static final long serialVersionUID = 1234501L;

	public StatisticItems() {
	}

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: StatisticItems\n";
		for (StatisticItem statisticItem : this) {
			result += statisticItem.toString();
		}
		return(result);
	}
}
