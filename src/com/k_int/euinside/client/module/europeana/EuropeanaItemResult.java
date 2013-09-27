package com.k_int.euinside.client.module.europeana;

import com.k_int.euinside.client.json.baseJSON;

public abstract class EuropeanaItemResult extends baseJSON {

	private boolean success;
	private String identifier;
	private String name;
	private String description;
	
	public boolean isSuccess() {
		return(success);
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
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
	
	public String getDescription() {
		return(description);
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: EuropeanaResult\n";
		result += "Success: " + success + "\n";
		result += "Identifier: " + identifier + "\n";
		result += "Name: " + name + "\n";
		result += "Description: " + description + "\n";
		return(result);
	}
}
