package com.k_int.euinside.client.module.validation.semantika;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents the errors being returned from the semantika validation service
 */
public class Errors {

	@JsonProperty("BrokenRules")
	ArrayList<Error> brokenRules;
	@JsonProperty("IsValid")
	boolean isValid;
	@JsonProperty("Message")
	String message;
	
	public Errors() {
	}

	/**
	 * Retrieves the validation errors (if any) that occurred
	 * 
	 * @return The validation errors
	 */
	public ArrayList<Error> getBrokenRules() {
		return(brokenRules);
	}

	/**
	 * Sets the validation errors (if any) that occurred
	 * 
	 * @param brokenRules The validation errors
	 */
	public void setBrokenRules(ArrayList<Error> brokenRules) {
		this.brokenRules = brokenRules;
	}

	/**
	 * Was validation successful or not
	 * 
	 * @return true if validation was successful
	 */
	public boolean isValid() {
		return(isValid);
	}

	/**
	 * Set whether the validation was successful or not
	 * 
	 * @param isValid true if validation was successful
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * Retrieves a generic validation message
	 * 
	 * @return The generic validation message
	 */
	public String getMessage() {
		return(message);
	}

	/**
	 * Sets the generic validation message
	 * 
	 * @param message The generic validation message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Errors:\n"; 
		result += "\tisValid: " + isValid + "\n";
		result += "\tmessage: " + message + "\n";
		for (Error error : brokenRules) {
			result += error.toString();
		}
		return(result);
	}
}
