package com.k_int.euinside.client.module.setmanager.statistics;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.json.baseJSON;
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaDataSet;
import com.k_int.euinside.client.module.aggregator.generic.GenericEuropeanaStatistic;
import com.k_int.euinside.client.module.aggregator.generic.GenericStatistic;

public class Statistic extends baseJSON {
	private static Log log = LogFactory.getLog(Statistic.class);
	private String providerCode;
	private String collectionCode;
	private String description;
	private Long accepted;
	private Long pending;
	private Long rejected;
	private Long total;
	private EuropeanaDataSet europeanaMostRecent;
	
	@Override
	protected Log getLogger() {
		return(log);
	}

	public static Log getLog() {
		return log;
	}

	public static void setLog(Log log) {
		Statistic.log = log;
	}

	public String getProviderCode() {
		return(providerCode);
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getCollectionCode() {
		return(collectionCode);
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	public String getDescription() {
		return(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAccepted() {
		return(accepted);
	}

	public void setAccepted(Long accepted) {
		this.accepted = accepted;
	}

	public Long getPending() {
		return(pending);
	}

	public void setPending(Long pending) {
		this.pending = pending;
	}

	public Long getRejected() {
		return(rejected);
	}

	public void setRejected(Long rejected) {
		this.rejected = rejected;
	}

	public Long getTotal() {
		return(total);
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public EuropeanaDataSet getEuropeanaMostRecent() {
		return(europeanaMostRecent);
	}

	public void setEuropeanaMostRecent(EuropeanaDataSet europeanaMostRecent) {
		this.europeanaMostRecent = europeanaMostRecent;
	}
	
	/**
	 * Converts this instance into a generic statistics result
	 * 
	 * @return A generic statistics object that represents this data
	 */
	public GenericStatistic convertToGeneric() {
		GenericStatistic result = new GenericStatistic();
		result.setAccepted(getAccepted());
		result.setCollectionCode(getCollectionCode());
		result.setDescription(getDescription());
		result.setPending(getPending());
		result.setProviderCode(getProviderCode());
		result.setRejected(getRejected());
		result.setTotal(getTotal());
		if (europeanaMostRecent != null) {
			ArrayList<GenericEuropeanaStatistic> europeanaStatistics = new ArrayList<GenericEuropeanaStatistic>();
			result.setEuropeanaDataSets(europeanaStatistics);
			europeanaStatistics.add(europeanaMostRecent.convertToGenericEuropeana());
		}
		return(result);
	}
}
