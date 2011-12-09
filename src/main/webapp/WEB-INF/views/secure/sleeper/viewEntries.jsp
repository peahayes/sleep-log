<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<h1>
		Sleep Entries
	</h1>

	<c:forEach var="entry" items="${sleepLog.entries}">
		<div>
			<span id="sleepLogHeaderCol">Date</span>
			<span id="sleepLogHeaderCol">Rested</span>
			<span id="sleepLogHeaderCol">Restful</span>
			<span id="sleepLogHeaderCol">Drinks</span> 
		</div>
		<div>
			<span id="sleepLogDataCol"><spring:eval expression="entry.date" /></span>
			<span id="sleepLogDataCol">${entry.restedScore}</span>
			<span id="sleepLogDataCol">${entry.restfulnessScore}</span>
			<span id="sleepLogDataCol">${entry.numDrinks}</span>
			<a href="updateSleep/${entry.id}" id="edit">edit</a> |
			<a class='example2' href="enterSleep?id=${entry.id}" id="editInPopup">edit in popup</a> |
			<a href="#" onClick="processDeleteClick (${entry.id}, 'deleteEntry');" id="delete">delete</a><br>
		</div>
	</c:forEach>
	
	<p></p>
	
	<div>
		<div id="formRowSeparator"></div>
		<div>
			<div id="summaryLabelCol">Avg energy:</div>
			<div id="formFieldCol"><spring:eval expression="sleepLog.avgEnergyLevel" /></div>
		</div>
		<div id="formRowSeparator"></div>
		<div>
			<div id="summaryLabelCol">Avg rested:</div>
			<div id="formFieldCol"><spring:eval expression="sleepLog.avgRestedness" /></div>
		</div>
		<div id="formRowSeparator"></div>
		<div>
			<div id="summaryLabelCol">Avg restfulness:</div>
			<div id="formFieldCol"><spring:eval expression="sleepLog.avgRestfulness" /></div>
		</div>
		<div id="formRowSeparator"></div>
		<div>
			<div id="summaryLabelCol">Avg drinks:</div>
			<div id="formFieldCol"><spring:eval expression="sleepLog.avgNumDrinks" /></div>
		</div>
		<div id="formRowSeparator"></div>
	</div>