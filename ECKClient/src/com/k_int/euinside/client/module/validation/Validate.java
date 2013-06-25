package com.k_int.euinside.client.module.validation;

import java.util.ArrayList;

//import javax.servlet.http.HttpServletResponse;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.http.ClientHTTP;
//import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;

/**
 * This class provides the interface for the Validation module supplied by monguz
 *  
 */
public class Validate extends BaseModule{

	static private String VALIDATION_PROFILE = "lido";
	
	/**
	 * Builds the path required for the validaion module
	 * 
	 * @return The path that is required for the validation module
	 */
	static private String buildPath() {
		return(buildPath(Module.VALIDATION, PATH_SEPARATOR + VALIDATION_PROFILE + PATH_SEPARATOR + Action.VALIDATION_VALIDATE.getName()));
	}

	/**
	 * Maps the result returned json into a set of classes, at the moment the data is not returned in a structured way, so we just return what was returned by the module
	 * 
	 * @param result ... The result returned from the validation module
	 * 
	 * @return The result returned from the server
	 */
	static private HttpResult mapHttpResultToErrors(HttpResult result) {
		// Monguz are not returning anything structured yet
//		Errors errors = null;
//		if (result.getHttpStatusCode() == HttpServletResponse.SC_OK) {
//			errors = ClientJSON.readJSONString(result.getContent(), Errors.class);
//		}

		return(result);
	}

	/**
	 * Sends the supplied record to the Validation module for validation
	 *  
	 * @param xmlRecord ... The record that is to be sent
	 * 
	 * @return The result returned form the server (it is not structured so we do not attempt to interpret it)
	 */
	static public HttpResult sendBytes(byte[] xmlRecord) {
		ArrayList<byte[]> recordArray = new ArrayList<byte[]>();
		recordArray.add(xmlRecord);
		return(mapHttpResultToErrors(ClientHTTP.sendBytes(buildPath(), recordArray, null)));
	}

	/**
	 * Sends the supplied record to the Validation module for validation
	 *  
	 * @param filename ... The file that contains the record that is to be sent to the validation module
	 * 
	 * @return The result returned form the server (it is not structured so we do not attempt to interpret it)
	 */
	static public HttpResult sendFiles(String filename) {
		ArrayList<String> filenameArray = new ArrayList<String>();
		filenameArray.add(filename);
		return(mapHttpResultToErrors(ClientHTTP.sendFiles(buildPath(), filenameArray)));
	}
	
	/**
	 * Exercises all the methods with the supplied parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 *      <tr><td>-coreBaseURL</td><td>The base URL of the core module</td></tr>
	 * 		<tr><td>-badFilename</td><td>The name of the file that contains a record with validation errors</td></tr>
	 * 		<tr><td>-filename</td><td>The name of a file that contains a record with no validation errors</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		
		if (!arguments.getFilenames().isEmpty()) {
			HttpResult errors = sendFiles(arguments.getFilenames().get(0));
	
			if (errors == null) {
				System.out.println("Failed to validate file");
			} else {
				System.out.println("Result from validate");
				System.out.println(errors.toString());
			}
		}
		
		if (!arguments.getBadFilenames().isEmpty()) {
			HttpResult errors = sendFiles(arguments.getBadFilenames().get(0));
	
			if (errors == null) {
				System.out.println("Failed to validate file");
			} else {
				System.out.println("Result from validate");
				System.out.println(errors.toString());
			}
		}
	}
}
