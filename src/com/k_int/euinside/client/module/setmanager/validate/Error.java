package com.k_int.euinside.client.module.setmanager.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.json.baseJSON;

/**
 * The Error class represents an error that has occured
 *
 */
public class Error extends baseJSON {
	private static Log log = LogFactory.getLog(Error.class);

	String errorCode;
	String additionalInformation;
	
	public Error() {
	}

	@Override
	protected Log getLogger() {
		return(log);
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
}
