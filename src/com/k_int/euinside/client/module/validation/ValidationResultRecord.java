package com.k_int.euinside.client.module.validation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * This class contains the result for an individual record
 */
@JacksonXmlRootElement(localName="record")
public class ValidationResultRecord {
	private static final String RESULT_SUCCESS = "success";  

	@JacksonXmlProperty(isAttribute=true)  
	private String id;
	
	@JacksonXmlProperty(isAttribute=true)  
	private boolean result = false;
	
	@JacksonXmlElementWrapper(useWrapping=false)
	@JacksonXmlProperty(isAttribute=false, localName="error")
	private List<ValidationResultRecordError> errors;
	
	public ValidationResultRecord() {
	}

	/**
	 * Returns the id of the record that his result is for
	 * 
	 * @return The identifier for this result
	 */
	public String getId() {
		return(id);
	}

	/**
	 * Sets the identifier for this result
	 * 
	 * @param id The identifier
	 */
	public void setId(String id) {
		this.id = id;
	}

	@JsonSetter("Id")
	public void setIdSemantika(String id) {
		setId(id);
	}

	/**
	 * Returns whether the validation was successful or not
	 * 
	 * @return true if validation was successful, false if not
	 */
	public boolean getResult() {
		return(result);
	}

	/**
	 * Sets the result of the validation
	 *  
	 * @param result If the valus is "success" then the validation was successful otherwise it failed
	 */
	public void setResult(String result) {
		this.result = RESULT_SUCCESS.equalsIgnoreCase(result);
	}

	@JsonSetter("Result")
	public void setResultsemantika(String result) {
		setResult(result);
	}

	/**
	 * Returns a list of the validation errors
	 * 
	 * @return The validation errors
	 */
	public List<ValidationResultRecordError> getErrors() {
		return(errors);
	}

	/**
	 * Sets the list of validation errors
	 * 
	 * @param errors The errors that occured during validation
	 */
	public void setErrors(List<ValidationResultRecordError> errors) {
		setErrors(errors);
	}

	@JacksonXmlElementWrapper(useWrapping=false)
	@JsonSetter("Error")
	public void setErrorsSemantika(List<ValidationResultRecordError> errors) {
		this.errors = errors;
	}

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: ValidationResultRecord:\n"; 
		result += "\tid: " + id + "\n";
		result += "\tresult: " + (this.result ? "true" : "false") + "\n";
		if (errors != null) {
			for (ValidationResultRecordError error : errors) {
				result += error.toString();
			}
		}
		return(result);
	}
}
