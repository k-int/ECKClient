package com.k_int.euinside.client.module.statistics;

public class TrackerStatistic {
	
	private long startTime;
	private int numberSuccessful;
	private int numberFailed;
	private long endTime;

	public void start() {
		startTime = System.currentTimeMillis();
		numberSuccessful = 0;
		numberFailed = 0;
	}
	
	public void incrementSuccessful() {
		incrementSuccessful(1);
	}
	
	public void incrementSuccessful(int stepValue) {
		numberSuccessful += stepValue;
	}
	
	public void incrementFailed() {
		incrementFailed(1);
	}
	
	public void incrementFailed(int stepValue) {
		numberFailed += stepValue;
	}
	
	public void completed() {
		endTime = System.currentTimeMillis();
	}
	
	public void flush(String moduleName, String group) {
		Statistics.update(moduleName, group, startTime, endTime, numberFailed, numberSuccessful);
	}
}
