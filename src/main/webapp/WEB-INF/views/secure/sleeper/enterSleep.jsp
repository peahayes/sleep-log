<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<c:url var="url" value="/secure/sleep/enterSleep" /> 
	<form:form action="${url}" modelAttribute="sleepEntry" method="POST" >
        <div class="form-row">
            <label for="restedScore">Rested Score:</label>
            <span class="input"><form:input path="restedScore"/></span>
        </div>       
        <div class="form-buttons">
            <div class="button"><input name="submit" type="submit" value="Create New Entry" /></div>
        </div>
	</form:form>
	