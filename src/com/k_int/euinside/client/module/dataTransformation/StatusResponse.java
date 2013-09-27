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

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: StatusResponse:\n"; 
		result += "\ttransformationStatus: " + transformationStatus.toString() + "\n";
		return(result);
	}
}
