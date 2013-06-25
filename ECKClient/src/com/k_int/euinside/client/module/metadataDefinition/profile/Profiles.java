package com.k_int.euinside.client.module.metadataDefinition.profile;

import java.util.ArrayList;

/**
 * The profiles class is an array of Profiles that are returned by the Metadata Definition module 
 */
public class Profiles extends ArrayList<Profile> {
	private static final long serialVersionUID = 1234504L;
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Profiles:\n"; 
		for (Profile profile : this) {
			result += profile.toString();
		}
		return(result);
	}
}
