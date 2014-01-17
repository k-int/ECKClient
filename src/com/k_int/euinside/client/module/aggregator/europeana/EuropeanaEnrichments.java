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
	private String lidoRecID;
	private List<String> identifiers;
	private List<Map<String, Object>> agents;
	private List<Map<String, Object>> concepts;
	private List<Map<String, Object>> places;
	private List<Map<String, Object>> timespans;
	private String timestampCreated;
	private String timestampUpdated;
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
			// We need to get hold of the proxy record that defines the enrichments
			List<String> potentialEnrichedAbouts = null;
			for (EuropeanaRecordProxy proxy : record.getProxies()) {
				if (proxy.getEuropeanaProxy()) {
					// We have found the one we are after
					potentialEnrichedAbouts = proxy.getPotentialEnrichedAbout();
				}
			}
			
			// Set the enrichments from the record
			agents = mapFilterByAbout(record.getAgents(), potentialEnrichedAbouts);
			concepts = mapFilterByAbout(record.getConcepts(), potentialEnrichedAbouts);
			places = mapFilterByAbout(record.getPlaces(), potentialEnrichedAbouts);
			timespans = mapFilterByAbout(record.getTimespans(), potentialEnrichedAbouts);
			years = listFilterByAbout(record.getYear(), potentialEnrichedAbouts);
			setEuropeanaIdentifier(record.getAbout());
			setIdentifiers(record.getProxies());
			setTimestampCreated(record.getTimestampCreated());
			setTimestampUpdated(record.getTimestampUpdated());
		}
	}

	@Override
	protected Log getLogger() {
		return(log);
	}

	private List<Map<String, Object>> mapFilterByAbout(List<Map<String, Object>> objectMaps, List<String> abouts) {
		List<Map<String, Object>> result = null;
		if ((abouts != null) &&  (objectMaps != null)) {
			for (Map<String, Object> objectMap : objectMaps) {
				String objectAbout = (String)objectMap.get("about");
				if (objectAbout != null) {
					boolean found = false;
					if (abouts.contains(objectAbout)) {
						found = true;
					} else {
						// Look to see if this is part of an isPartOf
						for (Map<String, Object> objectMap1 : objectMaps) {
							if (!found) {
								@SuppressWarnings("unchecked")
								Map<String, List<String>> objectIsPartOf = (Map<String, List<String>>)objectMap1.get("isPartOf");
								if (objectIsPartOf != null) {
									for (List<String> strings : objectIsPartOf.values()) {
										found = strings.contains(objectAbout);
									}
								}
							}
						}
					}
					if (found) {
						if (result == null) {
							result = new ArrayList<Map<String, Object>>(); 
						}
						result.add(objectMap);
					}
				}
			}
		}
		return(result);
	}
	
	private List<String> listFilterByAbout(List<String> strings, List<String> abouts) {
		List<String> result = null;
		if ((abouts != null) && (strings != null)) {
			for (String string : strings) {
				if (abouts.contains(string)) {
					if (result == null) {
						result = new ArrayList<String>(); 
					}
					result.add(string);
				}
			}
		}
		return(result);
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
	 * Retrieves the lidoRecID
	 * 
	 * @return The lidoRecID
	 */
	public String getLidoRecID() {
		return(lidoRecID);
	}

	/**
	 * Sets the lidoRecID
	 * 
	 * @param lidoRecID The value to set the lidoRecID to
	 */
	public void setLidoRecID(String lidoRecID) {
		this.lidoRecID = lidoRecID;
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
	 * Sets the identifiers from the proxies or identifiers
	 * 
	 * @param proxiesOrIdentifiers The proxies or identifiers from where we retrieve the identifiers
	 */
	@SuppressWarnings("unchecked")
	public void setIdentifiers(List<?> proxiesOrIdentifiers) {
		// Determine the identifiers
		if ((proxiesOrIdentifiers != null) &&
			!proxiesOrIdentifiers.isEmpty()) {
			// We need to determine whether we are dealing with Strings or proxies
			if (proxiesOrIdentifiers.get(0) instanceof String) {
				// we are dealing with a list of strings
				identifiers = (List<String>)proxiesOrIdentifiers;
			} else {
				// Must be dealing with proxies
				identifiers = new ArrayList<String>();
				for (EuropeanaRecordProxy proxy : (List<EuropeanaRecordProxy>)proxiesOrIdentifiers) { 
					Map<String, Object> proxyDcIdentifiers = proxy.getDcIdentifier(); 
					if (proxyDcIdentifiers != null) {
						Collection<Object> proxyIdentifiersCollection = proxyDcIdentifiers.values();
						for (Object identifier : proxyIdentifiersCollection) {
							if (identifier instanceof String) {
								identifiers.add((String)identifier);
							} else if (identifier instanceof ArrayList) {
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
	public String getTimestampCreated() {
		return(timestampCreated);
	}

	/**
	 * Sets the timestamp of when the record was created
	 * 
	 * @param timestampCreated Timestamp for when the record was created
	 */
	public void setTimestampCreated(String timestampCreated) {
		this.timestampCreated = timestampCreated;
	}

	/**
	 * Retrieves the timestamp of when the record was updated
	 * 
	 * @return The timestamp for when the record was updated
	 */
	public String getTimestampUpdated() {
		return(timestampUpdated);
	}

	/**
	 * Sets the timestamp of when the record was updated
	 * 
	 * @param timestampUpdated Timestamp for when the record was updated
	 */
	public void setTimestampUpdated(String timestampUpdated) {
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

	/**
	 * Converts this class to the generic full record class
	 * 
	 * @return The generic full record class
	 */
	public EuropeanaEnrichments convertToGeneric() {
		// This is the generic class, so no conversion required
		return(this);
	}
}
