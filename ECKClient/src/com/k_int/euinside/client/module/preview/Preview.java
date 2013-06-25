package com.k_int.euinside.client.module.preview;

import java.util.ArrayList;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;

public class Preview extends BaseModule{

	static private String DEFAULT_TEMPLATE = "default";
	
	/**
	 * Builds the path required for the validaion module
	 * 
	 * @return The path that is required for the validation module
	 */
	static private String buildPath() {
		return(buildPath(Module.PREVIEW, PATH_SEPARATOR + DEFAULT_TEMPLATE + PATH_SEPARATOR + Action.PREVIEW_PREVIEW.getName()));
	}

	/**
	 * Sends the supplied record to the preview module
	 *  
	 * @param xmlRecord ... The record that is to be sent
	 * 
	 * @return The result returned from the server
	 */
	static public HttpResult sendBytes(byte[] xmlRecord) {
		ArrayList<byte[]> recordArray = new ArrayList<byte[]>();
		recordArray.add(xmlRecord);
		return(ClientHTTP.sendBytes(buildPath(), recordArray, null));
	}

	/**
	 * Sends the supplied record to the preview module
	 *  
	 * @param filename ... The file that contains the record that is to be sent
	 * 
	 * @return The result returned from the server
	 */
	static public HttpResult sendFiles(String filename) {
		ArrayList<String> filenameArray = new ArrayList<String>();
		filenameArray.add(filename);
		return(ClientHTTP.sendFiles(buildPath(), filenameArray));
	}
	
	/**
	 * Exercises all the methods with the supplied parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 *      <tr><td>-coreBaseURL</td><td>The base URL of the core module</td></tr>
	 *      <tr><td>-filename</td><td>The name of a file that contains a record with no validation errors</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		
		if (!arguments.getFilenames().isEmpty()) {
			HttpResult result = sendFiles(arguments.getFilenames().get(0));
	
			if (result == null) {
				System.out.println("Failed to preview file");
			} else {
				System.out.println("Result from preview");
				System.out.println(result.toString());
			}
		}
	}
}
