package com.k_int.euinside.client.module.setmanager.validate;

/**
 * The Error class represents an error that has occured
 *
 */
public class Error {

	String errorCode;
	String additionalInformation;
	
	public Error() {
	}

	/**
	 * Retrieve the error code associated with this error
     *
	 * @return The error code
	 */
	public String getErrorCode() {
		return(errorCode);
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Retrieve any additional information associated with the error
	 * 
	 * @return The additional information
	 */
	public String getAdditionalInformation() {
		return(additionalInformation);
	}
	
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Error:\n"; 
		result += "\tError Code: " + errorCode + "\n";
		result += "\tAdditional Information: " + additionalInformation + "\n";
		return(result);
	}
}
