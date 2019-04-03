<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link
	href="/stylesheet/createAdminForm.css"
	rel="stylesheet">
</head>
<style type="text/css">
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
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/">Eliquite</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
    <ul class="nav navbar-nav">
        <li class="activeTab"><a href="${pageContext.request.contextPath}/addAdmin"><span class="glyphicon glyphicon-plus"></span> Add Admin</a></li>
        <li><a href="${pageContext.request.contextPath}/manageAdmin"><span class="glyphicon glyphicon-tower"></span> Manage Admin</a></li>
         <li><a href="${pageContext.request.contextPath}/collegeFeedback"><span class="glyphicon glyphicon-book"></span> Feedback</a></li>
         <li><a href="${pageContext.request.contextPath}/setFeedback"><span class="glyphicon glyphicon-tag"></span> Set Feedback</a></li>
      	        <li><a href="${pageContext.request.contextPath}/Attendance"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/collegeLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 

	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-0 col-xs-0"></div>
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<%
					if (request.getAttribute("invalidAdmin") != null) {
				%>
				<p style="color: red">Admin has already fixed for this department!!!!</p>
				<%
					}
				%>
				<form:form method="post" action="processCreateAdminForm" modelAttribute="admin">
					<form:hidden path="id" />
					<label for="collegeName">Admin Name : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span> <form:input
							path="adminName" cssClass="form-control form-control-sm"
							placeholder="enter admin name" required="required" />
					</span>
					<label for="username">Username : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-user"></i></span> <form:input path="username"
							cssClass="form-control form-control-sm"
							placeholder="enter username" required="required"/>
					</span>
					<label for="password">Password : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-lock"></i></span> <form:password
							path="password" cssClass="form-control form-control-sm"
							placeholder="enter password" required="required"/>
					</span>
					<label for="department">Department : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span>
							 <form:select path="department" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="CSE" label="CSE"/>
							 	<form:option value="IT" label="IT"/>
							 	<form:option value="EEE" label="EEE"/>
							 	<form:option value="ECE" label="ECE"/>
							 	<form:option value="ETE" label="ETE"/>
							 	<form:option value="CIVIL" label="CIVIL"/>
							 	<form:option value="MECH" label="MECH"/>
							 	<form:option value="AUTOMOBILE" label="AUTOMOBILE"/>
							 </form:select>
					</span>
					<br />
					<input class="btn btn-primary" type="submit" value="Create">
				</form:form>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-0 col-xs-0"></div>
		</div>
	</div>

</body>
</html>