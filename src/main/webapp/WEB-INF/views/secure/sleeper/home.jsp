<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<h1>
	Your Sleep Entry Summary
</h1>

<div>
	<div id="summaryLabelCol">Rested Score:</div><div id="formFieldCol">${sleepEntry.restedScore}</div>
	<div id="summaryLabelCol">Restfulness Score:</div><div id="formFieldCol">${sleepEntry.restfulnessScore}</div>
	<div id="summaryLabelCol">Number of drinks</div><div id="formFieldCol">${sleepEntry.numDrinks}</div>
</div>
