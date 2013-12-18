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
	PROFILE("profile"),
	QUERY("query"),
	RECORD("record"),
	RECORD_ID("recordId"),
	RECORD_TYPE("recordType"),
	RECORDS("records"),
	REQUEST_ID("request_id"),
	ROWS("rows"),
	SOURCE_FORMAT("sourceFormat"),
	START("start"),
	TARGET_FORMAT("targetFormat"),
	WSKEY("wskey");

	private String name;
	
	private Attribute(String name) {
		this.name = name;
	}
	
	public String getName() {
		return(name);
	}
}
