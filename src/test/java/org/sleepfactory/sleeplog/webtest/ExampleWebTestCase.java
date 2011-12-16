package org.sleepfactory.sleeplog.webtest;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;
import net.sourceforge.jwebunit.junit.WebTester;

import org.joda.time.DateTime;
import org.junit.Before;
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

	private WebTester tester;
	
	DateTime firstDate = new DateTime (2011, 3, 1, 6, 0, 0, 0);
	
	final int firstDrinks = 0;
	final SleepQuality firstRested = SleepQuality.EXCELLENT;
	final Restfulness firstRestful = Restfulness.WELL_RESTED;
	final EnergyLevel firstEnergy = EnergyLevel.VERY_ENERGETIC;
	final Set<Long> firstActivities = new HashSet<Long>();
	
	final int secondDrinks = 6;
	final SleepQuality secondRested = SleepQuality.POOR;
	final Restfulness secondRestful = Restfulness.NOT_AT_ALL;
	final EnergyLevel secondEnergy = EnergyLevel.EXTREMELY_FATIGUED;
	final Set<Long> secondActivities = new HashSet<Long>();

	final int thirdDrinks = 2;
	final SleepQuality thirdRested = SleepQuality.GOOD;
	final Restfulness thirdRestful = Restfulness.RESTED;
	final EnergyLevel thirdEnergy = EnergyLevel.SOMEWHAT_ENERGETIC;
	final Set<Long> thirdActivities = new HashSet<Long>();
	
	protected BrowserVersion defaultBrowserVersion = BrowserVersion.INTERNET_EXPLORER_7;

	private static final Logger logger = LoggerFactory.getLogger (ExampleWebTestCase.class);

	@Before
	public void prepare() 
	{
		firstActivities.add (Activity.WEIGHTS.valueOf());
		firstActivities.add (Activity.BIKING.valueOf());
		
		secondActivities.add (Activity.WALKING.valueOf());
		secondActivities.add (Activity.WORKOUT.valueOf());
		
		thirdActivities.add (Activity.HIKING.valueOf());
		
		tester = new WebTester();
		tester.setBaseUrl ("http://localhost:8180/sleep-log");

		tester.getTestContext().setResourceBundleName ("messages");

		tester.getTestContext().setUserAgent (defaultBrowserVersion.getUserAgent());
		
		if (tester.getTestingEngine() instanceof HtmlUnitTestingEngineImpl)
			((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).setDefaultBrowserVersion (defaultBrowserVersion);
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
		
		assertAveragesLabelsPresent();
	}

	@Test
	public void testBeginAtViewEntries_noEntries() 
	{
		tester.beginAt ("/secure/sleep/viewEntries");
		tester.assertTitleEqualsKey ("window.title");
		assertAveragesLabelsPresent();
		tester.assertLinkPresent ("viewEntries");
	}

	@Test
	public void testAddEntry()
	{
		SleepEntry entry = addEntry (firstDate, firstEnergy, firstRested, firstRestful, firstDrinks, firstActivities);
		
		assertEntryAttributeLabelsPresent();
		assertEntryValuesPresentAsText (entry);
		
		SleepEntry entry2 = addEntry (firstDate.plusDays (1), secondEnergy, secondRested, secondRestful, secondDrinks, secondActivities);
		assertEntryValuesPresentAsText (entry2);

		tester.clickLink ("viewEntries");
		
		assertAveragesLabelsPresent();
		assertAverages (entry, entry2);
	}
	
	@Test
	public void testEditInPopup() throws InterruptedException
	{
		logger.info ("Clicked View Entries...");
		
		if (tester.getTestingEngine() instanceof HtmlUnitTestingEngineImpl)
		{
			if (((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).getWebClient() != null)
				((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).getWebClient().setAjaxController (new NicelyResynchronizingAjaxController());
		}

		tester.beginAt ("/");
		tester.clickLink ("viewEntries");

		logger.info ("Clicked edit in popup...");
		tester.clickLink ("editInPopup");
		
		logger.info ("Waiting so popup has time to display...");
		Thread.sleep (2000);
		
		// focus on the popup iframe
		tester.assertFramePresent ("editFrame");

		logger.info ("Going to editFrame...");
		tester.gotoFrame ("editFrame");
		
		logger.info ("Waiting for goToFrame()...");
		Thread.sleep (400);

		assertTextFieldsForEntryEqual (firstEnergy, firstRested, firstRestful, firstDrinks, firstActivities);
		setFieldValues (thirdEnergy, thirdRested, thirdRestful, thirdDrinks, thirdActivities);
		
		tester.assertButtonPresent ("save");
		tester.assertButtonPresentWithText ("Update Entry");

		logger.info ("Clicking Update Entry...");
		tester.clickButtonWithText ("Update Entry");

		// focus back on the main window again
		tester.gotoRootWindow();
		
		tester.assertTextPresent ("Your Sleep Entry Summary");
		assertEntryValuesPresentAsText (thirdEnergy, thirdRested, thirdRestful, thirdDrinks, thirdActivities);
	}

	@Test
	public void testDeleteEntries()
	{
		logger.info ("Clicked View Entries...");
		tester.beginAt ("/");
		tester.clickLink ("viewEntries");
		
		tester.setExpectedJavaScriptConfirm ("Are you sure you want to delete?", true);
		tester.clickLink ("delete");
	}

	@Test
	public void testEditEntry() 
		throws InterruptedException
	{
		if (tester.getTestingEngine() instanceof HtmlUnitTestingEngineImpl)
		{
			if (((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).getWebClient() != null)
				((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).getWebClient().setAjaxController (new NicelyResynchronizingAjaxController());
		}

		logger.info ("Clicked View Entries...");
		tester.beginAt ("/");

		tester.clickLink ("viewEntries");
		tester.clickLink ("edit");
		
		logger.info ("Waiting for setWorkingForm()...");
		Thread.sleep (400);

		assertTextFieldsForEntryEqual (thirdEnergy, thirdRested, thirdRestful, thirdDrinks, thirdActivities);

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
		
		SleepEntry entry = new SleepEntry ();
		
		entry.setRestedScore (firstRested.valueOf());
		entry.setRestfulnessScore (firstRestful.valueOf());
		entry.setActivities (firstActivities);
		entry.setDate (firstDate);
		
		tester.clickRadioOption ("restedScore", String.valueOf (firstRested.valueOf()));
		tester.clickRadioOption ("restfulnessScore", String.valueOf (firstRestful.valueOf()));
		
		for (Long act : entry.getActivities())
			tester.checkCheckbox ("activities", String.valueOf (act));
		
		tester.submit();
		
		tester.assertTextPresent ("Please select your level of energy");
		tester.assertTextPresent ("Please enter the number of drinks you consumed");
		
		entry.setEnergyLevel (firstEnergy.valueOf());
		tester.selectOptionByValue ("energyLevel", String.valueOf (firstEnergy.valueOf()));
		
		tester.submit();
		tester.assertTextPresent ("Please enter the number of drinks you consumed");

		entry.setNumDrinks (firstDrinks);
		tester.setTextField ("numDrinks", String.valueOf (firstDrinks));
		tester.submit();

		assertEntryAttributeLabelsPresent();
		assertEntryValuesPresentAsText (entry);	
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
		
		SleepEntry entry = new SleepEntry ();
		
		entry.setEnergyLevel (energy.valueOf());
		entry.setRestedScore (rested.valueOf());
		entry.setRestfulnessScore (restful.valueOf());
		entry.setNumDrinks (numDrinks);
		entry.setActivities (activities);
		entry.setDate (date);
		entry.setBedTime (date.minusHours (8));
		entry.setRiseTime (date);
		entry.setWakeTime (date.plusMinutes (15));
		
		setFieldValues (entry);
		
		logger.debug ("Saving entry...");
		tester.submit();
		
		return entry;
	}

	private void setFieldValues (SleepEntry entry) 
	{
		tester.selectOptionByValue ("energyLevel", String.valueOf (entry.getEnergyLevel()));
		tester.clickRadioOption ("restedScore", String.valueOf (entry.getRestedScore()));
		tester.clickRadioOption ("restfulnessScore", String.valueOf (entry.getRestfulnessScore()));
		tester.setTextField ("numDrinks", String.valueOf (entry.getNumDrinks()));
		
		for (Long act : entry.getActivities())
			tester.checkCheckbox ("activities", String.valueOf (act));
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

	private void assertAveragesLabelsPresent() 
	{
		tester.assertKeyPresent ("avg.energy.label");
		tester.assertKeyPresent ("avg.rested.label");
		tester.assertKeyPresent ("avg.restfulness.label");
		tester.assertKeyPresent ("avg.drinks.label");
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
		
		String activities = entry.getActivitiesAsString();
		String[] parts = activities.split (",");
		
		tester.assertTextInElement ("jwebunit_activities", parts[0].trim());
		tester.assertTextInElement ("jwebunit_activities", parts[1].trim());
	}

	/**
	 * We use assertTextInElement() here instead of assertTextPresent() because energyLevel,
	 * restedScore, restfulnessScore, and numDrinks are just numbers that could appear elsewhere
	 * on the screen for some other reason (e.g., the date).
	 */
	private void assertEntryValuesPresentAsText (EnergyLevel energy, SleepQuality rested, Restfulness restful, int numDrinks, Set<Long> activities) 
	{
		tester.assertTextInElement ("jwebunit_rested", String.valueOf (rested.valueOf()));
		tester.assertTextInElement ("jwebunit_restfulness", String.valueOf (restful.valueOf()));
		tester.assertTextInElement ("jwebunit_drinks", String.valueOf (numDrinks));
		tester.assertTextInElement ("jwebunit_activities", Activity.asString (activities));
		tester.assertTextInElement ("jwebunit_energy", String.valueOf (energy.valueOf()));
	}

	private void assertTextFieldsForEntryEqual (EnergyLevel energy, SleepQuality rested, Restfulness restful, int numDrinks, Set<Long> activities) 
		throws InterruptedException 
	{
		tester.assertRadioOptionSelected ("restedScore", String.valueOf (rested.valueOf()));
		tester.assertTextFieldEquals ("numDrinks", String.valueOf (numDrinks));
		
		for (Long act : activities)
			tester.assertCheckboxSelected ("activities", String.valueOf (act));

		tester.assertSelectedOptionEquals ("energyLevel", energy.qualitative());
		tester.assertRadioOptionSelected ("restfulnessScore", String.valueOf (restful.valueOf()));
	}

}