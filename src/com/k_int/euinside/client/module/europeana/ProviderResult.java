package com.k_int.euinside.client.module.europeana;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProviderResult extends EuropeanaResult {
	private static Log log = LogFactory.getLog(ProviderResult.class);

	private ArrayList<Provider> items;

	public ProviderResult() {
	}
	
	@Override
	protected Log getLogger() {
		return(log);
	}
	
	public ArrayList<Provider> getItems() {
		return(items);
	}

	public void setItems(ArrayList<Provider> items) {
		this.items = items;
	}

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Providers\n";
		result += super.toString();

		if (items != null) {
			for (Provider detail : items) {
				result += detail.toString();
			}
		}
		return(result);
	}
}
