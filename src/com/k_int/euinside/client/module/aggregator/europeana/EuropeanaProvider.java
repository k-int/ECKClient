package com.k_int.euinside.client.module.aggregator.europeana;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class contains Europeana provider details
 */
public class EuropeanaProvider extends EuropeanaItemResult {
	private static Log log = LogFactory.getLog(EuropeanaProvider.class);

	private String country;
	private String acronym;
	private String altname;
	private String scope;
	private String domain;
	private String geolevel;
	private String role;
	private String website;

	public EuropeanaProvider() {
	}

	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retrieves the country this provider is associated with
	 * 
	 * @return The contry associated with this provider
	 */
	public String getCountry() {
		return(country);
	}

	/**
	 * Sets the country associated with this provider
	 * 
	 * @param country The country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Retrieves the acronym
	 * 
	 * @return The acronym
	 */
	public String getAcronym() {
		return(acronym);
	}

	/**
	 * Sets the acronym
	 * 
	 * @param acronym The acronym
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	/**
	 * Retrieves the alternative name
	 * 
	 * @return The alternative name
	 */
	public String getAltname() {
		return(altname);
	}

	/**
	 * Sets the alternative name
	 * 
	 * @param altname The alternative name
	 */
	public void setAltname(String altname) {
		this.altname = altname;
	}

	/**
	 * Retrieves the scope
	 * 
	 * @return The scope
	 */
	public String getScope() {
		return(scope);
	}

	/**
	 * Sets the scope
	 * 
	 * @param scope The scope
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * Retrieves the domain
	 * 
	 * @return The domain
	 */
	public String getDomain() {
		return(domain);
	}

	/**
	 * Sets the domain
	 * 
	 * @param domain The domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * Retrieves the geo level
	 * 
	 * @return The geo level
	 */
	public String getGeolevel() {
		return(geolevel);
	}

	/**
	 * Sets the geo level
	 * 
	 * @param geolevel The geo level
	 */
	public void setGeolevel(String geolevel) {
		this.geolevel = geolevel;
	}

	/**
	 * Retrieves the role
	 * 
	 * @return The role
	 */
	public String getRole() {
		return(role);
	}

	/**
	 * Sets the role
	 * 
	 * @param role The role
	 */
	public void setRole(String role) {
		this.role = role;
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
	 * Sets the website
	 * 
	 * @param website The website
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
}
