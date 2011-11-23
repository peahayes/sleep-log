<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<h1>
		Sleep Entries
	</h1>

	<c:forEach var="entry" items="${sleepLog.entries}">
		${entry.restedScore}<br>
	</c:forEach>
	
	<p></p>
	
	Average rested score:  ${sleepLog.avgRestedness}