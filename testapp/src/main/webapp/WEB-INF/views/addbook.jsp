<html>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="metro-bootstrap-master/css/metro-bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="../template/header.jsp" %>
    	<c:if test="${not empty msg}">
    		<c:out value="${msg}"/>
    	</c:if>
		<span><b>Add new book:</b></span><br/>		
			<form action="addbook" method="post">
				<label style="width:80px">Title: </label><input type="text" name="title" value="${book.title}" required="required"/> <br/>
				<label style="width:80px">Author: </label><input type="text" name="author" value="${book.author}" required="required"/> <br/>
				<label style="width:80px">ISBN: </label><input type="text" name="isbn" value="${book.isbn}" required="required"/> <br/>
				<label style="width:80px">State: </label><input type="text" name="state" value="${book.state}" required="required"/> <br/>
				<input type="submit" value="Add book"/>
			</form>						
<%@ include file="../template/footer.jsp" %>
</body>
</html>