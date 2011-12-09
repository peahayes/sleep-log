package org.sleepfactory.sleeplog.webtest;

import net.sourceforge.jwebunit.junit.WebTester;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.sleepfactory.sleeplog.SleepEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleWebTestCase {

	private WebTester tester;
	
	DateTime firstDate = new DateTime (2011, 3, 1, 6, 0, 0, 0);
	final long firstRested = 9, firstRestful = 8;
	final int firstDrinks = 0;
	
	final long secondRested = 7, secondRestful = 6;
	final int secondDrinks = 1;

	final long thirdRested = 5, thirdRestful = 4;
	final int thirdDrinks = 3;

	private static final Logger logger = LoggerFactory.getLogger (ExampleWebTestCase.class);

	@Before
	public void prepare() {
		
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
		SleepEntry entry = addEntry (firstDate, firstRested, firstRestful, firstDrinks);
		assertEntryAttributeLabelsPresent();
		assertEntryValuesPresentAsText (entry);
		
		SleepEntry entry2 = addEntry (firstDate.plusDays (1), secondRested, secondRestful, secondDrinks);
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
		
		setFieldValues (thirdRested, thirdRestful, thirdDrinks);

		System.out.println ("Clicked Update Entry...");
		tester.clickButtonWithText ("Update Entry");

		assertEntryValuesPresentAsText (thirdRested, thirdRestful, thirdDrinks);
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
	
		assertEntryValuesPresentAsText (thirdRested, thirdRestful, thirdDrinks);

		tester.clickLink ("edit");
		
		tester.assertTextPresent ("Edit Sleep Entry");
		
		tester.assertLinkPresent ("cancel");
		tester.assertLinkPresentWithText ("Cancel");
		
		tester.assertButtonPresent ("save");
		tester.assertButtonPresentWithText ("Update Entry");
		
		assertTextFieldsForEntryEqual (thirdRested, thirdRestful, thirdDrinks);
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

	private SleepEntry addEntry (DateTime date, Long rested, Long restful, int numDrinks) 
	{
		System.out.println ("Going to home page...");
		tester.beginAt ("/");
		
		SleepEntry entry = new SleepEntry ();
		
		entry.setRestedScore (rested);
		entry.setRestfulnessScore (restful);
		entry.setNumDrinks (numDrinks);
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
		tester.setTextField ("restedScore", String.valueOf (entry.getRestedScore()));
		tester.setTextField ("restfulnessScore", String.valueOf (entry.getRestfulnessScore()));
		tester.setTextField ("numDrinks", String.valueOf (entry.getNumDrinks()));
	}
	
	private void setFieldValues (Long rested, Long restful, int numDrinks) 
	{
		tester.setTextField ("restedScore", String.valueOf (rested));
		tester.setTextField ("restfulnessScore", String.valueOf (restful));
		tester.setTextField ("numDrinks", String.valueOf (numDrinks));
	}
	
	private void assertEntryAttributeLabelsPresent() 
	{
		tester.assertTextPresent ("Rested Score");
		tester.assertTextPresent ("Restfulness Score");
		tester.assertTextPresent ("Number of drinks");
	}

	private void assertAveragesLabelsPresent() 
	{
		tester.assertTextPresent ("Average rested score");
		tester.assertTextPresent ("Average restfulness score");
		tester.assertTextPresent ("Average num drinks");
	}

	private void assertEntryValuesPresentAsText (SleepEntry entry) 
	{
		tester.assertTextPresent (String.valueOf (entry.getRestedScore()));
		tester.assertTextPresent (String.valueOf (entry.getRestfulnessScore()));
		tester.assertTextPresent (String.valueOf (entry.getNumDrinks()));
	}

	private void assertEntryValuesPresentAsText (Long rested, Long restful, int numDrinks) 
	{
		tester.assertTextPresent (String.valueOf (rested));
		tester.assertTextPresent (String.valueOf (restful));
		tester.assertTextPresent (String.valueOf (numDrinks));
	}

	private void assertTextFieldsForEntryEqual (Long rested, Long restful, int numDrinks) 
	{
		tester.assertTextFieldEquals ("restedScore", String.valueOf (rested));
		tester.assertTextFieldEquals ("restfulnessScore", String.valueOf (restful));
		tester.assertTextFieldEquals ("numDrinks", String.valueOf (numDrinks));
	}

}
