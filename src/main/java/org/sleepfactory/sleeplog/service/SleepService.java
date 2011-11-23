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

	public String getHomePageURLForUser() 
	{
		String homePage = "/secure/sleeper/home";
		return homePage;
	}

	public void add (SleepEntry sleepEntry) 
	{	
		log.add (sleepEntry);
	}

}
