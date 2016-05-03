<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="clearfix">
	<nav class="navbar navbar-default" role="navigation">
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="firstpage">Home</a></li>
				<c:if test="${not empty sessionScope.loggedUser}">	
					<c:if test="${not empty tabsList}">
						<c:forEach var="obj" varStatus="status" items="${tabsList}">
							<c:if test="${sessionScope.loggedUser.admin == true || obj.forAdmin == false}">
								<li class="active"><a href="${obj.url}">${obj.label}</a></li>
							</c:if>
						</c:forEach>
					</c:if>
				</c:if>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="signout">Sign Out</a></li>
			</ul>
		</div>
	</nav>
</header>