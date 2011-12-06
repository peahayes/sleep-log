<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<h1>
		Sleep Entries
	</h1>

	<c:forEach var="entry" items="${sleepLog.entries}">
		<a href="updateSleep/${entry.id}"><spring:eval expression="entry.date" /></a> 
		Rested: ${entry.restedScore}; Restfulness: ${entry.restfulnessScore}
		Num Drinks: ${entry.numDrinks} 
		<a class='example2' href="enterSleep?id=${entry.id}" id="edit">edit</a><br>
	</c:forEach>
	
	<p></p>
	
	<div>
		<div id="formRowSeparator"></div>
		<div>
			<div id="summaryLabelCol">Average rested score:</div>
			<div id="formFieldCol"><spring:eval expression="sleepLog.avgRestedness" /></div>
		</div>
		<div id="formRowSeparator"></div>
		<div>
			<div id="summaryLabelCol">Average restfulness score:</div>
			<div id="formFieldCol"><spring:eval expression="sleepLog.avgRestfulness" /></div>
		</div>
		<div id="formRowSeparator"></div>
		<div>
			<div id="summaryLabelCol">Average num drinks:</div>
			<div id="formFieldCol"><spring:eval expression="sleepLog.avgNumDrinks" /></div>
		</div>
		<div id="formRowSeparator"></div>
	</div>