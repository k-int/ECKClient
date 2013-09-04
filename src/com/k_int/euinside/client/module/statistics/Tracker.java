package com.k_int.euinside.client.module.statistics;

public class Tracker {

	private String moduleName;
	private String group;
	
	private TrackerStatistic statistic = new TrackerStatistic();

	Tracker(String moduleName, String group) {
		this.moduleName = moduleName;
		this.group = group;
	}
	
	public void start() {
		statistic.start();
	}
	
	public void incrementSuccessful() {
		incrementSuccessful(1);
	}
	
	public void incrementSuccessful(int stepValue) {
		statistic.incrementSuccessful(stepValue);
	}
	
	public void incrementFailed() {
		incrementFailed(1);
	}
	
	public void incrementFailed(int stepValue) {
		statistic.incrementFailed(stepValue);
	}
	
	public void completed() {
		statistic.completed();
		flush();
	}
	
	public void flush() {
		statistic.flush(moduleName, group);
	}

	public TrackerStatistic getStatistic() {
		return(statistic);
	}

	public void setStatistic(TrackerStatistic statistic) {
		this.statistic = statistic;
	}
}
