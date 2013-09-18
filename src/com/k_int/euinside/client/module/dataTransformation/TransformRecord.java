package com.k_int.euinside.client.module.dataTransformation;

import com.k_int.euinside.client.ZipRecords;

/*
 * Helper class for transforming multiple records at once
 */
public class TransformRecord {
	private byte [] xmlRecordToConvert;
	private Long identifier;
	private byte [] xmlConvertedRecord;

	/**
	 * Retrieves the record to be converted
	 * 
	 * @return The record to be converted
	 */
	public byte [] getXmlRecordToConvert() {
		return(xmlRecordToConvert);
	}
	
	/**
	 * Sets the record to be converted
	 * 
	 * @param xmlRecordToConvert The record to be converted
	 */
	public void setXmlRecordToConvert(byte[] xmlRecordToConvert) {
		this.xmlRecordToConvert = xmlRecordToConvert;
	}

	/**
	 * Gets the identifier for the record
	 * 
	 * @return The records identifier
	 */
	public Long getIdentifier() {
		return(identifier);
	}

	/**
	 * Sets the records identifier
	 * 
	 * @param identifier Sets the record identifier
	 */
	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	/**
	 * Retries the converted record after the transformation process has occured
	 * 
	 * @return The converted record
	 */
	public byte [] getXmlConvertedRecord() {
		return(xmlConvertedRecord);
	}

	/**
	 * Sets the converted record
	 * 
	 * @param xmlConvertedRecord The converted record
	 */
	public void setXmlConvertedRecord(byte[] xmlConvertedRecord) {
		this.xmlConvertedRecord = xmlConvertedRecord;
	}

	/**
	 * Adds this record to the zip file
	 * 
	 * @param zipRecords The zip file that it is to be added to
	 * 
	 * @return The name given to the entry in the zip file (identifer postfixed with .xml)
	 */
	public String add(ZipRecords zipRecords) {
		String name = identifier.toString() + ".xml";
		zipRecords.addEntry(name, xmlRecordToConvert);
		return(name);
	}
}
