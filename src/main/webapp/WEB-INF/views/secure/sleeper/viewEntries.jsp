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

	<c:if test="${sleepLog.entries == null || sleepLog.entries.size() == 0}">
		<spring:message code="no.sleep.entries"/>
	</c:if>
	
	<c:if test="${sleepLog.entries != null && sleepLog.entries.size() > 0}">
		<span id="mediumHeaderCol">Date</span>
		<span id="shortHeaderCol">Rested</span>
		<span id="shortHeaderCol">Restful</span>
		<span id="shortHeaderCol">Energy</span>
		<span id="shortHeaderCol">Drinks</span> 
		<span id="longHeaderCol">Activities</span> 

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

		<div id="summaryLabelCol"><spring:message code="avg.energy.label"/>:</div>
		<div id="jwebunit_avgEnergy"><spring:eval expression="sleepLog.avgEnergyLevel" /></div>
		<div id="formRowSeparator"></div>
		
		<div id="summaryLabelCol"><spring:message code="avg.rested.label"/>:</div>
		<div id="jwebunit_avgRested"><spring:eval expression="sleepLog.avgRestedness" /></div>
		<div id="formRowSeparator"></div>
		
		<div id="summaryLabelCol"><spring:message code="avg.restfulness.label"/>:</div>
		<div id="jwebunit_avgRestful"><spring:eval expression="sleepLog.avgRestfulness" /></div>
		<div id="formRowSeparator"></div>
		
		<div id="summaryLabelCol"><spring:message code="avg.drinks.label"/>:</div>
		<div id="jwebunit_avgDrinks"><spring:eval expression="sleepLog.avgNumDrinks" /></div>
		<div id="formRowSeparator"></div>
	</c:if>