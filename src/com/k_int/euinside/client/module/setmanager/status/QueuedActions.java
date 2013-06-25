package com.k_int.euinside.client.module.setmanager.status;

import java.util.Date;

/**
 * The QueuedActions class contains the actions that are waiting to be processed when the SetManager action status is called  
 */
public class QueuedActions {

	private String action;
	private Date queued;
	private String contentType;
	private String recordsToBeDeleted;
	private boolean deleteAll;
	
	public QueuedActions() {
	}

	/**
	 * Retrieves the action that is waiting to be performed
	 * 
	 * @return The action waiting to be performed
	 */
	public String getAction() {
		return(action);
	}

	/**
	 * Sets the action waiting to be performed
	 * 
	 * @param action The action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Retrieves the date / time when this action was queued
	 * 
	 * @return The date / time when this action was queued
	 */
	public Date getQueued() {
		return(queued);
	}

	/**
	 * Sets the date / time when the action was queued
	 * 
	 * @param queued The date / time
	 */
	public void setQueued(Date queued) {
		this.queued = queued;
	}

	/**
	 * Retrieve the content type of any data associated with this action
	 * 
	 * @return The content type of the associated data
	 */
	public String getContentType() {
		return(contentType);
	}
	
	/**
	 * Sets the content type of the associated data
	 * 
	 * @param contentType The content type
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * Retrieves the records to be deleted when the action is executed (semi-colon delimited)
	 * 
	 * @return The records to be deleted
	 */
	public String getRecordsToBeDeleted() {
		return(recordsToBeDeleted);
	}

	/**
	 * Sets the records to be deleted (semi-colon delimited)
	 * 
	 * @param recordsToBeDeleted The records to be deleted
	 */
	public void setRecordsToBeDeleted(String recordsToBeDeleted) {
		this.recordsToBeDeleted = recordsToBeDeleted;
	}

	/**
	 * Do we delete all records prior to process the data associated with this action
	 * 
	 * @return true if all the records are to be deleted
	 */
	public boolean getDeleteAll() {
		return(deleteAll);
	}

	/**
	 * Sets whether all records are to be deleted
	 * 
	 * @param deleteAll Whether all records are to be deleted, prior to processing
	 */
	public void setDeleteAll(boolean deleteAll) {
		this.deleteAll = deleteAll;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: QueuedAction:\n"; 
		result += "\tAction: " + action + "\n";
		result += "\tQueued: " + queued + "\n";
		result += "\tContent Type: " + contentType + "\n";
		result += "\tRecords to Delete: " + recordsToBeDeleted + "\n";
		result += "\tDelete All: " + deleteAll + "\n\n";
		return(result);
	}
}
