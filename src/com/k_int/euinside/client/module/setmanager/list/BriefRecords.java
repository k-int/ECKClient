package com.k_int.euinside.client.module.setmanager.list;

import java.util.ArrayList;

/*
 * BriefRecords is an array of BriefRecords that is used by the module SetManager with the lit action
 */
public class BriefRecords extends ArrayList<BriefRecord>{

	private static final long serialVersionUID = 1234502L;

	public BriefRecords() {
	}

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: BriefRecords\n";
		for (BriefRecord briefRecord : this) {
			result += briefRecord.toString();
		}
		return(result);
	}
}
