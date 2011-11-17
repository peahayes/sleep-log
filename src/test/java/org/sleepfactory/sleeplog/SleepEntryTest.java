package org.sleepfactory.sleeplog;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class SleepEntryTest {
	
	SleepEntry entry = new SleepEntry();
	SleepEntry entry2;
	SleepEntry entry3;
	
	@Before
	public void setUp() throws Exception {

		entry = new SleepEntry (new Date());
		entry2 = new SleepEntry (new Date (entry.getDate().getTime() + 3600000));
		entry3 = new SleepEntry (entry.getDate());

		entry.setRestedScore (4L);
		entry.setResfulnessScore (4L);
		
		entry2.setRestedScore (3L);
		entry2.setResfulnessScore (4L);
		
		entry3.setResfulnessScore (entry.getResfulnessScore());
		entry3.setRestedScore (entry.getRestedScore());
	}

	@Test
	public void testEquals() 
	{
		assertTrue (entry.equals (entry));
	}

}
