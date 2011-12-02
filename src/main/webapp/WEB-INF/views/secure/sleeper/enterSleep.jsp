<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<c:url var="url" value="/secure/sleep/enterSleep/${editMode}" />

	<form:form action="${url}" modelAttribute="sleepEntry" method="POST" >
	
		<c:if test="${editMode == 'update' }">
			<input type="hidden" name="id" value="${id} "/>
		</c:if>
		
        <div class="form-row">
            <div id="formLabelCol"><label for="restedScore">Rested Score:</label></div>
            <div id="formFieldCol"><span class="input"><form:input path="restedScore"/></span></div>
        </div>
        <div id="formRowSeparator"></div>

        <div class="form-row">
            <div id="formLabelCol"><label for="restfulnessScore">Restfulness Score:</label></div>
            <div id="formFieldCol"><span class="input"><form:input path="restfulnessScore"/></span></div>
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
            	<input name="submit" type="submit" value="${action} Entry" />
            </div>
        </div>
	</form:form>
	