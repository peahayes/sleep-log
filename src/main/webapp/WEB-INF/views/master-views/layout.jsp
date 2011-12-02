<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet/less" type="text/css" href="<c:url value='/resources/css/sleep-log.css'/>" />
	<script src="<c:url value='/resources/css/less-1.1.5.min.js'/>" type="text/javascript"></script>
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
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