package com.k_int.euinside.client.module.europeana;

import com.k_int.euinside.client.json.baseJSON;

/**
 * Base class for a europeana result
 *
 */
public abstract class EuropeanaResult extends baseJSON {
	private String apikey;
	private String action;
	private boolean success;
	private String error;
	private Long statsStartTime;
	private Long statsDuration;
	private Integer itemsCount;
	private Integer totalResults;

	/**
	 * Retrieves the wskey that was used for the query
	 * 
	 * @return The wskey
	 */
	public String getApikey() {
		return(apikey);
	}

	/**
	 * Sets the wskey used for this result
	 * 
	 * @param apikey The wskey used to generate the results
	 */
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

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
	public Integer getTotalResults() {
		return(totalResults);
	}

	/**
	 * Sets the total number of results for this action
	 * 
	 * @param totalResults The total number of results for this action
	 */
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: EuropeanaResult\n";
		result += "API Key: " + apikey + "\n";
		result += "Action: " + action + "\n";
		result += "Success: " + success + "\n";
		result += "Error: " + ((error == null) ? "" : error) + "\n";
		result += "Stats Start Time: " + ((statsStartTime == null) ? "" : statsStartTime.toString()) + "\n";
		result += "Stats Duration: " + ((statsDuration == null) ? "" : statsDuration.toString()) + "\n";
		result += "Items Count: " + itemsCount.toString() + "\n";
		result += "Items Total: " + totalResults.toString() + "\n";
		return(result);
	}
}
