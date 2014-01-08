package com.k_int.euinside.client.module.aggregator.generic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.json.baseJSON;

/**
 * This class contains the generic europeana statistical information for a collection
 */
public class GenericEuropeanaStatistic extends baseJSON {
	private static Log log = LogFactory.getLog(GenericEuropeanaStatistic.class);
	
	private String identifier;
	private String name;
	private String creationDate;
	private String status;
	private Long publishedRecords;
	private Long deletedRecords;

	public GenericEuropeanaStatistic() {
	}

	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retrieves the identifier for this europeana dataset
	 * 
	 * @return The europeana dataset identifier
	 */
	public String getIdentifier() {
		return(identifier);
	}

	/**
	 * Sets the europeana identifier for this dataset
	 * 
	 * @param identifier The europeana identifier
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Retrieves the name for this dataset
	 * 
	 * @return The name for the dataset
	 */
	public String getName() {
		return(name);
	}

	/**
	 * Sets the name for the dataset
	 * 
	 * @param name The dataset name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the creation date for this dataset
	 * 
	 * @return The europeana creation date for this dataset
	 */
	public String getCreationDate() {
		return(creationDate);
	}

	/**
	 * Sets the creation date for this dataset
	 * 
	 * @param creationDate The creation date
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Retrieves the status for this dataset
	 * 
	 * @return The europeana status for this dataset
	 */
	public String getStatus() {
		return(status);
	}

	/**
	 * Sets the status for this dataset
	 * 
	 * @param status The europeana status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Retrieves the number of published / accepted records
	 * 
	 * @return The number of published / accepted rcords
	 */
	public Long getPublishedRecords() {
		return(publishedRecords);
	}

	/**
	 * Sets the number of published / accepted records
	 * 
	 * @param publishedRecords The number of published / accepted records
	 */
	public void setPublishedRecords(Long publishedRecords) {
		this.publishedRecords = publishedRecords;
	}

	/**
	 * Retrieves the number of deleted / rejected records
	 * 
	 * @return The number of deleted / rejected records
	 */
	public Long getDeletedRecords() {
		return(deletedRecords);
	}

	/**
	 * Sets the number of rejected / deleted records
	 * 
	 * @param deletedRecords The number of deleted / rejected records
	 */
	public void setDeletedRecords(Long deletedRecords) {
		this.deletedRecords = deletedRecords;
	}
}
