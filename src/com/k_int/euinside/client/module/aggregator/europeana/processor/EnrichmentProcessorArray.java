package com.k_int.euinside.client.module.aggregator.europeana.processor;

import java.util.ArrayList;
import java.util.List;

import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaEnrichments;

public class EnrichmentProcessorArray extends EnrichmentProcessor {
	private long recordsToFetch;
	private List<EuropeanaEnrichments> enrichments = new ArrayList<EuropeanaEnrichments>();
	
	public EnrichmentProcessorArray(long recordsToFetch) {
		this.recordsToFetch = recordsToFetch;
	}

	public List<EuropeanaEnrichments> getEnrichments() {
		return(enrichments);
	}

	@Override
	public void start(Long totalRecords, Long startPosition) {
		// Nothing to do here
	}

	@Override
	public boolean process(EuropeanaEnrichments enrichments) {
		// Just add the enrichments to the array
		this.enrichments.add(enrichments);
		return(this.enrichments.size() < recordsToFetch);
	}

	@Override
	public void completed() {
		// Nothing to do here
	}
}
