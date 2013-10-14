package com.k_int.euinside.client.module.aggregator.europeana;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.module.aggregator.generic.GenericEuropeanaStatistic;
import com.k_int.euinside.client.module.aggregator.generic.GenericStatistic;


/**
 * Class to define a Europeana data set
 */
public class EuropeanaDataSet extends EuropeanaItemResult {
	private static Log log = LogFactory.getLog(EuropeanaDataSet.class);
	private String status;
	private Long publishedRecords;
	private Long deletedRecords;

	public EuropeanaDataSet() {
	}
	
	@Override
	protected Log getLogger() {
		return(log);
	}
	
	/**
	 * Retrieves the status
	 * 
	 * @return The status of the data set
	 */
	public String getStatus() {
		return(status);
	}

	/**
	 * Sets the status
	 * @param status The status of the data set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Retrieves the number of accepted records
	 * 
	 * @return The number of accepted records in the data set
	 */
	public Long getPublishedRecords() {
		return(publishedRecords);
	}

	/**
	 * Sets the number of accepted records
	 * 
	 * @param publishedRecords The number of accepted records in the data set
	 */
	public void setPublishedRecords(Long publishedRecords) {
		this.publishedRecords = publishedRecords;
	}

	/**
	 * Retrieves the number of rejected records
	 * 
	 * @return The number of rejected records in the data set
	 */
	public Long getDeletedRecords() {
		return(deletedRecords);
	}

	/**
	 * Sets the number of rejected records
	 * 
	 * @param deletedRecords The number of rejected records in the data set
	 */
	public void setDeletedRecords(String deletedRecords) {
		try {
			this.deletedRecords = Long.parseLong(deletedRecords);
		} catch (NumberFormatException e) {
			// Not a valid number so we will ignore for now
		}
	}
	
	/**
	 * Converts this instance into a generic statistics result
	 * 
	 * @return A generic statistics object that represents this data
	 */
	public GenericStatistic convertToGeneric() {
		GenericStatistic result = new GenericStatistic();
		long total = 0;
		if (getPublishedRecords() != null) {
			result.setAccepted(getPublishedRecords());
			total += getPublishedRecords();
		}
		result.setCollectionCode(getIdentifier());
		result.setDescription(getName());
		if (getDeletedRecords() != null) {
			result.setRejected(getDeletedRecords());
			total += getDeletedRecords();
		}
		result.setStatus(getStatus());
		result.setTotal(total);
		return(result);
	}
	
	/**
	 * Converts this instance into a generic europeana result
	 * 
	 * @return A generic europeana statistics object that represents this data
	 */
	public GenericEuropeanaStatistic convertToGenericEuropeana() {
		GenericEuropeanaStatistic result = new GenericEuropeanaStatistic();
		if (getPublishedRecords() != null) {
			result.setPublishedRecords(getPublishedRecords());
		}
		result.setIdentifier(getIdentifier());
		result.setName(getName());
		if (getDeletedRecords() != null) {
			result.setDeletedRecords(getDeletedRecords());
		}
		result.setStatus(getStatus());
		return(result);
	}
}
