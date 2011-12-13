<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<h1>
	Your Sleep Entry Summary
</h1>

<div>
	<!-- We use spans with IDs here for JWebUnit; do not change these without also changing
		 the associated JWebUnit tests! -->
	
	<div id="summaryLabelCol">Energy Level:</div>
	<div id="formFieldCol"><span id="jwebunit_energy">${sleepEntry.energyLevel}</span></div>
	
	<div id="summaryLabelCol">Rested Score:</div>
	<div id="formFieldCol"><span id="jwebunit_rested">${sleepEntry.restedScore}</span></div>
	
	<div id="summaryLabelCol">Restfulness Score:</div>
	<div id="formFieldCol"><span id="jwebunit_restfulness">${sleepEntry.restfulnessScore}</span></div>
	
	<div id="summaryLabelCol">Number of drinks:</div>
	<div id="formFieldCol"><span id="jwebunit_drinks">${sleepEntry.numDrinks}</span></div>
	
	<div id="summaryLabelCol">Activities:</div>
	<div id="formFieldCol"><span id="jwebunit_activities">${sleepEntry.activitiesAsString}</span></div>
</div>
