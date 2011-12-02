<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<!-- This demonstrates some css & <div>'s, but couldn't be used
	 	 unless the panel in which it is displayed can scroll -->

	<h2>
		Sleep Entries
	</h2>

	<c:forEach var="entry" items="${sleepLog.entries}">
		<div id="sleepEntryDateCol">
			<a href="updateSleep/${entry.id}"><spring:eval expression="entry.date" /></a>
		</div>
		<div id="sleepEntryInfo">
			<div id="sleepEntryInfoLeftCol">
				<div>Rested: ${entry.restedScore}</div>
				<div>Restfulness: ${entry.restfulnessScore}</div>
			</div>
			<div>
				<div>Num drinks: ${entry.numDrinks}</div>
			</div>
		</div>
		<div id="formRowSeparator"></div>
	</c:forEach>
	
	<p></p>
	
	<h2>
		Averages
	</h2>

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
			<div id="formFieldCol"><spring:eval expression="sleepLog.avgRestfulness" /></div>
		</div>
		<div id="formRowSeparator"></div>
	</div>