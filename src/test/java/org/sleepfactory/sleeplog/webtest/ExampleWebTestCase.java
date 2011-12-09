package org.sleepfactory.sleeplog.webtest;

import java.util.HashSet;
import java.util.Set;

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

public class ExampleWebTestCase {

	private WebTester tester;
	
	DateTime firstDate = new DateTime (2011, 3, 1, 6, 0, 0, 0);
	
	final int firstDrinks = 0;
	final SleepQuality firstRested = SleepQuality.EXCELLENT;
	final Restfulness firstRestful = Restfulness.RESTED;
	final EnergyLevel firstEnergy = EnergyLevel.VERY_ENERGETIC;
	final Set<Long> firstActivities = new HashSet<Long>();
	
	final int secondDrinks = 6;
	final SleepQuality secondRested = SleepQuality.POOR;
	final Restfulness secondRestful = Restfulness.NOT_AT_ALL;
	final EnergyLevel secondEnergy = EnergyLevel.EXTREMELY_FATIGUED;
	final Set<Long> secondActivities = new HashSet<Long>();

	final int thirdDrinks = 3;
	final SleepQuality thirdRested = SleepQuality.EXCELLENT;
	final Restfulness thirdRestful = Restfulness.RESTED;
	final EnergyLevel thirdEnergy = EnergyLevel.VERY_ENERGETIC;
	final Set<Long> thirdActivities = new HashSet<Long>();

	private static final Logger logger = LoggerFactory.getLogger (ExampleWebTestCase.class);

	@Before
	public void prepare() 
	{
		firstActivities.add (Activity.BIKING.valueOf());
		firstActivities.add (Activity.WEIGHTS.valueOf());
		
		secondActivities.add (Activity.WALKING.valueOf());
		secondActivities.add (Activity.WORKOUT.valueOf());
		
		thirdActivities.add (Activity.WEIGHTS.valueOf());

		tester = new WebTester();
		tester.setBaseUrl ("http://localhost:8180/sleep-log");
	}
	
	@Test
	public void testViewEntries_noEntries() 
	{
		System.out.println ("Going to home page...");
		tester.beginAt ("/");
		
		tester.assertLinkPresent ("viewEntries");

		System.out.println ("Clicked View Entries...");
		tester.clickLink ("viewEntries");
		
		tester.assertTitleEquals ("Sleep Factory");
		
		assertAveragesLabelsPresent();
	}

	@Test
	public void testBeginAtViewEntries_noEntries() 
	{
		tester.beginAt ("/secure/sleep/viewEntries");
		tester.assertTitleEquals ("Sleep Factory");
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
		System.out.println ("Clicked View Entries...");
		
		tester.beginAt ("/");
		tester.clickLink ("viewEntries");

		System.out.println ("Clicked edit in popup...");
		tester.clickLink ("editInPopup");
		
		System.out.println ("Waiting so popup has time to display...");
		Thread.sleep (2000);
		
		// focus on the popup iframe
		tester.assertFramePresent ("editFrame");

		System.out.println ("Going to editFrame...");
		tester.gotoFrame ("editFrame");
		
		setFieldValues (thirdEnergy, thirdRested, thirdRestful, thirdDrinks, thirdActivities);
		
		tester.assertButtonPresent ("save");
		tester.assertButtonPresentWithText ("Update Entry");

		System.out.println ("Clicked Update Entry...");
		tester.clickButtonWithText ("Update Entry");

		tester.assertTextPresent ("Your Sleep Entry Summary");
		assertEntryValuesPresentAsText (thirdEnergy, thirdRested, thirdRestful, thirdDrinks, thirdActivities);
	}

	@Test
	public void testDeleteEntries()
	{
		System.out.println ("Clicked View Entries...");
		tester.beginAt ("/");
		tester.clickLink ("viewEntries");
		
		tester.setExpectedJavaScriptConfirm ("Are you sure you want to delete?", true);
		tester.clickLink ("delete");
	}

	@Test
	public void testEditEntry()
	{
		System.out.println ("Clicked View Entries...");
		tester.beginAt ("/");
		tester.clickLink ("viewEntries");
	
		assertEntryValuesPresentAsText (thirdEnergy, thirdRested, thirdRestful, thirdDrinks, thirdActivities);

		tester.clickLink ("edit");
		
		tester.assertTextPresent ("Edit Sleep Entry");
		
		tester.assertLinkPresent ("cancel");
		tester.assertLinkPresentWithText ("Cancel");
		
		tester.assertButtonPresent ("save");
		tester.assertButtonPresentWithText ("Update Entry");
		
		assertTextFieldsForEntryEqual (thirdEnergy, thirdRested, thirdRestful, thirdDrinks, thirdActivities);
	}

	private void assertAverages (SleepEntry entry1, SleepEntry entry2) 
	{
		double avg = entry1.getRestedScore() + entry2.getRestedScore();
		String avgStr = String.valueOf (avg / 2); 
		tester.assertTextPresent (avgStr.substring (0, avgStr.indexOf (".")));

		avg = entry1.getRestfulnessScore() + entry2.getRestfulnessScore();
		avgStr = String.valueOf (avg / 2);
		tester.assertTextPresent (avgStr.substring (0, avgStr.indexOf (".")));

		avg = entry1.getNumDrinks() + entry2.getNumDrinks();
		avgStr = String.valueOf (avg / 2);
		tester.assertTextPresent (avgStr.substring (0, avgStr.indexOf (".")));
	}

	private SleepEntry addEntry (DateTime date, EnergyLevel energy, SleepQuality rested, Restfulness restful, int numDrinks, Set<Long> activities) 
	{
		System.out.println ("Going to home page...");
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
		tester.assertTextPresent ("Energy Level");
		tester.assertTextPresent ("Rested Score");
		tester.assertTextPresent ("Restfulness Score");
		tester.assertTextPresent ("Number of drinks");
	}

	private void assertAveragesLabelsPresent() 
	{
		tester.assertTextPresent ("Avg energy");
		tester.assertTextPresent ("Avg rested");
		tester.assertTextPresent ("Avg restfulness");
		tester.assertTextPresent ("Avg drinks");
	}

	private void assertEntryValuesPresentAsText (SleepEntry entry) 
	{
		tester.assertTextPresent (String.valueOf (entry.getEnergyLevel()));
		tester.assertTextPresent (String.valueOf (entry.getRestedScore()));
		tester.assertTextPresent (String.valueOf (entry.getRestfulnessScore()));
		tester.assertTextPresent (String.valueOf (entry.getNumDrinks()));
	}

	private void assertEntryValuesPresentAsText (EnergyLevel energy, SleepQuality rested, Restfulness restful, int numDrinks, Set<Long> activities) 
	{
		tester.assertTextPresent (String.valueOf (energy.valueOf()));
		tester.assertTextPresent (String.valueOf (rested.valueOf()));
		tester.assertTextPresent (String.valueOf (restful.valueOf()));
		tester.assertTextPresent (String.valueOf (numDrinks));
		tester.assertTextPresent (Activity.asString (activities));
	}

	private void assertTextFieldsForEntryEqual (EnergyLevel energy, SleepQuality rested, Restfulness restful, int numDrinks, Set<Long> activities) 
	{
		tester.assertSelectedOptionEquals ("energyLevel", energy.qualitative());
		tester.assertRadioOptionSelected ("restedScore", String.valueOf (rested.valueOf()));
		tester.assertRadioOptionSelected ("restfulnessScore", String.valueOf (restful.valueOf()));
		tester.assertTextFieldEquals ("numDrinks", String.valueOf (numDrinks));
		
		for (Long act : activities)
			tester.assertCheckboxSelected ("activities", String.valueOf (act));
	}

}
