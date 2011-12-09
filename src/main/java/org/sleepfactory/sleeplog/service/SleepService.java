package org.sleepfactory.sleeplog.service;

import java.util.Set;

import org.sleepfactory.sleeplog.SleepEntry;
import org.sleepfactory.sleeplog.SleepLog;
import org.springframework.stereotype.Service;

@Service ("sleepService")
public class SleepService {
	
	private SleepLog log = new SleepLog();
	
	private Long masterId = 1L;

	public Set<SleepEntry> getEntries()
	{
		return log.getEntries();
	}

	public void add (SleepEntry sleepEntry) 
	{	
		sleepEntry.setId (masterId++);
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
		SleepEntry oldEntry = getSleepEntryById (sleepEntry.getId());
		
		log.getEntries().remove (oldEntry);
		log.getEntries().add (sleepEntry);
	}

	public void removeEntry (Long id) 
	{
		SleepEntry entry = getSleepEntryById (id);
		log.getEntries().remove (entry);
	}

}
