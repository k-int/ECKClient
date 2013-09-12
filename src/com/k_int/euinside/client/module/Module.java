package com.k_int.euinside.client.module;

public enum Module {

	DATA_TRANSFORMATION("DataTransformation", "DataTransformation"),
	DEFINITION("Definition", "Definition"),
	PID_GENERATE("PIDGenerate", "PIDGeneration"),
	PREVIEW("Preview", "Preview"),
	SET_MANAGER("SetManager/Set", "SetManager"), // Note: The name is used as part of a url, so cannot contain spaces 
	STATISTICS("Statistics", "Statistics"),
	VALIDATION("Validation", "Validation-Monguz"),
	VALIDATION2("Validation2", "Validation-Semantika");

	private String rootPath;
	private String name;
	
	Module(String rootPath, String name) {
		this.rootPath = rootPath;
		this.name = name;
	}
	
	public String getRootPath() {
		return(rootPath);
	}
	
	public String getName() {
		return(name);
	}
}
