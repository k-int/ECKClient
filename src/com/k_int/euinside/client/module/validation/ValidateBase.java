package com.k_int.euinside.client.module.validation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.xml.ClientXML;

public abstract class ValidateBase extends BaseModule {
	static public final String VALIDATION_PROFILE = "lido";
	static public final String VALIDATION_SINGLE  = "single";
	static public final String DEFAULT_PROVIDER = "default";

	/**
	 * Returns the module that the validation is going to occur against
	 * 
	 * @return The module that is going to perform the validation
	 */
	protected abstract Module getModule();

	/**
	 * Returns the filename for the default lido profile
	 * 
	 * @return The filename we use for the default lido profile
	 */
	protected abstract String getDefaultLidoProfile();
	
	/**
	 * Builds the path required for the validation module
	 * 
	 * @param provider The code for the data provider
	 * @param action The validation action to be performed
	 * @param includeSet Do we include the set in the url or not
	 * 
	 * @return The path that is required for the validation module
	 */
	private String buildPath(String provider, Action action, boolean includeSet) {
		return(buildPath(getModule(), PATH_SEPARATOR + provider + (includeSet ? (PATH_SEPARATOR + VALIDATION_SINGLE) : "") + PATH_SEPARATOR + action.getName() + PATH_SEPARATOR + VALIDATION_PROFILE));
//		return(buildPath(getModule(), PATH_SEPARATOR + DEFAULT_PROVIDER + (includeSet ? (PATH_SEPARATOR + VALIDATION_SINGLE) : "") + PATH_SEPARATOR + action.getName() + PATH_SEPARATOR + VALIDATION_PROFILE));
	}

	/**
	 * Builds the path required for the profile action
	 * 
	 * @param provider The code for the data provider
	 * 
	 * @return The path that is required for the profile action
	 */
	private String buildProfilePath(String provider) {
		return(buildPath(provider, Action.VALIDATION_PROFILES, false));
	}
	
	/**
	 * Builds the path required for the validate action
	 * 
	 * @param provider The code for the data provider
	 * 
	 * @return The path that is required for the validate action
	 */
	private String buildValidatePath(String provider) {
		return(buildPath(provider, Action.VALIDATION_VALIDATE, true));
	}
	
	private void sendDefaultProfile(String provider) {
		ArrayList<byte[]> recordArray = new ArrayList<byte[]>();
		
		try {
			InputStream lidoProfileStream = getClass().getResourceAsStream(getDefaultLidoProfile());
			if (lidoProfileStream != null) {
				// We have a default profile so try and send it
				recordArray.add(IOUtils.toByteArray(lidoProfileStream));
				ClientHTTP.sendBytes(buildProfilePath(provider), recordArray, null, ContentType.TEXT_HTML);
			}
		} catch (IOException e) {
			// We will ignore the IOException as there is not a lot we can do if that happens
		}
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
		return(send(provider, xmlRecord, true));
	}

	/**
	 * Sends the supplied record to the Validation module for validation
	 *
	 * @param provider The code for the data provider
	 * @param xmlRecord The record that is to be sent
	 * @param sendProfileOnFailure If we fail to get a result, do we send a default profile and try again
	 * 
	 * @return The interpreted data returned form the server
	 */
	private ValidationResult send(String provider, byte[] xmlRecord, boolean sendProfileOnFailure) {
		ArrayList<byte[]> recordArray = new ArrayList<byte[]>();
		recordArray.add(xmlRecord);
		ValidationResult result = mapHttpResultToErrors(ClientHTTP.sendBytes(buildValidatePath(provider), recordArray, null, null, ContentType.APPLICATION_XML));
		if ((result == null) && sendProfileOnFailure) {
			sendDefaultProfile(provider);
			result = send(provider, xmlRecord, false);
		}
		return(result);
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
		return(send(provider, filename, true));
	}
	
	/**
	 * Sends the supplied record to the Validation module for validation
	 *  
	 * @param provider The code for the data provider
	 * @param filename The file that contains the record that is to be sent to the validation module
	 * @param sendProfileOnFailure If we fail to get a result, do we send a default profile and try again
	 * 
	 * @return The interpreted data returned form the server
	 */
	private ValidationResult send(String provider, String filename, boolean sendProfileOnFailure) {
		ArrayList<String> filenameArray = new ArrayList<String>();
		filenameArray.add(filename);
		ValidationResult result = mapHttpResultToErrors(ClientHTTP.sendFiles(buildValidatePath(provider), filenameArray, null, ContentType.APPLICATION_XML));
		if ((result == null) && sendProfileOnFailure) {
			sendDefaultProfile(provider);
			result = send(provider, filename, false);
		}
		return(result);
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
