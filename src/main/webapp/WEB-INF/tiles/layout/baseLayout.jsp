<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="Uacademy - Creative HTML Template">
<meta name="author" content="CreateIT">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1, shrink-to-fit=no">
<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
<!-- <link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> -->

<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/buttons.bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/select.bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/editor.bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datepicker.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-year-calendar.css">	
	<link rel="stylesheet" type="text/css"
	href="css/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/smart_wizard.css">
<link rel="stylesheet" type="text/css" href="css/smart_wizard_theme_dots.css">
<link rel="stylesheet" type="text/css" href="css/smart_wizard_theme_circles.css">
<link rel="stylesheet" type="text/css" href="css/smart_wizard_theme_arrows.css">


<!-- JavaScripts -->
<script>
var isDashBoard=false;
</script>
 <script src="js/main-compiled.js"></script>
 <script src="js/jquery-1.10.2.js"></script>
<!-- <script type="text/javascript" src="webjars/jquery/1.11.1/jquery.min.js"></script> -->
<!-- <script type="text/javascript">
var jQuery_1_1_3 = $.noConflict(true);
</script> -->

<script type="text/javascript"
	src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="js/respond.min.js"></script>
<!-- <script src="js/bootstrap.min.js"></script> -->
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-year-calendar.js"></script>
<script src="js/bootstrap-popover.js"></script>

<script src="js/jquery.dataTables.js"></script>
<script src="js/dataTables.bootstrap.js"></script>
<script src="js/dataTables.buttons.js"></script>
<script src="js/buttons.bootstrap.js"></script>
<script src="js/dataTables.select.js"></script>
<script src="js/dataTables.editor.js"></script>
<script src="js/editor.bootstrap.js"></script>

</head>
<body onload="init();">
	<div>
		<tiles:insertAttribute name="header" />
	</div> 
	<%-- <div style="float: left; padding: 10px; width: 0%;">
		<tiles:insertAttribute name="menu" />
	</div>
	 --%>
	<div>
		<tiles:insertAttribute name="body" />
	</div>
	  <div>
		<tiles:insertAttribute name="footer" />
	</div> 

	<script>
		function init() {
			$("#wrapper").toggleClass("active");
		}
	</script>
</body>
</html>
