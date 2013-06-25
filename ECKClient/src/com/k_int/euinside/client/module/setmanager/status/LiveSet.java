package com.k_int.euinside.client.module.setmanager.status;

import java.util.Date;

/**
 * The LiveSet class contains the live set elements when the SetManager action status is called  
 */
public class LiveSet {

	private String status;
	private Date dateCommitted;
	private int numberOfRecords;
	
	public LiveSet() {
	}

	/**
	 * Retrieves the status of the Live Set
	 * 
	 * @return The status
	 */
	public String getStatus() {
		return(status);
	}

	/**
	 * Sets the status of the live set
	 * 
	 * @param status The status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * The date / time the live set was committed
	 * 
	 * @return The date date / time the set was committed
	 */
	public Date getDateCommitted() {
		return(dateCommitted);
	}

	/**
	 * Sets the date / time the live set was committed
	 * 
	 * @param dateCommitted Date /time committed
	 */
	public void setDateCommitted(Date dateCommitted) {
		this.dateCommitted = dateCommitted;
	}

	/**
	 * Retrieves the number of records in the live set 
	 * 
	 * @return The number of records in the live set
	 */
	public int getNumberOfRecords() {
		return(numberOfRecords);
	}

	/**
	 * Sets the number of records in the live set
	 * 
	 * @param numberOfRecords The number of records
	 */
	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: LiveSet:\n"; 
		result += "\tStatus: " + status + "\n";
		result += "\tDate Committed: " + dateCommitted + "\n";
		result += "\tNumber of Records: " + numberOfRecords + "\n\n";
		return(result);
	}
}
