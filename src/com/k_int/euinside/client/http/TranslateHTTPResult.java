package com.k_int.euinside.client.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.xml.ClientXML;

public class TranslateHTTPResult<T> {
	private Log log = LogFactory.getLog(TranslateHTTPResult.class);

	private Class<T> instanceClass = null;

	@SuppressWarnings("unchecked")
	public TranslateHTTPResult() {
		try {
			// Determine our superclass
			Type type = getClass().getGenericSuperclass();
			if (type != null) {
				Type[] types = ((ParameterizedType)type).getActualTypeArguments();
				if (types != null) {
					if (types.length > 0) {
						// For Java 7 we need to extract the class name from toString, this still works in Java 8
						String className = types[0].toString();
						className = className.replace("class ", "");
						instanceClass = (Class<T>)Class.forName(className);

						// For Java 8 we could just use this one liner instead of the above 3 lines, therefore we do not get caught out if toString changes
//						instanceClass = (Class<T>)Class.forName(types[0].getTypeName());

						// Ensure the logging now output the correct class
						log = LogFactory.getLog(instanceClass);
					}
				}
			}
		} catch (Exception e) {
			log.error("Unable to determine class type", e);
		}
	}

	/**
	 * Maps the returned result into the specified class
	 * 
	 * @param result ... The result returned in the form of the specified class
	 * 
	 * @return The interpreted data returned from the server
	 */
	public T translate(HttpResult httpResult) {
		T result = null;
		if (instanceClass == null) {
			log.error("Instance class not set unable to translate");
		} else {
			String data = httpResult.getContent();
			if (isResultAcceptable(httpResult.getHttpStatusCode())) {
				if (httpResult.isContentTypeJSON()) {
					result = ClientJSON.readJSONString(data, instanceClass);
				} else {
					result = ClientXML.readXMLString(data, instanceClass);
				}
			} else {
				log.error("Unexpected result from server, status: " + httpResult.getHttpStatusCode() + ", content: " + httpResult.getContent());
			}
		}
		return(result);
	}
	
	public boolean isResultAcceptable(int responseCode) {
		return(responseCode == HttpStatus.SC_OK);
	}
}
