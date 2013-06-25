package com.k_int.euinside.client;

/**
 * The HttpResult contans the result of an http call
 */
public class HttpResult {
	private Error callResult;
	private int httpStatusCode;
	private String content;

	public HttpResult() {
		callResult = Error.NONE;
	}

	/**
	 * Retrieves the result of the http call
	 * 
	 * @return The result of the http call
	 */
	public Error getCallResult() {
		return(callResult);
	}

	/**
	 * Sets the result of the http call
	 * 
	 * @param callResult The result of the http call
	 */
	public void setCallResult(Error callResult) {
		this.callResult = callResult;
	}

	/**
	 * Retrieves the http status code that was returned
	 * 
	 * @return The http status code
	 */
	public int getHttpStatusCode() {
		return(httpStatusCode);
	}

	/**
	 * Set the http status code
	 * 
	 * @param httpStatusCode The http status code returned by the server
	 */
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	/**
	 * Retrieves the textual content returned by the server
	 * 
	 * @return The textual content returned by the server
	 */
	public String getContent() {
		return(content);
	}

	/**
	 * Sets the textual content returned by the server
	 * 
	 * @param content The textual content that was returned
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: HttpResult:\n"; 
		result += "\tcallResult: " + callResult + "\n";
		result += "\tHhttpStatusCode: " + httpStatusCode + "\n";
		result += "\tContent: " + ((content == null) ? "" : content) + "\n";
		return(result);
	}
}
