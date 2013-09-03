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
	
	public void incremntSuccessful() {
		incremntSuccessful(1);
	}
	
	public void incremntSuccessful(int stepValue) {
		statistic.incremntSuccessful(stepValue);
	}
	
	public void incremntFailed() {
		incremntFailed(1);
	}
	
	public void incremntFailed(int stepValue) {
		statistic.incremntFailed(stepValue);
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
