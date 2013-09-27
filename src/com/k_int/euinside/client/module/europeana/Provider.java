package com.k_int.euinside.client.module.europeana;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Provider extends EuropeanaItemResult {
	private static Log log = LogFactory.getLog(Provider.class);

	private String country;

	public Provider() {
	}

	@Override
	protected Log getLogger() {
		return(log);
	}
	
	public String getCountry() {
		return(country);
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: ProviderDetail\n";
		result += super.toString();
		result += "Country: " + country + "\n"; 
		return(result);
	}
}
