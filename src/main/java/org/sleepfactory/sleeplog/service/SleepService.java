package org.sleepfactory.sleeplog.service;

import java.util.Set;

import org.sleepfactory.sleeplog.SleepEntry;
import org.sleepfactory.sleeplog.SleepLog;
import org.springframework.stereotype.Service;

@Service ("sleepService")
public class SleepService {
	
	private SleepLog log = new SleepLog();
	
	public Set<SleepEntry> getEntries()
	{
		return log.getEntries();
	}

	public void add (SleepEntry sleepEntry) 
	{	
		log.add (sleepEntry);
	}

	public SleepLog getSleepLog() 
	{
		return log;
	}
	
	public SleepEntry getSleepEntryById (Long id)
	{
		if (id == null)
			return null;
		
		for (SleepEntry entry : getEntries())
		{
			if (id.equals (entry.getId()))
				return entry;
		}
			
		return null;
	}

	public void update (SleepEntry sleepEntry) 
	{
		// TODO Auto-generated method stub
	}

}
