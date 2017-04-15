<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="Uacademy - Creative HTML Template">
<meta name="author" content="CreateIT">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1, shrink-to-fit=no">

<title><tiles:insertAttribute name="title" ignore="true" /></title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/login.css">



<!-- JavaScripts -->
<script>
</script>
 <script src="js/jquery-1.10.2.js"></script>

<script type="text/javascript"
	src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="js/respond.min.js"></script>

</head>
<body>
	<div>
		<tiles:insertAttribute name="header" />
	</div> 
	<div>
		<tiles:insertAttribute name="body" />
	</div>

</body>
</html>
