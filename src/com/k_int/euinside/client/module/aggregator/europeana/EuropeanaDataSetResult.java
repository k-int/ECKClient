package com.k_int.euinside.client.module.aggregator.europeana;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.module.aggregator.generic.GenericEuropeanaStatistic;
import com.k_int.euinside.client.module.aggregator.generic.GenericStatistic;


/**
 * Class that defines a europeana DataSet result
 *
 */
public class EuropeanaDataSetResult extends EuropeanaResult {
	private static Log log = LogFactory.getLog(EuropeanaDataSetResult.class);

	private ArrayList<EuropeanaDataSet> items;
	
	public EuropeanaDataSetResult() {
	}
	
	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retries all the data sets contained within this result
	 * 
	 * @return The list of DataSets for this result
	 */
	public ArrayList<EuropeanaDataSet> getItems() {
		return(items);
	}

	/**
	 * Sets the list of DataSets contained within this result
	 * 
	 * @param items The DataSets this result represents
	 */
	public void setItems(ArrayList<EuropeanaDataSet> items) {
		this.items = items;
	}

	/** 
	 * Converts this statistic into a generic one
	 * 
	 * @return The generic statistic
	 */
	public GenericStatistic convertToGeneric() {
		GenericStatistic result = new GenericStatistic();
		
		// we have nothing to put at the top level as all the results go in the europeana section
		if ((items != null) && !items.isEmpty()) {
			ArrayList<GenericEuropeanaStatistic> europeanaDataSets = new ArrayList<GenericEuropeanaStatistic>();
			result.setEuropeanaDataSets(europeanaDataSets);
			for (EuropeanaDataSet item : items) {
				europeanaDataSets.add(item.convertToGenericEuropeana());
			}
		}

		return(result);
	}
	
}
