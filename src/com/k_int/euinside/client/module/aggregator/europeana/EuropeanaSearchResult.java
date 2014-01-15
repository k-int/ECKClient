package com.k_int.euinside.client.module.aggregator.europeana;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EuropeanaSearchResult extends EuropeanaResult {
	private static Log log = LogFactory.getLog(EuropeanaSearchResult.class);
	private List<EuropeanaSearchItem> items;

	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retrieves the items found in the search
	 * 
	 * @return The search result items
	 */
	public List<EuropeanaSearchItem> getItems() {
		return(items);
	}

	/**
	 * Sets the search result items
	 * 
	 * @param items The items
	 */
	public void setItems(List<EuropeanaSearchItem> items) {
		this.items = items;
	}

	/**
	 * Converts this class to the generic search result class
	 * 
	 * @return The generic search result class
	 */
	public EuropeanaSearchResult convertToGeneric() {
		// for the time being we will treat the europeana class as the generic search result class
		return(this);
	}
}
