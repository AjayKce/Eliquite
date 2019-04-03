<%@ page import="java.util.*,student.kce.erp.model.*" %>
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
.activeTab a span{
color:blue;
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
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/">Eliquite</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
    <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath}/addBatch"><span class="glyphicon glyphicon-plus"></span> Add Batch</a></li>
        <li><a href="${pageContext.request.contextPath}/addClass"><span class="glyphicon glyphicon-plus"></span> Add Class</a></li>
        <li><a href="${pageContext.request.contextPath}/addStaff"><span class="glyphicon glyphicon-plus"></span> Add Staff</a></li>
        <li><a href="${pageContext.request.contextPath}/addSubject"><span class="glyphicon glyphicon-apple"></span> Add Subject</a></li>
        <li><a href="${pageContext.request.contextPath}/setTutor"><span class="glyphicon glyphicon-adjust"></span> Set Tutor</a></li>
      	<li><a href="${pageContext.request.contextPath}/subjectAllocation"><span class="glyphicon glyphicon-file"></span> Allocate Subject</a></li>
      	<li class="activeTab"><a href="${pageContext.request.contextPath}/departmentFeedback"><span class="glyphicon glyphicon-tag"></span> Feedback</a></li>
    </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/AdminLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 
<% 
CalculateDepartmentFeedPercentage eachfeedPercentage = (CalculateDepartmentFeedPercentage)session.getAttribute("eachFeedPercentages");
int i=0;
%>
<div class="container">
	<div class="row">
		<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12"></div>
		<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
			<table class="table table-bordered table-responsive">
				<thead>
					<th>Excellent</th>
					<th>Good</th>
					<th>Moderate</th>
					<th>Intermediate</th>
					<th>Poor</th>
					<th>Very Poor</th>
					<th>Overall</th>
				</thead>
				<tr>
					<td><%=session.getAttribute("excellentPercentage") %>%</td>
					<td><%=session.getAttribute("goodPercentage") %>%</td>
					<td><%=session.getAttribute("moderatePercentage") %>%</td>
					<td><%=session.getAttribute("intermediatePercentage") %>%</td>
					<td><%=session.getAttribute("poorPercentage") %>%</td>
					<td><%=session.getAttribute("veryPoorPercentage") %>%</td>
					<td><%=session.getAttribute("totalMark") %>%</td>
				</tr>
			</table>
		</div>
		<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12"></div>
	</div>
	
	<div class="row">
		<div class="col-lg-1 col-md-1 col-sm-12 col-xs-12"></div>
		<div class="col-lg-10 col-md-10 col-sm-12 col-xs-12">
		<hr>
			<table class="table table-bordered table-responsive">
				<thead>
					<th>Feed</th>
					<th>Excellent</th>
					<th>Good</th>
					<th>Moderate</th>
					<th>Intermediate</th>
					<th>Poor</th>
					<th>Very Poor</th>
				</thead>
				<%for(i=0;i<eachfeedPercentage.getExcellent().length;i++){ %>
				<tr>
					<td><%=eachfeedPercentage.getFeeds()[i] %></td>
					<td><%=eachfeedPercentage.getExcellent()[i] %>%</td>
					<td><%=eachfeedPercentage.getGood()[i] %>%</td>
					<td><%=eachfeedPercentage.getModerate()[i] %>%</td>
					<td><%=eachfeedPercentage.getIntermediate()[i] %>%</td>
					<td><%=eachfeedPercentage.getPoor()[i] %>%</td>
					<td><%=eachfeedPercentage.getVeryPoor()[i] %>%</td>
				</tr>
				<%} %>
			</table>
		</div>
		<div class="col-lg-1 col-md-1 col-sm-12 col-xs-12"></div>
	</div>
	
</div>

</body>
</html>