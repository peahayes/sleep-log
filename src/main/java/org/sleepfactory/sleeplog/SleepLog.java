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
}
