<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.*,student.kce.erp.model.*" %>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/collegeHomePage.css" rel="stylesheet">
</head>
<style>
.activeTab a {
	font-weight: bolder;
}

.activeTab a span {
	color: blue;
}
th{
color:blue;
font-size:18px;
text-align:center;
}
td{
font-size:12px;
text-align:center;
font-weight:bolder;
}
.btn{
width:80%;
}
</style>
<body>
<%
String batch=(String)request.getAttribute("batch");
String semester=(String)request.getAttribute("semester");
String department = (String)request.getAttribute("department");
String section = (String)request.getAttribute("section");
String date = (String)request.getAttribute("date");
List<AttendanceScheduler> periods = (List<AttendanceScheduler>)request.getAttribute("periods");
List<FullAttendanceResult> resultAttendance = (List<FullAttendanceResult>)request.getAttribute("resultAttendance");
int i=0;
%>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Batch : </b><%=batch %></h5>
				<h5 align="center"><b style="color:brown;">Semester : </b><%=semester %></h5>
			</div>
			
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<br>
				<h5 align="center"><b style="color:brown;">Date : </b><%=date %></h5>
			</div>
			
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Department : </b><%=department %></h5>
				<h5 align="center"><b style="color:brown;">Section : </b><%=section %></h5>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><hr></div>
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
			<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
				<table class="table table-responsive">
					<thead>
						<th>Roll Number</th>
						<th>Student Name</th>
						<%for(AttendanceScheduler temp:periods){ %>
						<th><%=temp.getHour() %></th>
						<%} %>
					</thead>
					<%for(FullAttendanceResult tempResult:resultAttendance){ %>
					<tr class="rowborder">
						<td><%=tempResult.getStudentRoll() %></td>
						<td><%=tempResult.getStudentName() %></td>
						<%for(i=0;i<tempResult.getPeriod().length;i++){ %>
							<%if(tempResult.getStatus()[i].equals("PRESENT")){ %>
							<td style="color:green;">
							<form:form action="changePresent" method="post" style="margin:0px;padding:0px;border:0px;" modelAttribute="attendanceResult">
								<form:hidden path="id" value="<%=tempResult.getAttendanceResultId()[i] %>"/>
								<form:hidden path="adminId" value="<%=tempResult.getAdminId() %>"/>
								<form:hidden path="collegeId" value="<%=tempResult.getCollegeId() %>"/>
								<form:hidden path="staffId" value="<%=tempResult.getStaffId()[i] %>"/>
								<form:hidden path="subjectId" value="<%=tempResult.getSubjectId()[i] %>"/>
								<form:hidden path="studentId" value="<%=tempResult.getStudentId() %>"/>
								<form:hidden path="batch" value="<%=tempResult.getBatch() %>"/>
								<form:hidden path="semester" value="<%=tempResult.getSemester() %>"/>
								<form:hidden path="department" value="<%=tempResult.getDepartment() %>"/>
								<form:hidden path="section" value="<%=tempResult.getSection() %>"/>
								<form:hidden path="studentName" value="<%=tempResult.getStudentName() %>"/>
								<form:hidden path="rollno" value="<%=tempResult.getStudentRoll() %>"/>
								<form:hidden path="subjectTitle" value="<%=tempResult.getSubjectTitle()[i] %>"/>
								<form:hidden path="subjectCode" value="<%=tempResult.getSubjectCode()[i] %>"/>
								<form:hidden path="staffName" value="<%=tempResult.getStaffName()[i] %>"/>
								<form:hidden path="date" value="<%=tempResult.getDate() %>"/>
								<form:hidden path="period" value="<%=tempResult.getPeriod()[i] %>"/>
								<form:hidden path="startTime" value="<%=tempResult.getStartTime()[i] %>"/>
								<form:hidden path="endTime" value="<%=tempResult.getEndTime()[i] %>"/>
								<form:hidden path="status" value="<%=tempResult.getStatus()[i] %>"/>
								<button type="submit" class="btn btn-sm btn-success"><%= tempResult.getStatus()[i] %></button>
							</form:form>
							</td>
							<%}else if(tempResult.getStatus()[i].equals("ABSENT")){ %>
							<td style="color:red;">
							<form:form action="changeAbsent" method="post" style="margin:0px;padding:0px;border:0px;" modelAttribute="attendanceResult">
								<form:hidden path="id" value="<%=tempResult.getAttendanceResultId()[i] %>"/>
								<form:hidden path="adminId" value="<%=tempResult.getAdminId() %>"/>
								<form:hidden path="collegeId" value="<%=tempResult.getCollegeId() %>"/>
								<form:hidden path="staffId" value="<%=tempResult.getStaffId()[i] %>"/>
								<form:hidden path="subjectId" value="<%=tempResult.getSubjectId()[i] %>"/>
								<form:hidden path="studentId" value="<%=tempResult.getStudentId() %>"/>
								<form:hidden path="batch" value="<%=tempResult.getBatch() %>"/>
								<form:hidden path="semester" value="<%=tempResult.getSemester() %>"/>
								<form:hidden path="department" value="<%=tempResult.getDepartment() %>"/>
								<form:hidden path="section" value="<%=tempResult.getSection() %>"/>
								<form:hidden path="studentName" value="<%=tempResult.getStudentName() %>"/>
								<form:hidden path="rollno" value="<%=tempResult.getStudentRoll() %>"/>
								<form:hidden path="subjectTitle" value="<%=tempResult.getSubjectTitle()[i] %>"/>
								<form:hidden path="subjectCode" value="<%=tempResult.getSubjectCode()[i] %>"/>
								<form:hidden path="staffName" value="<%=tempResult.getStaffName()[i] %>"/>
								<form:hidden path="date" value="<%=tempResult.getDate() %>"/>
								<form:hidden path="period" value="<%=tempResult.getPeriod()[i] %>"/>
								<form:hidden path="startTime" value="<%=tempResult.getStartTime()[i] %>"/>
								<form:hidden path="endTime" value="<%=tempResult.getEndTime()[i] %>"/>
								<form:hidden path="status" value="<%=tempResult.getStatus()[i] %>"/>
								<button  type="submit" class="btn btn-sm btn-danger"><%=tempResult.getStatus()[i] %></button>
							</form:form>
							</td>
							<%}else{ %>
								<td>
								<button  class="btn btn-sm btn-warning"><%=tempResult.getStatus()[i] %></button>
								</td>
							<%} %>
						<%} %>
					</tr>
					<% }%>					
				</table>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
		</div>
	</div>

</body>
</html>