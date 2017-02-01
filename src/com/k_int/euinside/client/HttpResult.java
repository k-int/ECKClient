package com.k_int.euinside.client;

import java.nio.charset.StandardCharsets;

import org.apache.http.entity.ContentType;

/**
 * The HttpResult contans the result of an http call
 */
public class HttpResult {
	private Error callResult;
	private int httpStatusCode;
	private byte [] content;
	private String contentType;

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
		return(new String(content, StandardCharsets.UTF_8));
	}

	/**
	 * Retrieves the content as returned by the server
	 * 
	 * @return The content as returned by the server
	 */
	public byte [] getContentBytes() {
		return(content);
	}

	/**
	 * Sets the content returned by the server
	 * 
	 * @param content The textual content that was returned
	 */
	public void setContent(byte [] content) {
		this.content = content;
	}
	
	/**
	 * Retrieves the content type as returned from the server
	 * 
	 * @return The content type as returned by the server
	 */
	public String getContentType() {
		return(contentType);
	}

	/**
	 * Sets the content type returned by the server
	 * 
	 * @param contentType The content type of the the content
	 */
	public void setContentType(String contentType) {
		// Not interested in the charset
		if (contentType != null) {
			this.contentType = contentType.replaceAll(";.*$", "").toLowerCase();
		}
	}
	
	public boolean isContentTypeJSON() {
		return((contentType != null) && contentType.equals(ContentType.APPLICATION_JSON));
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
		result += "\tContent Type: " + ((contentType == null) ? "" : contentType) + "\n";
		result += "\tContent: " + ((content == null) ? "" : content) + "\n";
		return(result);
	}
}
