package com.k_int.euinside.client.module.dataTransformation;

import com.k_int.euinside.client.json.baseJSON;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestResponse extends baseJSON {
	private static Log log = LogFactory.getLog(RequestResponse.class);
	private String requestId;

	public String getRequestId() {
		return(requestId);
	}

	@Override
	protected Log getLogger() {
		return(log);
	}
	
	public void setRequest_id(String requestId) {
		this.requestId = requestId;
	}
}
