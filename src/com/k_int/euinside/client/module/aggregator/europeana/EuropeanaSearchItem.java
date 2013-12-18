package com.k_int.euinside.client.module.aggregator.europeana;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.json.baseJSON;

/**
 * This class represents a europeana search result item
 *
 */
public class EuropeanaSearchItem extends baseJSON {
	private static Log log = LogFactory.getLog(EuropeanaSearchItem.class);

	// We are just collecting the "minimal" profile fields, which is more than we need
	private String id;
	private String type;
	private List<String> provider;
	private String link;
	private List<String> title;
	private String guid;
	private List<String> dataProvider;
	private List<String> rights;
	private Integer europeanaCompleteness;
	private List<String> edmPreview;

	/**
	 * Constructor, This disables logging of unmapped fields
	 */
	public EuropeanaSearchItem() {
		// As we are not collecting all the fields, we do not want to fill up the log file
		setLogUnknownFields(false);
	}

	@Override
	protected Log getLogger() {
		return(log);
	}

	/**
	 * Retrieves the europeana identifier for this result
	 * 
	 * @return The europeana identifier
	 */
	public String getId() {
		return(id);
	}

	/**
	 * Sets the europeana identifier
	 * 
	 * @param id The eueopeana identifier
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retrieves the type of this item
	 * 
	 * @return The item type
	 */
	public String getType() {
		return(type);
	}

	/**
	 * Sets the type of the item
	 * 
	 * @param type The type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Retrieves the provider of the item
	 * 
	 * @return The provider
	 */
	public List<String> getProvider() {
		return(provider);
	}

	/**
	 * Sets the provider
	 * 
	 * @param provider The provider
	 */
	public void setProvider(List<String> provider) {
		this.provider = provider;
	}

	/**
	 * Retrieves the link to the item
	 * 
	 * @return The link
	 */
	public String getLink() {
		return(link);
	}

	/**
	 * Sets the link to the item
	 * 
	 * @param link The link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Retrieves the title
	 * 
	 * @return The title
	 */
	public List<String> getTitle() {
		return(title);
	}

	/**
	 * Sets the title
	 * 
	 * @param title The title
	 */
	public void setTitle(List<String> title) {
		this.title = title;
	}

	/**
	 * Retrieves the GUID
	 * 
	 * @return The GUID
	 */
	public String getGuid() {
		return(guid);
	}

	/**
	 * Sets the GUID
	 * 
	 * @param guid The GUID
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * Retrieves the data provider
	 * 
	 * @return The data proviider
	 */
	public List<String> getDataProvider() {
		return(dataProvider);
	}

	/**
	 * Sets the data provider
	 * 
	 * @param dataProvider The data provider
	 */
	public void setDataProvider(List<String> dataProvider) {
		this.dataProvider = dataProvider;
	}

	/**
	 * Retrieve the rights
	 * 
	 * @return The rights
	 */
	public List<String> getRights() {
		return(rights);
	}

	/**
	 * Set the rights
	 * 
	 * @param rights The rights
	 */
	public void setRights(List<String> rights) {
		this.rights = rights;
	}

	/**
	 * Retrieves the europeana completeness
	 * 
	 * @return The europeana completeness
	 */
	public Integer getEuropeanaCompleteness() {
		return(europeanaCompleteness);
	}

	/**
	 * Set the europeana completeness
	 * 
	 * @param europeanaCompleteness The europeana completeness
	 */
	public void setEuropeanaCompleteness(Integer europeanaCompleteness) {
		this.europeanaCompleteness = europeanaCompleteness;
	}

	/**
	 * Retrieves the EDM preview
	 *  
	 * @return The EDM preview
	 */
	public List<String> getEdmPreview() {
		return(edmPreview);
	}

	/**
	 * Sets the EDM preview
	 * 
	 * @param edmPreview The EDM preview
	 */
	public void setEdmPreview(List<String> edmPreview) {
		this.edmPreview = edmPreview;
	}
}
