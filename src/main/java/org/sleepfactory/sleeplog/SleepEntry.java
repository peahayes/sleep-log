package org.sleepfactory.sleeplog;

import java.util.Collection;

import org.joda.time.DateTime;
import org.sleepfactory.sleeplog.scale.Activity;
import org.sleepfactory.sleeplog.scale.EnergyLevel;
import org.sleepfactory.sleeplog.scale.Restfulness;
import org.sleepfactory.sleeplog.scale.SleepQuality;
import org.springframework.format.annotation.DateTimeFormat;

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

	private Long id;
	
	@DateTimeFormat (pattern = "EEE MMM dd")
	private DateTime date;

	private SleepQuality restedScore;
	private Restfulness restfulnessScore;
	private EnergyLevel energyLevel;
	
	private Collection<Long> activities;
	
	private Integer numDrinks;
	
	private DateTime riseTime;
	private DateTime wakeTime;
	private DateTime bedTime;

	public SleepEntry() 
	{
	}

	public SleepEntry (DateTime date) 
	{
		this.date = date;
	}

	public SleepEntry (Long id) 
	{
		this.id = id;
	}

	/**
	 * Unique identifier and primary key of this SleepEntry
	 * 
	 * @return Unique identifier
	 */
	public Long getId() 
	{
		return id;
	}

	public void setId (Long id) 
	{
		this.id = id;
	}
	
	/**
	 * Date of this entry, which is the date on which sleeper went to bed.
	 * 
	 * @return The date on which sleeper went to bed.
	 */
	public DateTime getDate() 
	{
		return date;
	}
	
	/**
	 * Set the date of this entry, which is the date on which sleeper went to bed.
	 * 
	 * @return The date on which sleeper went to bed.
	 */
	public void setDate (DateTime date) 
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
		this.restedScore = SleepQuality.enumValueOf (score);
	}

	/**
	 * Score indicating how rested sleeper felt during day.
	 * 
	 * @return How rested sleeper felt during day (a number 
	 *         between 1 and 5)
	 */
	public Long getRestedScore() 
	{
		if (restedScore == null)
			return null;
		return restedScore.valueOf();
	}

	/**
	 * Set the score indicating how restful this night of sleep was.
	 * 
	 * @param score A number between 1 and 5.
	 */
	public void setRestfulnessScore (Long score) 
	{
		this.restfulnessScore = Restfulness.enumValueOf (score);
	}
	
	/**
	 * Returns the score indicating how restful this night of sleep was.
	 * 
	 * @return Number between 1 and 5.
	 */
	public Long getRestfulnessScore() 
	{
		if (restfulnessScore == null)
			return null;
		return restfulnessScore.valueOf();
	}

	/**
	 * Returns the score indicating how energetic the sleeper felt.
	 * 
	 * @return Number between 1 and 5.
	 */
	public Long getEnergyLevel() 
	{
		if (energyLevel == null)
			return null;
		return energyLevel.valueOf();
	}

	/**
	 * Set the score indicating how energetic the sleeper felt.
	 * 
	 * @param energyLevel Number between 1 and 5.
	 */
	public void setEnergyLevel (Long score) 
	{
		this.energyLevel = EnergyLevel.enumValueOf (score);
	}
	
	public Collection<Long> getActivities() 
	{
		return activities;
	}

	public void setActivities (Collection<Long> activities) 
	{
		this.activities = activities;
	}
	
	public String getActivitiesAsString()
	{
		return Activity.asString (activities);
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
	public DateTime getRiseTime() 
	{
		return riseTime;
	}

	/**
	 * Set the date/time at which the sleeper rose from bed.
	 * 
	 * @param riseTime A date/time
	 */
	public void setRiseTime (DateTime riseTime) 
	{
		this.riseTime = riseTime;
	}
	
	/**
	 * Returns the date/time at which the sleeper awoke in the morning.
	 * 
	 * @return A date/time.
	 */
	public DateTime getWakeTime() 
	{
		return wakeTime;
	}

	/**
	 * Set the date/time at which the sleeper awoke in the morning.
	 * 
	 * @param wakeTime A date/time.
	 */
	public void setWakeTime (DateTime wakeTime) 
	{
		this.wakeTime = wakeTime;
	}

	/**
	 * Returns the date/time at which the sleeper laid down to to
	 * to sleep.
	 * 
	 * @return A date/time
	 */
	public DateTime getBedTime() 
	{
		return bedTime;
	}

	/**
	 * Set the date/time at which the sleeper laid down to to
	 * to sleep.
	 * 
	 * @param bedTime A date/time
	 */
	public void setBedTime (DateTime bedTime) 
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
		
		if ((this.energyLevel != null && this.energyLevel.equals (that.energyLevel)) &&
			(this.restfulnessScore != null && this.restfulnessScore.equals (that.restfulnessScore)) &&
			(this.restedScore != null && this.restedScore.equals (that.restedScore)) &&
			(this.date != null && this.date.equals (that.date)) &&
			(this.numDrinks != null && this.numDrinks.equals (that.numDrinks)))
			return true;
		
		return false;
	}
	
	@Override
	public int compareTo (Object obj) 
	{
		SleepEntry that = (SleepEntry) obj;
		
		if (this.date != null)
			return this.date.compareTo (that.date);
		
		// they're both null
		else if (that.date == null)
			return 0;
		
		// only this.date is null
		return -1;
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
		long time = wakeTime.getMillis() - bedTime.getMillis();
		double timeInHours = (((time / 1000) / 60) / 60);
		return timeInHours;
	}
	
	/**
	 * Returns the amount of time the sleeper spent in bed, including
	 * both sleep time and wake time.
	 * 
	 * @return Any double
	 */
	public double getInBedAmount()
	{
		long time = riseTime.getMillis() - bedTime.getMillis();
		double timeInHours = (((time / 1000) / 60) / 60);
		return timeInHours;
	}
	
	@Override
	public String toString()
	{
		String str = "{ [" + id + "] ";
		str += "Date: " + date + "; ";
		str += "Energy: " + energyLevel + "; ";
		str += "Rested: " + restedScore + "; ";
		str += "Restulness: " + restfulnessScore + "; ";
		str += "Num Drinks: " + numDrinks + "}";
		return str;
	}

}
