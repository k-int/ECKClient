package com.k_int.euinside.client.module.aggregator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.Aggregator;
import com.k_int.euinside.client.module.Attribute;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.module.aggregator.cultureGrid.CultureGridSearchResult;
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaEnrichments;
import com.k_int.euinside.client.module.aggregator.europeana.processor.EnrichmentProcessor;
import com.k_int.euinside.client.module.aggregator.europeana.processor.EnrichmentProcessorConsole;

/**
 * This class retrieves the enrichments for a europeana collection, stored within culturegrid
 */
public class CultureGridDataEnrichments extends BaseModule {
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(CultureGridDataEnrichments.class);
	private static final int MAXIMUM_FETCH_SIZE = 50;
	private Aggregator aggregator;

	/**
	 * Constructor that initialises the class, it is only expected to be substantiated from one of the static classes in here
	 * 
	 * @param aggregator The aggregator to search
	 */
	private CultureGridDataEnrichments(Aggregator aggregator) {
		this.aggregator = aggregator;
	}
	
	/**
	 * Fetches the enrichments for a single record
	 *
	 * @param provider The provider identifier
	 * @param lidoRecID The lido identifier of the record
	 * 
	 */
	private EuropeanaEnrichments getEnrichments(String provider, String resourceId, String lidoRecID) {
		EuropeanaEnrichments enrichments = null;
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		if (lidoRecID != null) {
			attributes.add(new BasicNameValuePair(Attribute.LIDO_REC_ID.getName(), lidoRecID));
		}
		String recordURL = buildPath(Module.AGGREGATOR, PATH_SEPARATOR + aggregator.toString() + PATH_SEPARATOR + Action.AGGREGATOR_ENRICHMENT_RECORD.getName() + PATH_SEPARATOR + provider + PATH_SEPARATOR + ((resourceId == null) ? "*" : resourceId), attributes); 
		enrichments = ClientJSON.readJSON(recordURL, EuropeanaEnrichments.class);
		return(enrichments);
	}
	
	/**
	 * Fetches the enrichments for a single record
	 * 
	 * @param aggregator The aggregator to search
	 * @param provider The provider identifier
	 * @param lidoRecID The lido identifier of the record
	 * 
	 */
	static public EuropeanaEnrichments getEnrichments(Aggregator aggregator, String provider, String lidoRecID) {
		return((new CultureGridDataEnrichments(aggregator)).getEnrichments(provider, null, lidoRecID));
	}
	
	/**
	 * Fetches the enrichments for an entire collection
	 * 
	 * @param collectionName The name of the collection as known by europeana
	 * @param enrichmentProcessor The processor to be called when we have determined the enrichments
	 * @param start Where to start processing records from in the result set
	 * 
	 */
	private void getEnrichments(String collectionName, EnrichmentProcessor enrichmentProcessor, long start) {
		getEnrichments(collectionName, enrichmentProcessor, start, MAXIMUM_FETCH_SIZE);
	}
	
	/**
	 * Fetches the enrichments for an entire europeana collection
	 * 
	 * @param collectionName The name of the collection as known by europeana
	 * @param enrichmentProcessor The processor to be called when we have determined the enrichments
	 * @param start Where to start processing records from in the result set
	 * @param rows The number of records to fetch at a time
	 * 
	 */
	private void getEnrichments(String collectionName, EnrichmentProcessor enrichmentProcessor, long start, int rows) {
		boolean initialiseProcessor = true;
		Long startPosition = new Long((start < 1) ? 1 : start);
		boolean moreRecords = true;
		ArrayList<BasicNameValuePair> attributes;
		String searchURL;
		
		while (moreRecords) {
			// generate the url 
			attributes = new ArrayList<BasicNameValuePair>();
			attributes.add(new BasicNameValuePair(Attribute.ROWS.getName(), Integer.toString(rows)));
			attributes.add(new BasicNameValuePair(Attribute.START.getName(), startPosition.toString()));
			searchURL = buildPath(Module.AGGREGATOR, PATH_SEPARATOR + aggregator.toString() + PATH_SEPARATOR + Action.AGGREGATOR_SEARCH.getName() + PATH_SEPARATOR + "*" + PATH_SEPARATOR + collectionName, attributes); 

			CultureGridSearchResult searchResults = ClientJSON.readJSON(searchURL, CultureGridSearchResult.class);
			if (searchResults == null) {
				// Have an error of some sort
				moreRecords = false;
			} else {
				List<Long> identifiers = searchResults.getIdentifiers();
				if ((identifiers != null) && !identifiers.isEmpty()) {
					// Initialise the processor
					if (initialiseProcessor) {
						initialiseProcessor = false;
						enrichmentProcessor.start(new Long(identifiers.size()), startPosition);
					}
					
					// We have some hits to deal with, so loop through all the items
					for (Long identifier : identifiers) {
						EuropeanaEnrichments enrichments = getEnrichments(searchResults.getProviderCode(), identifier.toString(), null);
						if (enrichments != null) {
							// If they want us to continue they will return true
							if (!enrichmentProcessor.process(enrichments)) {
								// They do not want to continue processing, so we need to set the flag and jump out of the loop
								moreRecords = false;
								break;
							}
						}
					}
					
					// increment the start position, so we do not get the same records back
					startPosition += identifiers.size();
				} else {
					// No point continuing as either we have come to the end of the result set or there is an error
					moreRecords = false;
				}
			}
		}
		
		if (!initialiseProcessor) {
			enrichmentProcessor.completed();
		}
	}

	/**
	 * Fetches the enrichments for an entire europeana collection
	 * 
	 * @param aggregator The aggregator to search
	 * @param collectionName The name of the collection as known by europeana
	 * @param enrichmentProcessor The processor to be called when we have determined the enrichments
	 * @param start Where to start processing records from in the result set
	 * 
	 */
	static public void getEnrichments(Aggregator aggregator, String collectionName, EnrichmentProcessor enrichmentProcessor, long start) {
		(new CultureGridDataEnrichments(aggregator)).getEnrichments(collectionName, enrichmentProcessor, start);
	}
	
	/**
	 * Fetches the enrichments for an entire europeana collection
	 * 
	 * @param aggregator The aggregator to search
	 * @param provider The code for the provider of the data
	 * @param collectionName The name of the collection as known by europeana
	 * @param enrichmentProcessor The processor to be called when we have determined the enrichments
	 * @param start Where to start processing records from in the result set
	 * 
	 */
	static public void getEnrichments(Aggregator aggregator, String collectionName, EnrichmentProcessor enrichmentProcessor, long start, int rows) {
		(new CultureGridDataEnrichments(aggregator)).getEnrichments(collectionName, enrichmentProcessor, start, rows);
	}
	
	/**
	 * Exercises all the methods with the passed in parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 *      <tr><td>-aggregator</td><td>The culture grid aggregator we want to fetch the enrichments from</td></tr>
	 *      <tr><td>-provider</td><td>The provider code that the record belongs to</td></tr>
	 *      <tr><td>-recordId</td><td>The lidoRecId we want to fetch the enrichments for</td></tr>
	 *      <tr><td>-set</td><td>The collection we want to fetch the enrichments for</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		Aggregator aggregator = Aggregator.get(arguments.getAggregator());
		String collection = arguments.getSet();
		String lidoRecID = arguments.getRecordId();
		String provider = arguments.getProvider();
		Integer offset = arguments.getOffset();
		
		if (aggregator == null) {
			System.out.println("No aggregator specified");
		} else {
			if (((lidoRecID == null) || lidoRecID.isEmpty()) ||
			    ((provider == null) || provider.isEmpty())) {
				System.out.println("Skipping fetching single record as either provider or recordId is not specified");
			} else {
				EuropeanaEnrichments enrichments = getEnrichments(aggregator, provider, lidoRecID);
				if (enrichments == null) {
					System.out.println("Error obtaining enrichments");
				} else {
					System.out.println("Enrichments for \"" + lidoRecID + "\": \n" + enrichments.toString());
				}
			}

			if ((collection == null) || collection.isEmpty()) {
				System.out.println("Skipping fetching records for a collection as the collection is not specified");
			} else {
				getEnrichments(aggregator, collection, new EnrichmentProcessorConsole(), ((offset == null) ? 1 : offset.longValue()));
			}
		}
	}
}
