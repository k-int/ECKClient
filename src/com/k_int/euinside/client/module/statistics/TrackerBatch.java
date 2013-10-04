package com.k_int.euinside.client.module.statistics;

import java.util.ArrayList;

/**
 * This class builds up statistics and only informs the Statistics module when the flush method is called
 */
public class TrackerBatch extends Tracker {

	ArrayList<TrackerStatistic> statistics = new ArrayList<TrackerStatistic>();
	
	TrackerBatch(String moduleName, String group) {
		super(moduleName, group);
	}

	/**
	 * Stops recordings statistics and adds the current statistic to the list waiting to be sent to the Statistic Module 
	 */
	public void completed() {
		getStatistic().completed();
		statistics.add(getStatistic());
		setStatistic(new TrackerStatistic());
	}

	/**
	 * Sends the waiting statistics to the Statistics module
	 */
	public void flush() {
		for (TrackerStatistic statistic : statistics) {
			setStatistic(statistic);
			super.flush();
		}
		statistics.clear();
	}
}
