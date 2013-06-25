package com.k_int.euinside.client.module.setmanager.update;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.module.Attribute;

/**
 * The Update class handles the specific requirements for performing the SetManagers Update action
 */
public class Update {

	/**
	 * Builds the attribute array for the Update action
	 * 
	 * @param deleteAll ........ true if all records are to be deleted from the set 
	 * @param recordsToDelete .. An array of record ids that should be deleted
	 *  
	 * @return An array of NameValuePairs that will be used for the query part of the url
	 */
	static private ArrayList<BasicNameValuePair> buildattributeArray(boolean deleteAll, ArrayList<String> recordsToDelete) {
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		
		// Add the possible attributes to our attribute array
		if (deleteAll) {
			attributes.add(new BasicNameValuePair(Attribute.DELETE_ALL.getName(), "yes"));
		}
		if ((recordsToDelete != null) && !recordsToDelete.isEmpty()) {
			String commaSeparated = "";
			for (String recordId : recordsToDelete) {
				if (!commaSeparated.isEmpty()) {
					commaSeparated += ",";
				}
				commaSeparated += recordId;
			}
			attributes.add(new BasicNameValuePair(Attribute.DELETE.getName(), commaSeparated));
		}
		
		// Now we can make the http call
		return(attributes);
	}

	/**
	 * Updates the SetManager with the supplied details
	 * 
	 * @param path ............ The URL path for the action 
	 * @param xmlData ......... An array of xml records that are to be posted
	 * @param zipData ......... An array of zip files that are to be posted
	 * @param deleteAll ....... true if all records in the set are to be deleted
	 * @param recordsToDelete . An array of records that are to be deleted from the set
	 *  
	 * @return A HttpResult object, if the httpStatusCode is HttpServletResponse.SC_ACCEPTED, then the call has been successful 
	 */
	static public HttpResult sendBytes(String path, ArrayList<byte[]> xmlData, ArrayList<byte[]> zipData, boolean deleteAll, ArrayList<String> recordsToDelete) {
		return(ClientHTTP.sendBytes(path, xmlData, zipData, buildattributeArray(deleteAll, recordsToDelete)));
	}

	/**
	 * Updates the SetManager with the supplied details
	 * 
	 * @param path ............ The URL path for the action 
	 * @param filenames ....... An array of files that are to be posted (only files with extensions .xml and .zip are posted)
	 * @param deleteAll ....... true if all records in the set are to be deleted
	 * @param recordsToDelete . An array of records that are to be deleted from the set
	 *  
	 * @return A HttpResult object, if the httpStatusCode is HttpServletResponse.SC_ACCEPTED, then the call has been successful 
	 */
	static public HttpResult sendFiles(String path, ArrayList<String> filenames, boolean deleteAll, ArrayList<String> recordsToDelete) {
		return(ClientHTTP.sendFiles(path, filenames, buildattributeArray(deleteAll, recordsToDelete)));
	}
}
