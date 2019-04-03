<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/superUserHomePage.css" rel="stylesheet">
<style>
.container{
margin-top:70px;
}
</style>
</head>
<body >
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
        <li><a href="${pageContext.request.contextPath}/manageColleges"><span class="glyphicon glyphicon-tower"></span> Manage Colleges</a></li>
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
		<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<br><br>
				<center>
				<img class="img img-responsive" src="img/superuser.png" alt="Superuser" > 
				</center>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
		</div>
	</div>

</body>
</html>