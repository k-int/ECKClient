package com.k_int.euinside.client.module.metadataDefinition.error;

import com.k_int.euinside.client.json.baseJSON;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Error class holds the error definition returned from the Metadata Definition module
 */
public class Error extends baseJSON {
	private static Log log = LogFactory.getLog(Error.class);

	String error;
	String definition;
	
	public Error() {
	}

	@Override
	protected Log getLogger() {
		return(log);
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
