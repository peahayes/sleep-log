package org.sleepfactory.sleeplog;

import java.util.Date;

/**
 * SleepEntry represents a one night of sleep.  The entry spans bedtime through riseTime, 
 * with wakeTime in between.  There are scores for how restful the sleep was, and how rested
 * the sleeper felt during the day.  There are calculations for the amount of time the
 * sleeper spent in bed and the amount of sleep the sleeper got.  There are factors that might
 * affect sleep quality, such as numDrinks.
 * 
 * @author phayes
 *
 */
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

	/**
	 * Date of this entry, which is the date on which sleeper went to bed.
	 * 
	 * @return The date on which sleeper went to bed.
	 */
	public Date getDate() 
	{
		return date;
	}
	
	/**
	 * Set the date of this entry, which is the date on which sleeper went to bed.
	 * 
	 * @return The date on which sleeper went to bed.
	 */
	public void setDate (Date date) 
	{
		this.date = date;
	}

	/**
	 * Set the score indicating how rested sleeper felt during day.
	 * 
	 * @return How rested sleeper felt during day
	 */
	public void setRestedScore (Long score) 
	{
		this.restedScore = score;
	}

	/**
	 * Score indicating how rested sleeper felt during day.
	 * 
	 * @return How rested sleeper felt during day (a number 
	 *         between 1 and 5)
	 */
	public Long getRestedScore() 
	{
		return restedScore;
	}

	/**
	 * Set the score indicating how restful this night of sleep was.
	 * 
	 * @param score A number between 1 and 5.
	 */
	public void setResfulnessScore (Long score) 
	{
		this.restfulnessScore = score;
	}
	
	/**
	 * Returns the score indicating how restful this night of sleep was.
	 * 
	 * @return Number between 1 and 5.
	 */
	public Long getRestfulnessScore() 
	{
		return restfulnessScore;
	}

	/**
	 * Return number of drinks sleeper had before going to bed.
	 * 
	 * @return Any number greater than or equal to 0.
	 */
	public Integer getNumDrinks() 
	{
		return numDrinks;
	}

	/**
	 * Set number of drinks sleeper had before going to bed.
	 * 
	 * @param numDrinks Any number greater than or equal to 0.
	 */
	public void setNumDrinks(Integer numDrinks) 
	{
		this.numDrinks = numDrinks;
	}

	/** 
	 * Date/time at which sleep rose from his or her bed.
	 * 
	 * @return A date/time
	 */
	public Date getRiseTime() 
	{
		return riseTime;
	}

	/**
	 * Set the date/time at which the sleeper rose from bed.
	 * 
	 * @param riseTime A date/time
	 */
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

	/**
	 * Returns the date/time at which the sleeper laid down to to
	 * to sleep.
	 * 
	 * @return A date/time
	 */
	public Date getBedTime() 
	{
		return bedTime;
	}

	/**
	 * Set the date/time at which the sleeper laid down to to
	 * to sleep.
	 * 
	 * @param bedTime A date/time
	 */
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

	/**
	 * Returns the amount of time the sleeper was asleep during
	 * this sleep episode.
	 * 
	 * @return
	 * @throws Exception
	 */
	public double getSleepAmount()
		throws Exception
	{
		long time = wakeTime.getTime() - bedTime.getTime();
		double timeInHours = (((time / 1000) / 60) / 60);
		return timeInHours;
	}
	
	public double getInBedAmount()
	{
		long time = riseTime.getTime() - bedTime.getTime();
		double timeInHours = (((time / 1000) / 60) / 60);
		return timeInHours;
	}

}
