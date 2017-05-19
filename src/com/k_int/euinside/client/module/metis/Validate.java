package com.k_int.euinside.client.module.metis;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.entity.ContentType;

import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.validation.ValidationResult;
import com.k_int.euinside.client.module.validation.ValidationResultRecord;

// Imports only used if using the Metis Validation Client
// Note: we stopped using because of the jar file it includes
//import eu.europeana.validation.client.ValidationClient;
//import eu.europeana.validation.model.ValidationResult;

public class Validate {
	private static Log log = LogFactory.getLog(Validate.class);

	private static final String VALIDATION_PROVIDER_METIS = "metis";

// Variable only used if using Metis Validation client
//	/** The metis client class we are going to use for validation */
//	private static ValidationClient metisClient = null;

	/** The base url of the metis validation api we will be using */
	private static String baseURL = "http://metis-validation-rest-test.cfapps.io/";

	/** The schema we will be using */
	private static String schema = "EDM-EXTERNAL";

	/** The version of the schema we will be using */
	private static String version = "undefined";

	/** The url to use for metis */
	private static String validateURL;

	static {
		setValidateURL();
	}

// Only required if we use the metis validation client
//	/**
//	 * Retrieve the instance of the metis client that we are using
//	 * 
//	 * @return an instance of the metis validation client
//	 */
//	private static ValidationClient getMetisClient() {
//		// We do not initialise on startup as that causes knock on problems in the metis validation client
//		if (metisClient == null) {
//			metisClient = new ValidationClient(baseURL);
//		}
//		return(metisClient);
//	}

	/**
	 * Set the base url to the Metis validation service
	 * 
	 * @param baseURL the base url to the metis validation service (default: http://metis-validation-test.cfapps.io)
	 */
	public static void setURL(String baseURL) {
		if (!StringUtils.isEmpty(baseURL)) {
			Validate.baseURL = baseURL;
			setValidateURL();
		}
	}

	/**
	 * Set the schema and version that the source records are encoded with
	 * 
	 * @param schema the schema the records are using (default: EDM-EXTERNAL) 
	 * @param version the version of the schema that they are using (default: undefined)
	 */
	public static void setSchema(String schema, String version) {
		if (!StringUtils.isEmpty(schema) && !StringUtils.isEmpty(version)) {
			Validate.schema = schema;
			Validate.version = version;
			setValidateURL();
		}
	}

	/**
	 * Sets the metis validate url base on the current settings
	 * 
	 */
	static void setValidateURL() {
		validateURL = baseURL + "/schema/validate/" + schema + "/" + version;
	}

	/**
	 * Sends the supplied record to the Validation module for validation
	 *  
	 * @param edmRecord The edm record that needs to be validated
	 * 
	 * @return The validation result
	 */
	static public ValidationResult validate(String edmRecord) {
		// Ensure we have a result record
		ValidationResult validationResult = new ValidationResult(VALIDATION_PROVIDER_METIS);
		ValidationResultRecord validationResultRecord = validationResult.addRecord();

		// Nothing to do if we do no not have an edm record
		if (StringUtils.isEmpty(edmRecord)) {
			validationResultRecord.addError("No EDM record supplied");
		} else {
			try {
				MetisRecord record = new MetisRecord();
				record.setRecord(edmRecord);
				String json = ClientJSON.convertToJSON(record);
// This is the call if you use the metis validation client
//				ValidationResult metisResult = getMetisClient().validateRecord(schema, edmRecord, version);
				MetisValidationResult metisResult = TranslateMetisValidationResult.instance.translate(ClientHTTP.sendJSONData(validateURL, json, ContentType.APPLICATION_JSON));

				if (metisResult != null) {
					validationResultRecord.setResult(metisResult.isSuccess());
					validationResultRecord.addError(metisResult.getMessage());
				}
			} catch (Exception e) {
				log.error("Exception thrown while attempting to validate edm record:\n" + edmRecord, e);	
				validationResultRecord.addError(e.getMessage());
			}
		}

		// return the result to the caller, we will probably create our own class for this
		return(validationResult);
	}
	
	/**
	 * Sends the supplied record to the Validation module for validation
	 *  
	 * @param filename The file that contains the record that is to be sent to the metis
	 * 
	 * @return The interpreted data returned from the server or null if we are unable to read the contents of the file
	 */
	static public ValidationResult validateFile(String filename) {
		ValidationResult validationResult = null;
		try {
			String edmRecord = FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);
			validationResult = validate(edmRecord);
		} catch (Exception e) {
			log.error("Exception thrown while attempting to validate edm file:\n" + filename, e);	
		}

		// return the result to the caller, we will probably create our own class for this
		return(validationResult);
	}
	
	/**
	 * Sends the supplied record to the Validation module for validation
	 *  
	 * @param record the record as a byte array that is to be validated, it is assumed the record is UTF-8
	 * 
	 * @return The interpreted data returned from the server or null if we are unable to read the contents of the file
	 */
	static public ValidationResult validate(byte [] record) {
		return(validate(new String(record, StandardCharsets.UTF_8)));
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
				ValidationResult result = validateFile(filename);
		
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
