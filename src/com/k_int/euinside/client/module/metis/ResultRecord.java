package com.k_int.euinside.client.module.metis;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.k_int.euinside.client.json.baseJSON;

@JacksonXmlRootElement(localName="result")
public class ResultRecord extends baseJSON {
	private static Log log = LogFactory.getLog(ResultRecord.class);

	@JacksonXmlProperty(isAttribute=false)  
	private boolean result = false;

	@JacksonXmlProperty(isAttribute=false)  
	private String recordId;

	@JacksonXmlProperty(isAttribute=false)  
	private String message;

	@JacksonXmlProperty(isAttribute=false)  
	private Date expiryDate;

	@JacksonXmlProperty(isAttribute=false)  
	private List<String> recordURLs;
	
	@Override
	protected Log getLogger() {
		// TODO Auto-generated method stub
		return(log);
	}

	public boolean getResult() {
		return(result);
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getRecordId() {
		return(recordId);
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getMessage() {
		return(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getExpiryDate() {
		return(expiryDate);
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public List<String> getRecordURLs() {
		return(recordURLs);
	}

	public void setRecordURLs(List<String> recordURLs) {
		this.recordURLs = recordURLs;
	}

}
