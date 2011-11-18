package org.sleepfactory.sleeplog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class SleepLogTest {

	TestSleepData data = new TestSleepData();

	@Before
	public void setUp() throws Exception 
	{

	}
	
	@Test
	public void testSleepEfficiency() 
		throws Exception
	{
		data.log1.add (data.entry);
		assertEquals ((data.entry.getSleepAmount() / data.entry.getInBedAmount()), data.log1.getSleepEfficiency(), 0);
	}
	
	@Test
	public void testAvgSleepAmount()
		throws Exception
	{
		data.log1.add (data.entry);
		assertEquals (data.log1.getAvgSleepAmount(), data.log1.getAvgSleepAmount(), 0);
	}
	
	@Test
	public void testAvgInBedAmount()
	{
		data.log1.add (data.entry);
		assertEquals (data.entry.getInBedAmount(), data.log1.getAvgInBedAmount(), 0);
	}

	@Test
	public void testAvgRestedness()
	{
		data.log1.add (data.entry);
		assertEquals (data.entry.getRestedScore(), data.log1.getAvgRestedness(), 0);
		
		data.log1.add (data.entry2);
		assertEquals (3.5, data.log1.getAvgRestedness(), 0);
		
		data.log1.add (new SleepEntry (new Date (data.entry2.getDate().getTime() + 86400000)));
		assertEquals (2.333, data.log1.getAvgRestedness(), .333);
	}

	@Test
	public void testAvgRestfulness()
	{
		data.log1.add (data.entry);
		assertEquals (data.entry.getRestfulnessScore(), data.log1.getAvgRestfulness(), 0);
		
		data.log1.add (data.entry2);
		assertEquals (4.0, data.log1.getAvgRestfulness(), 0);
		
		data.log1.add (new SleepEntry (new Date (data.entry2.getDate().getTime() + 86400000)));
		assertEquals (2.666666, data.log1.getAvgRestfulness(), .1);
	}

	@Test
	public void testSleepEntriesOrderedByDate()
	{
		data.log1.add (data.entry2);
		data.log1.add (data.entry);
		
		assertEquals (data.entry, data.log1.getEntries().iterator().next());
	}

	@Test
	public void testAdd() 
	{
		assertTrue (data.log1.isEmtpy());
		assertEquals (0, data.log1.getNumEntries());
		
		data.log1.add (data.entry);
		
		assertFalse (data.log1.isEmtpy());
		assertEquals (1, data.log1.getNumEntries());
	}
	
}
