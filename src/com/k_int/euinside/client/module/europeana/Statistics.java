package com.k_int.euinside.client.module.europeana;

import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;

/**
 * This class performs the following against the europeana api
 * <ul>
 * <li>1. Fetches the list of Europenana providers</li>
 * <li>2. Fetches details about a specific provider</li>
 * <li>3. Fetches the datasets for a provider</li>
 * <li>4. Fetches details about a dataset</li>
 * </ul>
 */
public class Statistics extends BaseModule {
	private static String JSON_POSTFIX = ".json";

	/**
	 * Builds a Europeana style path
	 * 
	 * @param action The eueopeana action that is being performed
	 * 
	 * @return The path appropriate for these parameters
	 */
	private static String buildPath(Action action) {
		return(buildPath(action, null));		
	}
	
	/**
	 * Builds a Europeana style path
	 * 
	 * @param action The eueopeana action that is being performed
	 * @param identifier The identifier for this action
	 * 
	 * @return The path appropriate for these parameters
	 */
	private static String buildPath(Action action, String identifier) {
		return(buildPath(Module.EUROPEANA, PATH_SEPARATOR + action.getName() + ((identifier == null) ? "" : (PATH_SEPARATOR + identifier)) + JSON_POSTFIX));		
	}
	
	/** 
	 * Generates the europeana list of providers
	 * 
	 * @return An array of the europeana providers
	 */
	static public Providers getProviders() {
		return(ClientJSON.readJSON(buildPath(Action.EUROPEANA_PROVIDERS), Providers.class));
	}

	/** 
	 * Provides details about the specified provider
	 * 
	 * @param providerId The identifier for the provider
	 * 
	 * @return The details for the specified provider 
	 */
	static public ProviderDetail getProvider(String providerId) {
		return(ClientJSON.readJSON(buildPath(Action.EUROPEANA_PROVIDERS, providerId), ProviderDetail.class));
	}

	/** 
	 * Provides details about the data sets for a provider
	 * 
	 * @param providerId The identifier for the provider
	 * 
	 * @return The details of the data sets for the specified provider 
	 */
	static public ProviderDataSets getProviderDataSets(String providerId) {
		return(ClientJSON.readJSON(buildPath(Action.EUROPEANA_DATASETS, providerId), ProviderDataSets.class));
	}

	/** 
	 * Provides details about the specified data set
	 * 
	 * @param dataSetId The identifier for the data set
	 * 
	 * @return The details for the specified data set 
	 */
	static public DataSet getDataSet(String dataSetId) {
		return(ClientJSON.readJSON(buildPath(Action.EUROPEANA_DATASETS, dataSetId), DataSet.class));
	}

	/** 
	 * Retrieves the most recent data set for a provider
	 * 
	 * @param providerId The identifier for the provider
	 * 
	 * @return The details for the most recent data set for the provider
	 */
	static public DataSet getMostRecentDataSet(String providerId) {
		DataSet dataSet = null;
		ProviderDataSets providerDataSets = getProviderDataSets(providerId);
		if ((providerDataSets != null) && !providerDataSets.isEmpty()) {
			dataSet = getDataSet(providerDataSets.get(providerDataSets.size() - 1).getIdentifier());
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
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		String providerId = arguments.getProvider();
		System.out.println("Using provider id: \"" + arguments.getProvider() + "\"");

		Providers providers = getProviders();
		if (providers == null) {
			System.out.println("Failed to get providers\n");
		} else {
			System.out.println("Result from getProviders:\n");
			System.out.println(providers.toString() + "\n");
		}

		if ((providerId != null) && !providerId.isEmpty()) {
			System.out.println("Using provider id: \"" + providerId + "\"");

			ProviderDetail providerDetail = getProvider(providerId);
			if (providerDetail == null) {
				System.out.println("Failed to get provider detail\n");
			} else {
				System.out.println("Result from getProvider:\n");
				System.out.println(providerDetail.toString() + "\n");
			}

			ProviderDataSets providerDataSets = getProviderDataSets(providerId);
			if (providerDataSets == null) {
				System.out.println("Failed to get provider data sets\n");
			} else {
				System.out.println("Result from getProviderDataSets:\n");
				System.out.println(providerDataSets.toString() + "\n");

				if (!providerDataSets.isEmpty()) {
					DataSet providerDataSet = getDataSet(providerDataSets.get(0).getFullIdentifier());
					if (providerDataSet == null) {
						System.out.println("Failed to get provider data set\n");
					} else {
						System.out.println("Result from getDataSet:\n");
						System.out.println(providerDataSet.toString() + "\n");
					}
					
					DataSet mostRecentDataSet = getMostRecentDataSet(providerId);
					if (mostRecentDataSet == null) {
						System.out.println("Failed to get most recent data set\n");
					} else {
						System.out.println("Result from getMostRecentDataSet:\n");
						System.out.println(mostRecentDataSet.toString() + "\n");
					}
				}
			}
		}
	}
}
