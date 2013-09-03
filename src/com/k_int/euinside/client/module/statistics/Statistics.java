package com.k_int.euinside.client.module.statistics;

import java.util.ArrayList;
import java.util.Date;

import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.Attribute;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.module.statistics.query.StatisticItem;
import com.k_int.euinside.client.module.statistics.query.StatisticItems;
import com.k_int.euinside.client.module.statistics.query.StatusItem;
import com.k_int.euinside.client.module.statistics.query.StatusItems;

public class Statistics extends BaseModule {

	static private final String QUERY_TYPE_ITEMS         = "items";
	static private final String QUERY_TYPE_SLOWEST       = "slowest";
	static private final String QUERY_TYPE_STATUS        = "status";
	static private final String QUERY_TYPE_STATUS_BY_DAY = "statusByDay";
	
	static private void addAttributeIfSet(ArrayList<BasicNameValuePair>attributes, Attribute attribute, Object value) {
		if (value != null) {
			attributes.add(new BasicNameValuePair(attribute.getName(), value.toString()));
		}
	}
	
	/**
	 * Generates the path and query string required to perform a query against the statistics
	 * 
	 * @param moduleName The name of the module we want statistics for
	 * @param group The group within the module
	 * @param queryType The type of query to be performed
	 * @param days The number of days the query is to cover
	 * 
	 * @return The path and query string required to perform the action
	 */
	static private String buildQueryPath(String moduleName, String group, String queryType, Integer days) {
		return(buildQueryPath(moduleName, group, queryType, days, null, null, null));
	}

	/**
	 * Generates the path and query string required to perform a query against the statistics
	 * 
	 * @param moduleName The name of the module we want statistics for
	 * @param group The group within the module
	 * @param queryType The type of query to be performed
	 * @param duration The minimum duration for a sindle item
	 * @param limit The maximum number of results to return in one fetch
	 * @param offset where in the result set should results be returned from
	 * 
	 * @return The path and query string required to perform the action
	 */
	static private String buildQueryPath(String moduleName, String group, String queryType, Integer duration, Integer limit, Integer offset) {
		return(buildQueryPath(moduleName, group, queryType, null, duration, limit, offset));
	}

	/**
	 * Generates the path and query string required to perform a query against the statistics
	 * 
	 * @param moduleName The name of the module we want statistics for
	 * @param group The group within the module
	 * @param queryType The type of query to be performed
	 * @param days The number of days the query is to cover
	 * @param duration The minimum duration for a sindle item
	 * @param limit The maximum number of results to return in one fetch
	 * @param offset where in the result set should results be returned from
	 * 
	 * @return The path and query string required to perform the action
	 */
	static private String buildQueryPath(String moduleName, String group, String queryType, Integer days, Integer duration, Integer limit, Integer offset) { 
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		addAttributeIfSet(attributes, Attribute.DAYS, days);
		addAttributeIfSet(attributes, Attribute.DURATION, duration);
		addAttributeIfSet(attributes, Attribute.LIMIT, limit);
		addAttributeIfSet(attributes, Attribute.OFFSET, offset);
		String modulePath = PATH_SEPARATOR + moduleName + PATH_SEPARATOR + group + PATH_SEPARATOR + Action.STATISTICS_QUERY.getName() + PATH_SEPARATOR + queryType;
		return(buildPath(Module.STATISTICS, modulePath, attributes));
	}

	/**
	 * Generates the path and query string required to perform an update against the statistics
	 * 
	 * @param moduleName The name of the module we want statistics for
	 * @param group The group within the module
	 * @param dateTime When the processing first started
	 * @param duration The time in milliseconds that the processing took
	 * @param itemsProcessed The number of items that were processed
	 * @param numberFailed The number of items that failed during processing
	 * @param numberSuccessful The number of items that were successfully processed
	 * 
	 * @return The path and query string required to perform the action
	 */
	static private String buildUpdatePath(String moduleName, String group, Date dateTime, Long duration, Integer itemsProcessed, Integer numberFailed, Integer numberSuccessful) { 
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		if (dateTime != null) {
			addAttributeIfSet(attributes, Attribute.DATE_TIME, StatisticItem.expectedDateTimeFormat.format(dateTime));
		}
		addAttributeIfSet(attributes, Attribute.DURATION, duration);
		addAttributeIfSet(attributes, Attribute.ITEMS_PROCESSED, itemsProcessed);
		addAttributeIfSet(attributes, Attribute.NUMBER_FAILED, numberFailed);
		addAttributeIfSet(attributes, Attribute.NUMBER_SUCCESSFUL, numberSuccessful);
		String modulePath = PATH_SEPARATOR + moduleName + PATH_SEPARATOR + group + PATH_SEPARATOR + Action.STATISTICS_UPDATE.getName();
		return(buildPath(Module.STATISTICS, modulePath, attributes));
	}

	/**
	 * Performs the items query for the given parameters
	 * 
	 * @param moduleName The name of the module we want statistics for
	 * @param group The group within the module
	 * @param duration The minimum duration for a sindle item
	 * @param limit The maximum number of results to return in one fetch
	 * @param offset where in the result set should results be returned from
	 * 
	 * @return The array of items that meet this criteria
	 */
	static public StatisticItems queryItems(String moduleName, String group, Integer duration, Integer limit, Integer offset) {
		StatisticItems result = null;
		String path = buildQueryPath(moduleName, group, QUERY_TYPE_ITEMS, duration, limit, offset);

		result = ClientJSON.readJSON(path, StatisticItems.class);
		return(result);
	}

	/**
	 * Performs the slowest items query for the given parameters
	 * 
	 * @param moduleName The name of the module we want statistics for
	 * @param group The group within the module
	 * @param duration The minimum duration for a sindle item
	 * @param limit The maximum number of results to return in one fetch
	 * @param offset where in the result set should results be returned from
	 * 
	 * @return The array of items that meet this criteria
	 */
	static public StatisticItems querySlowest(String moduleName, String group, Integer duration, Integer limit, Integer offset) {
		StatisticItems result = null;
		String path = buildQueryPath(moduleName, group, QUERY_TYPE_SLOWEST, duration, limit, offset);

		result = ClientJSON.readJSON(path, StatisticItems.class);
		return(result);
	}

	/**
	 * Performs the status query for the given parameters
	 * 
	 * @param moduleName The name of the module we want statistics for
	 * @param group The group within the module
	 * @param days The number of days you want the query to run over
	 * 
	 * @return The status statistics for this module and group
	 */
	static public StatusItem queryStatus(String moduleName, String group, Integer days) {
		StatusItem result = null;
		String path = buildQueryPath(moduleName, group, QUERY_TYPE_STATUS, days);

		result = ClientJSON.readJSON(path, StatusItem.class);
		return(result);
	}

	/**
	 * Performs the status by day query for the given parameters
	 * 
	 * @param moduleName The name of the module we want statistics for
	 * @param group The group within the module
	 * @param days The number of days you want the query to run over
	 * 
	 * @return The array of status statistics that meet this criteria
	 */
	static public StatusItems queryStatusByDay(String moduleName, String group, Integer days) {
		StatusItems result = null;
		String path = buildQueryPath(moduleName, group, QUERY_TYPE_STATUS_BY_DAY, days);

		result = ClientJSON.readJSON(path, StatusItems.class);
		return(result);
	}

	/**
	 * Updates the statistics with the supplied information
	 * 
	 * @param moduleName The name of the module we want statistics for
	 * @param group The group within the module
	 * @param startTime The time the processing started 
	 * @param endTime The time the processing completed
	 * @param numberFailed The number of items that failed the processing
	 * @param numberSuccessful The number of items that completed the processing
	 * 
	 * @return A HttpResult of the call, a getHttpStatusCode of 202 means this call was successful
	 */
	static public HttpResult update(String moduleName, String group, long startTime, long endTime, int numberFailed, int numberSuccessful) {
		return(update(moduleName, group, new Date(startTime), new Long(endTime - startTime), null, new Integer(numberFailed), new Integer(numberSuccessful))); 
	}
	
	/**
	 * Updates the statistics with the supplied information
	 * 
	 * @param moduleName The name of the module we want statistics for
	 * @param group The group within the module
	 * @param dateTime The date / time the processing started 
	 * @param duration How long in milliseconds the processing took
	 * @param itemsProcessed the total number of items that were processed
	 * @param numberFailed The number of items that failed the processing
	 * @param numberSuccessful The number of items that completed the processing
	 * 
	 * @return A HttpResult of the call, a getHttpStatusCode of 202 means this call was successful
	 */
	static public HttpResult update(String moduleName, String group, Date dateTime, Long duration, Integer itemsProcessed, Integer numberFailed, Integer numberSuccessful) {
		String path = buildUpdatePath(moduleName, group, dateTime, duration, itemsProcessed, numberFailed, numberSuccessful); 
		return(ClientHTTP.send(path));
	}

	/**
	 * Exercises all the methods with the passed in parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 *      <tr><td>-coreBaseURL</td><td>The base URL of the core module</td></tr>
	 * 		<tr><td>-all</td><td>Call all methods</td></tr>
	 * 		<tr><td>-query</td><td>Calls the query methods</td></tr>
	 * 		<tr><td>-update</td><td>Call the update method</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		System.out.println("Using module: \"" + arguments.getModuleName() + "\" and group: \"" + arguments.getGroup() + "\"");

		if (arguments.isRunAll() || arguments.isRunQuery()) {
			System.out.println("Running query items");
			StatisticItems queryItems = queryItems(arguments.getModuleName(), arguments.getGroup(), arguments.getDurationInteger(), arguments.getLimit(), arguments.getOffset());
			if (queryItems == null) {
				System.out.println("Failed to run query items\n");
			} else {
				System.out.println("Result:\n" + queryItems.toString());
			}
			
			System.out.println("Running query slowest");
			StatisticItems querySlowest = querySlowest(arguments.getModuleName(), arguments.getGroup(), arguments.getDurationInteger(), arguments.getLimit(), arguments.getOffset());
			if (querySlowest == null) {
				System.out.println("Failed to run query slowest\n");
			} else {
				System.out.println("Result:\n" + querySlowest.toString());
			}
			
			System.out.println("Running query status");
			StatusItem queryStatus = queryStatus(arguments.getModuleName(), arguments.getGroup(), arguments.getDays());
			if (queryStatus == null) {
				System.out.println("Failed to run query status\n");
			} else {
				System.out.println("Result:\n" + queryStatus.toString());
			}
			
			System.out.println("Running query statusByDay");
			StatusItems queryStatusByDay = queryStatusByDay(arguments.getModuleName(), arguments.getGroup(), arguments.getDays());
			if (queryStatusByDay == null) {
				System.out.println("Failed to run query statusByDay\n");
			} else {
				System.out.println("Result:\n" + queryStatusByDay.toString());
			}
		}
		
		if (arguments.isRunAll() || arguments.isRunUpdate()) {
			System.out.println("Calling Action update");
			HttpResult update = update(arguments.getModuleName(), arguments.getGroup(), arguments.getDateTime(), arguments.getDuration(), arguments.getItemsProcessed(), arguments.getNumberFailed(), arguments.getNumberSuccessful());
			System.out.println("Result from update: " + update.toString() + "\n");
		}
	}
}
