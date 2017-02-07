package com.k_int.euinside.client.module.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.k_int.euinside.client.json.baseJSON;

/**
 * This class provides the result of the validation
 */
@JacksonXmlRootElement(localName="validationresult")
public class ValidationResult extends baseJSON {
	private static Log log = LogFactory.getLog(ValidationResult.class);

	@JacksonXmlProperty(isAttribute=true)  
	private String provider;

	@JacksonXmlProperty(isAttribute=true)  
	private String set;

	@JacksonXmlElementWrapper(useWrapping=false)
	@JacksonXmlProperty(localName="record")
	private List<ValidationResultRecord> records;

	/**
	 * Constructs a ValidationResult instance with a provider and set
	 * 
	 * @param Provider the provider of the results
	 * @param set the name given to this set of results
	 */
	public ValidationResult(String provider, String set) {
		setProvider(provider);
		setSet(set);
	}

	/**
	 * Constructs a ValidationResult instance with a provider
	 * 
	 * @param Provider the provider of the results
	 */
	public ValidationResult(String provider) {
		this(provider, null);
	}

	/**
	 * Constructs a ValidationResult instance
	 */
	public ValidationResult() {
	}

	@Override
	protected Log getLogger() {
		return(log);
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

	/**
	 * Creates a new validation result record
	 * 
	 * @return the newly created result record
	 */
	public ValidationResultRecord addRecord() {
		ValidationResultRecord result = new ValidationResultRecord();
		if (records == null) {
			records = new ArrayList<ValidationResultRecord>();
		}
		records.add(result);
		return(result);
	}

	@JacksonXmlElementWrapper(useWrapping=false)
	@JsonSetter("Record")
	public void setRecordsSemantika(List<ValidationResultRecord> records) {
		setRecords(records);
	}
}
