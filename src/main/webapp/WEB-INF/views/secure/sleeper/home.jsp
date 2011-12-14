<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<h1>
	Your Sleep Entry Summary
</h1>

<div>
	<!-- We use divs with jwebunit_xxxx IDs here for JWebUnit; do not change these without also changing
		 the associated JWebUnit tests! -->
	
	<div id="summaryLabelCol">Energy Level:</div>
	<div id="jwebunit_energy">${sleepEntry.energyLevel}</div>
	
	<div id="summaryLabelCol">Rested Score:</div>
	<div id="jwebunit_rested">${sleepEntry.restedScore}</div>
	
	<div id="summaryLabelCol">Restfulness Score:</div>
	<div id="jwebunit_restfulness">${sleepEntry.restfulnessScore}</div>
	
	<div id="summaryLabelCol">Number of drinks:</div>
	<div id="jwebunit_drinks">${sleepEntry.numDrinks}</div>
	
	<div id="summaryLabelCol">Activities:</div>
	<div id="jwebunit_activities">${sleepEntry.activitiesAsString}</div>
</div>
