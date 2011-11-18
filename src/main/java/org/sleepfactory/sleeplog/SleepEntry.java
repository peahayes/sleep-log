package org.sleepfactory.sleeplog;

import java.util.Date;

public class SleepEntry implements Comparable {

	private Date date;

	private Long restedScore;
	private Long restfulnessScore;
	
	private Integer numDrinks;
	
	private Date riseTime;
	private Date wakeTime;
	private Date bedTime;

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
	
	public Long getRestfulnessScore() 
	{
		return restfulnessScore;
	}

	public Integer getNumDrinks() 
	{
		return numDrinks;
	}

	public void setNumDrinks(Integer numDrinks) 
	{
		this.numDrinks = numDrinks;
	}

	public Date getRiseTime() 
	{
		return riseTime;
	}

	public void setRiseTime(Date riseTime) 
	{
		this.riseTime = riseTime;
	}
	
	public Date getWakeTime() 
	{
		return wakeTime;
	}

	public void setWakeTime(Date wakeTime) 
	{
		this.wakeTime = wakeTime;
	}

	public Date getBedTime() 
	{
		return bedTime;
	}

	public void setBedTime(Date bedTime) 
	{
		this.bedTime = bedTime;
	}

	@Override
	public boolean equals (Object obj)
	{
		if (obj == null) return false;
		if (! (obj instanceof SleepEntry)) return false;
		
		SleepEntry that = (SleepEntry) obj;
		if (this == that) return true;
		
		if (this.restfulnessScore.equals (that.restfulnessScore) &&
			this.restedScore.equals (that.restedScore) &&
			this.date.equals (that.date) &&
			this.numDrinks.equals (that.numDrinks))
			return true;
		
		return false;
	}
	
	@Override
	public int compareTo (Object obj) 
	{
		SleepEntry that = (SleepEntry) obj;
		return this.getDate().compareTo (that.getDate());
	}

	public double getSleepAmount()
	{
		long time = wakeTime.getTime() - bedTime.getTime();
		double timeInHours = (((time / 1000) / 60) / 60);
		return timeInHours;
	}
}
