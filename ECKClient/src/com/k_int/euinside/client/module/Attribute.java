package com.k_int.euinside.client.module;

public enum Attribute {
	ACCESSION_NUMBER("accessionNumber"),
	DELETE("delete"),
	DELETE_ALL("deleteAll"),
	HISTORY_ITEMS("historyItems"),
	INSTITUTION_URL("institutionUrl"),
	RECORD_ID("recordId"),
	RECORD_TYPE("recordType");

	private String name;
	
	private Attribute(String name) {
		this.name = name;
	}
	
	public String getName() {
		return(name);
	}
}
