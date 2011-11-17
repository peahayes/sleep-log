package org.sleepfactory.sleeplog;

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
	public void test() {
		fail("Not yet implemented");
	}

}
