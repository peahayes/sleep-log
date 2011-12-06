package org.sleepfactory.sleeplog.webtest;

//import static net.sourceforge.jwebunit.junit.JWebUnit.assertLinkPresent;
//import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
//import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
//import static net.sourceforge.jwebunit.junit.JWebUnit.setTestingEngineKey;
//import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
//import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

public class ExampleWebTestCase {

	private WebTester tester;
	
	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl ("http://localhost:8180/sleep-log");
//        setTestingEngineKey (TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);    // use HtmlUnit
	}
	
	@Test
	public void testViewEntries_noEntries() 
	{
		tester.beginAt ("/");
		tester.assertLinkPresent ("viewEntries");
		tester.clickLink ("viewEntries");
		tester.assertTitleEquals ("Sleep Factory");
		tester.assertTextPresent ("Average rested score");
		tester.assertTextPresent ("Average restfulness score");
		tester.assertTextPresent ("Average num drinks");
	}
	
	@Test
	public void testBeginAtViewEntries_noEntries() 
	{
		tester.beginAt ("/secure/sleep/viewEntries");
		tester.assertTitleEquals ("Sleep Factory");
		tester.assertTextPresent ("Average rested score");
		tester.assertTextPresent ("Average rested score");
		tester.assertTextPresent ("Average restfulness score");
		tester.assertTextPresent ("Average num drinks");
		tester.assertLinkPresent ("viewEntries");
	}

	@Test
	public void testAddEntry()
	{
		tester.beginAt ("/");
		
		tester.setTextField ("restedScore", "5");
		tester.setTextField ("restfulnessScore", "4");
		tester.setTextField ("numDrinks", "1");
		
		tester.submit();
		
		tester.assertTextPresent ("Rested Score");
		tester.assertTextPresent ("Restfulness Score");
		tester.assertTextPresent ("Number of drinks");
		
		tester.assertTextPresent ("5");
		tester.assertTextPresent ("4");
		tester.assertTextPresent ("1");
	}

}
