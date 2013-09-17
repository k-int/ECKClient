package com.k_int.euinside.client.module.validation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * This class provides the result of the validation
 */
@JacksonXmlRootElement(localName="validationresult")
public class ValidationResult {

	@JacksonXmlProperty(isAttribute=true)  
	private String provider;

	@JacksonXmlProperty(isAttribute=true)  
	private String set;
	  
	@JacksonXmlElementWrapper(useWrapping=false)
	@JacksonXmlProperty(localName="record")
	private List<ValidationResultRecord> records;
	
	public ValidationResult() {
	}

	/**
	 * Returns the provider that the validation was submitted for
	 * 
	 * @return The provider
	 */
	public String getProvider() {
		return(provider);
	}

	/**
	 * Sets the provider who this validation result is for
	 * 
	 * @param provider The provider
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	@JsonSetter("Provider")
	public void setProviderSemantika(String provider) {
		setProvider(provider);
	}

	/**
	 * Gets hold of the set this validation was submitted for
	 * 
	 * @return The set
	 */
	public String getSet() {
		return(set);
	}

	/**
	 * Sets the set for which this result represents
	 * 
	 * @param set The set
	 */
	public void setSet(String set) {
		this.set = set;
	}

	/**
	 * Sets the set for which this result represents
	 * 
	 * @param set The set
	 */
	@JsonSetter("Set")
	public void setSetSemantika(String set) {
		setSet(set);
	}

	/**
	 * Returns the list of records that are part of this validation set 
	 *
	 * @return The results for each of the records that were validated
	 */
	public List<ValidationResultRecord> getRecords() {
		return(records);
	}

	/**
	 * Sets the results for each of the records that were validated
	 * 
	 * @param records The list of results
	 */
	public void setRecords(List<ValidationResultRecord> records) {
		this.records = records;
	}

	@JacksonXmlElementWrapper(useWrapping=false)
	@JsonSetter("Record")
	public void setRecordsSemantika(List<ValidationResultRecord> records) {
		setRecords(records);
	}

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: ValidationResult:\n"; 
		result += "\tprovider: " + provider + "\n";
		result += "\tset: " + set + "\n";
		if (records != null) {
			for (ValidationResultRecord record : records) {
				result += record.toString();
			}
		}
		return(result);
	}
}
