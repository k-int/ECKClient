package com.k_int.euinside.client.module.metadataDefinition.error;

import java.util.ArrayList;

/**
 * Class to hold an array of Error definitions returned from the Metadata definitions module
 */
public class Errors extends ArrayList<Error> {
	private static final long serialVersionUID = 1234503L;
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Errors:\n"; 
		for (Error error : this) {
			result += error.toString();
		}
		return(result);
	}
}
