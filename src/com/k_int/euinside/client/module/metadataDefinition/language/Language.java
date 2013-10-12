package com.k_int.euinside.client.module.metadataDefinition.language;

import com.k_int.euinside.client.json.baseJSON;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Language class defines a Language as returned by the Metadata Defiinition module
 */
public class Language extends baseJSON {
	private static Log log = LogFactory.getLog(Language.class);

	String code;
	String language;
	
	public Language() {
	}

	@Override
	protected Log getLogger() {
		return(log);
	}
	
	/**
	 * Retrieves the language code
	 * 
	 * @return The language code
	 */
	public String getCode() {
		return(code);
	}

	/**
	 * Sets the language code
	 * 
	 * @param code The language code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Retrieves the definition of the language, 
	 * 
	 * @return The definition of the language
	 */
	public String getLanguage() {
		return(language);
	}

	/**
	 * Sets the defintiion of the language
	 * 
	 * @param language The language definition
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
}
