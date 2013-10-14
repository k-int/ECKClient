package com.k_int.euinside.client.module.aggregator.cultureGrid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.module.aggregator.generic.GenericEuropeanaStatistic;

/**
 * This class contains the europeana statistics that are returned through a search of CultureGrid
 */
public class CultureGridEuropeanaStatistic extends GenericEuropeanaStatistic {
	private static Log log = LogFactory.getLog(CultureGridEuropeanaStatistic.class);
	
	public CultureGridEuropeanaStatistic() {
	}

	@Override
	protected Log getLogger() {
		return(log);
	}
	
	/**
	 * Converts this instance into a generic europeana statistics result
	 * 
	 * @return A generic europeana statistics object that represents this data
	 */
	public GenericEuropeanaStatistic convertToGeneric() {
		// We can just return this instance, since we have not extended it from the generic variant
		return(this);
	}
}
