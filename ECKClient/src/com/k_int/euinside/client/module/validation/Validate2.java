package com.k_int.euinside.client.module.validation;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.module.validation.semantika.Errors;

/**
 * This class provides the interface for the Validation module supplied by semantika
 *  
 */
public class Validate2 extends BaseModule {

	/**
	 * Builds the path required for the validaion module
	 * 
	 * @return The path that is required for the validation module
	 */
	static private String buildPath() {
		return(buildPath(Module.VALIDATION2, PATH_SEPARATOR + Action.VALIDATION_VALIDATE.getName()));
	}

	/**
	 * Maps the HttpResult into the Errors array
	 * 
	 * @param result The HttpResult from calling the module
	 * 
	 * @return The list of errros for this record
	 */
	static private Errors mapHttpResultToErrors(HttpResult result) {
		Errors errors = null;
		if (result.getHttpStatusCode() == HttpServletResponse.SC_OK) {
			errors = ClientJSON.readJSONString(result.getContent(), Errors.class);
		}

		return(errors);
	}

	/**
	 * Sends the supplied record to the validation module
	 * 
	 * @param record The record that needs to be validated
	 * 
	 * @return All errors associated with the record
	 */
	static public Errors sendBytes(byte[] record) {
		ArrayList<byte[]> recordArray = new ArrayList<byte[]>();
		recordArray.add(record);
		return(mapHttpResultToErrors(ClientHTTP.sendBytes(buildPath(), recordArray, null)));
	}

	/**
	 * Sends the contents of the specified file to be validated
	 * 
	 * @param filename The name of the file that contains the record that requires validating
	 * 
	 * @return All errors associated with the record
	 */
	static public Errors sendFiles(String filename) {
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
			Errors errors = sendFiles(arguments.getFilenames().get(0));
	
			if (errors == null) {
				System.out.println("Failed to validate file");
			} else {
				System.out.println("Result from validate2");
				System.out.println(errors.toString());
			}
			
			for (String filename : arguments.getFilenames()) {
				try {
					String fileContents = FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);
					 errors = sendBytes(fileContents.getBytes());
					
					if (errors == null) {
						System.out.println("Failed to validate bytes");
					} else {
						System.out.println("Result from validate2 (bytes)");
						System.out.println(errors.toString());
					}
				} catch (IOException e) {
					System.out.println("IOException reading file: " + filename);
				}
			}
		}

		if (!arguments.getBadFilenames().isEmpty()) {
			Errors errors = sendFiles(arguments.getBadFilenames().get(0));
	
			if (errors == null) {
				System.out.println("Failed to validate file");
			} else {
				System.out.println("Result from validate2");
				System.out.println(errors.toString());
			}
		}
	}
}
