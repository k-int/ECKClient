package com.k_int.euinside.client;

/**
 * The BaseClient class contains the functionality that is shared by all the clients 
 */
public class BaseClient {
	private static String coreBaseURL = null;

	/**
	 * Retrieves the URL to the ECKCore
	 *  
	 * @return The URL to the ECKCore
	 */
	static public String getCoreBaseURL() {
		return(coreBaseURL);
	}

	/**
	 * Sets the URL to be used when communicating with the ECKCore
	 * 
	 * @param coreBaseURL The URL to the ECKCore
	 */
	static public void setCoreBaseURL(String coreBaseURL) {
		BaseClient.coreBaseURL = coreBaseURL;
	}

	/**
	 * Builds a URL that will be used to communicate with the ECKCore
	 * 
	 * @param path The path you want to access within the ECKCore
	 * 
	 * @return The URL to be used to access the ECKCore
	 */
	static public String buildURL(String path) {
		String url = path;

		// Only prefix with the base url if the path does not begin with http
		if (!path.startsWith("http")) {
			url = coreBaseURL + "/" + path;
		}
		return(url);
	}
}
