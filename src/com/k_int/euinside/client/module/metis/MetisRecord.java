package com.k_int.euinside.client.module.metis;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="record")
public class MetisRecord {
	private String record;

	public String getRecord() {
		return this.record;
	}

	public void setRecord(String record) {
		this.record = record;
	}
}
