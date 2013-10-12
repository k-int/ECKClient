package com.k_int.euinside.client.module.dataTransformation;

import com.k_int.euinside.client.json.baseJSON;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StatusResponse extends baseJSON {
	private static Log log = LogFactory.getLog(StatusResponse.class);
	private TransformationStatus transformationStatus;

	@Override
	protected Log getLogger() {
		return(log);
	}
	
	public TransformationStatus getTransformationStatus() {
		return(transformationStatus);
	}

	public void setStatus_code(String transformationStatus) {
		this.transformationStatus = TransformationStatus.get(transformationStatus);
	}
}
