package com.k_int.euinside.client.module.aggregator.cultureGrid;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.module.aggregator.StatisticBase;

public class CultureGridSearchResult extends StatisticBase {
	private static Log log = LogFactory.getLog(CultureGridSearchResult.class);
	private List<Long> identifiers;
	private String providerCode; 

	@Override
	protected Log getLogger() {
		return(log);
	}

	public List<Long> getIdentifiers() {
		return(identifiers);
	}

	public void setIdentifiers(List<Long> identifiers) {
		this.identifiers = identifiers;
	}

	public String getProviderCode() {
		return(providerCode);
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	/**
	 * Converts this class to the generic search result class
	 * 
	 * @return The generic search result class
	 */
	public CultureGridSearchResult convertToGeneric() {
		// for the time being we will treat this class as the generic search result class
		return(this);
	}
}
