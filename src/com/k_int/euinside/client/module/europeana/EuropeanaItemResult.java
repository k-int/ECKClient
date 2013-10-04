package com.k_int.euinside.client.module.europeana;

import com.k_int.euinside.client.json.baseJSON;

/**
 * base class for a europeana item that all records seem to contain
 *
 */
public abstract class EuropeanaItemResult extends baseJSON {

	private boolean success;
	private String identifier;
	private String name;
	private String description;

	/**
	 * Was it successful or not, I dont't know what success means in this scenario
	 * 
	 * @return true if successful
	 */
	public boolean isSuccess() {
		return(success);
	}

	/**
	 * Sets whether it was successful or not
	 * 
	 * @param success true if successful
	 */
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

	/**
	 * Retrieves the description
	 * 
	 * @return The description
	 */
	public String getDescription() {
		return(description);
	}

	/**
	 * Sets the description
	 * 
	 * @param description The description
	 */
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
