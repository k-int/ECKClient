package com.k_int.euinside.client.module.metadataDefinition.language;

import java.util.ArrayList;

/**
 * The Languages class is an array of Language objects returned by the Metadata Definition module
 * @author chas
 *
 */
public class Languages extends ArrayList<Language> {
	private static final long serialVersionUID = 1234504L;

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Languages:\n"; 
		for (Language language : this) {
			result += language.toString();
		}
		return(result);
	}
}
