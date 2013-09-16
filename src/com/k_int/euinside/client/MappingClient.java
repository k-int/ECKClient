package com.k_int.euinside.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The MappingClient class handles json / xml manipulation for us,
 * either through results from a http call or being passed a json / xml string,
 * both methods return class instances of the specified type if the json / xml is of the correct format,
 * derived classes need to override the method getObjectMapper
 */
public abstract class MappingClient extends BaseClient {
	private static Log log = LogFactory.getLog(MappingClient.class);

	abstract protected ObjectMapper getObjectMapper(); 

	/**
	 * Takes a json / xm,l string and turns it into instances of the specified class
	 * 
	 * @param source The json / xml string that needs to be interpreted
	 * @param resultType The class that the json / xml string needs to be interpreted as
	 * 
	 * @return An instance of the specified class or null if the json / xml was not in the correct format for this class
	 */
	public <T> T readString(String source, Class<T> resultType) {
		T result = null;
		try {
			result = getObjectMapper().readValue(source, resultType);
		} catch (JsonParseException e) {
			log.error("Exception parsing JSON / XML in string: \"" + source + "\"", e);
		} catch (JsonMappingException e) {
			log.error("Exception mapping JSON / XML in string: \"" + source + "\"", e);
		} catch (IOException e) {
			log.error("IOException reading JSON / XML in string: \"" + source + "\"", e);
		}
		return(result);
	}
	
	/**
	 * Takes a url / path and performs a HTTP GET operation and turns the resulting json / xml string into instances of the specified class
	 * 
	 * @param path The url / path that we need to perform the http get against to obtain the json / xml
	 * @param resultType The class that the json / xml string needs to be interpreted as
	 * 
	 * @return An instance of the specified class or null if the json was not in the correct format for this class or there was an http error
	 */
	public <T> T read(String path, Class<T> resultType) {
		T result = null;
		if (getCoreBaseURL() == null) {
			log.error("The url to the ECKCore module has not been set (coreBaseURL)");
		} else {
			String fullPath = buildURL(path);
			try {
				log.debug("Calling URL: " + fullPath);
				URL url = new URL(fullPath);
				result = getObjectMapper().readValue(url, resultType);
			} catch (MalformedURLException e) {
				log.error("Malformed URL: \"" + fullPath + "\"", e);
			} catch (JsonParseException e) {
				log.error("Exception parsing JSON / XML from url: \"" + fullPath + "\"", e);
			} catch (JsonMappingException e) {
				log.error("Exception mapping JSON / XML from URL: \"" + fullPath + "\"", e);
			} catch (IOException e) {
				log.error("IOException reading JSON / XML from URL: \"" + fullPath + "\"", e);
			}
		}	
		return(result);
	}
}
