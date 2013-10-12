package com.k_int.euinside.client.xml;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.MappingClient;

/**
 * The ClientJSON class handles xml manipulation for us, through one of the following methods
 * <ul><li>Being passed an xml string</li>
 * <li>A path to a url which will be executed using HTTP GET with the resultant content expected to be xml</li>
 * <li>A HttpResult object where the content is expected to be an xml string</li>
 * </ul> 
 * All methods return class instances of the specified type if the xml is of the correct format. 
 */
public class ClientXML extends MappingClient {

	private static ClientXML workerObject = null;
	private static XmlMapper mapper = null;
	
	static {
		workerObject = new ClientXML();
		mapper = new XmlMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}

	@Override
	protected ObjectMapper getObjectMapper() {
		return(mapper);
	}

	/** 
	 * Converts the specified xml string into an instance of the specified class
	 * 
	 * @param xml The xml to be converted
	 * @param resultType The type of the class that is to be generated
	 * 
	 * @return An instance of the specified class if the xml is correctly formatted
	 */
	static public <T> T readXMLString(String xml, Class<T> resultType) {
		return(workerObject.readString(xml, resultType));
	}

	/**
	 * Performs a HTTP GET on the specified path and converts the resulting xml into an instance of the specified class
	 *  
	 * @param path The path that an HTTP GET is to be executed against
	 * @param resultType The type of the class that is to be generated
	 *
	 * @return An instance of the specified class if the xml is correctly formatted
	 */
	static public <T> T readXML(String path, Class<T> resultType) {
		return(workerObject.read(path, resultType));
	}

	/**
	 * Checks the result of the HttpResult and converts the content into an instance of the specified class
	 * 
	 * @param httpResult The http result object that we will obtain the xml from
	 * @param resultType The type of the class that is to be generated
	 *
	 * @return An instance of the specified class if the xml is correctly formatted
	 */
	static public <T> T readXML(HttpResult httpResult, Class<T> resultType) {
		T result = null;
		if ((httpResult.getHttpStatusCode() == HttpServletResponse.SC_OK) ||
		    (httpResult.getHttpStatusCode() == HttpServletResponse.SC_PRECONDITION_FAILED)) {
			result = readXMLString(httpResult.getContent(), resultType);
		}
		return(result);
	}
}
