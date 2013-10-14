package com.k_int.euinside.client.module.aggregator.europeana;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class contains Europeana provider details
 */
public class EuropeanaProvider extends EuropeanaItemResult {
	private static Log log = LogFactory.getLog(EuropeanaProvider.class);

	private String country;

	public EuropeanaProvider() {
	}

	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retrieves the country this provider is associated with
	 * 
	 * @return The contry associated with this provider
	 */
	public String getCountry() {
		return(country);
	}

	/**
	 * Sets the country associated with this provider
	 * 
	 * @param country The country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
}
