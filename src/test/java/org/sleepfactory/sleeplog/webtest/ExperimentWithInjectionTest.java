package org.sleepfactory.sleeplog.webtest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sleepfactory.sleeplog.service.SleepService;
import org.sleepfactory.sleeplog.service.SleepService.Score;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith (SpringJUnit4ClassRunner.class)

/**
 * specifies the Spring configuration to load for this test fixture
 */
@ContextConfiguration (locations = { "classpath:spring-test.xml" })

/**
 * Needed to inject beans in the application context.
 */
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
public class ExperimentWithInjectionTest {

	@Inject @Named ("sleepService")
	private SleepService sleepService;
	
	private static final Logger logger = LoggerFactory.getLogger (ExperimentWithInjectionTest.class);

	@Before
	public void setUp()
		throws Exception
	{
		List<Score> levels = sleepService.getEnergyLevels();
		logger.info ("levels = " + levels);
	}
	
	@Test
	public void testSomething()
	{
		
	}
}
