package org.sleepfactory.sleeplog;

import java.util.Date;

public class SleepEntry {

	private Date date;

	public SleepEntry() 
	{
	}

	public SleepEntry (Date date) 
	{
		this.date = date;
	}

	public Date getDate() 
	{
		return date;
	}
	
	public void setDate (Date date) 
	{
		this.date = date;
	}

}
