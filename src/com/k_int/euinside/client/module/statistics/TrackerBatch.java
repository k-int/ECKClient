package com.k_int.euinside.client.module.statistics;

import java.util.ArrayList;

public class TrackerBatch extends Tracker {

	ArrayList<TrackerStatistic> statistics = new ArrayList<TrackerStatistic>();
	
	TrackerBatch(String moduleName, String group) {
		super(moduleName, group);
	}
	
	public void completed() {
		getStatistic().completed();
		statistics.add(getStatistic());
		setStatistic(new TrackerStatistic());
	}
	
	public void flush() {
		for (TrackerStatistic statistic : statistics) {
			setStatistic(statistic);
			super.flush();
		}
		statistics.clear();
	}
}
