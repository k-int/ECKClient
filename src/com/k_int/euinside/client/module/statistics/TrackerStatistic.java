package com.k_int.euinside.client.module.statistics;

/**
 * This class is where the statistics are recorded
 */
public class TrackerStatistic {
	
	private long startTime;
	private int numberSuccessful;
	private int numberFailed;
	private long endTime;

	/**
	 * Start recording statistics
	 */
	public void start() {
		startTime = System.currentTimeMillis();
		numberSuccessful = 0;
		numberFailed = 0;
	}

	/**
	 * Increment the successful count
	 */
	public void incrementSuccessful() {
		incrementSuccessful(1);
	}

	/**
	 * Incrase the successful count by the specified value
	 * 
	 * @param stepValue The number of successful items
	 */
	public void incrementSuccessful(int stepValue) {
		numberSuccessful += stepValue;
	}

	/**
	 * Increment the failed count
	 */
	public void incrementFailed() {
		incrementFailed(1);
	}

	/**
	 * Increase the failed count by the specified value
	 * 
	 * @param stepValue The number of failed items
	 */
	public void incrementFailed(int stepValue) {
		numberFailed += stepValue;
	}

	/**
	 * Complete the recording of the statistics
	 */
	public void completed() {
		endTime = System.currentTimeMillis();
	}

	/**
	 * Informs the statistic module of the statistics
	 * 
	 * @param moduleName The module the statistic belong to
	 * @param group The group the statistic is for
	 */
	public void flush(String moduleName, String group) {
		Statistics.update(moduleName, group, startTime, endTime, numberFailed, numberSuccessful);
	}
}
