package com.k_int.euinside.client.module;


/**
 * Contains the list of aggregators that the ECKClient specifically knows about
 */
public enum Aggregator {
	CultureGridLive,
	CultureGridTest,
	DarkAggregator,
	Europeana,
	SetManager;
	
	public static Aggregator get(String value) {
    	if (value != null) {
	    	for (Aggregator aggregator : values()) {
	    	    if (aggregator.toString().equalsIgnoreCase(value)){
	    	        return(aggregator);
	    	    }
	    	}
    	}
    	return(null);
	}
}
