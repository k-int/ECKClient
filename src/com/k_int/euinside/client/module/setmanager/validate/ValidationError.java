package com.k_int.euinside.client.module.setmanager.validate;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.json.baseJSON;

/**
 * The ValidationError class holds the validation errors for a particular record 
 */
public class ValidationError extends baseJSON {
	private static Log log = LogFactory.getLog(ValidationError.class);

	private String cmsId;
	private String persistentId;
	private Date lastUpdated;
	private ArrayList<Error> errors;
	
	public ValidationError() {
	}

	@Override
	protected Log getLogger() {
		return(log);
	}
	
	/**
	 * Retrieves the cms identifier that these validation errors are for
	 * 
	 * @return The cms identifier for the record in error
	 */
	public String getCmsId() {
		return(cmsId);
	}
	
	public void setCmsId(String cmsId) {
		this.cmsId = cmsId;
	}
	
	/**
	 * Retrieves the persistent identifier that these validation errors are for
	 * 
	 * @return The persistent identifier for the record in error
	 */
	public String getPersistentId() {
		return(persistentId);
	}
	
	public void setPersistentId(String persistentId) {
		this.persistentId = persistentId;
	}
	
	/**
	 * Retrieves when the record was last updated
	 * 
	 * @return The date when the record was last updated
	 */
	public Date getLastUpdated() {
		return(lastUpdated);
	}
	
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * Retrieves the errors for this record
	 * 
	 * @return The errors for this record
	 */
	public ArrayList<Error> getErrors() {
		return(errors);
	}
	
	public void setErrors(ArrayList<Error> errors) {
		this.errors = errors;
	}
}
