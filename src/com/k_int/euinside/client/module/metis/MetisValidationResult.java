package com.k_int.euinside.client.module.metis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.k_int.euinside.client.json.baseJSON;
import com.k_int.euinside.client.module.validation.ValidationResultRecord;

public class MetisValidationResult extends baseJSON {
	private static Log log = LogFactory.getLog(MetisValidationResult.class);

	@JacksonXmlProperty(isAttribute=false, localName="recordId")  
	private String recordId;

	@JacksonXmlProperty(isAttribute=false)  
	private String message;

	@JacksonXmlProperty(isAttribute=false)  
	private boolean success;

	@Override
	protected Log getLogger() {
		return(log);
	}
	
	public String getRecordId() {
		return this.recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
