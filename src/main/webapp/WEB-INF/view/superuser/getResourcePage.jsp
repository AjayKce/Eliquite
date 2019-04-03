<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.*,student.kce.erp.model.*" %>
  <html>

  <head>
    <title>Eliquite</title>
    <jsp:include page="bootstrap.jsp"></jsp:include>
    <link href="/stylesheet/home.css" rel="stylesheet">
    <style>
    .container{
margin-top:70px;
}
    </style>
  </head>
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
          <li><a href="${pageContext.request.contextPath}/getResourcePage"><span class="glyphicon glyphicon-book"></span> Resource</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="${pageContext.request.contextPath}/requestErp"><span class="glyphicon glyphicon-envelope"></span> Request</a></li>
          <li><a href="${pageContext.request.contextPath}/superUserLogin"><span class="glyphicon glyphicon-log-in"></span> superuser</a></li>
        </ul>
        </div>
      </div>
    </nav>
	
	<div class="container">
	<div class="row">
	<form target="_blank" method="post" action="getStudentResources">
		<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12"></div>
		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
			<label for="groupCode">Group Code : </label>
			<span class="input-group"> <span class="input-group-addon">
			<i class="fa fa-book"></i></span>
			<input type="text" name="groupCode" class="form-control form-control-sm" required>
			</span><br>
		</div>
		<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
			<label>&nbsp</label>
			<span class="input-group"> <span class="input-group-addon">
			<i class="fa fa-angle-double-right"></i></span>
			<input type="submit" value="Submit" class="btn btn-primary form-control form-control-sm" required>
			</span><br>
		</div>
		<div class="col-lg-4 col-md-4 col-xs-12 col-sm-12"></div>
	</form>
	</div>
	</div>

  </body>

  </html>