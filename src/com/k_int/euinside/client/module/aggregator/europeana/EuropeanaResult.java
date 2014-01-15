package com.k_int.euinside.client.module.aggregator.europeana;

import com.k_int.euinside.client.json.baseJSON;

/**
 * Base class for a europeana result
 *
 */
public abstract class EuropeanaResult extends baseJSON {
	// Note: have specifically excluded the apikey, as it maybe added by the ECKCore and we dot want callers knowing what it was
	private String action;
	private boolean success;
	private String error;
	private Long statsStartTime;
	private Long statsDuration;
	private Integer requestNumber;
	private Integer itemsCount;
	private Long totalResults;

	/**
	 * The action that was performed to generate the results
	 * 
	 * @return The action
	 */
	public String getAction() {
		return(action);
	}

	/**
	 * Sets the action that was performed to generate the results
	 * 
	 * @param action The action that was performed
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Was the action successful or not
	 * 
	 * @return true if the action was successful
	 */
	public boolean isSuccess() {
		return(success);
	}

	/**
	 * Sets whether the action was successful or not
	 * 
	 * @param success true if the action was successful
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Retrieves the error if action was unsuccessful
	 * 
	 * @return The error
	 */
	public String getError() {
		return(error);
	}

	/**
	 * Sets the error if the action was unsuccessful
	 * 
	 * @param error The reason for being unsuccessful
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Retrieves the time the action started
	 * 
	 * @return The time the action started
	 */
	public Long getStatsStartTime() {
		return(statsStartTime);
	}

	/**
	 * Sets the time the action started
	 * 
	 * @param statsStartTime The time the action started
	 */
	public void setStatsStartTime(Long statsStartTime) {
		this.statsStartTime = statsStartTime;
	}

	/**
	 * Retrieves the duration of the action
	 * 
	 * @return The duratin (ms) of how long the action took to process
	 */
	public Long getStatsDuration() {
		return(statsDuration);
	}

	/**
	 * Sets how long it took to process the action
	 * 
	 * @param statsDuration How long in ms it took to process the action
	 */
	public void setStatsDuration(Long statsDuration) {
		this.statsDuration = statsDuration;
	}

	/**
	 * Retrieves the number of requests that have been made by this apikey in the last 24 hours
	 * 
	 * @return The number of requests made by the apikey in the last 24 hours
	 */
	public Integer getRequestNumber() {
		return(requestNumber);
	}

	/**
	 * Sets the number of requests made by this api key in the last 24 hours
	 * 
	 * @param requestNumber The number of requests made by this api key in the last 24 hours
	 */
	public void setRequestNumber(Integer requestNumber) {
		this.requestNumber = requestNumber;
	}

	/**
	 * Retrieves the number of items that have been returned
	 * 
	 * @return The number of items that have been returned in this result
	 */
	public Integer getItemsCount() {
		return(itemsCount);
	}

	/**
	 * Sets the number of items that have been returned in this result
	 * 
	 * @param itemsCount The number of items of items that have been returned
	 */
	public void setItemsCount(Integer itemsCount) {
		this.itemsCount = itemsCount;
	}

	/**
	 * Retrieves the total number of results for this action
	 * 
	 * @return The total number of results for this action
	 */
	public Long getTotalResults() {
		return(totalResults);
	}

	/**
	 * Sets the total number of results for this action
	 * 
	 * @param totalResults The total number of results for this action
	 */
	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}
}
