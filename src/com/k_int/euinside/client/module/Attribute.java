package com.k_int.euinside.client.module;

public enum Attribute {
	ACCESSION_NUMBER("accessionNumber"),
	DATE_TIME("dateTime"),
	DELETE("delete"),
	DELETE_ALL("deleteAll"),
	DAYS("days"),
	DURATION("duration"),
	HISTORY_ITEMS("historyItems"),
	ITEMS_PROCESSED("itemsProcessed"),
	INSTITUTION_URL("institutionUrl"),
	LIMIT("limit"),
	NUMBER_FAILED("numberFailed"),
	NUMBER_SUCCESSFUL("numberSuccessful"),
	OFFSET("offset"),
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
