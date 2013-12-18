package com.k_int.euinside.client.module;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.k_int.euinside.client.BaseClient;

/**
 * The BaseModule class contains the functionality that is shared by all the clients 
 */
public class BaseModule {

	static public String PATH_SEPARATOR = "/";
	static public String QUERY_START = "?";
	static public String ATTRIBUTE_EQUALS = "=";
	static public String ATTRIBUTE_SEPARATOR = "&";

	/**
	 * Retrieves the ECKCore base URL
	 * 
	 * @return The base url used for ECKCore, if it has not been set it will be null
	 */
	static public String getCoreBaseURL() {
		return(BaseClient.getCoreBaseURL());
	}

	/**
	 * Sets the base url for ECKCore
	 * 
	 * @param coreBaseURL The base paart of the URL to be used for accessing the ECKCore
	 */
	static public void setCoreBaseURL(String coreBaseURL) {
		BaseClient.setCoreBaseURL(coreBaseURL);
	}

	/**
	 * Helper to build a path to the specified module
	 * 
	 * @param module The module to be accessed
	 * @param attributes A name value array of attributes that need to be added to the path
	 *   
	 * @return The path / url to the module
	 */
	static public String buildPath(Module module, ArrayList<BasicNameValuePair> attributes) {
		return(buildPath(module, null, attributes));
	}
	
	/**
	 * Helper to build a path to the specified module
	 * 
	 * @param module The module to be accessed
	 * @param modulePath The path within the modules that is to be accessed
	 *   
	 * @return The path / url to the module
	 */
	static public String buildPath(Module module, String modulePath) {
		return(buildPath(module, modulePath, null));
	}
	
	/**
	 * Helper to build a path to the specified module
	 * 
	 * @param module The module to be accessed
	 * @param modulePath The path within the modules that is to be accessed
	 * @param attributes A name value array of attributes that need to be added to the path
	 *   
	 * @return The path / url to the module
	 */
	static public String buildPath(Module module, String modulePath, ArrayList<BasicNameValuePair> attributes) {
		String path = module.getRootPath();
		if (modulePath != null) {
			path += modulePath;
		}
		path = buildURL(path, attributes);
		return(path);
	}

	/**
	 * Builds up the URL given the specified attributes
	 *  
	 * @param baseURL The url the attributes need appending to
	 * @param attributes The attributes that need adding tot he url
	 * 
	 * @return The desired url
	 */
	static public String buildURL(String baseURL, ArrayList<BasicNameValuePair> attributes){
		String url = baseURL;
		
		if (attributes != null) {
			String paramString = URLEncodedUtils.format(attributes, StandardCharsets.UTF_8);
			if ((paramString != null) && !paramString.isEmpty()) {
				url += QUERY_START + paramString;
			}
		}
		return(url);
	}
	
	/**
	 * Builds an attribute for the query part of the url 
	 * 
	 * @param attribute The attribute name
	 * @param value The value of the attribute, this will be URL encoded
	 * @param addSeparator true if we need to add the attribute separator at the beginning of the string
	 * 
	 * @return A string containing the attribute and value that can be added to the query part of the url
	 */
	static public String buildAttribute(Attribute attribute, String value, boolean addSeparator) {
		String result = (addSeparator ? ATTRIBUTE_SEPARATOR : "");
		try {
			result += attribute.getName() + ATTRIBUTE_EQUALS + URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// Ignore these exceptions as they should not occur
		}
		return(result);
	}
	
	/**
	 * Parses the command line arguments for testing the modules
	 * 
	 * @param args The arguments as specified on the command line
	 * 
	 * @return A CommandLineArguments instance object that holds the parsed arguments
	 */
	static public CommandLineArguments parseCommandLineArguments(String [] args) {
		return(new CommandLineArguments(args));
	}
}
