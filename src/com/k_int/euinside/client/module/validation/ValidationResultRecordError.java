package com.k_int.euinside.client.module.validation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * This class contains an error that occurred during validation
 */
@JacksonXmlRootElement(localName="error")
public class ValidationResultRecordError {
	@JacksonXmlProperty(isAttribute=true)  
	private String plugin;

	@JacksonXmlText
	private String text;

	@JsonCreator
	public ValidationResultRecordError(@JsonProperty("''") String text) {
		setText(text);
	}

	/**
	 * The plugin that discovered the error
	 * 
	 * @return The name of the plugin that generated the error
	 */
	public String getPlugin() {
		return(plugin);
	}

	/**
	 * Sets the name of the plugin that generated the error
	 * 
	 * @param plugin The name of the plugin
	 */
	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}

	/**
	 * Returns the text of the error
	 * 
	 * @return The error text
	 */
	public String getText() {
		return(text);
	}

	/**
	 * Sets the text of the error
	 * 
	 * @param text The error that was generated by the validator
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: ValidationResultRecordError:\n"; 
		result += "\tplugin: " + plugin + "\n";
		result += "\ttext: " + text + "\n";
		return(result);
	}
}