package com.k_int.euinside.client.module.aggregator;

import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.module.aggregator.cultureGrid.CultureGridStatistic;

/**
 * This class fetches statistics from a CultureGrid instance
 */
public class CultureGridStatistics extends BaseModule {

	/**
	 * Builds a Aggregator style path
	 * 
	 * @param action The action that is being performed against the aggregator
	 * @param provider The provider that the action is to be performed against
	 * @param collection The collection that the action is to be performed against
	 * @param aggregator The aggregator that the action is to be performed against
	 * 
	 * @return The path appropriate for these parameters
	 */
	private static String buildPath(Action action, String provider, String collection, String aggregator) {
		return(buildPath(Module.AGGREGATOR, PATH_SEPARATOR + aggregator + PATH_SEPARATOR + action.getName() + PATH_SEPARATOR + provider + PATH_SEPARATOR + collection));
	}

	/**
	 * Retrieves the statistics for the supplied provider and collection against the specified CultureGrid aggregator
	 * 
	 * @param provider The provider you are interested in statistics for
	 * @param collection The collection that you want statistics for
	 * @param aggregator The aggregator that we need to interrogate
	 * 
	 * @return The statistics for the supplied provider and collection from the specified aggregator
	 */
	static public CultureGridStatistic getStatistics(String provider, String collection, String aggregator) {
		return(ClientJSON.readJSON(buildPath(Action.AGGREGATOR_STATISTICS, provider, collection, aggregator), CultureGridStatistic.class));
	}

	/**
	 * Exercises all the methods with the passed in parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 *      <tr><td>-coreBaseURL</td><td>The base URL of the core module</td></tr>
	 *      <tr><td>-provider</td><td>The provider identifier we will retrieve information about</td></tr>
	 *      <tr><td>-set</td><td>The collection id we will retrieve information about</td></tr>
	 *      <tr><td>-aggregator</td><td>The CultureGrid aggregator we will retrieve the information from</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		String aggregator = arguments.getAggregator();
		String providerId = arguments.getProvider();
		String collectionId = arguments.getSet();
		
		if ((aggregator == null) || aggregator.isEmpty()) {
			System.out.println("No aggregator specified\n");
		} else if ((providerId == null) || providerId.isEmpty()) {
			System.out.println("No provider specified\n");
		} else if ((collectionId == null) || collectionId.isEmpty()) {
			System.out.println("No collection specified\n");
		} else {
			CultureGridStatistic result = getStatistics(providerId, collectionId, aggregator);
			if (result == null) {
				System.out.println("Failed to get statistics\n");
			} else {
				System.out.println("Result:\n");
				System.out.println(result.toString() + "\n");
				System.out.println("Generic Result:\n");
				System.out.println(result.convertToGeneric().toString() + "\n");
			}
		}
	}
}
