package org.sleepfactory.sleeplog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SleepEntryTest {
	
	TestSleepData data = new TestSleepData();
	
	@Before
	public void setUp() throws Exception {

	}
	
	@Test
	public void testGetInBedAmount()
	{
		assertEquals (7.0, data.entry.getInBedAmount(), .1);
		assertEquals (7.0, data.entry2.getInBedAmount(), .1);
		assertEquals (7.0, data.entry3.getInBedAmount(), .1);
	}

	@Test
	public void testGetSleepAmount()
		throws Exception
	{
		assertEquals (7.0, data.entry.getSleepAmount(), .1);
		assertEquals (7.0, data.entry2.getSleepAmount(), .1);
		assertEquals (7.0, data.entry3.getSleepAmount(), .1);
	}

	@Test
	public void testCompareTo()
	{
		assertEquals (0, data.entry.compareTo (data.entry));
		assertEquals (-1, data.entry.compareTo (data.entry2));
		assertEquals (1, data.entry2.compareTo (data.entry));
	}

	@Test
	public void testEquals_differentClass()
	{
		assertFalse (data.entry.equals (2L));
	}

	@Test
	public void testEquals_null()
	{
		assertFalse (data.entry.equals (null));
	}

	@Test
	public void testEquals_differentProps() 
	{
		assertFalse (data.entry.equals (data.entry2));
	}

	@Test
	public void testEquals_sameProps() 
	{
		assertTrue (data.entry.equals (data.entry3));
	}

	@Test
	public void testEquals_sameObject() 
	{
		assertTrue (data.entry.equals (data.entry));
	}

}
