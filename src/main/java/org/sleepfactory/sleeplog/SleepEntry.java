package org.sleepfactory.sleeplog;

import java.util.Date;

public class SleepEntry {

	private Date date;

	private Long restedScore;
	private Long restfulnessScore;

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

	public void setRestedScore (Long score) 
	{
		this.restedScore = score;
	}

	public Long getRestedScore() 
	{
		return restedScore;
	}

	public void setResfulnessScore (Long score) 
	{
		this.restfulnessScore = score;
	}
	
	public Long getResfulnessScore() 
	{
		return restfulnessScore;
	}

}
