package com.k_int.euinside.client.module.metadataDefinition.error;

/**
 * The Error class holds the error definition returned from the Metadata Definition module
 */
public class Error {

	String error;
	String definition;
	
	public Error() {
	}

	/**
	 * Retrieves the error code for this error
	 * 
	 * @return The error code
	 */
	public String getError() {
		return(error);
	}

	/**
	 * Sets the error code for this error
	 * 
	 * @param error The error code
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Retrieves the definition for this error
	 *  
	 * @return The definition
	 */
	public String getDefinition() {
		return(definition);
	}

	/**
	 * Sets the definition for this error
	 * 
	 * @param definition The definition for this error
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Error:\n"; 
		result += "\terror: " + error + "\n";
		result += "\tdefinition: " + definition + "\n";
		return(result);
	}
}
