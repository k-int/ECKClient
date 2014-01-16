package com.k_int.euinside.client.module.aggregator.europeana;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EuropeanaRecordEnrichment extends EuropeanaRecord {
	private static Log log = LogFactory.getLog(EuropeanaRecord.class);

	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Converts this class to the generic enrichment class
	 * 
	 * @return The generic full record class
	 */
	public EuropeanaEnrichments convertToGeneric() {
		return(new EuropeanaEnrichments(getObject()));
	}
}
