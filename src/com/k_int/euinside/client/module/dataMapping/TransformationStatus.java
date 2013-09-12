package com.k_int.euinside.client.module.dataMapping;

public enum TransformationStatus {
	
	INVALID_ID("0"),
	NOT_READY("1"),
	READY("2");
	
	private String code;

	TransformationStatus(String code) {
		this.code = code;
	}
	
	static public TransformationStatus get(String code) {
		// Default to invalid id
		TransformationStatus result = INVALID_ID;
		for (TransformationStatus transformationStatus : values()) {
			if (transformationStatus.code.equals(code)) {
				result = transformationStatus;
				break;
			}
		}
		return(result);
	}
}
