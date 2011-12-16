<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" %>

<h1>
	<spring:message code="comfirmation.screen.title"/>
</h1>

<div>
	<!-- We use divs with jwebunit_xxxx IDs here for JWebUnit; do not change these without also changing
		 the associated JWebUnit tests! -->
	
	<div id="summaryLabelCol"><spring:message code="energy.label"/>:</div>
	<div id="jwebunit_energy">${sleepEntry.energyLevel}</div>
	
	<div id="summaryLabelCol"><spring:message code="rested.label"/>:</div>
	<div id="jwebunit_rested">${sleepEntry.restedScore}</div>
	
	<div id="summaryLabelCol"><spring:message code="restful.label"/>:</div>
	<div id="jwebunit_restfulness">${sleepEntry.restfulnessScore}</div>
	
	<div id="summaryLabelCol"><spring:message code="drinks.label"/>:</div>
	<div id="jwebunit_drinks">${sleepEntry.numDrinks}</div>
	
	<div id="summaryLabelCol"><spring:message code="activities.label"/>:</div>
	<div id="jwebunit_activities">${sleepEntry.activitiesAsString}</div>
</div>
