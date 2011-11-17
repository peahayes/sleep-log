package org.sleepfactory.sleeplog;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class SleepLogTest {

	SleepLog log1 = new SleepLog();

	SleepEntry entry1 = new SleepEntry (new Date());
	SleepEntry entry2 = new SleepEntry (new Date (entry1.getDate().getTime() + 86400000));

	@Before
	public void setUp() throws Exception {

		entry1.setResfulnessScore (3L);
		entry2.setResfulnessScore (4L);
		
		entry1.setRestedScore (3L);
		entry2.setRestedScore (4L);
	}

	@Test
	public void testSleepEntriesOrderedByDate()
	{
		log1.add (entry2);
		log1.add (entry1);
		
		assertEquals (entry1, log1.getEntries().iterator().next());
	}

	@Test
	public void testAdd() 
	{
		assertTrue (log1.isEmtpy());
		assertEquals (0, log1.getNumEntries());
		
		log1.add (entry1);
		
		assertFalse (log1.isEmtpy());
		assertEquals (1, log1.getNumEntries());
	}
	
}
