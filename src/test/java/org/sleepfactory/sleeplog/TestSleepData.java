package org.sleepfactory.sleeplog;

import org.joda.time.DateTime;

public class TestSleepData {

	protected SleepLog log1 = new SleepLog();

	protected SleepEntry entry = new SleepEntry();
	protected SleepEntry entry2;
	protected SleepEntry entry3;
	
	public TestSleepData()
	{
		entry = new SleepEntry (new DateTime());
		entry2 = new SleepEntry (new DateTime (entry.getDate().getMillis() + 3600000));
		entry3 = new SleepEntry (entry.getDate());

		entry.setRestedScore (4L);
		entry.setRestfulnessScore (4L);
		entry.setNumDrinks (0);
		entry.setBedTime (entry.getDate());
		entry.setRiseTime (new DateTime (entry.getDate().getMillis() + 25200000));
		entry.setWakeTime (new DateTime (entry.getDate().getMillis() + 25200000));
		
		entry2.setRestedScore (3L);
		entry2.setRestfulnessScore (4L);
		entry2.setNumDrinks (1);
		entry2.setBedTime (entry2.getDate());
		entry2.setRiseTime (new DateTime (entry2.getDate().getMillis() + 25200000));
		entry2.setWakeTime (new DateTime (entry2.getDate().getMillis() + 25200000));
		
		entry3.setRestfulnessScore (entry.getRestfulnessScore());
		entry3.setRestedScore (entry.getRestedScore());
		entry3.setNumDrinks (entry.getNumDrinks());
		entry3.setBedTime (entry.getDate());
		entry3.setRiseTime (entry.getRiseTime());
		entry3.setWakeTime (entry.getWakeTime());		
	}

}
