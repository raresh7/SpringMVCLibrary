<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="metro-bootstrap-master/css/metro-bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
</head>
<body>
<%@ include file="../template/header.jsp" %>
		<ul>
			<li><label>User Name: </label> ${loggedUser.userName}</li>
			<li><label>SSN: </label> ${loggedUser.ssn}</li>
			<li><label>Address: </label> ${loggedUser.address}</li>
		</ul>
<%@ include file="../template/footer.jsp" %>
</body>
</html>