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