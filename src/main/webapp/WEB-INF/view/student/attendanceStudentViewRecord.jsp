<%@ page import="java.util.*,student.kce.erp.model.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/manageSubject.css" rel="stylesheet">
</head>
<style>
.activeTab a {
	font-weight: bolder;
}
.activeTab a span{
color:blue;
}

th{
color:blue;
font-size:18px;
text-align:center;
}
td{
font-size:13px;
text-align:center;
font-weight:bolder;
}
.container{
margin-top:70px;
}
</style>
<body>

<% 
String batch=(String)request.getAttribute("batch");
String semester=(String)request.getAttribute("semester");
String section = (String)request.getAttribute("section");
String subjectCode = (String) request.getAttribute("subjectCode");
String subjectTitle = (String) request.getAttribute("subjectTitle");
String department = session.getAttribute("department").toString();
String studentRoll = (String) request.getAttribute("studentRoll").toString();
String studentName = (String) request.getAttribute("studentName").toString();
float overallPercentage =(float) request.getAttribute("overallPercentage");

List<AttendanceResult> presentDays=(List<AttendanceResult>) request.getAttribute("presentDays");
List<AttendanceResult> absentDays=(List<AttendanceResult>) request.getAttribute("absentDays");

%>

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
                <li><a href="${pageContext.request.contextPath}/studentGroupResource"><span class="glyphicon glyphicon-book"></span> Group Resource</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/studentLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 

<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Batch : </b><%=batch %></h5>
				<h5 align="center"><b style="color:brown;">Semester : </b><%=semester %></h5>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Student Name : </b><%=studentName %> &nbsp<b style="color:brown;">Roll No : </b><%=studentRoll %></h5>
				<h5 align="center"><b style="color:brown;">Subject : </b><%=subjectTitle %></h5>
				<h4 align="center"><b style="color:violet;">Percentage : </b><%=(int)overallPercentage %>%</h5>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Class : </b><%=department %> <%=section %></h5>
				<h5 align="center"><b style="color:brown;">SubjectCode : </b><%=subjectCode %></h5>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><hr></div>
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<h5 align="center"><b style="color:brown;">Present Days</b></h5>
				<table class="table table-responsive table-bordered">
					<thead>
						<th>Date</th>
						<th>Period</th>
					</thead>
					<%for(AttendanceResult temp:presentDays){ %>
					<tr>
						<td><%=temp.getDate() %></td>
						<td><%=temp.getPeriod() %></td>
					</tr>
					<%} %>
				</table>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<h5 align="center"><b style="color:brown;">Absent Days</b></h5>
				<table class="table table-responsive table-bordered">
					<thead>
						<th>Date</th>
						<th>Period</th>
					</thead>
					<%for(AttendanceResult temp:absentDays){ %>
					<tr>
						<td><%=temp.getDate() %></td>
						<td><%=temp.getPeriod() %></td>
					</tr>
					<%} %>
				</table>
			</div>
		</div>
	</div>

</body>
</html>