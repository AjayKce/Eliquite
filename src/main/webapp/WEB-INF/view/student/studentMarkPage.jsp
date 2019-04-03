<%@ page import="java.util.*,student.kce.erp.model.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/studentEnrollPage.css" rel="stylesheet">
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
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/">Eliquite</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
    <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath}/studentEnrollment"><span class="glyphicon glyphicon-blackboard"></span> Enrollment</a></li>
        <li><a href="${pageContext.request.contextPath}/studentFeedback"><span class="glyphicon glyphicon-book"></span> Feedback</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/studentCourse"><span class="glyphicon glyphicon-education"></span> Courses</a></li>
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/studentLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 

<% 
List<StudentMark> studentMarks = (List<StudentMark>)request.getAttribute("studentMarks");
%>
<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 left">
				<table class="table table-responsive">
					<thead>
						<th>Exam Title</th>
						<th>Obtained Mark</th>
						<th>Expected Mark</th>
						<th>Status</th>
					</thead>
					<%for(StudentMark temp:studentMarks){ %>
					<tr class="rowborder">
						<td><%=temp.getExamTitle() %></td>
						<td><%=temp.getObtainedMark() %></td>
						<td><%=temp.getFullMark() %></td>
						<td><%=temp.getStatus() %></td>
					</tr>
					<% }%>
				</table>
				<hr>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12"></div>
		</div>
	</div>

</body>
</html>