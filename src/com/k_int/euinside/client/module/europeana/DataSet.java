package com.k_int.euinside.client.module.europeana;

/**
 * Class to define a Europeana data set
 */
public class DataSet {
	private String identifier;
	private String name;
	private String description;
	private String status;
	private Long accepted;
	private Long rejected;

	public DataSet() {
	}
	
	/**
	 * Retrieves the identifier of the data set
	 * 
	 * @return The identifier
	 */
	public String getIdentifier() {
		return(identifier);
	}

	/**
	 * Sets the Identifier
	 * 
	 * @param identifier The identifier for this data set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Retrieves the name of the data set
	 * 
	 * @return The name
	 */
	public String getName() {
		return(name);
	}

	/**
	 * Sets the name
	 * 
	 * @param name The name of the data set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the description
	 * 
	 * @return The description of the data set
	 */
	public String getDescription() {
		return(description);
	}

	/**
	 * Sets the description
	 * 
	 * @param description The description of the data set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retrieves the status
	 * 
	 * @return The status of the data set
	 */
	public String getStatus() {
		return(status);
	}

	/**
	 * Sets the status
	 * @param status The status of the data set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Retrieves the number of accepted records
	 * 
	 * @return The number of accepted records in the data set
	 */
	public Long getAccepted() {
		return(accepted);
	}

	/**
	 * Sets the number of accepted records
	 * 
	 * @param accepted The number of accepted records in the data set
	 */
	public void setAccepted(Long accepted) {
		this.accepted = accepted;
	}

	/**
	 * Retrieves the number of rejected records
	 * 
	 * @return The number of rejected records in the data set
	 */
	public Long getRejected() {
		return(rejected);
	}

	/**
	 * Sets the number of rejected records
	 * 
	 * @param rejected The number of rejected records in the data set
	 */
	public void setRejected(Long rejected) {
		this.rejected = rejected;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: DataSet\n";
		result += "Identifier: " + identifier + "\n"; 
		result += "Name: " + name + "\n"; 
		result += "Description: " + description + "\n"; 
		result += "Status: " + status + "\n"; 
		result += "Accepted: " + accepted.toString() + "\n"; 
		result += "Rejected: " + rejected.toString() + "\n"; 
		return(result);
	}
}
