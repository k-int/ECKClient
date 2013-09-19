package com.k_int.euinside.client.module.europeana;

import java.util.ArrayList;

public class ProviderDataSets extends ArrayList<DataSetBriefDetail> {

	private static final long serialVersionUID = 1940287540793031423L;

	public ProviderDataSets() {
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: ProviderDataSets\n";
		for (DataSetBriefDetail dataSetBriefDetails : this) {
			result += dataSetBriefDetails.toString();
		}
		return(result);
	}
}
