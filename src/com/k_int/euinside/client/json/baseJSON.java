package com.k_int.euinside.client.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import com.k_int.euinside.client.ClassUtils;
import com.k_int.euinside.client.ClassUtilsToString;

import org.apache.commons.logging.Log;

/**
 * Abstract base class for JSON deserialisation 
 */
public abstract class baseJSON extends ClassUtilsToString {

	/**
	 * Retrieves the logger for the class being deserialised so that the logging information 
	 * 
	 * @return The logger
	 */
	abstract protected Log getLogger();

	/**
	 * Captures any fields that do not have an appropriate setter and reports it to the log file
	 * This will stop the exception being thrown when it cannot find a setter
	 * 
	 * @param fieldName The fieldname it cannot find a setter for
	 * @param value The value for this field
	 */
	@JsonAnySetter
	public void setUnknownField(String fieldName, Object value) {
		getLogger().info("JSON, field ignored: \"" + fieldName + "\", value: \"" + value.toString() + "\"");
	}

	@Override
	public String toString(int indent) {
		return(ClassUtils.toString(this, baseJSON.class, indent));
	}
	
	@Override
	public String toString() {
		return(toString(0));
	}
}
