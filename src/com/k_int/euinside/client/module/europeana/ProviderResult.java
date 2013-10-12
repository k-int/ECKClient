package com.k_int.euinside.client.module.europeana;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class contains the provider result details
 */
public class ProviderResult extends EuropeanaResult {
	private static Log log = LogFactory.getLog(ProviderResult.class);

	private ArrayList<Provider> items;

	public ProviderResult() {
	}
	
	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retrieves the list of providers that have been returned with this result
	 * 
	 * @return The list of providers
	 */
	public ArrayList<Provider> getItems() {
		return(items);
	}

	/**
	 * Sets the list of providers that will be returned with this result
	 * 
	 * @param items The list of providers
	 */
	public void setItems(ArrayList<Provider> items) {
		this.items = items;
	}
}
