<html>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="metro-bootstrap-master/css/metro-bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="../template/header.jsp" %>
		<span><b>Edit book:</b></span><br/>		
		<c:if test="${empty book}">
		<span>The book cannot be found!</span>
		</c:if>
		<c:if test="${not empty book}">
			<form action="editbook?id=${book.id}" method="post">
				<label style="width:80px">Title: </label><input type="text" name="title" value="${book.title}"/> <br/>
				<label style="width:80px">Author: </label><input type="text" name="author" value="${book.author}"/> <br/>
				<label style="width:80px">ISBN: </label><input type="text" name="isbn" value="${book.isbn}"/> <br/>
				<label style="width:80px">State: </label><input type="text" name="state" value="${book.state}"/> <br/>
				<input type="submit" value="Save modifications"/>
			</form>
		</c:if>								
<%@ include file="../template/footer.jsp" %>
</body>
</html>