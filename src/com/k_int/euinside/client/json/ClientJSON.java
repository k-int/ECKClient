package com.k_int.euinside.client.json;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.BaseClient;

/**
 * The ClientJSON class handles json manipulation for us,
 * either through results from a http call or being passed a json string,
 * both methods return class instances of the specified type if the json is of the correct format. 
 */
public class ClientJSON extends BaseClient {
	private static Log log = LogFactory.getLog(ClientJSON.class);

	private static ObjectMapper mapper = null; 

	static {
		mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}

	/**
	 * Takes a json string and turns it into instances of the specified class
	 * 
	 * @param json The json string that needs to be interpreted
	 * @param resultType The class that the json string needs to be interpreted as
	 * 
	 * @return An instance of the specified class or null if the json was not in the correct format for this class
	 */
	static public <T> T readJSONString(String json, Class<T> resultType) {
		T result = null;
		try {
			result = mapper.readValue(json, resultType);
		} catch (JsonParseException e) {
			log.error("Exception parsing JSON in string: \"" + json + "\"", e);
		} catch (JsonMappingException e) {
			log.error("Exception mapping JSON in string: \"" + json + "\"", e);
		} catch (IOException e) {
			log.error("IOException reading JSON in string: \"" + json + "\"", e);
		}
		return(result);
	}
	
	/**
	 * Takes a url / path and performs a HTTP GET operation and turns the resulting json string into instances of the specified class
	 * 
	 * @param path The url / path that we need to perform the http get against to obtain the json
	 * @param resultType The class that the json string needs to be interpreted as
	 * 
	 * @return An instance of the specified class or null if the json was not in the correct format for this class or there was an http error
	 */
	static public <T> T readJSON(String path, Class<T> resultType) {
		T result = null;
		if (getCoreBaseURL() == null) {
			log.error("The url to the ECKCore module has not been set (coreBaseURL)");
		} else {
			String fullPath = buildURL(path);
			try {
				log.debug("Calling URL: " + fullPath);
				URL url = new URL(fullPath);
				result = mapper.readValue(url, resultType);
			} catch (MalformedURLException e) {
				log.error("Malformed URL: \"" + fullPath + "\"", e);
			} catch (JsonParseException e) {
				log.error("Exception parsing JSON from url: \"" + fullPath + "\"", e);
			} catch (JsonMappingException e) {
				log.error("Exception mapping JSON from URL: \"" + fullPath + "\"", e);
			} catch (IOException e) {
				log.error("IOException reading JSON from URL: \"" + fullPath + "\"", e);
			}
		}	
		return(result);
	}
}
