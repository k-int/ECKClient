package com.k_int.euinside.client.module.aggregator;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Attribute;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaEnrichments;
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaRecord;
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
	private static Log log = LogFactory.getLog(EuropeanaDataEnrichments.class);
	static private String europeanaAPIBaseURL = "http://europeana.eu/api/v2/";
	static private final String JSON_EXTENSION = ".json";
	static private final String EUROPEANA_API_RECORD_ACTION = "record";
	static private final String EUROPEANA_API_SEARCH_ACTION = "search" + JSON_EXTENSION;
	static private final Integer FETCH_SIZE = new Integer(96); 
	static private final String QUERY_ATTRIBUTE_COLLECTION_NAME = "europeana_collectionName";
	static private final String PROFILE_MINIMAL = "minimal";
	static private final String PROFILE_FULL = "full";

	private String wskey;
	private String tailRecordURL;

	/**
	 * Constructor that initialises the class, it is only expected to be substantiated from one of the static classes in here
	 * 
	 * @param wskey The api key that is to be used when accessing the europeana api
	 */
	private EuropeanaDataEnrichments(String wskey) {
		// We build up the tail of the record url here, so we do not have to do it for every record
		this.wskey = wskey;
		tailRecordURL = JSON_EXTENSION + QUERY_START + buildAttribute(Attribute.WSKEY, wskey, false) + buildAttribute(Attribute.PROFILE, PROFILE_FULL, true);
	}
	
	/**
	 * Set the base URL to use in the Europeana calls, we default to using the live service
	 * 
	 * @param baseURL The base url to use when accessing the Europeana api
	 */
	static public void setEuropeanaAPIBaseURL(String baseURL) {
		europeanaAPIBaseURL = baseURL;
	}

	/**
	 * Retrieves the base url to use when performing a search
	 *  
	 * @return The base url to be used when performing a search
	 */
	private String getEuropeanaAPIBaseSearchURL() {
		return(europeanaAPIBaseURL + EUROPEANA_API_SEARCH_ACTION);
	}

	/**
	 * Retries the base url to use when fetching a record
	 * 
	 * @return The base url to use when fecthing a record
	 */
	private String getEuropeanaAPIBaseRecordURL() {
		return(europeanaAPIBaseURL + EUROPEANA_API_RECORD_ACTION);
	}

	/**
	 * Fetches the enrichments for a single europeana record
	 * 
	 * @param europeanaIdentifier The identifier of the record
	 * 
	 */
	public EuropeanaEnrichments getEnrichments(String europeanaIdentifier) {
		EuropeanaEnrichments enrichments = null;
		String recordURL = getEuropeanaAPIBaseRecordURL() + europeanaIdentifier + tailRecordURL;
		EuropeanaRecord fullRecord = ClientJSON.readJSONfullpath(recordURL, EuropeanaRecord.class);
		if (fullRecord != null) {
			enrichments = new EuropeanaEnrichments(fullRecord.getObject());
		}
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
	 * 
	 */
	private void getEnrichments(String collectionName, EnrichmentProcessor enrichmentProcessor) {
		Integer startPosition = new Integer(1);
		boolean moreRecords = true;
		String searchURL;
		
		// Build the baseurl that does not change for the duration of the search
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		attributes.add(new BasicNameValuePair(Attribute.WSKEY.getName(), wskey));
		attributes.add(new BasicNameValuePair(Attribute.QUERY.getName(), QUERY_ATTRIBUTE_COLLECTION_NAME + ":\"" + collectionName + "\""));
		attributes.add(new BasicNameValuePair(Attribute.ROWS.getName(), FETCH_SIZE.toString()));
		attributes.add(new BasicNameValuePair(Attribute.PROFILE.getName(), PROFILE_MINIMAL));
		String baseSearchURL = buildURL(getEuropeanaAPIBaseSearchURL(), attributes);
		
		while (moreRecords) {
			// generate the url 
			searchURL = baseSearchURL + buildAttribute(Attribute.START, startPosition.toString(), true);

			EuropeanaSearchResult searchResults = ClientJSON.readJSONfullpath(searchURL, EuropeanaSearchResult.class);
			if (searchResults.isSuccess() && (searchResults.getItemsCount() > 0)) {
				// We have some hits to deal with, so loop through all the items
				for (EuropeanaSearchItem item : searchResults.getItems()) {
					EuropeanaEnrichments enrichments = getEnrichments(item.getId());
					if (enrichments != null) {
						enrichmentProcessor.process(enrichments);
					}
				}
				
				// increment the start position, so we do not get the same records back
				startPosition += FETCH_SIZE;
			} else {
				// No point continuing as either we have come to the end of the result set or there is an error
				moreRecords = false;
			}
		}
	}

	/**
	 * Fetches the enrichments for an entire europeana collection
	 * 
	 * @param wskey The api key that is to be used when accessing the europeana api
	 * @param collectionName The name of the collection as known by europeana
	 * @param enrichmentProcessor The processor to be called when we have determined the enrichments
	 * 
	 */
	static public void getEnrichments(String wskey, String collectionName, EnrichmentProcessor enrichmentProcessor) {
		(new EuropeanaDataEnrichments(wskey)).getEnrichments(collectionName, enrichmentProcessor);
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
				getEnrichments(wskey, collection, new EnrichmentProcessorConsole());
			}
		}
	}
}
