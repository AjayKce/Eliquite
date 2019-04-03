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
        <li><a href="${pageContext.request.contextPath}/staffClass"><span class="glyphicon glyphicon-blackboard"></span> classes</a></li>
        <li><a href="${pageContext.request.contextPath}/subjectsHandled"><span class="glyphicon glyphicon-education"></span> subject handled</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/staffAttendancePage"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/staffLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 


<% 
String batch=(String)request.getAttribute("batch");
String semester=(String)request.getAttribute("semester");
String section = (String)request.getAttribute("section");
String subjectCode = (String) request.getAttribute("subjectCode");
String subjectTitle = (String) request.getAttribute("subjectTitle");

List<Enrollment> enrolledStudents = (List<Enrollment>)request.getAttribute("enrolledStudents");
float[] percentages = (float[])request.getAttribute("percentages");
String department = session.getAttribute("department").toString();
int i=0;
%>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Batch : </b><%=batch %></h5>
				<h5 align="center"><b style="color:brown;">Semester : </b><%=semester %></h5>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">No of Students : </b><%=enrolledStudents.size() %></h5>
				<h5 align="center"><b style="color:brown;">Subject : </b><%=subjectTitle %></h5>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Class : </b><%=department %> <%=section %></h5>
				<h5 align="center"><b style="color:brown;">SubjectCode : </b><%=subjectCode %></h5>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><hr></div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<table class="table table-responsive">
				<%if(enrolledStudents.size()==0){ %>
					<h3>No Students Enrolled</h3>
				<%}else{%>
					<thead>
						<th>Roll Number</th>
						<th>Student Name</th>
						<th>Percentage</th>
						<th>view</th>
					</thead>
				<%} %>
					<%for(i=0;i<enrolledStudents.size();i++){ %>
					<tr class="rowborder">
						<td><%=enrolledStudents.get(i).getStudentRoll() %></td>
						<td><%=enrolledStudents.get(i).getStudentName() %></td>
						<td><%=(int)percentages[i] %>%</td>
						<td>
							<form target="_blank" action="attendanceViewRecord" method="post"	style="margin: 0px; padding: 0px; border: 0px;">
								<input type="hidden" name="studentId" value="<%=enrolledStudents.get(i).getStudentId() %>" />
								<input type="hidden" name="subjectId" value="<%=enrolledStudents.get(i).getSubjectId() %>" />
								<input type="hidden" name="studentName" value="<%=enrolledStudents.get(i).getStudentName() %>" />
								<input type="hidden" name="studentRoll" value="<%=enrolledStudents.get(i).getStudentRoll() %>" />
								<input type="hidden" name="batch" value="<%=batch%>" />
								<input type="hidden" name="semester" value="<%=semester%>" />
								<input type="hidden" name="section" value="<%=section%>" />
								<input type="hidden" name="subjectCode"	value="<%=subjectCode%>" />
								<input type="hidden" name="subjectTitle" value="<%=enrolledStudents.get(i).getSubjectTitle() %>" />
     							<button class="btn btn-sm btn-default" type="submit">
        							<i style="padding:3px;color:red;" class="fa fa-exclamation"></i>
        							<i style="padding:3px;" class="fa fa-angle-double-right"></i>
      							</button>
							</form>
						</td>
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