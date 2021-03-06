package com.k_int.euinside.client.module.validation;

import com.k_int.euinside.client.module.CommandLineArguments;

/**
 * This class alternates the validation between monguz and semantika
 * If you want to always use a specific validation module then call either ValidateMonguz or ValidateSemantika
 */
public class Validate {
	private static boolean useSemantika = true;
	
	// As the validation objects have no class members it is fine to just have 1 static worker that we use all the time
	private static final ValidateBase monguz = new ValidateMonguz();
	private static final ValidateBase semantika = new ValidateSemantika();

	/**
	 * Retrieves the worker object to use for validation
	 * 
	 * @return An instance of a validation object
	 */
	private static ValidateBase getWorker() {
		// Ensure we use a different worker to last time
		useSemantika = !useSemantika;
		
		return(useSemantika ? semantika : monguz);
		
	}
	
	/**
	 * Sends the supplied record to a Validation module for validation
	 *
	 * @param provider The code for the data provider
	 * @param xmlRecord The record that is to be sent
	 * 
	 * @return The interpreted data returned form the server
	 */
	static public ValidationResult sendBytes(String provider, byte[] xmlRecord) {
		return(getWorker().send(provider,  xmlRecord));
	}

	/**
	 * Sends the supplied record to the Validation module for validation
	 *  
	 * @param provider The code for the data provider
	 * @param filename The file that contains the record that is to be sent to the validation module
	 * 
	 * @return The interpreted data returned form the server
	 */
	static public ValidationResult sendFiles(String provider, String filename) {
		return(getWorker().send(provider,  filename));
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
	public static void main(String [] args)
	{
		CommandLineArguments arguments = new CommandLineArguments(args);
		useSemantika = !arguments.useSemantika();
		getWorker().test(args);
	}

}
