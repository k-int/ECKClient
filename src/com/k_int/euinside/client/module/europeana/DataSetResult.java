package com.k_int.euinside.client.module.europeana;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataSetResult extends EuropeanaResult {
	private static Log log = LogFactory.getLog(DataSetResult.class);

	private ArrayList<DataSet> items;
	
	public DataSetResult() {
	}
	
	@Override
	protected Log getLogger() {
		return(log);
	}
	
	public ArrayList<DataSet> getItems() {
		return(items);
	}

	public void setItems(ArrayList<DataSet> items) {
		this.items = items;
	}

	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = "Class: DataSetResult\n";
		result += super.toString();
		if (items != null) {
			for (DataSet dataSet : items) {
				result += dataSet.toString();
			}
		}
		return(result);
	}
}
