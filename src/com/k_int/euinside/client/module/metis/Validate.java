package com.k_int.euinside.client.module.metis;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.module.CommandLineArguments;

import eu.europeana.validation.client.ValidationClient;
import eu.europeana.validation.model.ValidationResult;

public class Validate {
	private static Log log = LogFactory.getLog(Validate.class);

	/** The metis client class we are going to use for validation */
	private static ValidationClient metisClient = null;

	/** The base url of the metis validation api we will be using */
	private static String baseURL = "http://metis-validation-test.cfapps.io";

	/** The schema we will be using */
	private static String schema = "EDM-EXTERNAL";

	/** The version of the schema we will be using */
	private static String version = "undefined";

	/**
	 * Retrieve the instance of the metis client that we are using
	 * 
	 * @return an instance of the metis validation client
	 */
	private static ValidationClient getMetisClient() {
		// We do not initialise on startup as that causes knock on problems in the metis validation client
		if (metisClient == null) {
			metisClient = new ValidationClient(baseURL);
		}
		return(metisClient);
	}

	/**
	 * Set the base url to the Metis validation service
	 * 
	 * @param baseURL the base url to the metis validation service (default: http://metis-validation-test.cfapps.io)
	 */
	public static void setURL(String baseURL) {
		if (!StringUtils.isEmpty(baseURL)) {
			Validate.baseURL = baseURL;
		}
	}

	/**
	 * Set the schema and version that the source records are encoded with
	 * 
	 * @param schema the schema the records are using (default: EDM-EXTERNAL) 
	 * @param version the version of the schema that they are using (default: undefined)
	 */
	public static void setSchema(String schema, String version) {
		Validate.schema = schema;
		Validate.version = version;
	}

	static public ResultRecord validate(String edmRecord) {
		return(validate(edmRecord, null));
	}

	/**
	 * Sends the supplied record to the Validation module for validation
	 *  
	 * @param edmRecord The edm record that needs to be validated
	 * 
	 * @return The interpreted data returned from the server
	 */
	static public ResultRecord validate(String edmRecord, ResultRecord result) {
		// Ensure we have a result record
		if (result == null) {
			result = new ResultRecord();
		}

		// Nothing to do if we do no not have an edm record
		if (StringUtils.isEmpty(edmRecord)) {
			result.setMessage("No EDM record supplied");
		} else {
			try {
				ValidationResult metisResult = getMetisClient().validateRecord(schema, edmRecord, version);
				if (metisResult != null) {
					result.setResult(metisResult.isSuccess());
					result.setMessage(metisResult.getMessage());
				}
			} catch (Exception e) {
				log.error("Exception thrown while attempting to validate edm record:\n" + edmRecord, e);	
				result.setMessage(e.getMessage());
			}
		}

		// return the result to the caller, we will probably create our own class for this
		return(result);
	}
	
	/**
	 * Sends the supplied record to the Validation module for validation
	 *  
	 * @param filename The file that contains the record that is to be sent to the metis
	 * 
	 * @return The interpreted data returned from the server
	 */
	static public ResultRecord validateFile(String filename) {
		ResultRecord result = new ResultRecord();
		try {
			String edmRecord = FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);
			validate(edmRecord, result);
		} catch (Exception e) {
			log.error("Exception thrown while attempting to validate edm file:\n" + filename, e);	
		}

		// return the result to the caller, we will probably create our own class for this
		return(result);
	}
	
	/**
	 * Exercises all the methods with the supplied parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 * 		<tr><td>-filename</td><td>The name of a file that contains an edm record to be validated</td></tr>
	 * 		<tr><td>-metisBaseURL</td><td>The URL base to be used for metis validation</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = new CommandLineArguments(args, true);
		if (arguments.getFilenames().isEmpty()) {
			log.error("No filenames supplied for validation");
		} else {
			setURL(arguments.getMetisBaseURL());
			for (String filename : arguments.getFilenames()) {
				log.info("Validating file: " + filename);
				ResultRecord result = validateFile(filename);
		
				if (result == null) {
					log.error("Failed to validate file");
				} else {
					log.info("Result from validate");
					log.info(result.toString());
				}
			}
		}
	}
}
