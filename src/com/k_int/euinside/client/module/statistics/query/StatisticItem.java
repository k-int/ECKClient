package com.k_int.euinside.client.module.statistics.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.k_int.euinside.client.json.baseJSON;

public class StatisticItem extends baseJSON {
	private static Log log = LogFactory.getLog(StatisticItem.class);

	static private final String FORMAT_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	static public final SimpleDateFormat expectedDateTimeFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
	private Integer numberFailed;
	private Long duration;
	private Date statisticDate;
	private Integer numberSuccessful;
	private Integer numberProcessed;
	
	@Override
	protected Log getLogger() {
		return(log);
	}
	
	public Integer getNumberFailed() {
		return(numberFailed);
	}
	
	public void setNumberFailed(Integer numberFailed) {
		this.numberFailed = numberFailed;
	}
	
	public Long getDuration() {
		return(duration);
	}
	
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	public Date getStatisticDate() {
		return(statisticDate);
	}
	
	public void setStatisticDate(String statisticDate) {
		try {
			this.statisticDate = expectedDateTimeFormat.parse(statisticDate);
		} catch (ParseException e) {
			// We will ignore this exception, as we should not have a date in a naff format
		}
	}
	
	public Integer getNumberSuccessful() {
		return(numberSuccessful);
	}
	
	public void setNumberSuccessful(Integer numberSuccessful) {
		this.numberSuccessful = numberSuccessful;
	}
	
	public Integer getNumberProcessed() {
		return(numberProcessed);
	}
	
	public void setNumberProcessed(Integer numberProcessed) {
		this.numberProcessed = numberProcessed;
	}
}
