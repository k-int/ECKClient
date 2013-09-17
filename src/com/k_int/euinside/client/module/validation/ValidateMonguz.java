package com.k_int.euinside.client.module.validation;

import com.k_int.euinside.client.module.Module;

/**
 * This class provides the interface for the Validation module supplied by monguz
 *  
 */
public class ValidateMonguz extends ValidateBase {
	static private final String DEFAULT_LIDO_PROFILE_FILENAME = "LidoValidationProfile_Monguz.xml"; 

	// Due to the base class being abstract, we need to instantiate an object and use it
	// Using the one object for multiple calls is fine as there are no class members
	static private final ValidateMonguz workerValidate = new ValidateMonguz();
	
	@Override
	protected Module getModule() {
		return(Module.VALIDATION);
	}
	
	@Override
	protected String getDefaultLidoProfile() {
		return(DEFAULT_LIDO_PROFILE_FILENAME);
	}
	
	/**
	 * Sends the supplied record to the Validation module for validation
	 *
	 * @param provider The code for the data provider
	 * @param xmlRecord The record that is to be sent
	 * 
	 * @return The interpreted data returned form the server
	 */
	static public ValidationResult sendBytes(String provider, byte[] xmlRecord) {
		return(workerValidate.send(provider,  xmlRecord));
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
		return(workerValidate.send(provider,  filename));
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
		workerValidate.test(args);
	}
}
