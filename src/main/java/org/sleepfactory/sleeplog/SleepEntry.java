package org.sleepfactory.sleeplog;

import java.util.Date;

public class SleepEntry implements Comparable {

	private Date date;

	private Long restedScore;
	private Long restfulnessScore;
	
	private Long numDrinks;

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

	public Long getNumDrinks() 
	{
		return numDrinks;
	}

	public void setNumDrinks(Long numDrinks) 
	{
		this.numDrinks = numDrinks;
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
			this.date.equals (that.date))
			return true;
		
		return false;
	}
	
	@Override
	public int compareTo (Object obj) 
	{
		SleepEntry that = (SleepEntry) obj;
		return this.getDate().compareTo (that.getDate());
	}

}
