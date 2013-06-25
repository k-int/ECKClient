package com.k_int.euinside.client.module.metadataDefinition.profile;

/**
 * The Field class represents a field as defined by the Metadata Definition Module
 */
public class Field {

	private String definition;
	private String example;
	private String field;
	private String xpath;
	
	public Field() {
	}

	/**
	 * Retrieves the definition of the field
	 * 
	 * @return The field definition
	 */
	public String getDefinition() {
		return(definition);
	}

	/**
	 * Sets the definition of the field
	 * 
	 * @param definition The field definition
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 * Retrieves an example value for this field
	 * 
	 * @return The example value
	 */
	public String getExample() {
		return(example);
	}

	/**
	 * Sets the example value for this field
	 * 
	 * @param example The example value
	 */
	public void setExample(String example) {
		this.example = example;
	}

	/**
	 * Retrieves the field code
	 * 
	 * @return The code for this field
	 */
	public String getField() {
		return(field);
	}

	/**
	 * Sets the field code
	 * 
	 * @param field The field code
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * Retrieves the xpath for this field
	 * 
	 * @return The xpath for this field
	 */
	public String getXpath() {
		return(xpath);
	}

	/**
	 * Sets the xpath for this field
	 * 
	 * @param xpath The xpsth that represents this field
	 */
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Field:\n"; 
		result += "\tdefinition: " + definition + "\n";
		result += "\texample: " + example + "\n";
		result += "\tfield: " + field + "\n";
		result += "\txpath: " + xpath + "\n";
		return(result);
	}
}
