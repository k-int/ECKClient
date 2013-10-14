package com.k_int.euinside.client.module.aggregator;

import com.k_int.euinside.client.json.baseJSON;

/**
 * This is the base class that holds the common statistic elements 
 */
public abstract class StatisticBase extends baseJSON {

	private String providerCode;
	private String collectionCode;
	private String description;
	private long accepted = 0;
	private long pending = 0;
	private long rejected = 0;

	public StatisticBase() {
	}

	/**
	 * Retrieves the provider code
	 * 
	 * @return The provider code
	 */
	public String getProviderCode() {
		return(providerCode);
	}

	/**
	 * Sets the provider code
	 * 
	 * @param providerCode The provider code
	 */
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	/**
	 * Retrieves the collection code
	 * 
	 * @return The collection code
	 */
	public String getCollectionCode() {
		return(collectionCode);
	}

	/**
	 * Sets the collection code
	 * 
	 * @param collectionCode The collection code
	 */
	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	/**
	 * Retrieves the description
	 * 
	 * @return The description
	 */
	public String getDescription() {
		return(description);
	}

	/**
	 * Sets the description
	 * 
	 * @param description The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retrieves the number of records that have been accepted
	 * 
	 * @return The number of records that have been accepted
	 */
	public long getAccepted() {
		return(accepted);
	}

	/**
	 * Sets the number of records that have been accepted
	 * 
	 * @param accepted The number of records that have been accepted
	 */
	public void setAccepted(long accepted) {
		this.accepted = accepted;
	}

	/**
	 * Retrieves the number of records that are awaiting processing
	 * 
	 * @return The number of records that are awaiting processing
	 */
	public long getPending() {
		return(pending);
	}

	/**
	 * Sets the number of records that are awaiting processing
	 * 
	 * @param pending The number of records that are awaiting processing
	 */
	public void setPending(long pending) {
		this.pending = pending;
	}

	/**
	 * Retrieves the number of records that have been rejected
	 * 
	 * @return The number of records that have been rejected
	 */
	public long getRejected() {
		return(rejected);
	}

	/**
	 * Sets the number of records that have been rejected
	 * 
	 * @param rejected The number of records that have been rejected
	 */
	public void setRejected(long rejected) {
		this.rejected = rejected;
	}

	/**
	 * Copies the elements of this instance to the instance that has been passed in
	 * 
	 * @param copyInstance The instance that is to receive the values of this instance
	 */
	public void copy(StatisticBase copyInstance) {
		copyInstance.setProviderCode(getProviderCode());
		copyInstance.setCollectionCode(getCollectionCode());
		copyInstance.setDescription(getDescription());
		copyInstance.setAccepted(getAccepted());
		copyInstance.setPending(getPending());
		copyInstance.setRejected(getRejected());
	}
}
