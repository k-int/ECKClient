package com.k_int.euinside.client.module.aggregator.europeana.processor;

import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaEnrichments;

/**
 * This abstract class provides an interface for clients to process the enrichment of records
 *
 */
public abstract class EnrichmentProcessor {
	/**
	 * Allows processing of the enrichments that have been found
	 * 
	 * @param enrichments The enrichments added by europeana
	 */
	abstract public void process(EuropeanaEnrichments enrichments); 
}
