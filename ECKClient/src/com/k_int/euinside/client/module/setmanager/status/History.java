package com.k_int.euinside.client.module.setmanager.status;

import java.util.Date;

/**
 * The History class contains the history element when the SetManager action sttaus is called  
 */
public class History {

	private String action;
	private Date when;
	private int numberOfRecords;
	private int duration;
	
	public History() {
	}

	/**
	 * Retrieves the action that was performed
	 * 
	 * @return The action performed
	 */
	public String getAction() {
		return(action);
	}

	/**
	 * Sets the action that was performed
	 * 
	 * @param action The action performed
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Retrieves the date / time when the action was performed
	 * 
	 * @return The date / time the action was performed
	 */
	public Date getWhen() {
		return(when);
	}

	/**
	 * Sets the date / time when the action was performed
	 * 
	 * @param when The date / time
	 */
	public void setWhen(Date when) {
		this.when = when;
	}

	/**
	 * Retrieves the number of records that were affected by this action
	 * 
	 * @return The number of records affected
	 */
	public int getNumberOfRecords() {
		return(numberOfRecords);
	}

	/**
	 * Sets the number of records affected by this action
	 * 
	 * @param numberOfRecords The number of records
	 */
	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	/**
	 * Retrieves the duration in milliseconds it took to perform this action
	 * 
	 * @return The length of time it took to perform the action
	 */
	public int getDuration() {
		return(duration);
	}

	/**
	 * Sets the length of time it took to perform this action
	 * 
	 * @param duration The length of time
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: History:\n"; 
		result += "\tAction: " + action + "\n";
		result += "\tWhen: " + when + "\n";
		result += "\tNumber of Records: " + numberOfRecords + "\n";
		result += "\tDuration: " + duration + "\n";
		return(result);
	}
}
