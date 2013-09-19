package com.k_int.euinside.client.module;

public enum Action {
	// Actions used by the data mapping service
	DATA_TRANSFORMATION_FETCH("fetch"),
	DATA_TRANSFORMATION_STATUS("status"),
	DATA_TRANSFORMATION_TRANSFORM("Transform"),
	
	// Actions used by the definition service
	DEFINITION_ERRORS("errors"),
	DEFINITION_LANGUAGES("languages"),
	DEFINITION_PROFILES("profiles"),

	// Actions used by Europeana
	EUROPEANA_DATASETS("datasets"),
	EUROPEANA_PROVIDERS("providers"),

	// Actions used by PID Generate
	PID_GENERATE_GENERATE("generate"),
	PID_GENERATE_LOOKUP("lookup"),

	// Actions used by the preview service
	PREVIEW_PREVIEW("preview"),
	
	// Actions Used by the Set Manager
	SET_MANAGER_COMMIT("commit"),
	SET_MANAGER_LIST("list"),
	SET_MANAGER_PREVIEW("preview"),
	SET_MANAGER_STATUS("status"),
	SET_MANAGER_UPDATE("update"),
	SET_MANAGER_VALIDATE("validate"),

	// Actions used by the Statistics module
	STATISTICS_QUERY("query"),
	STATISTICS_UPDATE("update"),
	
	// Actions used by validation
	VALIDATION_PROFILES("profiles"),
	VALIDATION_VALIDATE("validate");
	
	private String name;
	
	private Action(String name) {
		this.name = name;
	}

	public String getName() {
		return(name);
	}
}
