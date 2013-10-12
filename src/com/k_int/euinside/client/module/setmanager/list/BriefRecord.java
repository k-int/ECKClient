package com.k_int.euinside.client.module.setmanager.list;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.json.baseJSON;

/**
 * The BriefRecord class represnts a record that is returned from the SetManager module using the list action
 */
public class BriefRecord extends baseJSON {
	private static Log log = LogFactory.getLog(BriefRecord.class);

	private String cmsId;
	private String persistentId;
	private Date lastUpdated;
	private boolean deleted;
	private String validationStatus;
	
	public BriefRecord() {
	}

	@Override
	protected Log getLogger() {
		return(log);
	}
	
	/**
	 * Retrieves the cms Identifier
	 * 
	 * @return The cms identifier
	 */
	public String getCmsId() {
		return(cmsId);
	}

	/**
	 * Sets the cms identifier
	 * 
	 * @param cmsId The cms identifier
	 */
	public void setCmsID(String cmsId) {
		this.cmsId = cmsId;
	}

	/**
	 * Retrieves the persistent identifier
	 *  
	 * @return The persistent identifier
	 */
	public String getPersistentId() {
		return(persistentId);
	}

	/**
	 * Sets the persistent identifier
	 * 
	 * @param persistentId The persistent identifier
	 */
	public void setPersistentId(String persistentId) {
		this.persistentId = persistentId;
	}

	/**
	 * Retrieves the last updated field
	 * 
	 * @return The date / time this record was last updated
	 */
	public Date getLastUpdated() {
		return(lastUpdated);
	}

	/**
	 * Sets the last updated date / time
	 * 
	 * @param lastUpdated The date / time this record was last updated
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * Is this a deleted record
	 * 
	 * @return true if the record has been deleted
	 */
	public boolean isDeleted() {
		return(deleted);
	}

	/**
	 * Marks the records as being deleted
	 * 
	 * @param deleted true if the record has been deleted
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	/**
	 * Retrieves the validation status of this record
	 * 
	 * @return The validation status
	 */
	public String getValidationStatus() {
		return(validationStatus);
	}

	/**
	 * Sets the validation status of this record
	 * 
	 * @param validationStatus The validation status
	 */
	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}
}
