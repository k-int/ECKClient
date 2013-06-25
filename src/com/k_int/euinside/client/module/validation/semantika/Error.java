package com.k_int.euinside.client.module.validation.semantika;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Error class contains a validation error returned from the semantika validation service
 */
public class Error {
	@JsonProperty("BrokenRuleCode")
	String brokenRuleCode;
	@JsonProperty("Message")
	String message;
	@JsonProperty("Severity")
	int severity;

	public Error() {
	}

	/**
	 * Retrieves the error code for this error
	 * 
	 * @return The error code
	 */
	public String getBrokenRuleCode() {
		return(brokenRuleCode);
	}

	/**
	 * Sets the error code
	 * 
	 * @param brokenRuleCode The error code
	 */
	public void setBrokenRuleCode(String brokenRuleCode) {
		this.brokenRuleCode = brokenRuleCode;
	}

	/**
	 * Retrieves an informative message that was returned with this error code
	 * 
	 * @return The informative message
	 */
	public String getMessage() {
		return(message);
	}

	/**
	 * Sets the informative message for this validation error
	 * 
	 * @param message The informative message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Retrieve the severity of this error
	 * 
	 * @return The severity of this error
	 */
	public int getSeverity() {
		return(severity);
	}

	/**
	 * Sets the severity of this error
	 * 
	 * @param severity The severity of this error
	 */
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Error:\n"; 
		result += "\tbrokenRuleCode: " + brokenRuleCode + "\n";
		result += "\tmessage: " + message + "\n";
		result += "\tseverity: " + severity + "\n";
		return(result);
	}
}
