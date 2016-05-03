<html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<title>Library site</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="metro-bootstrap-master/css/metro-bootstrap.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
 <script src="//code.jquery.com/jquery-1.10.2.js"></script>
 <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

 <script>
  $(function() {

	  $('#datepicker').datepicker({dateFormat: 'dd.mm.yy'});

	  $( "#datepicker2" ).datepicker({ dateFormat: 'dd.mm.yy' });
  });
 </script>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function callFunction() {
        $.ajax({
            url : 'ajaxtest',
            data: ({param:"test"}),
            success : function(data) {
                $('#replace').html(data);
            }
        });
    }
</script>
 
 
 
</head>
<body>
<%@ include file="../template/header.jsp" %>
<input type="button" onclick="callFunction()" value="press"/>
<div id = "replace">"${msg}"</div>
    	<c:if test="${not empty msg}">
    		<c:out value="${msg}"></c:out>
    	</c:if>
		<span><b>Choose the user you are interested in seeing the info about:</b></span><br/>		
		
			
			<div>
				<form action="addtrans" method="post">
					<label style="width:80px">User: </label>
						<select name="user" onchange="showUserDetails(this.value)">
							<c:forEach var="obj" varStatus="status" items="${users}">
								<option value="${obj.id}" >${obj.userName}</option>							
							</c:forEach>
						</select>
				</form>
			</div>		
			<div id="userinfo">
			<c:if test = "${not empty trans}">
				<table>	
					<thead>
						<tr>
							<th>1</th>	
							<th>1</th>
							<th>1</th>
							<th>1</th>
							<th>1</th>
							<th>1</th>
						</tr>
					</thead>
				<c:forEach var="obj" varStatus="status" items="${trans}">
								<option value="${obj.id}" >${obj.userName}</option>
								<tr>
									<td>${obj.title}</td>
									<td>${obj.author}</td>
									<td>${obj.dateOfBorrow}</td>
									<td>${obj.expectedDateOfReturn}</td>
									<td>${obj.dateOfReturn}</td>
								</tr>							
				</c:forEach>
				</table>
			</c:if>
			</div>					
<%@ include file="../template/footer.jsp" %>
</body>
</html>