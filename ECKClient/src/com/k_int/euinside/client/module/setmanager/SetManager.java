package com.k_int.euinside.client.module.setmanager;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.Attribute;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.setmanager.list.BriefRecords;
import com.k_int.euinside.client.module.setmanager.status.Status;
import com.k_int.euinside.client.module.setmanager.update.Update;
import com.k_int.euinside.client.module.setmanager.validate.ValidationErrors;

/**
 * This class provides the interface for the Set Manager module
 *  
 */
public class SetManager extends BaseModule {

	static public String PROVIDER_DEFAULT = "default";
	static public String SET_DEFAULT = "default";

	/**
	 * Builds the path required by the Set Manager module
	 *  
	 * @param provider ... The provider of the records
	 * @param set ........ The set the records belong to
	 * @param action ..... The action being performed
	 * 
	 * @return The path to be used to perform the action required
	 */
	static private String buildPath(String provider, String set, Action action) {
		return(buildPath(provider, set, action, null));
	}

	/**
	 * Builds the path and query string required by the Set Manager module
	 * 
	 * @param provider ... The provider of the records
	 * @param set ........ The set the records belong to
	 * @param action ..... The action being performed
	 * @param attributes . Any query attributes that need to be appended to the path
	 * 
	 * @return The path and query string required to perform the action
	 */
	static private String buildPath(String provider, String set, Action action, ArrayList<BasicNameValuePair> attributes) {
		if (provider == null) {
			provider = PROVIDER_DEFAULT;
		}
		if (set == null) {
			set = SET_DEFAULT;
		}
		String modulePath = PATH_SEPARATOR + provider + PATH_SEPARATOR + set + PATH_SEPARATOR + action.getName();
		return(buildPath(Module.SET_MANAGER, modulePath, attributes));
	}

	/**
	 * Retrieves the status for the default provider and default set
	 * 
	 * @return The status for the for the default provider and default set 
	 */
	static public Status getStatus() {
		return(getStatus(null, null, 0));
	}

	/**
	 * Returns the status for the specified provider and set
	 * 
	 * @param provider ... The provider of the records
	 * @param set ........ The set the records belong to
	 * 
	 * @return The status of the set for the specified provider
	 */
	static public Status getStatus(String provider, String set) {
		return(getStatus(provider, set, 0));
	}

	/**
	 * Returns the status for the specified provider and set
	 * 
	 * @param provider ....... The provider of the records
	 * @param set ............ The set the records belong to
	 * @param historyItems ... The number of history items to see in the status
	 * 
	 * @return The status of the set for the specified provider
	 */
	static public Status getStatus(String provider, String set, int historyItems) {
		Status result = null;
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		if (historyItems > 0) {
			attributes.add(new BasicNameValuePair(Attribute.HISTORY_ITEMS.getName(), Integer.toString(historyItems)));
		}
		String path = buildPath(provider, set, Action.SET_MANAGER_STATUS, attributes);

		result = ClientJSON.readJSON(path, Status.class);
		return(result);
	}

	/**
	 * Returns a list of the brief records in the specified set for the given provider
	 * 
	 * @param provider ... The provider of the records
	 * @param set ........ The set the records belong to
	 * @return A list of all the records in the set
	 */
	static public BriefRecords getList(String provider, String set) {
		BriefRecords result = null;
		String path = buildPath(provider, set, Action.SET_MANAGER_LIST);

		result = ClientJSON.readJSON(path, BriefRecords.class);
		return(result);
	}

	/**
	 * Returns a preview of the specified record
	 * 
	 * @param provider ... The provider of the records
	 * @param set ........ The set the records belong to
	 * @param recordId ... The identifier for the record you want to preview 
	 *
	 * @return A list of all the records in the set
	 */
	static public String preview(String provider, String set, String recordId) {
		String result = null;
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		attributes.add(new BasicNameValuePair(Attribute.RECORD_ID.getName(), recordId));
		String path = buildPath(provider, set, Action.SET_MANAGER_PREVIEW, attributes);

		result = ClientHTTP.send(path).getContent();
		return(result);
	}

	/**
	 * Commits all valid records in the working set to the live set which can then be pushed or harvested
	 * 
	 * @param provider ... The provider of the records
	 * @param set ........ The set the records belong to
	 *
	 * @return A HttpResult of the call, a getHttpStatusCode of 202 means this call was successful
	 */
	static public HttpResult commit(String provider, String set) {
		// Just call update
		return(update(provider, set, null, true, false, null));
	}

	/**
	 * Updates the specified set for the given provider with the specified files
	 * Only files with the extension of .zip and .zml with be submitted
	 * 
	 * @param provider ......... The provider of the records
	 * @param set .............. The set the records belong to
	 * @param filenames ........ The list of filenames containing records that need to be submitted 
	 * @param commit ........... Do we commit on completion
	 * @param deleteAll ........ Do we delete all the records before the update
	 * @param recordsToDelete .. A list of record ids that need to be deleted
	 * 
	 * @return A HttpResult of the call, a getHttpStatusCode of 202 means this call was successful
	 */
	static public HttpResult update(String provider, String set, ArrayList<String> filenames, boolean commit, boolean deleteAll, ArrayList<String> recordsToDelete) {
		// Are we going to commit or just update
		String path = buildPath(provider, set, commit ? Action.SET_MANAGER_COMMIT : Action.SET_MANAGER_UPDATE);
		return(Update.sendFiles(path, filenames, deleteAll, recordsToDelete));
	}

	/**
	 * Updates the specified set for the given provider with the supplied aml and zip files
	 * 
	 * @param provider ......... The provider of the records
	 * @param set .............. The set the records belong to
	 * @param xmlData .......... An array of xml records that need to be submitted
	 * @param zipData .......... An array of zip records that need to be submitted
	 * @param commit ........... Do we commit on completion
	 * @param deleteAll ........ Do we delete all the records before the update
	 * @param recordsToDelete .. A list of record ids that need to be deleted
	 * 
	 * @return A HttpResult of the call, a getHttpStatusCode of 202 means this call was successful
	 */
	static public HttpResult update(String provider, String set, ArrayList<byte[]> xmlData, ArrayList<byte[]> zipData, boolean commit, boolean deleteAll, ArrayList<String> recordsToDelete) {
		// Are we going to commit or just update
		String path = buildPath(provider, set, commit ? Action.SET_MANAGER_COMMIT : Action.SET_MANAGER_UPDATE);
		return(Update.sendBytes(path, xmlData, zipData, deleteAll, recordsToDelete));
	}

	/**
	 * Returns the validation errors for the given set
	 * 
	 * @param provider ......... The provider of the records
	 * @param set .............. The set the records belong to
	 *
	 * @return The validation errors for the specifies set
	 */
	static public ValidationErrors getValidate(String provider, String set) {
		ValidationErrors result = null;
		String path = buildPath(provider, set, Action.SET_MANAGER_VALIDATE);

		result = ClientJSON.readJSON(path, ValidationErrors.class);
		return(result);
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
	 * 		<tr><td>-commit</td><td>Call the commit method</td></tr>
	 * 		<tr><td>-deleteAll</td><td>Do we delete all records prior to the update</td></tr>
	 * 		<tr><td>-filename</td><td>A filename to be supplied to the update call (may occur multiple times)</td></tr>
	 * 		<tr><td>-list</td><td>Call the list method</td></tr>
	 * 		<tr><td>-provider</td><td>Who the provider of the records is</td></tr>
	 * 		<tr><td>-recordToDelete</td><td>The identifier for a record to be deleted (maybe occur multiple times)</td></tr>
	 * 		<tr><td>-set</td><td>The set that we want to action for the specified provider</td></tr>
	 * 		<tr><td>-status</td><td>Call the status method</td></tr>
	 * 		<tr><td>-update</td><td>Call the update method</td></tr>
	 * 		<tr><td>-validate</td><td>Call the validate method</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		System.out.println("Using provider: \"" + arguments.getProvider() + "\" and set: \"" + arguments.getSet() + "\"");

		if (arguments.isRunAll() || arguments.isRunUpdate()) {
			System.out.println("Calling Action update");
			
			// Parse the filename into an ArrayList
			HttpResult result = update(arguments.getProvider(), arguments.getSet(), arguments.getFilenames(), false, arguments.isDeleteAll(), arguments.getRecordsToDelete());
			System.out.println("Result from update: " + result.toString() + "\n");
		}
		
		if (arguments.isRunAll() || arguments.isRunStatus()) {
			System.out.println("Calling Action status");
			Status status = getStatus(arguments.getProvider(), arguments.getSet(), 10);
			if (status == null) {
				System.out.println("Failed to get hold of the status\n");
			} else {
				System.out.println("Result:\n" + status.toString());
			}
		}
		
		if (arguments.isRunAll() || arguments.isRunList()) {
			System.out.println("Calling Action list");
			BriefRecords briefRecords = getList(arguments.getProvider(), arguments.getSet());
			if (briefRecords == null) {
				System.out.println("Failed to get hold of the list\n");
			} else {
				System.out.println("Result:\n" + briefRecords.toString());
			}
		}
		
		if (arguments.isRunAll() || arguments.isRunValidate()) {
			System.out.println("Calling Action validate");
			ValidationErrors validationErrors = getValidate(arguments.getProvider(), arguments.getSet());
			if (validationErrors == null) {
				System.out.println("Failed to get hold of the validation errors\n");
			} else {
				System.out.println("Result:\n" + validationErrors.toString());
			}
		}

		if (arguments.isRunAll() || arguments.isRunCommit()) {
			System.out.println("Calling Action commit");
			HttpResult result = commit(arguments.getProvider(), arguments.getSet());
			System.out.println("Result from update: " + result.toString() + "\n");
		}

		if (arguments.isRunAll() || arguments.isRunPreview()) {
			System.out.println("Calling Action Preview");
			String result = preview(arguments.getProvider(), arguments.getSet(), arguments.getRecordId());
			System.out.println("Result from preview: " + result + "\n");
		}
	}
}
