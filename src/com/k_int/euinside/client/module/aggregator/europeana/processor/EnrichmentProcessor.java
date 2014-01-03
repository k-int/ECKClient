package com.k_int.euinside.client.module.aggregator.europeana.processor;

import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaEnrichments;

/**
 * This abstract class provides an interface for clients to process the enrichment of records
 *
 */
public abstract class EnrichmentProcessor {
	/**
	 * Notifies the processor we are about to start processing the records
	 * 
	 * @param totalRecords The total number of records to be processed
	 * @param startPostion The position in the result set at which we will start processing the records
	 */
	abstract public void start(Long totalRecords, Long startPosition);
	
	/**
	 * Allows processing of the enrichments that have been found
	 * 
	 * @param enrichments The enrichments added by europeana
	 * 
	 * @return true if we should carry on processing records
	 */
	abstract public boolean process(EuropeanaEnrichments enrichments);
	
	/**
	 * Notifies the processor that we have completed processing the records
	 * 
	 */
	abstract public void completed();
}
