<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/sleep-log.css'/>" />
	<link media="screen" rel="stylesheet" href="<c:url value='/resources/css/colorbox.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/scripts/jquery-1.6.1.js'/>"></script>	
	<script type="text/javascript" src="<c:url value='/resources/scripts/jquery.colorbox.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/scripts/popup.js'/>"></script>	
	<script type="text/javascript" src="<c:url value='/resources/scripts/sleep-log.js'/>"></script>	
	<script type="text/javascript" src="<c:url value="/resources/scripts/enterSleep.js" /> "></script>

<!-- 	<script type="text/javascript">
 		var cancelURL='<c:url value="/sleep-log/secure/sleep/viewEntries"/>';
 		setDocVars ('height=300,width=1000');
	</script> -->
</head>

<body>
	<h3>Edit Sleep Entry</h3>
	
	<c:url var="url" value="/secure/sleep/enterSleep/${editMode}" />

	<form:form id="sleepForm" action="${url}" modelAttribute="sleepEntry" method="POST" target="_top">

		<span id="spnWaitImage"><img src="<c:url value='/resources/images/wait.gif'/>"/>Please wait...</span>
	
		<c:if test="${editMode == 'update' }">
			<input type="hidden" name="id" value="${id} "/>
		</c:if>
		
        <div class="form-row">
            <div id="formLabelCol"><label for="energyLevel">Energy Level:</label></div>
            <div id="formFieldCol">
            	<form:select path="energyLevel">
	            	<form:option value="1">Extremely Fatigued</form:option>
	            	<form:option value="2">Moderately Fatigued</form:option>
	            	<form:option value="3">Mildly Fatigued</form:option>
	            	<form:option value="4">Somewhat Energetic</form:option>
		            <form:option value="5">Very Energetic</form:option>
             	</form:select>
            </div>
        </div>
        <div id="formRowSeparator"></div>

        <div class="form-row">
            <div id="formLabelCol"><label for="restedScore">Rested Score:</label></div>
            <div id="formFieldCol">
             	<span class="input">
            		<span id="radioButton"><form:radiobutton path="restedScore" value="1"/>Very poor</span>
            		<span id="radioButton"><form:radiobutton path="restedScore" value="2"/>Poor</span>
            		<span id="radioButton"><form:radiobutton path="restedScore" value="3"/>Fair</span>
            		<span id="radioButton"><form:radiobutton path="restedScore" value="4"/>Good</span>
            		<span id="radioButton"><form:radiobutton path="restedScore" value="5"/>Excellent</span>
             	</span>
            </div>
        </div>
        <div id="formRowSeparator"></div>

        <div class="form-row">
            <div id="formLabelCol"><label for="restfulnessScore">Restfulness Score:</label></div>
            <div id="formFieldCol">
            	<span class="input">
            		<span id="radioButton"><form:radiobutton path="restfulnessScore" value="1"/>Not at all</span>
            		<span id="radioButton"><form:radiobutton path="restfulnessScore" value="2"/>Slightly</span>
            		<span id="radioButton"><form:radiobutton path="restfulnessScore" value="3"/>Somewhat</span>
            		<span id="radioButton"><form:radiobutton path="restfulnessScore" value="4"/>Rested</span>
            		<span id="radioButton"><form:radiobutton path="restfulnessScore" value="5"/>Well rested</span>
            	</span>
            </div>
        </div>       
        <div id="formRowSeparator"></div>

         <div class="form-row">
            <div id="formLabelCol"><label for="numDrinks">Number of Drinks:</label></div>
            <div id="formFieldCol"><span class="input"><form:input path="numDrinks"/></span></div>
        </div>       
        <div id="formRowSeparator"></div>
        
        <c:set var="action" value="Add" />
        
        <c:if test="${editMode == 'update' }">
        	<c:set var="action" value="Update" />
        </c:if>
 
        <div class="form-buttons">
            <div class="button">
            	<a id="cancel" href="javascript:void(0);" >Cancel</a>&nbsp;&nbsp;&nbsp; 
            	<input id="save" name="submit" type="submit" value="${action} Entry" />
            </div>
        </div>
	</form:form>
</body>
</html>