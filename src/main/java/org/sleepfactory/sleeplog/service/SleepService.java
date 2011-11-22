package org.sleepfactory.sleeplog.service;

import java.util.Set;

import org.sleepfactory.sleeplog.SleepEntry;
import org.sleepfactory.sleeplog.SleepLog;
import org.springframework.stereotype.Service;

@Service ("sleepService")
public class SleepService {
	
	public Set<SleepEntry> getEntries (SleepLog log)
	{
		return log.getEntries();
	}

	public String getHomePageURLForUser() 
	{
		String homePage = "/secure/sleeper/home";
		return homePage;
	}

}
