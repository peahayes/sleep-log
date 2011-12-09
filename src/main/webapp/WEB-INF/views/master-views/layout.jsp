<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<title>Sleep Factory</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/sleep-log.css'/>" />
	<link media="screen" rel="stylesheet" href="<c:url value='/resources/css/colorbox.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/scripts/jquery-1.6.1.js'/>"></script>	
	<script type="text/javascript" src="<c:url value='/resources/scripts/jquery.colorbox.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/scripts/popup.js'/>"></script>	
	<script type="text/javascript" src="<c:url value='/resources/scripts/sleep-log.js'/>"></script>	
	<script type="text/javascript" src="<c:url value="/resources/scripts/enterSleep.js" /> "></script>

	<script type="text/javascript">
		var cancelURL='<c:url value="/sleep-log/secure/sleep/viewEntries"/>';
	</script>
</head>

<body>
	<div id="header">
        <tiles:insertAttribute name="header" />
    </div>
    <div>
        <div id="sidebar"><tiles:insertAttribute name="menu" /></div>
        <div id="page-wrap"><tiles:insertAttribute name="body" /></div>
    </div>
    <div id="footer">
        <tiles:insertAttribute name="footer" />
    </div>
</body>
</html>