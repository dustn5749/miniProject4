<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title"/></title>
<link rel="stylesheet" href='<c:url value="/resources/css/style.css"></c:url>'> 
<link href="https://fonts.googleapis.com/css2?family=Bagel+Fat+One&family=Caprasimo&family=Lato:wght@100&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
 <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

</head>
<body>


 	<div id="container">
		<div id="header">
			<tiles:insertAttribute name="header"/>
		</div>
		<div id="content">
		<tiles:insertAttribute name="body"/>
		</div>
		<div id="footer">
		<tiles:insertAttribute name="footer"/>
		</div>
	</div>
	
	  <script src="<c:url value="/resources/js/slideshow.js"/>"></script></body>
</html>      