package org.sleepfactory.sleeplog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
		entry2.setBedTime (entry.getDate());
		entry3.setRiseTime (entry.getRiseTime());
		entry3.setWakeTime (entry.getWakeTime());
	}

	@Test
	public void testCompareTo()
	{
		assertEquals (0, entry.compareTo (entry));
		assertEquals (-1, entry.compareTo (entry2));
		assertEquals (1, entry2.compareTo (entry));
	}

	@Test
	public void testEquals_differentClass()
	{
		assertFalse (entry.equals (2L));
	}

	@Test
	public void testEquals_null()
	{
		assertFalse (entry.equals (null));
	}

	@Test
	public void testEquals_differentProps() 
	{
		assertFalse (entry.equals (entry2));
	}

	@Test
	public void testEquals_sameProps() 
	{
		assertTrue (entry.equals (entry3));
	}

	@Test
	public void testEquals_sameObject() 
	{
		assertTrue (entry.equals (entry));
	}

}
