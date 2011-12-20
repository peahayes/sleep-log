package org.sleepfactory.sleeplog.webtest;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;
import net.sourceforge.jwebunit.junit.WebTester;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sleepfactory.sleeplog.SleepEntry;
import org.sleepfactory.sleeplog.scale.Activity;
import org.sleepfactory.sleeplog.scale.EnergyLevel;
import org.sleepfactory.sleeplog.scale.Restfulness;
import org.sleepfactory.sleeplog.scale.SleepQuality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;

public class ExampleWebTestCase {

	private static WebTester tester = new WebTester();
	
	DateTime firstDate = new DateTime (2011, 3, 1, 6, 0, 0, 0);
	
	final int drinks1 = 0, drinks2 = 6, drinks3 = 2;
	final SleepQuality rested1 = SleepQuality.EXCELLENT,  rested2 = SleepQuality.POOR, rested3 = SleepQuality.GOOD;
	final Restfulness restful1 = Restfulness.WELL_RESTED, restful2 = Restfulness.NOT_AT_ALL, restful3 = Restfulness.RESTED;
	final EnergyLevel energy1 = EnergyLevel.VERY_ENERGETIC, energy2 = EnergyLevel.EXTREMELY_FATIGUED, energy3 = EnergyLevel.SOMEWHAT_ENERGETIC;
	final static Set<Long> activities1 = new HashSet<Long>(), activities2 = new HashSet<Long>(), activities3 = new HashSet<Long>();
	
	final static BrowserVersion defaultBrowserVersion = BrowserVersion.INTERNET_EXPLORER_7;
	final static String baseUrl = "http://localhost:8080/sleep-log";

	final static Logger logger = LoggerFactory.getLogger (ExampleWebTestCase.class);

	@BeforeClass
	public static void setUp()
		throws Exception
	{
		logger.info ("******** SETTING UP BEFORE TESTS ********");
		
		// initialize sets of activities
		activities1.add (Activity.WEIGHTS.valueOf());
		activities1.add (Activity.BIKING.valueOf());
		activities2.add (Activity.WALKING.valueOf());
		activities2.add (Activity.WORKOUT.valueOf());
		activities3.add (Activity.HIKING.valueOf());
		
		// create a web tester so we don't have to use static JWebUnit methods, 
		// enabling us to take advantage of Eclipse's auto-completion to see available
		// methods
		tester.setBaseUrl (baseUrl);

		// this enables us to use message properties instead of hard-coded text
		tester.getTestContext().setResourceBundleName ("messages");

		// set default browser
		tester.getTestContext().setUserAgent (defaultBrowserVersion.getUserAgent());
		
		if (tester.getTestingEngine() instanceof HtmlUnitTestingEngineImpl)
			((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).setDefaultBrowserVersion (defaultBrowserVersion);
	}
	
	@After
	public void tearDown()
	{
		logger.info ("******** TEARING DOWN AFTER TEST ********");
	}
	
	@AfterClass
	public static void clearApplication()
	{
		logger.info ("******** CLEARING APPLICATION ********");

		logger.info ("Going to home page...");
		tester.beginAt (baseUrl);
		
		logger.info ("Clicked View Entries...");
		tester.clickLink ("viewEntries");

		// delete the two SleepEntries
		tester.setExpectedJavaScriptConfirm ("Are you sure you want to delete?", true);
		tester.clickLink ("delete");
	
		tester.setExpectedJavaScriptConfirm ("Are you sure you want to delete?", true);
		tester.clickLink ("delete");
	}
	
	@Test
	public void testViewEntries_noEntries() 
	{
		logger.info ("Going to home page...");
		tester.beginAt ("/");
		
		tester.assertKeyPresent ("header.title");
		tester.assertLinkPresent ("viewEntries");

		logger.info ("Clicked View Entries...");
		tester.clickLink ("viewEntries");
		
		tester.assertTitleEqualsKey ("window.title");
		tester.assertKeyPresent ("no.sleep.entries");
	}

	@Test
	public void testAddEntries()
	{
		SleepEntry entry = addEntry (firstDate, energy1, rested1, restful1, drinks1, activities1);
		
		assertEntryAttributeLabelsPresent();
		assertEntryValuesPresentAsText (entry);
		
		SleepEntry entry2 = addEntry (firstDate.plusDays (1), energy2, rested2, restful2, drinks2, activities2);
		assertEntryValuesPresentAsText (entry2);

		tester.clickLink ("viewEntries");
		
		tester.assertKeyPresent ("avg.energy.label");
		tester.assertKeyPresent ("avg.rested.label");
		tester.assertKeyPresent ("avg.restfulness.label");
		tester.assertKeyPresent ("avg.drinks.label");

		assertAverages (entry, entry2);
	}
	
	@Test
	public void testEditInPopup() throws InterruptedException
	{
		if (tester.getTestingEngine() instanceof HtmlUnitTestingEngineImpl)
		{
			if (((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).getWebClient() != null)
				((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).getWebClient().setAjaxController (new NicelyResynchronizingAjaxController());
		}

		logger.info ("Clicked View Entries...");
		tester.beginAt ("secure/sleep/viewEntries");

		logger.info ("Clicked edit in popup...");
		tester.clickLink ("editInPopup");
		
		logger.info ("Waiting so popup has time to display...");
		Thread.sleep (2000);
		
		// focus on the popup iframe
		logger.info ("Going to editFrame...");
		tester.gotoFrame ("editFrame");
		
		logger.info ("Waiting for goToFrame()...");
		Thread.sleep (400);

		assertTextFieldsForEntryEqual (energy1, rested1, restful1, drinks1, activities1);
		setFieldValues (energy3, rested3, restful3, drinks3, activities3);
		
		tester.assertButtonPresent ("save");
		tester.assertButtonPresentWithText ("Update Entry");

		logger.info ("Clicking Update Entry...");
		tester.clickButtonWithText ("Update Entry");

		// focus back on the main window again
		tester.gotoRootWindow();
		
		tester.assertTextPresent ("Your Sleep Entry Summary");
		assertEntryValuesPresentAsText (energy3, rested3, restful3, drinks3, activities3);
	}

	@Test
	public void testDeleteEntry()
	{
		logger.info ("Clicked View Entries...");
		tester.beginAt ("secure/sleep/viewEntries");
		
		tester.setExpectedJavaScriptConfirm ("Are you sure you want to delete?", true);
		tester.clickLink ("delete");
	}

	@Test
	public void testEditEntry() throws InterruptedException
	{
		// make Ajax calls run synchronously
		if (tester.getTestingEngine() instanceof HtmlUnitTestingEngineImpl)
		{
			if (((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).getWebClient() != null)
				((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).getWebClient().setAjaxController (new NicelyResynchronizingAjaxController());
		}

		logger.info ("Clicked View Entries...");
		tester.beginAt ("secure/sleep/viewEntries");
		tester.clickLink ("edit");
		
		assertTextFieldsForEntryEqual (energy3, rested3, restful3, drinks3, activities3);

		tester.assertTextPresent ("Edit Sleep Entry");
		
		tester.assertLinkPresent ("cancel");
		tester.assertLinkPresentWithText ("Cancel");
		
		tester.assertButtonPresent ("save");
		tester.assertButtonPresentWithText ("Update Entry");
	}

	@Test
	public void testValidation()
	{
		logger.info ("Going to home page...");
		tester.beginAt ("/");
		
		tester.clickRadioOption ("restedScore", String.valueOf (rested1.valueOf()));
		tester.clickRadioOption ("restfulnessScore", String.valueOf (restful1.valueOf()));
		
		for (Long act : activities1)
			tester.checkCheckbox ("activities", String.valueOf (act));
		
		tester.submit();
		
		tester.assertTextPresent ("Please select your level of energy");
		tester.assertTextPresent ("Please enter the number of drinks you consumed");
		
		tester.selectOptionByValue ("energyLevel", String.valueOf (energy1.valueOf()));
		
		tester.submit();
		tester.assertTextPresent ("Please enter the number of drinks you consumed");

		tester.setTextField ("numDrinks", String.valueOf (drinks1));
		tester.submit();

		assertEntryAttributeLabelsPresent();
		assertEntryValuesPresentAsText (energy1, rested1, restful1, drinks1, activities1);	
	}
	
	/**
	 * We use assertTextInElement() here instead of assertTextPresent() because avgEnergy,
	 * avgRested, avgRestfulness, and avgDrinks are just numbers that could appear elsewhere
	 * on the screen for some other reason (e.g., the date).
	 */
	private void assertAverages (SleepEntry entry1, SleepEntry entry2) 
	{
		double avg = entry1.getEnergyLevel() + entry2.getEnergyLevel();
		String avgStr = String.valueOf (avg / 2); 
		tester.assertTextInElement ("jwebunit_avgEnergy", avgStr.substring (0, avgStr.indexOf (".")));
		
		avg = entry1.getRestedScore() + entry2.getRestedScore();
		avgStr = String.valueOf (avg / 2); 
		tester.assertTextInElement ("jwebunit_avgRested", avgStr.substring (0, avgStr.indexOf (".")));

		avg = entry1.getRestfulnessScore() + entry2.getRestfulnessScore();
		avgStr = String.valueOf (avg / 2);
		tester.assertTextInElement ("jwebunit_avgRestful", avgStr.substring (0, avgStr.indexOf (".")));

		avg = entry1.getNumDrinks() + entry2.getNumDrinks();
		avgStr = String.valueOf (avg / 2);
		tester.assertTextInElement ("jwebunit_avgDrinks", avgStr.substring (0, avgStr.indexOf (".")));
	}

	private SleepEntry addEntry (DateTime date, EnergyLevel energy, SleepQuality rested, Restfulness restful, int numDrinks, Set<Long> activities) 
	{
		logger.info ("Going to home page...");
		tester.beginAt ("/");
		
		setFieldValues (energy, rested, restful, numDrinks, activities);
		
		logger.debug ("Saving entry...");
		tester.submit();
		
		return new SleepEntry (date, energy, rested, restful, numDrinks, activities);
	}
	
	private void setFieldValues (EnergyLevel energy, SleepQuality rested, Restfulness restful, int numDrinks, Set<Long> activities) 
	{
		tester.selectOptionByValue ("energyLevel", String.valueOf (energy.valueOf()));
		tester.clickRadioOption ("restedScore", String.valueOf (rested.valueOf()));
		tester.clickRadioOption ("restfulnessScore", String.valueOf (restful.valueOf()));
		tester.setTextField ("numDrinks", String.valueOf (numDrinks));

		for (Long act : activities)
			tester.checkCheckbox ("activities", String.valueOf (act));
	}
	
	private void assertEntryAttributeLabelsPresent() 
	{
		tester.assertKeyPresent ("energy.label");
		tester.assertKeyPresent ("rested.label");
		tester.assertKeyPresent ("restful.label");
		tester.assertKeyPresent ("drinks.label");
		tester.assertKeyPresent ("activities.label");
	}

	/**
	 * We use assertTextInElement() here instead of assertTextPresent() because energyLevel,
	 * restedScore, restfulnessScore, and numDrinks are just numbers that could appear elsewhere
	 * on the screen for some other reason (e.g., the date).
	 */
	private void assertEntryValuesPresentAsText (SleepEntry entry) 
	{
		tester.assertTextInElement ("jwebunit_energy", String.valueOf (entry.getEnergyLevel()));
		tester.assertTextInElement ("jwebunit_rested", String.valueOf (entry.getRestedScore()));
		tester.assertTextInElement ("jwebunit_restfulness", String.valueOf (entry.getRestfulnessScore()));
		tester.assertTextInElement ("jwebunit_drinks", String.valueOf (entry.getNumDrinks()));

		for (int i = 0; i < entry.getActivitiesAsString().split (",").length; i++)
			tester.assertTextInElement ("jwebunit_activities", entry.getActivitiesAsString().split (",")[i].trim());
	}

	/**
	 * We use assertTextInElement() here instead of assertTextPresent() because energyLevel,
	 * restedScore, restfulnessScore, and numDrinks are just numbers that could appear elsewhere
	 * on the screen for some other reason (e.g., the date).
	 */
	private void assertEntryValuesPresentAsText (EnergyLevel energy, SleepQuality rested, Restfulness restful, int numDrinks, Set<Long> activities) 
	{
		tester.assertTextInElement ("jwebunit_energy", String.valueOf (energy.valueOf()));
		tester.assertTextInElement ("jwebunit_rested", String.valueOf (rested.valueOf()));
		tester.assertTextInElement ("jwebunit_restfulness", String.valueOf (restful.valueOf()));
		tester.assertTextInElement ("jwebunit_drinks", String.valueOf (numDrinks));
		
		for (Long act : activities)
			tester.assertTextInElement ("jwebunit_activities", Activity.stringValueOf (act));
	}

	private void assertTextFieldsForEntryEqual (EnergyLevel energy, SleepQuality rested, Restfulness restful, int numDrinks, Set<Long> activities) throws InterruptedException 
	{
		tester.assertSelectedOptionEquals ("energyLevel", energy.qualitative());
		tester.assertRadioOptionSelected ("restedScore", String.valueOf (rested.valueOf()));
		tester.assertRadioOptionSelected ("restfulnessScore", String.valueOf (restful.valueOf()));
		tester.assertTextFieldEquals ("numDrinks", String.valueOf (numDrinks));
		
		for (Long act : activities)
			tester.assertCheckboxSelected ("activities", String.valueOf (act));
	}

}