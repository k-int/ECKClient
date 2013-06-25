package com.k_int.euinside.client.module.pidgenerate;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The PIDComponents class holds al the elements used or returned by the PID Generation module 
 */
public class PIDComponents {
	@JsonProperty("InstitutionUrl")
	private String institutionURL;
	@JsonProperty("RecordType")
	private String recordType;
	@JsonProperty("AccessionNumber")
	private String accessionNumber;

	public PIDComponents() {
	}

	/**
	 * Constructor that takes all the input elements as parameters
	 *  
	 * @param institutionURL The institution URL
	 * @param recordType The type of record the pid is being generated for
	 * @param accessionNumber The identifier for this item in the local system
	 * 
	 */
	public PIDComponents(String institutionURL, String recordType, String accessionNumber) {
		this.institutionURL = institutionURL;
		this.recordType = recordType;
		this.accessionNumber = accessionNumber;
	}

	/**
	 * Retrieves the institution URL
	 * 
	 * @return The institution URL
	 */
	public String getInstitutionURL() {
		return(institutionURL);
	}

	/**
	 * Set the institution URL
	 * 
	 * @param institutionURL The institution URL
	 */
	public void setInstitutionURL(String institutionURL) {
		this.institutionURL = institutionURL;
	}

	/** 
	 * Retrieves the record type that the accession number represents
	 * 
	 * @return The record type
	 */
	public String getRecordType() {
		return(recordType);
	}

	/**
	 * Sets the record type that the accession number represents
	 * 
	 * @param recordType The record type
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/**
	 * Retrieves the accession number
	 * 
	 * @return The accession number
	 */
	public String getAccessionNumber() {
		return(accessionNumber);
	}

	/**
	 * Sets the accession number
	 * 
	 * @param accessionNumber The accession number
	 */
	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: PIDComponents:\n"; 
		result += "\taccessionNumber: \"" + accessionNumber + "\"\n";
		result += "\tinstitutionURL: \"" + institutionURL + "\"\n";
		result += "\trecordType: \"" + recordType + "\"\n";
		return(result);
	}
}
