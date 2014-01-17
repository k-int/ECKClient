package com.k_int.euinside.client.module.aggregator.europeana;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.k_int.euinside.client.json.baseJSON;

/**
 * The class represnts a proxy object
 * This does not represent all the items in the proxy, it only contains the elements we are interested in
 * If any of the other elements become useful we can add them when required
 *
 */
// We will ignore the fields about, proxyIn and proxyFor
@JsonIgnoreProperties({"about", "proxyIn", "proxyFor"})
public class EuropeanaRecordProxy extends baseJSON {
	private static Log log = LogFactory.getLog(EuropeanaRecordProxy.class);

	// Only interested in the dcIdentifier
	private Map<String, Object> dcIdentifier;
	private Boolean europeanaProxy;
	private List<String> potentialEnrichedAbout  = new ArrayList<String>();

	/**
	 * Constructor, This disables logging of unmapped fields
	 */
	public EuropeanaRecordProxy() {
		// As we are not collecting all the fields, we do not want to fill up the log file
		setLogUnknownFields(false);
	}

	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retrieves the dcIdentifiers
	 * 
	 * @return The dcIdentifiers
	 */
	public Map<String, Object> getDcIdentifier() {
		return(dcIdentifier);
	}

	/**
	 * Sets the dcIdentifiers
	 * 
	 * @param dcIdentifier The dcIdentifiers
	 */
	public void setDcIdentifier(Map<String, Object> dcIdentifier) {
		this.dcIdentifier = dcIdentifier;
	}

	/**
	 * Retrieves whether this is the europeana proxy or not
	 * 
	 * @return Whether it is the Europeana Proxy or not
	 */
	public Boolean getEuropeanaProxy() {
		return(europeanaProxy);
	}

	/**
	 * Sets whether this proxy is the europeana one or not
	 * 
	 * @param europeanaProxy true if this proxy is the europeana one
	 */
	public void setEuropeanaProxy(Boolean europeanaProxy) {
		this.europeanaProxy = europeanaProxy;
	}
	
	@JsonIgnore
	public List<String> getPotentialEnrichedAbout() {
		return(potentialEnrichedAbout);
	}

	/**
	 * For the europeana proxy we need to capture all the fields
	 * As we need to know which fields have been enriched
	 * 
	 * @param fieldName The fieldname it cannot find a setter for
	 * @param value The value for this field
	 */
	@JsonAnySetter
	public void setUnknownField(String fieldName, Object value) {
		boolean added = false;
		if (value != null) {
			if (value instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, List<String>> objectTree = (Map<String, List<String>>)value; 
				List<String> values = objectTree.get("def");
				if (values != null) {
					potentialEnrichedAbout.addAll(values);
					added = true;
				}
			}
		}
		if (!added) {
			super.setUnknownField(fieldName, value);
		}
	}
}
