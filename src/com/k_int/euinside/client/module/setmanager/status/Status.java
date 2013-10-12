package com.k_int.euinside.client.module.setmanager.status;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.json.baseJSON;

/**
 * The Status class contains details about the set when the module SetManager action status is called  
 */
public class Status extends baseJSON {
	private static Log log = LogFactory.getLog(Status.class);


	private String code;
	private String description;
	private Date created;
	LiveSet liveSet;
	WorkingSet workingSet;
	ArrayList<QueuedActions> queuedActions;
	ArrayList<History> history;
	
	public Status() {
	}

	@Override
	protected Log getLogger() {
		return(log);
	}
	
	/**
	 * Retrieves the code for this set
	 * 
	 * @return The code for the set
	 */
	public String getCode() {
		return(code);
	}

	/**
	 * Sets the code for the set
	 * 
	 * @param code The code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Retrieves the description for the set
	 * 
	 * @return The description for the set
	 */
	public String getDescription() {
		return(description);
	}

	/**
	 * Sets the description for the set
	 * 
	 * @param description The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retrieves the date / time that the set was created
	 * 
	 * @return The date / time that the set was created
	 */
	public Date getCreated() {
		return(created);
	}

	/**
	 * Sets the date / time that the set was created
	 * 
	 * @param created The date / time
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Retrieves details about the live set
	 * 
	 * @return Details about the live set
	 */
	public LiveSet getLiveSet() {
		return(liveSet);
	}

	/**
	 * Sets the live set details
	 * 
	 * @param liveSet The live set
	 */
	public void setLiveSet(LiveSet liveSet) {
		this.liveSet = liveSet;
	}

	/** 
	 * Retrieves details about the working set
	 * 
	 * @return The working set
	 */
	public WorkingSet getWorkingSet() {
		return(workingSet);
	}

	/**
	 * Sets the details about the working set
	 * 
	 * @param workingSet The working set
	 */
	public void setWorkingSet(WorkingSet workingSet) {
		this.workingSet = workingSet;
	}

	/**
	 * Retrieves the queued actions for this set
	 * 
	 * @return An array of the queued actions
	 */
	public ArrayList<QueuedActions> getQueuedActions() {
		return(queuedActions);
	}

	/** 
	 * Sets the queued actions for this set
	 * 
	 * @param queuedActions The queued actions
	 */
	public void setQueuedActions(ArrayList<QueuedActions> queuedActions) {
		this.queuedActions = queuedActions;
	}

	/**
	 * Retrieves the history for this set
	 * 
	 * @return An array of history items for this set
	 */
	public ArrayList<History> getHistory() {
		return(history);
	}

	/**
	 * Sets the history for this set
	 * 
	 * @param history The history array
	 */
	public void setHistory(ArrayList<History> history) {
		this.history = history;
	}
}
