package com.k_int.euinside.client.module.aggregator.europeana;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.json.baseJSON;

/**
 * This class represents a record as returned by the Europeana API
 * This does not represent all the items in the record, it only contains the elements we are interested in
 * If any of the other elements become useful we can add them when required
 *
 */
public class EuropeanaRecordObject extends baseJSON {
	private static Log log = LogFactory.getLog(EuropeanaRecordObject.class);
	private String about;
	private List<Map<String, Object>> agents;
	private List<Map<String, Object>> concepts;
	private List<Map<String, Object>> places;
	private List<EuropeanaRecordProxy> proxies = new ArrayList<EuropeanaRecordProxy>();
	private List<Map<String, Object>> timespans;
	private List<String> year;
	private Long timestampCreated;
	private Long timestampUpdated;

	/**
	 * Constructor, This disables logging of unmapped fields
	 */
	public EuropeanaRecordObject() {
		// As we are not collecting all the fields, we do not want to fill up the log file
		setLogUnknownFields(false);
	}

	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retrieves the europeana identifier
	 * 
	 * @return The europeana identifier
	 */
	public String getAbout() {
		return(about);
	}

	/**
	 * Sets the europeana identifier
	 * 
	 * @param about The europeana identifier
	 */
	public void setAbout(String about) {
		this.about = about;
	}

	/**
	 * Retrieves the agents
	 * 
	 * @return The agents
	 */
	public List<Map<String, Object>> getAgents() {
		return(agents);
	}

	/**
	 * Sets the agents
	 * 
	 * @param agents The agents
	 */
	public void setAgents(List<Map<String, Object>> agents) {
		this.agents = agents;
	}

	/**
	 * Retrieves the concepts
	 * 
	 * @return The concepts
	 */
	public List<Map<String, Object>> getConcepts() {
		return(concepts);
	}

	/**
	 * Sets the concepts
	 * 
	 * @param concepts The concepts
	 */
	public void setConcepts(List<Map<String, Object>> concepts) {
		this.concepts = concepts;
	}

	/**
	 * Retrieves the places
	 * 
	 * @return The places
	 */
	public List<Map<String, Object>> getPlaces() {
		return(places);
	}

	/**
	 * Sets the places
	 * 
	 * @param places The places
	 */
	public void setPlaces(List<Map<String, Object>> places) {
		this.places = places;
	}

	/**
	 * Retrieves the proxies
	 * 
	 * @return The proxies
	 */
	public List<EuropeanaRecordProxy> getProxies() {
		return(proxies);
	}

	/**
	 * Sets the proxies
	 * 
	 * @param proxies The proxies
	 */
	public void setProxies(List<EuropeanaRecordProxy> proxies) {
		this.proxies = proxies;
	}

	/**
	 * Retrieves the timespans
	 * 
	 * @return The timespans
	 */
	public List<Map<String, Object>> getTimespans() {
		return(timespans);
	}

	/**
	 * Sets the timespans
	 * 
	 * @param timespans The timespans
	 */
	public void setTimespans(List<Map<String, Object>> timespans) {
		this.timespans = timespans;
	}

	/**
	 * Retrieves the years
	 * 
	 * @return The years
	 */
	public List<String> getYear() {
		return(year);
	}

	/**
	 * Sets the years
	 * 
	 * @param year The years
	 */
	public void setYear(List<String> year) {
		this.year = year;
	}

	/**
	 * Retrieves the timestamp of when the record was created
	 * 
	 * @return The timestamp for when the record was created
	 */
	public Long getTimestampCreated() {
		return(timestampCreated);
	}

	/**
	 * Sets the timestamp of when the record was created
	 * 
	 * @param timestampCreated Timestamp for when the record was created
	 */
	public void setTimestampCreated(Long timestampCreated) {
		this.timestampCreated = timestampCreated;
	}

	/**
	 * Retrieves the timestamp of when the record was updated
	 * 
	 * @return The timestamp for when the record was updated
	 */
	public Long getTimestampUpdated() {
		return(timestampUpdated);
	}

	/**
	 * Sets the timestamp of when the record was updated
	 * 
	 * @param timestampUpdated Timestamp for when the record was updated
	 */
	public void setTimestampUpdated(Long timestampUpdated) {
		this.timestampUpdated = timestampUpdated;
	}
}
