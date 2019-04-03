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
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/">Eliquite</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
    <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath}/addAdmin"><span class="glyphicon glyphicon-plus"></span> Add Admin</a></li>
        <li><a href="${pageContext.request.contextPath}/manageAdmin"><span class="glyphicon glyphicon-tower"></span> Manage Admin</a></li>
        <li><a href="${pageContext.request.contextPath}/collegeFeedback"><span class="glyphicon glyphicon-book"></span> Feedback</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/setFeedback"><span class="glyphicon glyphicon-tag"></span> Set Feedback</a></li>
      	        <li><a href="${pageContext.request.contextPath}/Attendance"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/collegeLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 

<%
List<CreateFeedback> feeds = (List<CreateFeedback>)request.getAttribute("feeds"); 
%>

<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">Student Roll : <b><%=session.getAttribute("studentRoll") %></b></div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">Subject Code : <b><%=session.getAttribute("subjectCode") %></b></div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">Staff Name : <b><%=session.getAttribute("staffName") %></b></div>
				</div>
				<hr>
				<table class="table table-responsive">
					<thead>
						<th>feed</th>
						<th>Result</th>
					</thead>
					<%for(CreateFeedback temp:feeds){ 
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