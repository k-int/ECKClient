package com.k_int.euinside.client.module.statistics.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusItem extends StatisticItem{

	static private final String FORMAT_DATE = "yyyyMMdd";
	static private final SimpleDateFormat expectedDateFormat = new SimpleDateFormat(FORMAT_DATE);
	
	private Integer fastestTime;
	private Integer slowestTime;
	private Integer averageTime;
	private Date date;
	
	public Integer getFastestTime() {
		return(fastestTime);
	}
	
	public void setFastestTime(Integer fastestTime) {
		this.fastestTime = fastestTime;
	}
	
	public Integer getSlowestTime() {
		return(slowestTime);
	}
	
	public void setSlowestTime(Integer slowestTime) {
		this.slowestTime = slowestTime;
	}
	
	public Integer getAverageTime() {
		return(averageTime);
	}
	
	public void setAverageTime(Integer averageTime) {
		this.averageTime = averageTime;
	}
	
	public Date getDate() {
		return(date);
	}
	
	public void setDate(String date) {
		try {
			this.date = expectedDateFormat.parse(date);
		} catch (ParseException e) {
			// We will ignore this exception, as we should not have a date in a naff format
		}
	}
	
	/**
	 * Formats the members of this class in a simple to view way
	 * 
	 * @return The formatted string
	 */
	public String toString() {
		String result = super.toString();
		result += "Class: StatusItem:\n"; 
		result += "\tfastestTime: " + fastestTime.toString() + "\n";
		result += "\tslowestTime: " + slowestTime.toString() + "\n";
		result += "\taverageTime: " + averageTime.toString() + "\n";
		if (date != null) {
			result += "\tDate: " + expectedDateFormat.format(date) + "\n";
		}
		return(result);
	}
}
