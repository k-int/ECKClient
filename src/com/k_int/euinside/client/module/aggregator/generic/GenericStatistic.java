package com.k_int.euinside.client.module.aggregator.generic;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.module.aggregator.StatisticBase;

/**
 * This class represents the generic view of statistics from an aggregator
 */
public class GenericStatistic extends StatisticBase {
	private static Log log = LogFactory.getLog(GenericStatistic.class);

	private String status;
	private long total = 0;
	private ArrayList<GenericEuropeanaStatistic> europeanaDataSets;

	public GenericStatistic() {
	}
	
	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retrieves the status of the collection
	 * 
	 * @return The status of the collection
	 */
	public String getStatus() {
		return(status);
	}

	/**
	 * Sets the status of the collection
	 * 
	 * @param status The status for the collection
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Retrieves the total number of records in the collection
	 * 
	 * @return The total number of records in the collection
	 */
	public long getTotal() {
		return(total);
	}

	/**
	 * Sets the total number of records in the collection
	 * 
	 * @param total The total number of records in the collection
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * Retrieves the europeana datasets associated with this collection
	 * 
	 * @return The europeana datasets associated with this collection
	 */
	public ArrayList<GenericEuropeanaStatistic> getEuropeanaDataSets() {
		return(europeanaDataSets);
	}

	/**
	 * Sets the europeana datasets associated with this collection
	 * 
	 * @param europeanaDataSets The europeana datasets
	 */
	public void setEuropeanaDataSets(ArrayList<GenericEuropeanaStatistic> europeanaDataSets) {
		this.europeanaDataSets = europeanaDataSets;
	}
}
