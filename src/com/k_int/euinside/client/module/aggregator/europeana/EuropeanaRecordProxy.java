package com.k_int.euinside.client.module.aggregator.europeana;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.json.baseJSON;

/**
 * The class represnts a proxy object
 * This does not represent all the items in the proxy, it only contains the elements we are interested in
 * If any of the other elements become useful we can add them when required
 *
 */
public class EuropeanaRecordProxy extends baseJSON {
	private static Log log = LogFactory.getLog(EuropeanaRecordProxy.class);

	// Only interested in the dcIdentifier
	private Map<String, Object> dcIdentifier;

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
}
