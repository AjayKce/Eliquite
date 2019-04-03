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
	<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<form:form method="post" action="/console/processEditSuperUser" modelAttribute="superUser">
					<form:hidden path="id" />
					<label for="username">Username : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="username" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					<label for="password">Password : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="password" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<input class="btn btn-primary" type="submit" value="Update">
				</form:form>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
		</div>
	</div>

</body>
</html>