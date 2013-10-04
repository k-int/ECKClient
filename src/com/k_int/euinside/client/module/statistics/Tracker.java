package com.k_int.euinside.client.module.statistics;

/**
 * The tracker class provides an interface into the statistics module and
 * at the same time provides an interface for helping to keep track of the statistics 
 */
public class Tracker {

	private String moduleName;
	private String group;
	
	private TrackerStatistic statistic = new TrackerStatistic();

	/**
	 * Constructor that initialises the Tracker instance
	 * 
	 * @param moduleName The module that statistics are gong to be recorded for
	 * @param group The group within this module that the statistics represent (eg. update, validation, transformation)
	 */
	Tracker(String moduleName, String group) {
		this.moduleName = moduleName;
		this.group = group;
	}

	/**
	 * Starts the timer and begins capturing the statistics
	 */
	public void start() {
		statistic.start();
	}

	/**
	 * Increment the successful count
	 */
	public void incrementSuccessful() {
		incrementSuccessful(1);
	}

	/**
	 * Increase the successful count by the specified value
	 * 
	 * @param stepValue The number of successful items
	 */
	public void incrementSuccessful(int stepValue) {
		statistic.incrementSuccessful(stepValue);
	}

	/**
	 * Increment the failure count
	 */
	public void incrementFailed() {
		incrementFailed(1);
	}

	/**
	 *  Increase the failure count by the specified value
	 *  
	 * @param stepValue The number of failed items
	 */
	public void incrementFailed(int stepValue) {
		statistic.incrementFailed(stepValue);
	}

	/**
	 * Stops the timer and informs the Statistics module of the statistics
	 */
	public void completed() {
		statistic.completed();
		flush();
	}

	/**
	 * Informs the statistics module of the statistics
	 */
	public void flush() {
		statistic.flush(moduleName, group);
	}

	/**
	 * Retrieves the statistics
	 * 
	 * @return The statistics that is represented by theis instance
	 */
	public TrackerStatistic getStatistic() {
		return(statistic);
	}

	/**
	 * Sets the statistics that is represented by this instance
	 * 
	 * @param statistic The statistics that will be represented by this instance
	 */
	public void setStatistic(TrackerStatistic statistic) {
		this.statistic = statistic;
	}
}
