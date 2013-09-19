package com.k_int.euinside.client.module.europeana;

import java.util.ArrayList;

public class Providers extends ArrayList<ProviderBriefDetail> {

	private static final long serialVersionUID = 6734301228232275596L;

	public Providers() {
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Providers\n";
		for (ProviderBriefDetail briefDetails : this) {
			result += briefDetails.toString();
		}
		return(result);
	}
}
