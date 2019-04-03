<%@ page import="java.util.*,student.kce.erp.model.*,student.kce.erp.controller.*" %>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/staffClassPage.css" rel="stylesheet">
</head>
<style>
.activeTab a {
	font-weight: bolder;
}
.activeTab a span{
color:blue;
}
.container{
margin-top:70px;
}
</style>
<body>
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
					<li><a href="${pageContext.request.contextPath}/addBatch"><span
							class="glyphicon glyphicon-plus"></span> Add Batch</a></li>
					<li><a href="${pageContext.request.contextPath}/addClass"><span
							class="glyphicon glyphicon-plus"></span> Add Class</a></li>
					<li><a href="${pageContext.request.contextPath}/addStaff"><span
							class="glyphicon glyphicon-plus"></span> Add Staff</a></li>
					<li><a href="${pageContext.request.contextPath}/addSubject"><span
							class="glyphicon glyphicon-apple"></span> Add Subject</a></li>
					<li><a href="${pageContext.request.contextPath}/setTutor"><span
							class="glyphicon glyphicon-adjust"></span> Set Tutor</a></li>
					<li><a
						href="${pageContext.request.contextPath}/subjectAllocation"><span
							class="glyphicon glyphicon-file"></span> Allocate Subject</a></li>
					<li class="activeTab"><a
						href="${pageContext.request.contextPath}/departmentFeedback"><span
							class="glyphicon glyphicon-tag"></span> Feedback</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a><span class="glyphicon glyphicon-user"></span>
							Hello,<%=session.getAttribute("user")%></a></li>
					<li><a href="${pageContext.request.contextPath}/AdminLogOut"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

<%
List<DepartmentFeedbackResult> feeds = (List<DepartmentFeedbackResult>)request.getAttribute("feedResult"); 
%>

<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<br>
				<br>
				<br>
				<table class="table table-responsive">
					<thead>
						<th>feed</th>
						<th>Result</th>
					</thead>
					<%for(DepartmentFeedbackResult temp:feeds){ 
					%>
					<tr class="rowborder">
						<td><%=temp.getFeed() %></td>
						<td><%=temp.getResult() %></td>
					</tr>
					<%} %>
				</table>
				<hr>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
		</div>
	</div>

</body>
</html>