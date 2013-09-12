package com.k_int.euinside.client.module.dataTransformation;

public class StatusResponse {
	private TransformationStatus transformationStatus;

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
