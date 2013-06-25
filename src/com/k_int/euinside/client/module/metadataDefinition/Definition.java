package com.k_int.euinside.client.module.metadataDefinition;

import com.k_int.euinside.client.json.ClientJSON;
import com.k_int.euinside.client.module.BaseModule;
import com.k_int.euinside.client.module.CommandLineArguments;
import com.k_int.euinside.client.module.Action;
import com.k_int.euinside.client.module.Module;
import com.k_int.euinside.client.module.metadataDefinition.error.Error;
import com.k_int.euinside.client.module.metadataDefinition.error.Errors;
import com.k_int.euinside.client.module.metadataDefinition.language.Languages;
import com.k_int.euinside.client.module.metadataDefinition.profile.Field;
import com.k_int.euinside.client.module.metadataDefinition.profile.Profiles;

/**
 * This class provides the interface for the Metadata Definition module
 *  
 */
public class Definition extends BaseModule {

	/**
	 * Builds the path for the profiles interface given the language, profile and field
	 * 
	 * @param language The language that is required
	 * @param profile The profile that the field belongs to
	 * @param field The field the definition is required for
	 * 
	 * @return The path for the profiles interface for the definition module given the supplied parameters
	 */
	static private String BuildProfilePath(String language, String profile, String field) {
		String definitionPath = PATH_SEPARATOR + Action.DEFINITION_PROFILES.getName();
		if ((language != null) && !language.isEmpty()){
			definitionPath += PATH_SEPARATOR + language;
			if ((profile != null) && !profile.isEmpty()) {
				definitionPath += PATH_SEPARATOR + profile;
				if ((field != null) && !field.isEmpty()) {
					definitionPath += PATH_SEPARATOR + field;
				}
			}
		}
		return(buildPath(Module.DEFINITION, definitionPath));
	}

	/**
	 * Builds the path for the languages interface
	 * 
	 * @return The path for the languages interface
	 */
	static private String BuildLanguagePath() {
		String definitionPath = PATH_SEPARATOR + Action.DEFINITION_LANGUAGES.getName();
		return(buildPath(Module.DEFINITION, definitionPath));
	}

	/**
	 * Builds the path for the errors interface
	 * 
	 * @param language The language the error is required in
	 * @param errorCode The error code the definition is required for
	 * 
	 * @return The path for the errors interface given the parameters
	 */
	static private String BuildErrorPath(String language, String errorCode) {
		String definitionPath = PATH_SEPARATOR + Action.DEFINITION_ERRORS.getName();
		if ((language != null) && !language.isEmpty()){
			definitionPath += PATH_SEPARATOR + language;
			if ((errorCode != null) && !errorCode.isEmpty()) {
				definitionPath += PATH_SEPARATOR + errorCode;
			}
		}
		return(buildPath(Module.DEFINITION, definitionPath));
	}

	/**
	 * Returns the Error definition given the language and error code
	 * 
	 * @param language The language you want the error in
	 * @param errorCode The error code you want the definition for
	 * 
	 * @return The error definition for the supplied error code
	 */
	static public Error getError(String language, String errorCode) {
		Error result = null;
		if (((language != null) && !language.isEmpty()) &&
			((errorCode != null) && !errorCode.isEmpty())) {
			result = ClientJSON.readJSON(BuildErrorPath(language, errorCode), Error.class);
		}
		return(result);
	}

	/**
	 * Retrieves all the errors for the given language
	 * 
	 * @param language The language you want the errors for
	 * 
	 * @return The errors for the specified language
	 */
	static public Errors getErrors(String language) {
		Errors result = ClientJSON.readJSON(BuildErrorPath(language, null), Errors.class);
		return(result);
	}

	/**
	 * Retrieves all the errors in the Metadata Definition module default language
	 * 
	 * @return All errors in the Metadata Definition module default language
	 */
	static public Errors getErrors() {
		return(getErrors(null));
	}

	/**
	 * Retrieves all the languages supported by the Metadata Definition module
	 * 
	 * @return All the languages supported by the Metadata Definition module
	 */
	static public Languages getLanguages() {
		Languages result = ClientJSON.readJSON(BuildLanguagePath(), Languages.class);
		return(result);
	}

	/**
	 * Retrieves the definition of a field for a given language and profile
	 * @param language The language you want the field details are required for
	 * @param profile The profile the field belongs to
	 * @param field The field you want the defintition for
	 * 
	 * @return The definition of the field for the given language and profile
	 */
	static public Field getProfileField(String language, String profile, String field) {
		Field result = null;
		if (((language != null) && !language.isEmpty()) &&
			((profile != null) && !profile.isEmpty()) &&
			((field != null) && !field.isEmpty())) {
			result = ClientJSON.readJSON(BuildProfilePath(language, profile, field), Field.class);
		}
		return(result);
	}

	/**
	 * Retrieves the specified profile for the the given language
	 * 
	 * @param language The language that the definition of the profile is required in
	 * @param profile The code for the profile that you want the definition for
	 * 
	 * @return The definition for the specified profile (if no profile is supplied then all profiles are returned)
	 */
	static public Profiles getProfiles(String language, String profile) {
		Profiles result = ClientJSON.readJSON(BuildProfilePath(language, profile, null), Profiles.class);
		return(result);
	}

	/**
	 * Returns the definition of all the profiles for the specified language
	 * 
	 * @param language The language you want the definition of the profiles in
	 * 
	 * @return The definition of all the profiles for the specified language
	 */
	static public Profiles getProfiles(String language) {
		return(getProfiles(language, null));
	}

	/**
	 * Retries the definition of all the profiles in the Metadata Definition modules default language
	 * 
	 * @return The definition of all the profiles
	 */
	static public Profiles getProfiles() {
		return(getProfiles(null, null));
	}

	/**
	 * Exercises all the methods with the passed in parameters
	 * 
	 * @param args The parameters passed in on the command line, valid parameters are:<br/><br/>
	 *  <table class="arguments">
	 * 	    <col width="15%"/>
	 *      <col width="85%"/>
	 *      <tr><td>-coreBaseURL</td><td>The base URL of the core module</td></tr>
	 *      <tr><td>-errorCode</td><td>The error code to request the definition for</td></tr>
	 *      <tr><td>-language</td><td>The languages the definitions are to be returned in</td></tr>
	 *      <tr><td>-profile</td><td>The profile that you are interested in</td></tr>
     *  </table>
	 */
	public static void main(String [] args)
	{
		CommandLineArguments arguments = parseCommandLineArguments(args);
		
		System.out.println("All Errors for the default language");
		Errors errors = getErrors();
		if (errors == null) {
			System.out.println("Error obtaining the errors");
		} else {
			System.out.println(errors.toString());
		}
		
		System.out.println("All Errors for the language: \"" + arguments.getLanguage() + "\"");
		errors = getErrors(arguments.getLanguage());
		if (errors == null) {
			System.out.println("Error obtaining the errors");
		} else {
			System.out.println(errors.toString());
		}
		
		System.out.println("Error \"" + arguments.getErrorCode() + "\" for language: \"" + arguments.getLanguage() + "\"");
		Error error = getError(arguments.getLanguage(), arguments.getErrorCode());
		if (error == null) {
			System.out.println("Error obtaining the errors");
		} else {
			System.out.println(error.toString());
		}
	
		System.out.println("All languages");
		Languages languages = getLanguages();
		if (languages == null) {
			System.out.println("Error obtaining the languages");
		} else {
			System.out.println(languages.toString());
		}
		
		System.out.println("All Profiles for all languages");
		Profiles profiles = getProfiles();
		if (profiles == null) {
			System.out.println("Error obtaining the profiles");
		} else {
			System.out.println(profiles.toString());
		}
		
		System.out.println("All Profiles for language: \"" + arguments.getLanguage() + "\"");
		profiles = getProfiles(arguments.getLanguage());
		if (profiles == null) {
			System.out.println("Error obtaining the profiles");
		} else {
			System.out.println(profiles.toString());
		}
		
		System.out.println("Profile: \"" + arguments.getProfile() + "\" for language: \"" + arguments.getLanguage() + "\"");
		profiles = getProfiles(arguments.getLanguage(), arguments.getProfile());
		if (profiles == null) {
			System.out.println("Error obtaining the profiles");
		} else {
			System.out.println(profiles.toString());
		}
		
		System.out.println("Field: " + arguments.getField() + "\" for Profile: \"" + arguments.getProfile() + "\" for language: \"" + arguments.getLanguage() + "\"");
		Field field = getProfileField(arguments.getLanguage(), arguments.getProfile(), arguments.getField());
		if (field == null) {
			System.out.println("Error obtaining the field");
		} else {
			System.out.println(field.toString());
		}
	}
}
