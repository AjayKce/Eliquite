<%@ page import="java.util.*,student.kce.erp.model.Staff"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link
	href="/stylesheet/editStaffPage.css"
	rel="stylesheet">
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
</head>
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
					<li class="activeTab"><a
						href="${pageContext.request.contextPath}/addStaff"><span
							class="glyphicon glyphicon-plus"></span> Add Staff</a></li>
					<li><a href="${pageContext.request.contextPath}/addSubject"><span
							class="glyphicon glyphicon-apple"></span> Add Subject</a></li>
					<li><a href="${pageContext.request.contextPath}/setTutor"><span
							class="glyphicon glyphicon-adjust"></span> Set Tutor</a></li>
					<li><a
						href="${pageContext.request.contextPath}/subjectAllocation"><span
							class="glyphicon glyphicon-file"></span> Allocate Subject</a></li>
					<li><a href="${pageContext.request.contextPath}/departmentFeedback"><span class="glyphicon glyphicon-tag"></span> Feedback</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a><span class="glyphicon glyphicon-user"></span>
							Hello,<%=request.getAttribute("user")%></a></li>
					<li><a href="${pageContext.request.contextPath}/AdminLogOut"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<form:form method="post" action="processAddStaffForm"
					modelAttribute="staff">
					<form:hidden path="id" />
					<form:hidden path="adminId" />
					<form:hidden path="department" />
					<label for="staffName">Staff Name : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span> <form:input path="staffName"
							cssClass="form-control form-control-sm"
							placeholder="Enter Staff Name" required="required" />
					</span>
					<br>
					<label for="username">User Name : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-user"></i></span> <form:input path="username"
							cssClass="form-control form-control-sm"
							placeholder="Enter User Name" required="required" />
					</span>
					<br>
					<label for="password">Password : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-lock"></i></span> <form:input
							path="password" cssClass="form-control form-control-sm"
							placeholder="Enter password" required="required" />
					</span>
					<br>
					<input class="btn btn-primary" type="submit" value="update">
				</form:form>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
		</div>
		<hr>
	</div>

</body>
</html>