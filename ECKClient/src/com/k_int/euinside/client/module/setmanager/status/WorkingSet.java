package com.k_int.euinside.client.module.setmanager.status;

/**
 * The WorkingSet class contains the working set elements when the SetManager action status is called  
 */
public class WorkingSet {

	private String status;
	private int numberOfRecords;
	private int numberOfRecordsValid;
	private int numberOfRecordsAwaitingValidation;
	private int numberOfRecordsValidationErrors;
	private int numberOfRecordsDeleted;
	
	public WorkingSet() {
	}

	/**
	 * Retrieves the status of the working set
	 * 
	 * @return The status
	 */
	public String getStatus() {
		return(status);
	}

	/**
	 * Sets the status of the working set
	 * 
	 * @param status The status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Retrieves the number of records in the working set
	 * 
	 * @return The number of records
	 */
	public int getNumberOfRecords() {
		return(numberOfRecords);
	}

	/**
	 * Sets the number of records in the working set
	 * 
	 * @param numberOfRecords The number of records
	 */
	public 	void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	/**
	 * Retrieves the number of records that are valid in the working set
	 * 
	 * @return The number of records
	 */
	public int getNumberOfRecordsValid() {
		return(numberOfRecordsValid);
	}

	/**
	 * Sets the number of records that are valid in the working set
	 * 
	 * @param numberOfRecordsValid The number of records
	 */
	public 	void setNumberOfRecordsValid(int numberOfRecordsValid) {
		this.numberOfRecordsValid = numberOfRecordsValid;
	}

	/**
	 * Retrieves the number of records that are awaiting validation
	 * 
	 * @return The number of records
	 */
	public int getNumberOfRecordsAwaitingValidation() {
		return(numberOfRecordsAwaitingValidation);
	}
	
	/**
	 * Sets the number of records that are awaiting validation in the working set
	 * 
	 * @param numberOfRecordsAwaitingValidation The number of records
	 */
	public void setNumberOfRecordsAwaitingValidation(int numberOfRecordsAwaitingValidation) {
		this.numberOfRecordsAwaitingValidation = numberOfRecordsAwaitingValidation;
	}

	/**
	 * Retrieves the number of records that have validation errors
	 * 
	 * @return The number of records
	 */
	public int getNumberOfRecordsValidationErrors() {
		return(numberOfRecordsValidationErrors);
	}
	
	/**
	 * Sets the number of records with validation errors in the working set
	 * 
	 * @param numberOfRecordsValidationErrors The number of records
	 */
	public void setNumberOfRecordsValidationErrors(int numberOfRecordsValidationErrors) {
		this.numberOfRecordsValidationErrors = numberOfRecordsValidationErrors;
	}

	/**
	 * Retrieves the number of records that have been deleted in the working set
	 * 
	 * @return The number of records
	 */
	public int getNumberOfRecordsDeleted() {
		return(numberOfRecordsDeleted);
	}
	
	/**
	 * Sets the number of records that have been deleted in the working set
	 * 
	 * @param numberOfRecordsDeleted The number of records
	 */
	public void setNumberOfRecordsDeleted(int numberOfRecordsDeleted) {
		this.numberOfRecordsDeleted = numberOfRecordsDeleted;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: WorkingSet:\n"; 
		result += "\tStatus: " + status + "\n";
		result += "\tNumber of Records (valid): " + numberOfRecordsValid + "\n";
		result += "\tNumber of Records (awaiting validation): " + numberOfRecordsAwaitingValidation + "\n";
		result += "\tNumber of Records (validation errors): " + numberOfRecordsValidationErrors + "\n";
		result += "\tNumber of Records (deleted): " + numberOfRecordsDeleted + "\n\n";
		return(result);
	}
}
