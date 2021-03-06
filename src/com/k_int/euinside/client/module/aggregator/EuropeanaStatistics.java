package com.k_int.euinside.client.module.aggregator;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.Attribute;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaDataSet;
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaDataSetResult;
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaProvider;
import com.k_int.euinside.client.module.aggregator.europeana.EuropeanaProviderResult;
import com.k_int.euinside.client.module.setmanager.SetManager;

/**
 * This class performs the following against the europeana api
 * <ul>
 * <li>1. Fetches the list of Europenana providers</li>
 * <li>2. Fetches details about a specific provider</li>
 * <li>3. Fetches the datasets for a provider</li>
 * <li>4. Fetches details about a dataset</li>
 * </ul>
 */
public class EuropeanaStatistics extends BaseModule {

	/**
	 * Builds a Europeana style path
	 * 
	 * @param action The eueopeana action that is being performed
	 * @param wskey The wskey that identifies the caller to europeana
	 * 
	 * @return The path appropriate for these parameters
	 */
	private static String buildPath(Action action, String wskey) {
		return(buildPath(action, wskey, null, null));		
	}
	
	/**
	 * Builds a Europeana style path
	 * 
	 * @param action The eueopeana action that is being performed
	 * @param wskey The wskey that identifies the caller to europeana
	 * @param identifier Either the datset or provider id that you are interested in or null if interested in all records
	 * @param secondaryAction If not null a secondary action needs to be performed
	 * 
	 * @return The path appropriate for these parameters
	 */
	private static String buildPath(Action action, String wskey, String identifier, Action secondaryAction) {
		String path = PATH_SEPARATOR + action.getName();
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		attributes.add(new BasicNameValuePair(Attribute.WSKEY.getName(), wskey));
		if (identifier != null) {
			path += PATH_SEPARATOR + identifier;
			if (secondaryAction != null) {
				path += PATH_SEPARATOR + secondaryAction.getName();
			}
		}
		return(buildPath(Module.EUROPEANA, path, attributes));
	}
	
	/** 
	 * Generates the europeana list of providers
	 * 
	 * @param wskey The wskey that identifies the caller to europeana
	 * 
	 * @return An array of the europeana providers
	 */
	static public EuropeanaProviderResult getProviders(String wskey) {
		return(ClientJSON.readJSON(buildPath(Action.EUROPEANA_PROVIDERS, wskey), EuropeanaProviderResult.class));
	}

	/** 
	 * Provides details about the specified provider
	 * 
	 * @param wskey The wskey that identifies the caller to europeana
	 * @param providerId The identifier for the provider
	 * 
	 * @return The details for the specified provider 
	 */
	static public EuropeanaProviderResult getProvider(String wskey, String providerId) {
		return(ClientJSON.readJSON(buildPath(Action.EUROPEANA_PROVIDER, wskey, providerId, null), EuropeanaProviderResult.class));
	}

	/** 
	 * Provides details about the data sets for a provider
	 * 
	 * @param wskey The wskey that identifies the caller to europeana
	 * @param providerId The identifier for the provider
	 * 
	 * @return The details of the data sets for the specified provider 
	 */
	static public EuropeanaDataSetResult getProviderDataSets(String wskey, String providerId) {
		return(ClientJSON.readJSON(buildPath(Action.EUROPEANA_PROVIDER, wskey, providerId, Action.EUROPEANA_DATASETS), EuropeanaDataSetResult.class));
	}

	/** 
	 * Provides details about the specified data set
	 * 
	 * @param wskey The wskey that identifies the caller to europeana
	 * @param dataSetId The identifier for the data set
	 * 
	 * @return The details for the specified data set 
	 */
	static public EuropeanaDataSetResult getDataSet(String wskey, String dataSetId) {
		return(ClientJSON.readJSON(buildPath(Action.EUROPEANA_DATASET, wskey, dataSetId, null), EuropeanaDataSetResult.class));
	}

	/** 
	 * Retrieves the most recent data set for a provider
	 * 
	 * @param wskey The wskey that identifies the caller to europeana
	 * @param providerId The identifier for the provider
	 * 
	 * @return The details for the most recent data set for the provider
	 */
	static public EuropeanaDataSet getMostRecentDataSet(String wskey, String providerId) {
		EuropeanaDataSet dataSet = null;
		EuropeanaDataSetResult providerDataSets = getProviderDataSets(wskey, providerId);
		if (providerDataSets != null) {
			ArrayList<EuropeanaDataSet> items = providerDataSets.getItems();
			if ((items != null) && !items.isEmpty()) {
				dataSet = items.get(items.size() - 1);
			}
		}
		return(dataSet);
	}

	/**
	 * Exercises all the methods with the passed in parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 *      <tr><td>-coreBaseURL</td><td>The base URL of the core module</td></tr>
	 *      <tr><td>-provider</td><td>The provider id we will retrieve information about</td></tr>
	 *      <tr><td>-set</td><td>The set id we will retrieve information about</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		String wskey = arguments.getWskey();
		String providerId = arguments.getProvider();
		String setId = arguments.getSet();
		
		if ((wskey == null) || wskey.isEmpty()) {
			System.out.println("No wskey specified\n");
		} else {
			EuropeanaProviderResult providers = getProviders(wskey);
			if (providers == null) {
				System.out.println("Failed to get providers\n");
			} else {
				System.out.println("Result from getProviders:\n");
				System.out.println(providers.toString() + "\n");
				if ((providerId == null) || providerId.isEmpty() || providerId.equals(SetManager.PROVIDER_DEFAULT)) {
					ArrayList<EuropeanaProvider> items = providers.getItems();
					if ((items != null) && !items.isEmpty()) {
						providerId = items.get(0).getIdentifier();
						
					}
				}
			}
	
			if ((providerId != null) && !providerId.isEmpty() && !providerId.equals(SetManager.PROVIDER_DEFAULT)) {
				System.out.println("Using provider id: \"" + providerId + "\"");
	
				providers = getProvider(wskey, providerId);
				if (providers == null) {
					System.out.println("Failed to get provider detail\n");
				} else {
					System.out.println("Result from getProvider:\n");
					System.out.println(providers.toString() + "\n");
				}
	
				EuropeanaDataSetResult providerDataSets = getProviderDataSets(wskey, providerId);
				if (providerDataSets == null) {
					System.out.println("Failed to get provider data sets\n");
				} else {
					System.out.println("Result from getProviderDataSets:\n");
					System.out.println(providerDataSets.toString() + "\n");
					if ((setId == null) || setId.isEmpty() || setId.equals(SetManager.SET_DEFAULT)) {
						ArrayList<EuropeanaDataSet> items = providerDataSets.getItems();
						if ((items != null) && !items.isEmpty()) {
							setId = items.get(0).getIdentifier();
						}
					}
	
					EuropeanaDataSet mostRecentDataSet = getMostRecentDataSet(wskey, providerId);
					if (mostRecentDataSet == null) {
						System.out.println("Failed to get most recent data set\n");
					} else {
						System.out.println("Result from getMostRecentDataSet:\n");
						System.out.println(mostRecentDataSet.toString() + "\n");
						System.out.println("Generic Result:\n");
						System.out.println(mostRecentDataSet.convertToGeneric().toString() + "\n");
					}
				}
			}
						
			if ((setId != null) && !setId.isEmpty() && !setId.equals(SetManager.SET_DEFAULT)) {
				System.out.println("Using set id: \"" + setId + "\"");
				EuropeanaDataSetResult dataSets = getDataSet(wskey, setId);
				if (dataSets == null) {
					System.out.println("Failed to get data set\n");
				} else {
					System.out.println("Result from getDataSet:\n");
					System.out.println(dataSets.toString() + "\n");
				}
			}
		}
	}
}
