<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Sleep Entry</title>
</head>
<body>

<c:url var="url" value="/secure/sleep/enterSleep" /> 
<form:form action="${url}" modelAttribute="sleepEntry" method="POST" >

    <fieldset>
        <div class="form-row">
            <label for="restedScore">Rested Score:</label>
            <span class="input"><form:input path="restedScore"/></span>
        </div>       
        <div class="form-buttons">
            <div class="button"><input name="submit" type="submit" value="Create New Entry" /></div>
        </div>
    </fieldset>
</form:form>



</body>
</html>