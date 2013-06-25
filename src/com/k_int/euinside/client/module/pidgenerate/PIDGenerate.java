package com.k_int.euinside.client.module.pidgenerate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.Attribute;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;

/**
 * This class provides the interface for the PID Generation module
 *  
 */
public class PIDGenerate extends BaseModule {
	private static Log log = LogFactory.getLog(PIDGenerate.class);

	/** 
	 * Generates a PID when supplied with all the requisite components
	 * 
	 * @param institutionURL .... The institutions url
	 * @param recordType ........ The type of record a pid is being requested for
	 * @param accessionNumber ... The unique identifier for the record
	 * 
	 * @return A generated pid for the supplied details
	 */
	static public String generate(String institutionURL, String recordType, String accessionNumber) {
		return(generate(new PIDComponents(institutionURL, recordType, accessionNumber)));
	}

	/**
	 * Generates a PID given a PIDComponents object
	 * 
	 * @param components ... Contains the insitutionURL, recordType and accessionNumber components that are required to generate the PID
	 * 
	 * @return A generated PID for the supplied details
	 */
	static public String generate(PIDComponents components) {
		String result;
		ArrayList<BasicNameValuePair> attributes = new ArrayList<BasicNameValuePair>();
		attributes.add(new BasicNameValuePair(Attribute.ACCESSION_NUMBER.getName(), components.getAccessionNumber()));
		attributes.add(new BasicNameValuePair(Attribute.INSTITUTION_URL.getName(), components.getInstitutionURL()));
		attributes.add(new BasicNameValuePair(Attribute.RECORD_TYPE.getName(), components.getRecordType()));
		String path = buildPath(Module.PID_GENERATE, PATH_SEPARATOR + Action.PID_GENERATE_GENERATE.getName(), attributes);

		result = ClientJSON.readJSON(path, String.class);
		
		return(result);
	}

	/**
	 * Reverse engineers a PID into its institution URL, record type and accession number, this will only work if the PID was generated from the ECK PID generation module
	 *  
	 * @param pid The PID that was generation from the ECK PID Generation module
	 * 
	 * @return A PIDComponents object that contains the separate components that make up an ECK PID 
	 */
	static public PIDComponents lookUp(String pid) {
		PIDComponents result = null;
		try {
			String path = buildPath(Module.PID_GENERATE, PATH_SEPARATOR + Action.PID_GENERATE_LOOKUP.getName() + PATH_SEPARATOR + URLEncoder.encode(pid, StandardCharsets.UTF_8.toString()));
			result = ClientJSON.readJSON(path, PIDComponents.class);
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException encodeing pid: \"" + pid + "\"", e);
		}
		
		return(result);
	}
	
	/**
	 * Exercises all the methods with the passed in parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 *      <tr><td>-coreBaseURL</td><td>The base URL of the core module</td></tr>
	 *      <tr><td>-accessionNumber</td><td>The unique identifier for this record in the CMS</td></tr>
	 *      <tr><td>-institutionURL</td><td>The institutions URL</td></tr>
	 *      <tr><td>-recordType</td><td>The type of record that we are generating a PID for</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		
		System.out.println("Using accessionNumber: \"" + arguments.getAccessionNumber() + "\"");
		System.out.println("      institutionURL: \"" + arguments.getInstitutionURL() + "\"");
		System.out.println("      recordType: \"" + arguments.getRecordType() + "\"");

		String result = generate(arguments.getInstitutionURL(), arguments.getRecordType(), arguments.getAccessionNumber());
		System.out.println("Result from generate: " + result.toString() + "\n");
		
		if (arguments.getPid().isEmpty()) {
			arguments.setPid(result);
		}

		System.out.println("Looking up PID: \"" + arguments.getPid() + "\"");
		PIDComponents pidComponents = lookUp(arguments.getPid());
		if (pidComponents == null) {
			System.out.println("Failed to lookup pid");
		} else {
			System.out.println("Result from lookup");
			System.out.println(pidComponents.toString());
		}
	}
}
