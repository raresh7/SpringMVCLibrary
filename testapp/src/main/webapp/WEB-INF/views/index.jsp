<html>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="metro-bootstrap-master/css/metro-bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="../template/header.jsp" %>
<h2>Hello World! - index</h2>
    	<c:if test="${not empty msg}">
    		<c:out value="${msg}"></c:out>
    	</c:if>
		<c:if test="${empty loggedUser}">
			<b>Login:</b>
			<form action="login" method="POST">
				<label>User Name: </label> <input type="text" name="user"/>
				<input type="submit" value="login"/>
			</form>
		</c:if>
		<c:if test="${not empty loggedUser}">
			<b>Hello, ${loggedUser.userName}, you may proceed now</b>
		</c:if>	

<%@ include file="../template/footer.jsp" %>
</body>
</html>