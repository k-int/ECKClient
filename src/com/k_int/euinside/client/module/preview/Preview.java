package com.k_int.euinside.client.module.preview;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.k_int.euinside.client.HttpResult;
import com.k_int.euinside.client.UnZipRecords;
import com.k_int.euinside.client.http.ClientHTTP;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Module;

public class Preview extends BaseModule{

	static public String DEFAULT_PROVIDER = "default";
	static public String NOT_BATCH = "single";
	
	/**
	 * Builds the path required for the validaion module
	 * 
	 * @return The path that is required for the validation module
	 */
	static private String buildPath(String provider, String batch) {
		return(buildPath(Module.PREVIEW, PATH_SEPARATOR + provider + PATH_SEPARATOR + batch + PATH_SEPARATOR + Action.PREVIEW_PREVIEW.getName() + PATH_SEPARATOR + "lido"));
	}

	/**
	 * Sends the supplied record to the preview module
	 *  
	 * @param xmlRecord ... The record that is to be sent
	 * 
	 * @return A byte array of the html record
	 */
	static public byte [] sendBytes(String provider, String batch, byte[] xmlRecord) {
		byte [] result = null;
		ArrayList<byte[]> recordArray = new ArrayList<byte[]>();
		recordArray.add(xmlRecord);
		HttpResult httpResult = ClientHTTP.sendBytes(buildPath(provider, batch), recordArray, null);
		if (httpResult.getHttpStatusCode() == HttpServletResponse.SC_OK) {
			// Now this should be a zip file, so we need tto unzip it
			UnZipRecords unzip = new UnZipRecords(httpResult.getContentBytes());
			result = unzip.getNextEntry(null);
		}
		return(result);
	}

	/**
	 * Sends the supplied record to the preview module
	 *  
	 * @param filename ... The file that contains the record that is to be sent
	 * 
	 * @return The result returned from the server
	 */
	static public HttpResult sendFiles(String provider, String batch, String filename) {
		ArrayList<String> filenameArray = new ArrayList<String>();
		filenameArray.add(filename);
		return(ClientHTTP.sendFiles(buildPath(provider, batch), filenameArray));
	}
	
	/**
	 * Exercises all the methods with the supplied parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 *      <tr><td>-coreBaseURL</td><td>The base URL of the core module</td></tr>
	 *      <tr><td>-filename</td><td>The name of a file that contains a record with no validation errors</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		
		if (!arguments.getFilenames().isEmpty()) {
			HttpResult result = sendFiles(arguments.getProvider(), arguments.getBatch(), arguments.getFilenames().get(0));
	
			if (result == null) {
				System.out.println("Failed to preview file");
			} else {
				if (arguments.getOutputFile() != null) {
					System.out.println("output written to file: " + arguments.getOutputFile());
					try {
						FileOutputStream outputStream = new FileOutputStream(new File(arguments.getOutputFile()));
						IOUtils.write(result.getContentBytes(), outputStream);
						outputStream.close();
					} catch (Exception e) {
						System.out.println("Exception writing to file: " + e.toString());
					}
				} else {
					System.out.println("Result from preview");
					System.out.println(result.toString());
				}
			}
		}
	}
}
