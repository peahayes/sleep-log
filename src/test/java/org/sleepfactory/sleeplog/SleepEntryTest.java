package org.sleepfactory.sleeplog;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SleepEntryTest {
	
	SleepEntry entry = new SleepEntry();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEquals() 
	{
		assertTrue (entry.equals (entry));
	}

}
