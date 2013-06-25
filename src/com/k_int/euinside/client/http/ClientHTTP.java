package com.k_int.euinside.client.http;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.k_int.euinside.client.BaseClient;
import com.k_int.euinside.client.Error;
import com.k_int.euinside.client.HttpResult;

/**
 * The ClientHTTP class wraps the basic http GET and POST functionality 
 */
public class ClientHTTP extends BaseClient {
	private static Log log = LogFactory.getLog(ClientHTTP.class);

	static private String FILENAME_PREFIX = "filename_";
	static private String PARAMETER_PREFIX = "parameter_";
	static private String EXTENSION_XML = ".xml"; 
	static private String EXTENSION_ZIP = ".zip";
	static private String CONTENT_TYPE_CHARSET_UTF_8 = "; charset=" + StandardCharsets.UTF_8.name(); 
	
	// Why isn't this part of the ContentType class
	static private String CONTENT_TYPE_APPLICATION_XML = "application/xml"; // The one in the ContentType class automatically appends iso-8859-1
	static private String CONTENT_TYPE_APPLICATION_ZIP = "application/zip";

	/**
	 * Performs a HTTP PUT to send the supplied data to the given path
	 * 
	 * @param path The URL / Path of where the data needs to be sent
	 * @param xmlData An array of xml files that are to be sent
	 * @param zipData An array of zip files that are to be sent
	 * 
	 * @return A HttpResult object that can be interrogated to see if the call was successful or not
	 */
	static public HttpResult sendBytes(String path, ArrayList<byte[]> xmlData, ArrayList<byte[]> zipData) {
		return(sendBytes(path, xmlData, zipData, null));
	}

	/**
	 * Performs a HTTP PUT to send the supplied data to the given path
	 * 
	 * @param path The URL / Path of where the data needs to be sent
	 * @param xmlData An array of xml files that are to be sent
	 * @param zipData An array of zip files that are to be sent
	 * @param attributes An array of attribute name / value pairs that need to be added to the path 
	 * 
	 * @return A HttpResult object that can be interrogated to see if the call was successful or not
	 */
	static public HttpResult sendBytes(String path, ArrayList<byte[]> xmlData, ArrayList<byte[]> zipData, ArrayList<BasicNameValuePair> attributes) {
		HttpResult result = new HttpResult();
		
        MultipartEntity requestEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        int filenameCount = 1;

		if (xmlData != null) {
			for (byte[] xmlBytes : xmlData) {
				requestEntity.addPart(PARAMETER_PREFIX + filenameCount, new ByteArrayBody(xmlBytes, CONTENT_TYPE_APPLICATION_XML + CONTENT_TYPE_CHARSET_UTF_8, FILENAME_PREFIX + filenameCount));
				filenameCount++;
			}
		}
		
		if (zipData != null) {
			for (byte[] zipBytes : zipData) {
				requestEntity.addPart(PARAMETER_PREFIX + filenameCount, new ByteArrayBody(zipBytes, CONTENT_TYPE_APPLICATION_ZIP, FILENAME_PREFIX + filenameCount));
				filenameCount++;
			}
		}
		
		result = send(path, requestEntity, attributes);
		return(result);
	}

	/**
	 * Performs a HTTP PUT to send the supplied files to the given path
	 * 
	 * @param path The URL / Path of where the files need to be sent
	 * @param filenames An array of filenames that need to be sent
	 * 
	 * @return A HttpResult object that can be interrogated to see if the call was successful or not
	 */
	static public HttpResult sendFiles(String path, ArrayList<String> filenames) {
		return(sendFiles(path, filenames, null));
	}
	
	/**
	 * Performs a HTTP PUT to send the supplied files to the given path
	 * 
	 * @param path The URL / Path of where the files need to be sent
	 * @param filenames An array of filenames that need to be sent
	 * @param attributes An array of attribute name / value pairs that need to be added to the path 
	 * 
	 * @return A HttpResult object that can be interrogated to see if the call was successful or not
	 */
	static public HttpResult sendFiles(String path, ArrayList<String> filenames, ArrayList<BasicNameValuePair> attributes) {
		HttpResult result = new HttpResult();
		
        MultipartEntity requestEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        int filenameCount = 1;
        String filenameContentType = null;

		if (filenames != null) {
			for (String filename : filenames) {
				File fileObject = new File(filename);
				
				// does the file exist
				if (fileObject.canRead()) {
					// If the filename does not have the extension .xml or .zip then we will abandon the post
					if (filename.endsWith(EXTENSION_XML)) {
						filenameContentType = CONTENT_TYPE_APPLICATION_XML;
					} else if (filename.endsWith(EXTENSION_ZIP)) {
						filenameContentType = CONTENT_TYPE_APPLICATION_ZIP;
					} else {
						// We do not understand the extension
						log.info("Filename has an unknwon extension: " + filename);
						result.setCallResult(Error.UNKNOWN_FILE_TYPE);
					}

					if (result.getCallResult() == Error.NONE) {
						log.info("Adding filename: " + filename);
						requestEntity.addPart(PARAMETER_PREFIX + filenameCount, new FileBody(fileObject, filenameContentType, StandardCharsets.UTF_8.name()));
						filenameCount++;
					}
				} else {
					log.info("unable to read file: " + filename);
					result.setCallResult(Error.UNABLE_TO_READ_FILE);
				}
			}
		}

		// If we did not have any errors, perform the post
		if (result.getCallResult() == Error.NONE) {
			result = send(path, requestEntity, attributes);
		}
		return(result);
	}

	/**
	 * Performs a HTTP GET
	 *  
	 * @param path The url / path to perform the GET operation against
	 * 
	 * @return A HttpResult object that can be interrogated to see if the call was successful or not
	 */
	static public HttpResult send(String path) {
		return(send(path, null, null));
	}

	/**
	 * Performs a HTTP operation against the specified  path
	 * 
	 * @param path The URL path to perform the operation against 
	 * @param requestEntity The entities to be posted, if this field is null then a GET will occur
	 * @param attributes An array of attribute name / value pairs that need to be added to the path
	 *  
	 * @return A HttpResult object that can be interrogated to see if the call was successful or not
	 */
	static public HttpResult send(String path, MultipartEntity requestEntity, ArrayList<BasicNameValuePair> attributes) {
		HttpResult result = new HttpResult();
		if (attributes != null) {
			for (BasicNameValuePair attribute : attributes) {
				try {
					requestEntity.addPart(attribute.getName(), new StringBody(attribute.getValue()));
				} catch (UnsupportedEncodingException e) {
					log.error("UnsupportedEncodingException thrown, attribute: \"" + attribute.getName() + "\", Value: \"" + attribute.getValue() + "\"", e);
					result.setCallResult(Error.ENCODEING);
				}		
			}
		}
		
		if (result.getCallResult() == Error.NONE){
			// Now we can execute the query
	        HttpClient httpclient = new DefaultHttpClient();
	        try {
	        	String url = buildURL(path);
	        	HttpRequestBase httpRequest;
	        	if (requestEntity == null) {
	        		httpRequest = new HttpGet(url);
	        	} else {
	        		HttpPost httpPost = new HttpPost(url);
	        		httpPost.setEntity(requestEntity);
	        		httpRequest = httpPost;
	        	}

	            log.info("executing request " + httpRequest.getRequestLine());
	            try {
		            HttpResponse response = httpclient.execute(httpRequest);
		            HttpEntity resEntity = response.getEntity();
		            int httpStatusCode = response.getStatusLine().getStatusCode();
	            	result.setContent(EntityUtils.toString(resEntity, StandardCharsets.UTF_8));		            	
		            result.setHttpStatusCode(httpStatusCode);
		            if ((httpStatusCode != HttpServletResponse.SC_OK) && (httpStatusCode != HttpServletResponse.SC_ACCEPTED)) {
		            	result.setCallResult(Error.HTTP_ERROR);
		            }
	
		            try {
		            	EntityUtils.consume(resEntity);
		            } catch (IOException e) {
		            	// We do not care about an IOException during tidyup, so we ignore this one 
		            }
	            } catch (ClientProtocolException e) {
	            	log.error("ClientProtocolException while communicating with server, url: \"" + url + "\"", e);
	            	result.setCallResult(Error.CLIENT_PROTOCOL_EXCEPTION);
	            } catch (IOException e) {
	            	log.error("IOException while communicating with server, url: \"" + url + "\"", e);
	            	result.setCallResult(Error.IO_EXCEPTION);
	            }
	        } finally {
	            try {
	            	// We have finished with this httpclient now, so clean up all the resources that it may have used
	            	httpclient.getConnectionManager().shutdown();
	            } catch (Exception e) {
	            	// Ignore any exceptions during shutdown
	            }
	        }
		}

		// Return the result to the caller
		return(result);
	}
}