package com.k_int.euinside.client.module.europeana;

public class DataSetBriefDetail {

	private String identifier;
	private String provider;
	private String fullIdentifier;

	public DataSetBriefDetail() {
	}
	
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
	 * Retrieves the provider
	 * 
	 * @return The provider
	 */
	public String getProvider() {
		return(provider);
	}

	/**
	 * Sets the provider
	 * 
	 * @param provider The provider
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * Retrievs the full identifier
	 * 
	 * @return The full identifier
	 */
	public String getFullIdentifier() {
		return(fullIdentifier);
	}

	/**
	 * Sets the full identifier
	 * 
	 * @param fullIdentifier The full identifier
	 */
	public void setFullIdentifier(String fullIdentifier) {
		this.fullIdentifier = fullIdentifier;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: DataSetBriefDetails\n";
		result += "Identifier: " + identifier + "\n"; 
		result += "Provider: " + provider + "\n"; 
		result += "Full Identifier: " + fullIdentifier + "\n"; 
		return(result);
	}
}
