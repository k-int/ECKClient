package com.k_int.euinside.client.module.aggregator;

import java.util.ArrayList;

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
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaEnrichments;
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaSearchItem;
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaSearchResult;
import com.k_int.euinside.client.module.aggregator.europeana.processor.EnrichmentProcessor;
import com.k_int.euinside.client.module.aggregator.europeana.processor.EnrichmentProcessorConsole;
import com.k_int.euinside.client.module.setmanager.SetManager;

/**
 * This class retrieves the enrichments for a europeana collection
 * These calls do not go via the core at the moment, this will be looked at in due course
 * This is performed by performing a search against the collection name
 * and then fecting the full details for each record in the result set.
 * The enhancements and identifiers are extracted from the full record and passed to the processor instance.
 */
public class EuropeanaDataEnrichments extends BaseModule {
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(EuropeanaDataEnrichments.class);
	private static final int MAXIMUM_FETCH_SIZE = 50;
	private String wskey;

	/**
	 * Constructor that initialises the class, it is only expected to be substantiated from one of the static classes in here
	 * 
	 * @param wskey The api key that is to be used when accessing the europeana api
	 */
	private EuropeanaDataEnrichments(String wskey) {
		this.wskey = wskey;
	}
	
	/**
	 * Fetches the enrichments for a single europeana record
	 * 
	 * @param europeanaIdentifier The identifier of the record
	 * 
	 */
	private EuropeanaEnrichments getEnrichments(String europeanaIdentifier) {
		EuropeanaEnrichments enrichments = null;
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		attributes.add(new BasicNameValuePair(Attribute.WSKEY.getName(), wskey));
		String recordURL = buildPath(Module.AGGREGATOR, PATH_SEPARATOR + Aggregator.Europeana + PATH_SEPARATOR + Action.AGGREGATOR_ENRICHMENT_RECORD.getName() + europeanaIdentifier, attributes); 
		enrichments = ClientJSON.readJSON(recordURL, EuropeanaEnrichments.class);
		return(enrichments);
	}
	
	/**
	 * Fetches the enrichments for a single europeana record
	 * 
	 * @param wskey The api key that is to be used when accessing the europeana api
	 * @param europeanaIdentifier The identifier of the record
	 * 
	 */
	static public EuropeanaEnrichments getEnrichments(String wskey, String europeanaIdentifier) {
		return((new EuropeanaDataEnrichments(wskey)).getEnrichments(europeanaIdentifier));
	}
	
	/**
	 * Fetches the enrichments for an entire europeana collection
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
			attributes.add(new BasicNameValuePair(Attribute.WSKEY.getName(), wskey));
			searchURL = buildPath(Module.AGGREGATOR, PATH_SEPARATOR + Aggregator.Europeana + PATH_SEPARATOR + Action.AGGREGATOR_SEARCH.getName() + PATH_SEPARATOR + "*" + PATH_SEPARATOR + collectionName, attributes); 

			EuropeanaSearchResult searchResults = ClientJSON.readJSON(searchURL, EuropeanaSearchResult.class);
			if (searchResults.isSuccess() && (searchResults.getItemsCount() > 0)) {
				// Initialise the processor
				if (initialiseProcessor) {
					initialiseProcessor = false;
					enrichmentProcessor.start(searchResults.getTotalResults(), startPosition);
				}
				
				// We have some hits to deal with, so loop through all the items
				for (EuropeanaSearchItem item : searchResults.getItems()) {
					EuropeanaEnrichments enrichments = getEnrichments(item.getId());
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
				startPosition += searchResults.getItems().size();
			} else {
				// No point continuing as either we have come to the end of the result set or there is an error
				moreRecords = false;
			}
		}
		
		if (!initialiseProcessor) {
			enrichmentProcessor.completed();
		}
	}

	/**
	 * Fetches the enrichments for an entire europeana collection
	 * 
	 * @param wskey The api key that is to be used when accessing the europeana api
	 * @param collectionName The name of the collection as known by europeana
	 * @param enrichmentProcessor The processor to be called when we have determined the enrichments
	 * @param start Where to start processing records from in the result set
	 * 
	 */
	static public void getEnrichments(String wskey, String collectionName, EnrichmentProcessor enrichmentProcessor, long start) {
		(new EuropeanaDataEnrichments(wskey)).getEnrichments(collectionName, enrichmentProcessor, start);
	}
	
	/**
	 * Fetches the enrichments for an entire europeana collection
	 * 
	 * @param wskey The api key that is to be used when accessing the europeana api
	 * @param collectionName The name of the collection as known by europeana
	 * @param enrichmentProcessor The processor to be called when we have determined the enrichments
	 * @param start Where to start processing records from in the result set
	 * 
	 */
	static public void getEnrichments(String wskey, String collectionName, EnrichmentProcessor enrichmentProcessor, long start, int rows) {
		(new EuropeanaDataEnrichments(wskey)).getEnrichments(collectionName, enrichmentProcessor, start, rows);
	}
	
	/**
	 * Exercises all the methods with the passed in parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 *      <tr><td>-recordId</td><td>The europeana identifier we want to fetch the enrichments for</td></tr>
	 *      <tr><td>-set</td><td>The collection we want to fetch the enrichments for</td></tr>
	 *      <tr><td>-wskey</td><td>The api key we use to access the europeana data</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		String wskey = arguments.getWskey();
		String collection = arguments.getSet();
		String europeanaIdentifier = arguments.getRecordId();
		Integer offset = arguments.getOffset();
		
		if ((wskey == null) || wskey.isEmpty()) {
			System.out.println("No wskey specified");
		} else {
			if ((europeanaIdentifier == null) || europeanaIdentifier.isEmpty()) {
				System.out.println("Skipping fetching single record as no recordId specified");
			} else {
				EuropeanaEnrichments enrichments = getEnrichments(wskey, europeanaIdentifier);
				if (enrichments == null) {
					System.out.println("Error obtaining enrichments");
				} else {
					System.out.println("Enrichments for \"" + europeanaIdentifier + "\": \n" + enrichments.toString());
				}
			}

			if ((collection == null) || collection.isEmpty() || collection.equals(SetManager.SET_DEFAULT)) {
				System.out.println("Skipping fetching records for a collection as not set specified");
			} else {
				getEnrichments(wskey, collection, new EnrichmentProcessorConsole(), ((offset == null) ? 1 : offset.longValue()));
			}
		}
	}
}
