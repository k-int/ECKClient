package com.k_int.euinside.client.module.dataTransformation;

public class RequestResponse {
	private String requestId;

	public String getRequestId() {
		return(requestId);
	}

	public void setRequest_id(String requestId) {
		this.requestId = requestId;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: RequestResponse:\n"; 
		result += "\trequestId: " + requestId + "\n";
		return(result);
	}
}
