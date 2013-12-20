package com.k_int.euinside.client.module.aggregator.europeana;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.k_int.euinside.client.json.baseJSON;
import com.k_int.euinside.client.xml.ClientXML;

/**
 * Class that represents the enrichments that have been performed by europeana
 *
 */
@JsonInclude(value=JsonInclude.Include.NON_DEFAULT)
public class EuropeanaEnrichments extends baseJSON {
	private static Log log = LogFactory.getLog(EuropeanaEnrichments.class);

	private String europeanaIdentifier;
	private List<String> identifiers;
	private List<Map<String, Object>> agents;
	private List<Map<String, Object>> concepts;
	private List<Map<String, Object>> places;
	private List<Map<String, Object>> timespans;
	private Long timestampCreated;
	private Long timestampUpdated;
	private List<String> years;

	/**
	 * Constructor
	 */
	public EuropeanaEnrichments() {
	}

	/**
	 * Constructor that extracts the enrichments from a full europeana record
	 * 
	 * @param record The full europeana record
	 */
	public EuropeanaEnrichments(EuropeanaRecordObject record) {

		if (record != null) {
			// Set the enrichments from the record
			setAgents(record.getAgents());
			setConcepts(record.getConcepts());
			setEuropeanaIdentifier(record.getAbout());
			setIdentifiers(record.getProxies());
			setPlaces(record.getPlaces());
			setTimespans(record.getTimespans());
			setTimestampCreated(record.getTimestampCreated());
			setTimestampUpdated(record.getTimestampUpdated());
			setYears(record.getYear());
		}
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
	public String getEuropeanaIdentifier() {
		return(europeanaIdentifier);
	}

	/**
	 * Sets the europeana identifier
	 * 
	 * @param europeanaIdentifier The value to set the europeana to
	 */
	public void setEuropeanaIdentifier(String europeanaIdentifier) {
		this.europeanaIdentifier = europeanaIdentifier;
	}

	/**
	 * Retrieves the identifiers
	 * 
	 * @return The identifiers
	 */
	@JacksonXmlElementWrapper(localName="identifiers")
	@JacksonXmlProperty(localName="identifier")
	public List<String> getIdentifiers() {
		return(identifiers);
	}

	/**
	 * Sets the identifiers from the proxies
	 * 
	 * @param proxies The proxies from where we retrieve the identifiers
	 */
	public void setIdentifiers(List<EuropeanaRecordProxy> proxies) {
		// Determine the identifiers
		if (proxies != null) {
			identifiers = new ArrayList<String>();
			for (EuropeanaRecordProxy proxy : proxies) { 
				Map<String, Object> proxyDcIdentifiers = proxy.getDcIdentifier(); 
				if (proxyDcIdentifiers != null) {
					Collection<Object> proxyIdentifiersCollection = proxyDcIdentifiers.values();
					for (Object identifier : proxyIdentifiersCollection) {
						if (identifier instanceof String) {
							identifiers.add((String)identifier);
						} else if (identifier instanceof ArrayList) {
							@SuppressWarnings("unchecked")
							List<String> identifiersList = (List<String>)identifier; 
							for (String identifierList : identifiersList) {
								identifiers.add(identifierList);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Retrieves the agents
	 * 
	 * @return The agents
	 */
	@JacksonXmlElementWrapper(localName="agents")
	@JacksonXmlProperty(localName="agent")
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
	@JacksonXmlElementWrapper(localName="concepts")
	@JacksonXmlProperty(localName="concept")
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
	@JacksonXmlElementWrapper(localName="places")
	@JacksonXmlProperty(localName="place")
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
	 * Retrieves the timespans
	 * 
	 * @return The timespans
	 */
	@JacksonXmlElementWrapper(localName="timespans")
	@JacksonXmlProperty(localName="timespan")
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

	/**
	 * Retrieves the years
	 * 
	 * @return The years
	 */
	@JacksonXmlElementWrapper(localName="years")
	@JacksonXmlProperty(localName="year")
	public List<String> getYears() {
		return(years);
	}

	/**
	 * Sets the years
	 * 
	 * @param years The years
	 */
	public void setYears(List<String> years) {
		this.years = years;
	}

	/**
	 * Turns this class into an xml representation of the enrichments
	 * 
	 * @return The xml that represents the enrichments
	 */
	public String toXML() {
		return(ClientXML.writeXML(this));
	}
}
