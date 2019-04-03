<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link
	href="/stylesheet/createCollegeForm.css"
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
    	<li><a href="${pageContext.request.contextPath}/console"><span class="glyphicon glyphicon-tag"></span> Console</a></li>
        <li><a href="${pageContext.request.contextPath}/addCollege"><span class="glyphicon glyphicon-plus"></span> Add College</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/manageColleges"><span class="glyphicon glyphicon-tower"></span> Manage Colleges</a></li>
        <li><a href="${pageContext.request.contextPath}/extendSuperUser"><span class="glyphicon glyphicon-plus"></span> Extend SuperUser</a></li>
      	<li><a href="${pageContext.request.contextPath}/viewRequisition"><span class="glyphicon glyphicon-file"></span> Requisition</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=request.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/superUserLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 

	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-0 col-xs-0"></div>
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<form:form method="post" action="processEditCollegeForm" modelAttribute="college">
					<form:hidden path="id" />
					<label for="collegeName">College : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-education"></i></span> <form:input
							path="collegeName" cssClass="form-control form-control-sm"
							placeholder="enter college name" required="required" />
					</span>
					<label for="email">Email : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-education"></i></span> <form:input
							path="email" cssClass="form-control form-control-sm"
							placeholder="enter college email" required="required" />
					</span>
					<label for="username">Username : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-user"></i></span> <form:input path="username"
							cssClass="form-control form-control-sm"
							placeholder="enter username" />
					</span>
					<label for="password">Password : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-lock"></i></span> <form:input
							path="password" cssClass="form-control form-control-sm"
							placeholder="enter password" />
					</span>
					<br />
					<input class="btn btn-primary" type="submit" value="Update">
				</form:form>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-0 col-xs-0"></div>
		</div>
	</div>

</body>
</html>