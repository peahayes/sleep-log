<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<link rel="stylesheet/less" type="text/css" href="<c:url value='/resources/css/sleep-log.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/scripts/less-1.1.5.min.js'/>"></script>	
	<link media="screen" rel="stylesheet" href="<c:url value='/resources/css/colorbox.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/scripts/jquery-1.6.1.js'/>"></script>	
	<script type="text/javascript" src="<c:url value='/resources/scripts/jquery.validation.1.9.0.js'/>"></script>	
	<script type="text/javascript" src="<c:url value='/resources/scripts/jquery.colorbox.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/scripts/popup.js'/>"></script>	
	<script type="text/javascript" src="<c:url value="/resources/scripts/enterSleep.js" /> "></script>
	<script type="text/javascript" src="<c:url value="/resources/scripts/createSleep.js" /> "></script>

<div id="popupBody">
	<h3>Edit Sleep Entry</h3>
	
	<c:url var="url" value="/secure/sleep/enterSleep/${editMode}" />
	
	<form:form id="sleepForm" class="cmxform" action="${url}" modelAttribute="sleepEntry" method="POST" target="_top">

		<span id="spnWaitImage"><img src="<c:url value='/resources/images/wait.gif'/>"/>Please wait...</span>
		
		<c:if test="${editMode == 'update' && sleepEntry.energyLevel != null}">
			<input id="savedEnergyLevel" type="hidden" value="${sleepEntry.energyLevel}"/>
			<input id="savedRestfulScore" type="hidden" value="${sleepEntry.restfulnessScore}"/>
		</c:if>
		
		<c:if test="${editMode == 'update'}">
			<input type="hidden" name="id" value="${id} "/>
		</c:if>
		
        <div id="formLabelCol"><label for="energyLevel">Energy Level:</label></div>
        <div id="energyLevel">
            <!-- This will be populated by Ajax -->
        </div>
        <div id="formRowSeparator"></div>

        <div id="formLabelCol"><label for="restedScore">Rested Score:</label></div>
        <form:radiobuttons path="restedScore" items="${restedScores}" element="div id='radioButton'"  onChange="showRestulness();"/>
       	<div id="formRowSeparator"></div>

		<!-- Hidden until needed -->
        <div id="restfulRow">
            <div id="formLabelCol"><label for="restfulnessScore">Restfulness Score:</label></div>
            <div id="restfulness" class="input">
            		<!-- This will be populated by Ajax -->
            </div>
         	<div id="formRowSeparator"></div>
        </div>       

        <div id="formLabelCol"><label for="activities">Activities:</label></div>
        <form:checkboxes path="activities" items="${activities}" element="div id='radioButton'" />
        <div id="formRowSeparator"></div>

        <div id="formLabelCol"><label for="numDrinks">Number of Drinks:</label></div>
        <div class="input"><form:input path="numDrinks"/></div>
        <div id="formRowSeparator"></div>
        
        <c:set var="action" value="Add" />
        
        <c:if test="${editMode == 'update' }">
        	<c:set var="action" value="Update" />
        </c:if>
 
        <a id="cancel" href="javascript:void(0);" >Cancel</a>&nbsp;&nbsp;&nbsp; 
        <input id="save" name="submit" type="submit" value="${action} Entry" />
	</form:form>
</div>