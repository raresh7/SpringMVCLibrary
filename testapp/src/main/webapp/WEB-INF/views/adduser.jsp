<html>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="metro-bootstrap-master/css/metro-bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="../template/header.jsp" %>
    	<c:if test="${not empty msg}">
    		<c:out value="${msg}"></c:out>
    	</c:if>
		<span><b>Add new user:</b></span><br/>		
			<form action="adduser" method="post">
				<label style="width:80px">Name: </label><input type="text" name="name" value="${user.userName}" required="required"/> <br/>
				<label style="width:80px">SSN: </label><input type="text" name="ssn" value="${user.ssn}" required="required"/> <br/>
				<label style="width:80px">Address: </label><input type="text" name="address" value="${user.address}" required="required"/> <br/>
				<label style="width:80px">Access Level: </label><input type="text" name="accessLevel" value="${user.accessLevel}" required="required"/> <br/>
				<label style="width:80px">Admin: </label><input type="checkbox" name="isAdmin" value="true"/> <br/>
				<input type="submit" value="Add user"/>
			</form>						
<%@ include file="../template/footer.jsp" %>
</body>
</html>