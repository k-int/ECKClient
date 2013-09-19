package com.k_int.euinside.client.module.europeana;

public class ProviderBriefDetail {

	private String identifier;
	private String name;

	public ProviderBriefDetail() {
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
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: ProviderBriefDetails\n";
		result += "Identifier: " + identifier + "\n"; 
		result += "Name: " + name + "\n"; 
		return(result);
	}
}
