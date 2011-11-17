package org.sleepfactory.sleeplog;

import java.util.SortedSet;
import java.util.TreeSet;

public class SleepLog {

	private SortedSet<SleepEntry> entries = new TreeSet<SleepEntry>();

	public SortedSet<SleepEntry> getEntries() 
	{
		return entries;
	}

	public void setEntries (SortedSet<SleepEntry> entries) 
	{
		this.entries = entries;
	}

	public void add (SleepEntry entry) 
	{
		getEntries().add (entry);	
	}

	public int getNumEntries() 
	{
		return getEntries().size();
	}

	public boolean isEmtpy() 
	{
		return getEntries().size() == 0;
	}

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

}
