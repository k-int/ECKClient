package com.k_int.euinside.client.module.validation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.k_int.euinside.client.json.baseJSON;

/**
 * This class contains an error that occurred during validation
 */
@JacksonXmlRootElement(localName="error")
public class ValidationResultRecordError extends baseJSON {
	private static Log log = LogFactory.getLog(ValidationResultRecordError.class);

	@JacksonXmlProperty(isAttribute=true)  
	private String plugin;

	@JacksonXmlText
	private String text;

	@JsonCreator
	public ValidationResultRecordError(@JsonProperty("''") String text) {
		setText(text);
	}

	public ValidationResultRecordError() {
	}
	
	@Override
	protected Log getLogger() {
		return(log);
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

	@JsonSetter("Plugin")
	public void setPluginSemantika(String plugin) {
		setPlugin(plugin);
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
	 * Sets the text of the error from the semantika validation server
	 * 
	 * @param text The error that was generated by the validator
	 */
	@JsonSetter("Value")
	public void setTextSemantika(String text) {
		this.text = text;
	}
}
