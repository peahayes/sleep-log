package org.sleepfactory.sleeplog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		
		entry1.setRestedScore (4L);
		entry2.setRestedScore (5L);
	}
	
	@Test
	public void testAvgInBedAmount()
	{
		log1.add (entry1);
		assertEquals (entry1.getInBedAmount(), log1.getAvgInBedAmount(), 0);
	}

	@Test
	public void testAvgRestedness()
	{
		log1.add (entry1);
		assertEquals (entry1.getRestedScore(), log1.getAvgRestedness(), 0);
		
		log1.add (entry2);
		assertEquals (4.5, log1.getAvgRestedness(), 0);
		
		log1.add (new SleepEntry (new Date (entry2.getDate().getTime() + 86400000)));
		assertEquals (3.0, log1.getAvgRestedness(), .1);
	}

	@Test
	public void testAvgRestfulness()
	{
		log1.add (entry1);
		assertEquals (entry1.getRestfulnessScore(), log1.getAvgRestfulness(), 0);
		
		log1.add (entry2);
		assertEquals (3.5, log1.getAvgRestfulness(), 0);
		
		log1.add (new SleepEntry (new Date (entry2.getDate().getTime() + 86400000)));
		assertEquals (2.3333333, log1.getAvgRestfulness(), .3);
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
