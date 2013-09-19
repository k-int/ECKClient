package com.k_int.euinside.client.module.europeana;

public class ProviderDetail extends ProviderBriefDetail {
	
	private String description;
	private String website;
	private String country;

	public ProviderDetail() {
	}
	
	/**
	 * Retrieves the description
	 * 
	 * @return The description
	 */
	public String getDescription() {
		return(description);
	}

	/**
	 * Sets the description
	 * 
	 * @param description The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retrieves the website
	 * 
	 * @return The website
	 */
	public String getWebsite() {
		return(website);
	}

	/**
	 * Sets the web site
	 * 
	 * @param website The web site
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * Retrieves the country
	 * 
	 * @return The country
	 */
	public String getCountry() {
		return(country);
	}

	/**
	 * Sets the country
	 * 
	 * @param country The country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: ProviderDetails\n";
		result += "Description: " + description + "\n"; 
		result += "Web Site: " + website + "\n"; 
		result += "Country: " + country + "\n";
		result += super.toString();
		return(result);
	}
}
