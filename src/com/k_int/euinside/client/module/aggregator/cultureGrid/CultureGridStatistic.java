package com.k_int.euinside.client.module.aggregator.cultureGrid;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.module.aggregator.StatisticBase;
import com.k_int.euinside.client.module.aggregator.generic.GenericEuropeanaStatistic;
import com.k_int.euinside.client.module.aggregator.generic.GenericStatistic;

/**
 * This class represents the statistics returned from CultureGrid for a collection
 *
 */
public class CultureGridStatistic extends StatisticBase {
	private static Log log = LogFactory.getLog(CultureGridStatistic.class);

	private ArrayList<CultureGridEuropeanaStatistic> europeanaDataSets;

	public CultureGridStatistic() {
	}
	
	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retrieves the europeana statistics associated with this CultureGrid collection
	 * 
	 * @return The europeana statistics for this collection
	 */
	public ArrayList<CultureGridEuropeanaStatistic> getEuropeanaDataSets() {
		return(europeanaDataSets);
	}

	/**
	 * Sets the europeana statistics for this collection
	 * 
	 * @param europeanaDataSets The europeana statistics
	 */
	public void setEuropeanaDataSets(ArrayList<CultureGridEuropeanaStatistic> europeanaDataSets) {
		this.europeanaDataSets = europeanaDataSets;
	}

	/**
	 * Converts this instance into a generic statistics result
	 * 
	 * @return A generic statistics object that represents this data
	 */
	public GenericStatistic convertToGeneric() {
		GenericStatistic result = new GenericStatistic();
		copy(result);
		result.setTotal(getAccepted() + getPending() + getRejected());
		if (europeanaDataSets != null) {
			ArrayList<GenericEuropeanaStatistic> europeanaStatistics = new ArrayList<GenericEuropeanaStatistic>();
			result.setEuropeanaDataSets(europeanaStatistics);
			for (CultureGridEuropeanaStatistic europeanaStatistic : getEuropeanaDataSets()) {
				europeanaStatistics.add(europeanaStatistic.convertToGeneric());
			}
		}
		return(result);
	}
}
