package com.k_int.euinside.client.module.europeana;

import com.k_int.euinside.client.json.baseJSON;

public abstract class EuropeanaResult extends baseJSON {
	private String apikey;
	private String action;
	private boolean success;
	private String error;
	private Long statsStartTime;
	private Long statsDuration;
	private Integer itemsCount;
	private Integer totalResults;
	
	public String getApikey() {
		return(apikey);
	}
	
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	
	public String getAction() {
		return(action);
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public boolean isSuccess() {
		return(success);
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getError() {
		return(error);
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public Long getStatsStartTime() {
		return(statsStartTime);
	}
	
	public void setStatsStartTime(Long statsStartTime) {
		this.statsStartTime = statsStartTime;
	}
	
	public Long getStatsDuration() {
		return(statsDuration);
	}
	
	public void setStatsDuration(Long statsDuration) {
		this.statsDuration = statsDuration;
	}
	
	public Integer getItemsCount() {
		return(itemsCount);
	}
	
	public void setItemsCount(Integer itemsCount) {
		this.itemsCount = itemsCount;
	}
	
	public Integer getTotalResults() {
		return(totalResults);
	}
	
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
