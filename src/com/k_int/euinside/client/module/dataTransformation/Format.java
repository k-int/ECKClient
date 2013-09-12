package com.k_int.euinside.client.module.dataTransformation;

public enum Format {
	EAD,
	EDM,
	LIDO,
	MARC;
	
	public static Format get(String value) {
    	if (value != null) {
	    	for (Format format : values()) {
	    	    if (format.toString().equalsIgnoreCase(value)){
	    	        return(format);
	    	    }
	    	}
    	}
    	return(null);
	}
}
