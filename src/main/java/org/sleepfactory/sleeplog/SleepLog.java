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

}
