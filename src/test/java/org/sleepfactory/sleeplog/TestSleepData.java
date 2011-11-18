package org.sleepfactory.sleeplog;

import java.util.Date;

public class TestSleepData {

	protected SleepLog log1 = new SleepLog();

	protected SleepEntry entry = new SleepEntry();
	protected SleepEntry entry2;
	protected SleepEntry entry3;
	
	public TestSleepData()
	{
		entry = new SleepEntry (new Date());
		entry2 = new SleepEntry (new Date (entry.getDate().getTime() + 3600000));
		entry3 = new SleepEntry (entry.getDate());

		entry.setRestedScore (4L);
		entry.setResfulnessScore (4L);
		entry.setNumDrinks (0);
		entry.setBedTime (entry.getDate());
		entry.setRiseTime(new Date (entry.getDate().getTime() + 25200000));
		entry.setWakeTime (new Date (entry.getDate().getTime() + 25200000));
		
		entry2.setRestedScore (3L);
		entry2.setResfulnessScore (4L);
		entry2.setNumDrinks (1);
		entry2.setBedTime (entry2.getDate());
		entry2.setRiseTime(new Date (entry2.getDate().getTime() + 25200000));
		entry2.setWakeTime (new Date (entry2.getDate().getTime() + 25200000));
		
		entry3.setResfulnessScore (entry.getRestfulnessScore());
		entry3.setRestedScore (entry.getRestedScore());
		entry3.setNumDrinks (entry.getNumDrinks());
		entry3.setBedTime (entry.getDate());
		entry3.setRiseTime (entry.getRiseTime());
		entry3.setWakeTime (entry.getWakeTime());		
	}

}
