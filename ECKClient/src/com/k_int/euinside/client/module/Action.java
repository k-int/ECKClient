package com.k_int.euinside.client.module;

public enum Action {
	// Actions used by the definition service
	DEFINITION_ERRORS("errors"),
	DEFINITION_LANGUAGES("languages"),
	DEFINITION_PROFILES("profiles"),
	
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

	// Actions used by validation
	VALIDATION_VALIDATE("validate");
	
	private String name;
	
	private Action(String name) {
		this.name = name;
	}

	public String getName() {
		return(name);
	}
}
