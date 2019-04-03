<%@ page import="java.util.*,student.kce.erp.model.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/addFeedSet.css" rel="stylesheet">
<style>
.activeTab a {
	font-weight: bolder;
}

.activeTab a span {
	color: blue;
}
.container{
margin-top:70px;
}
</style>
</head>
<body id="body">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">Eliquite</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a
						href="${pageContext.request.contextPath}/createCollegeFeedback"><span
							class="glyphicon glyphicon-blackboard"></span> College Feedback</a></li>
					<li class="activeTab"><a
						href="${pageContext.request.contextPath}/createDepartmentFeedback"><span
							class="glyphicon glyphicon-book"></span> Department Feedback</a></li>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a><span class="glyphicon glyphicon-user"></span>
							Hello,<%=session.getAttribute("user")%></a></li>
					<li><a href="${pageContext.request.contextPath}/studentLogOut"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<%
		List<DepartmentFeedbackSet> feedsets = (List<DepartmentFeedbackSet>) request.getAttribute("departmentFeedsets");
	%>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 left">
				<hr>
				<table class="table table-responsive">
					<%
						for (DepartmentFeedbackSet temp : feedsets) {
					%>
					<tr class="rowborder">
						<td><%=temp.getFeedSet()%></td>
						<td>
							<form action="DepartmentFeedSubmit" method="post"
								style="margin: 0px; padding: 0px; border: 0px;">
								<input type="hidden" name="id" value="<%=temp.getId()%>" />
								<button type="submit" class="btn btn-default">
									<span class="glyphicon glyphicon-tag"></span> Feedback
								</button>
							</form>
						</td>
					</tr>
					<%
						}
					%>
				</table>
				<hr>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
		</div>
	</div>

</body>
</html>