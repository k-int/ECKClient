package com.k_int.euinside.client.module.aggregator.europeana.processor;

import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaEnrichments;

/**
 * 
 * This class is a simple enrichment processor that outputs the enrichments to the console
 * @author Chas
 *
 */
public class EnrichmentProcessorConsole extends EnrichmentProcessor {

	@Override
	public boolean process(EuropeanaEnrichments enrichments) {
		System.out.println("Enrichments:\n" + enrichments.toString() + "\n");
		System.out.println("xml:\n" + enrichments.toXML() + "\n");
		return(true);
	}

	@Override
	public void start(Long totalRecords, Long startPosition) {
		System.out.println("Processing to start on " + totalRecords.toString() + " records, starting at record " + startPosition.toString());
	}

	@Override
	public void completed() {
		System.out.println("Processing completed");
	}
}
