package com.k_int.euinside.client.module.setmanager.validate;

import java.util.ArrayList;

/**
 * The ValidationErrors class is a list of ValidationError and has all the functionality of a normal list 
 */
public class ValidationErrors extends ArrayList<ValidationError>{

	private static final long serialVersionUID = 1234501L;

	public ValidationErrors() {
	}

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: ValidationErrors\n";
		for (ValidationError validationError : this) {
			result += validationError.toString();
		}
		return(result);
	}
}
