package com.k_int.euinside.client.module.validation;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.xml.ClientXML;

public abstract class ValidateBase extends BaseModule {
	static private String VALIDATION_PROFILE = "lido";
	static private String VALIDATION_SINGLE  = "single";

	/**
	 * Returns the module that the validation is going to occur against
	 * 
	 * @return The module that is going to perform the validation
	 */
	protected abstract Module getModule();
	
	/**
	 * Builds the path required for the validaion module
	 * 
	 * @return The path that is required for the validation module
	 */
	private String buildPath(String provider) {
		return(buildPath(getModule(), PATH_SEPARATOR + provider + PATH_SEPARATOR + VALIDATION_SINGLE + PATH_SEPARATOR + Action.VALIDATION_VALIDATE.getName() + PATH_SEPARATOR + VALIDATION_PROFILE));
	}

	/**
	 * Maps the result returned xml into a set of classes
	 * 
	 * @param result ... The result returned from the validation module
	 * 
	 * @return The interpreted data returned form the server
	 */
	private ValidationResult mapHttpResultToErrors(HttpResult result) {
		return(ClientXML.readXML(result, ValidationResult.class));
	}

	/**
	 * Sends the supplied record to the Validation module for validation
	 *
	 * @param provider The code for the data provider
	 * @param xmlRecord The record that is to be sent
	 * 
	 * @return The interpreted data returned form the server
	 */
	public ValidationResult send(String provider, byte[] xmlRecord) {
		ArrayList<byte[]> recordArray = new ArrayList<byte[]>();
		recordArray.add(xmlRecord);
		return(mapHttpResultToErrors(ClientHTTP.sendBytes(buildPath(provider), recordArray, null)));
	}

	/**
	 * Sends the supplied record to the Validation module for validation
	 *  
	 * @param provider The code for the data provider
	 * @param filename The file that contains the record that is to be sent to the validation module
	 * 
	 * @return The interpreted data returned form the server
	 */
	public ValidationResult send(String provider, String filename) {
		ArrayList<String> filenameArray = new ArrayList<String>();
		filenameArray.add(filename);
		return(mapHttpResultToErrors(ClientHTTP.sendFiles(buildPath(provider), filenameArray)));
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
	 * 		<tr><td>-provider</td><td>The providers code</td></tr>
     *  </table>
	 */
	protected void test(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		
		if (!arguments.getFilenames().isEmpty()) {
			ValidationResult result = send(arguments.getProvider(), arguments.getFilenames().get(0));
	
			if (result == null) {
				System.out.println("Failed to validate file");
			} else {
				System.out.println("Result from validate");
				System.out.println(result.toString());
			}
		}
		
		if (!arguments.getBadFilenames().isEmpty()) {
			ValidationResult result = send(arguments.getProvider(), arguments.getBadFilenames().get(0));
	
			if (result == null) {
				System.out.println("Failed to validate file");
			} else {
				System.out.println("Result from validate");
				System.out.println(result.toString());
			}
		}
	}
}
