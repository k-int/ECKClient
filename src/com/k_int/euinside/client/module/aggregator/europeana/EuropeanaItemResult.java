package com.k_int.euinside.client.module.aggregator.europeana;

import com.k_int.euinside.client.json.baseJSON;

/**
 * base class for a europeana item that all records seem to contain
 *
 */
public abstract class EuropeanaItemResult extends baseJSON {

	private String identifier;
	private String name;

	/**
	 * Retrieves the identifier
	 * 
	 * @return The identifier
	 */
	public String getIdentifier() {
		return(identifier);
	}
	
	/**
	 * Sets the identifier
	 * 
	 * @param identifier The identifier
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * Retrieves the name
	 * 
	 * @return The name
	 */
	public String getName() {
		return(name);
	}

	/**
	 * Sets the name
	 * @param name The name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
