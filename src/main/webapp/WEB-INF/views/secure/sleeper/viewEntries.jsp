<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<link media="screen" rel="stylesheet" href="<c:url value='/resources/css/colorbox.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/scripts/jquery.colorbox.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/scripts/popup.js'/>"></script>	
	<script type="text/javascript" src="<c:url value="/resources/scripts/enterSleep.js" /> "></script>
	<script type="text/javascript" src="<c:url value="/resources/scripts/updateSleep.js" /> "></script>

	<h1>
		Sleep Entries
	</h1>

	<div>
		<span id="mediumHeaderCol">Date</span>
		<span id="shortHeaderCol">Rested</span>
		<span id="shortHeaderCol">Restful</span>
		<span id="shortHeaderCol">Energy</span>
		<span id="shortHeaderCol">Drinks</span> 
		<span id="longHeaderCol">Activities</span> 
	</div>

	<c:forEach var="entry" items="${sleepLog.entries}">
		<div>
			<span id="mediumDataCol"><spring:eval expression="entry.date" /></span>
			<span id="shortDataCol">${entry.restedScore}</span>
			<span id="shortDataCol">${entry.restfulnessScore}</span>
			<span id="shortDataCol">${entry.energyLevel}</span>
			<span id="shortDataCol">${entry.numDrinks}</span>
			<span id="longDataCol">${entry.activitiesAsString}</span>
		</div>
		<div>
			<span id="mediumDataCol"></span>
			<span id="shortDataCol"></span>
			<span id="shortDataCol"></span>
			<span id="shortDataCol"></span>
			<span id="shortDataCol"></span>
			<span id="longDataCol">
				<a href="updateSleep/${entry.id}" id="edit">edit</a> |
				<a class='editFrame' href="enterSleep?id=${entry.id}" id="editInPopup">edit in popup</a> |
				<a href="#" onClick="processDeleteClick (${entry.id}, 'deleteEntry');" id="delete">delete</a><br>
			</span>
		</div>
		<div id="formRowSeparator"></div>
	</c:forEach>
	
	<p></p>
	
	<!-- We use spans with IDs here for JWebUnit; do not change these without also changing
		 the associated JWebUnit tests! -->

	<div>
		<div>
			<div id="summaryLabelCol">Avg energy:</div>
			<div id="formFieldCol">
				<span id="jwebunit_avgEnergy"><spring:eval expression="sleepLog.avgEnergyLevel" /></span>
			</div>
		</div>
		<div id="formRowSeparator"></div>
		<div>
			<div id="summaryLabelCol">Avg rested:</div>
			<div id="formFieldCol">
				<span id="jwebunit_avgRested"><spring:eval expression="sleepLog.avgRestedness" /></span>
			</div>
		</div>
		<div id="formRowSeparator"></div>
		<div>
			<div id="summaryLabelCol">Avg restfulness:</div>
			<div id="formFieldCol">
				<span id="jwebunit_avgRestful"><spring:eval expression="sleepLog.avgRestfulness" /></span>
			</div>
		</div>
		<div id="formRowSeparator"></div>
		<div>
			<div id="summaryLabelCol">Avg drinks:</div>
			<div id="formFieldCol">
				<span id="jwebunit_avgDrinks"><spring:eval expression="sleepLog.avgNumDrinks" /></span>
			</div>
		</div>
		<div id="formRowSeparator"></div>
	</div>