package com.k_int.euinside.client.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import org.apache.commons.logging.Log;

public abstract class baseJSON {

	abstract protected Log getLogger();
	
	@JsonAnySetter
	public void setUnknownField(String fieldName, Object value) {
		getLogger().info("JSON, field ignored: \"" + fieldName + "\", value: \"" + value.toString() + "\"");
	}
	
}
