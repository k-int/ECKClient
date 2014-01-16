package com.k_int.euinside.client.module.aggregator.europeana;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class represents an item record result 
 *
 */
public class EuropeanaRecord extends EuropeanaResult {
	private static Log log = LogFactory.getLog(EuropeanaRecord.class);
	
	private EuropeanaRecordObject object;

	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retrieves the record that was searched for
	 * 
	 * @return The record that was searched for
	 */
	public EuropeanaRecordObject getObject() {
		return(object);
	}

	/**
	 * Sets the record that was searched for
	 * 
	 * @param object The record that was searched for
	 */
	public void setObject(EuropeanaRecordObject object) {
		this.object = object;
	}
}
