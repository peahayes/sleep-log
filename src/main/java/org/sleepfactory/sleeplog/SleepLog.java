package org.sleepfactory.sleeplog;

import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * A SleepLog represents how a sleeper slept over a span of any number of days.
 * The SleepLog contains SleepEntries, which record detailed statistics about a
 * single night of sleep.  SleepLog provides averages for a number of these statistics, 
 * such as the average sleep time, the average time in bed, etc.  The SleepLog also
 * provides a value for sleepEfficiency.
 * 
 * @author phayes
 *
 */

public class SleepLog {

	private SortedSet<SleepEntry> entries = new TreeSet<SleepEntry>();

	/**
	 * Returns all entries in the SleepLog, sorted by date.
	 * 
	 * @return Sorted set of SleepEntries
	 */
	public SortedSet<SleepEntry> getEntries() 
	{
		return entries;
	}

	/**
	 * Replace any existing SleepEntrires with the specified set of
	 * SleepEntries.
	 * 
	 * @param entries A set of SleepEntries sorted by date
	 */
	public void setEntries (SortedSet<SleepEntry> entries) 
	{
		this.entries = entries;
	}

	/**
	 * Add a SleepEntry to this SleepLog
	 * 
	 * @param entry A single SleepEntry representing a single night of sleep.
	 */
	public void add (SleepEntry entry) 
	{
		getEntries().add (entry);	
	}

	/**
	 * The number of SleepEntrys in this SleepLog (one SleepEntry
	 * per night of sleep).
	 * 
	 * @return The number of SleepEntryies
	 */
	public int getNumEntries() 
	{
		return getEntries().size();
	}

	/**
	 * Returns true if there are entries and false if the SleepLog has
	 * no entries.
	 * 
	 * @return true if there are SleepEntries and false otherwise.
	 */
	public boolean isEmtpy() 
	{
		return getEntries().size() == 0;
	}

	/**
	 * Return the average of all resetfulness scores in all
	 * SleepEntries.
	 * 
	 * @return A double value
	 */
	@NumberFormat (style = Style.PERCENT, pattern="#.#")
	public double getAvgRestfulness() 
	{
		 return getSumRestfulness() / getNumEntries();
	}

	private double getSumRestfulness() 
	{
		double sum = 0;
		
		for (SleepEntry entry : entries)
		{
			if (entry.getRestfulnessScore() != null)
				sum += entry.getRestfulnessScore();
		}
		
		return sum;
	}
	
	/**
	 * Return the average of all energy level scores in all
	 * SleepEntries.
	 * 
	 * @return A double value
	 */
	@NumberFormat (style = Style.PERCENT, pattern="#.#")
	public double getAvgEnergyLevel() 
	{
		 return getSumEnergyLevel() / getNumEntries();
	}

	private double getSumEnergyLevel() 
	{
		double sum = 0;
		
		for (SleepEntry entry : entries)
		{
			if (entry.getEnergyLevel() != null)
				sum += entry.getEnergyLevel();
		}
		
		return sum;
	}

	/**
	 * Return the average of all rested scores in all
	 * SleepEntries.
	 * 
	 * @return A double value
	 */
	@NumberFormat (style = Style.PERCENT, pattern="#.#")
	public double getAvgRestedness() 
	{
		 return getSumRestedness() / getNumEntries();
	}

	private double getSumRestedness() 
	{
		double sum = 0;
		
		for (SleepEntry entry : entries)
		{
			if (entry.getRestedScore() != null)
				sum += entry.getRestedScore();
		}
		
		return sum;
	}
	
	/**
	 * Returns the average amount of sleep for all SleepEntries.
	 * 
	 * @return A double value
	 * 
	 * @throws Exception If any of the components of this calculation are null
	 */
	@NumberFormat (style = Style.PERCENT, pattern="#.#")
	public double getAvgSleepAmount()
		throws Exception
	{
		 return getSumSleepAmount() / getNumEntries();
	}
	
	private double getSumSleepAmount()
		throws Exception
	{
		double sum = 0;
		
		for (SleepEntry entry : entries)
		{
			sum += entry.getSleepAmount();
		}
		
		return sum;
	}
		
	/** 
	 * Return a calculation of the average amount of time
	 * the sleeper spent in bed over all SleepEntries in the 
	 * SleepLog
	 * 
	 * @return A double number
	 */
	@NumberFormat (style = Style.PERCENT, pattern="#.#")
	public double getAvgInBedAmount() 
	{
		 return getSumInBedAmount() / getNumEntries();
	}

	private double getSumInBedAmount() 
	{
		double sum = 0;
		
		for (SleepEntry entry : entries)
		{
			sum += entry.getInBedAmount();
		}
		
		return sum;
	}
	
	/** 
	 * Return a calculation of the average number of drinks
	 * the sleeper has drunk over all SleepEntries in the 
	 * SleepLog
	 * 
	 * @return A double number
	 */
	@NumberFormat (style = Style.PERCENT, pattern="#.#")
	public double getAvgNumDrinks()
	{
		return getSumNumDrinks() / getNumEntries();
	}
	
	private double getSumNumDrinks() 
	{
		double sum = 0;
		
		for (SleepEntry entry : entries)
		{
			if (entry.getNumDrinks() != null)
				sum += entry.getNumDrinks();
		}
		
		return sum;
	}
	
	/**
	 * Returns a calculation of the ratio of sleep amount to time
	 * spent in bed.  The more time spent in bed without sleeping, the
	 * lower the sleep efficiency.
	 * 
	 * @return A double value greater than 0 and <= 1
	 * 
	 * @throws Exception
	 */
	public double getSleepEfficiency() 
		throws Exception
	{
		return getSumSleepAmount() / getSumInBedAmount();
	}
}
