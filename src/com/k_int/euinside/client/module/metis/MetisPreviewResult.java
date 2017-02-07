package com.k_int.euinside.client.module.metis;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.k_int.euinside.client.json.baseJSON;

public class MetisPreviewResult extends baseJSON {
	private static Log log = LogFactory.getLog(MetisPreviewResult.class);

	@JacksonXmlElementWrapper(useWrapping=false)
	@JacksonXmlProperty(isAttribute=false, localName="resultList")
	private List<MetisValidationResult> resultList;

	@JacksonXmlProperty(isAttribute=false)  
	private boolean success;

	@JacksonXmlProperty(isAttribute=false, localName="portalUrl")  
	private String portalUrl;

	@JacksonXmlProperty(isAttribute=false)  
	private Date date;

	@Override
	protected Log getLogger() {
		return(log);
	}
	
	public String getPortalUrl() {
		return this.portalUrl;
	}

	public void setPortalUrl(String portalUrl) {
		this.portalUrl = portalUrl;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<MetisValidationResult> getResultList() {
		return this.resultList;
	}

	public void setResultList(List<MetisValidationResult> resultList) {
		this.resultList = resultList;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
