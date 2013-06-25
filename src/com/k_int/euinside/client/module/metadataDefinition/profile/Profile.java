package com.k_int.euinside.client.module.metadataDefinition.profile;

import java.util.ArrayList;

/**
 * The Profile class represents a Preofile as returned by the Metadata Definition module
 */
public class Profile {
	private String definition;
	private String profile;
	private ArrayList<Field> fields;
	
	public Profile() {
	}

	/**
	 * Retrieves the definition of the profile
	 * 
	 * @return The profile definiition
	 */
	public String getDefinition() {
		return(definition);
	}

	/**
	 * Sets the definition for the profile
	 * 
	 * @param definition The definition for this profile
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 * Retrieves the code for this profile
	 * 
	 * @return The code for this profile
	 */
	public String getProfile() {
		return(profile);
	}

	/**
	 * Sets the code for this profile
	 * 
	 * @param profile The profile code
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * Retrieves the fields that are associated with this profile
	 * 
	 * @return The fields contained in this profile
	 */
	public ArrayList<Field> getFields() {
		return(fields);
	}

	/**
	 * Sets the fields that are associated with this profile
	 * 
	 * @param fields The fields contained in this profile
	 */
	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: Profile:\n"; 
		result += "\tdefinition: " + definition + "\n";
		result += "\tprofile: " + profile + "\n";
		for (Field field : fields) {
			result += field.toString();
		}
		return(result);
	}
}
