package com.k_int.euinside.client.module.metadataDefinition.language;

/**
 * The Language class defines a Language as returned by the Metadata Defiinition module
 */
public class Language {
	String code;
	String language;
	
	public Language() {
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
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Language:\n"; 
		result += "\tcode: " + code + "\n";
		result += "\tlanguage: " + language + "\n";
		return(result);
	}

}
