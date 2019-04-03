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
List<Enrollment> enrolledStudents = (List<Enrollment>)request.getAttribute("enrolledStudents");
List<AttendanceScheduler> schedules = (List<AttendanceScheduler>)request.getAttribute("schedules");
String department = session.getAttribute("department").toString();
%>
<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<form:form method="post" action="processStaffAttendanceList" modelAttribute="attendanceList">
				<%if(enrolledStudents.size()!=0){ %>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<label for="date">Date : </label>
						<span class="input-group"> <span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i></span>
						 <input type="date" class="form-control form-control-sm" name="date" required>
						</span><br>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<label for="department">department : </label>
						<span class="input-group"> <span class="input-group-addon">
						<i class="glyphicon glyphicon-book"></i></span>
						 <form:select path="department" cssClass="form-control form-control-sm" required="required">
						 	<form:option value="<%=department %>" label="<%=department %>"/>
						 </form:select>
						</span><br>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<label for="period">Period : </label>
						<span class="input-group"> <span class="input-group-addon">
						<i class="glyphicon glyphicon-book"></i></span>
						 <form:select path="period" cssClass="form-control form-control-sm" required="required">
						 	<%for(AttendanceScheduler temp:schedules){ %>
						 	<form:option value="<%=temp.getHour() %>" label="<%=temp.getHour() %>"/>
						 	<%} %>
						 </form:select>
						</span><br>
				</div>
				<%} %>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<%if(request.getAttribute("message")!=null){ %>
					<p align="center" style="color:blue"><%=request.getAttribute("message") %></p>
					<%} %>
					<br>
				</div>
				<table class="table table-responsive">
				<%if(enrolledStudents.size()==0){ %>
					<h3>No Students Enrolled</h3>
				<%}else{%>
					<%for(Enrollment temp:enrolledStudents){ %>
					<tr class="rowborder">
						<td><%=temp.getStudentRoll() %></td>
						<td><%=temp.getStudentName() %></td>
						<td>
							<input type="hidden" name="studentName" value="<%=temp.getStudentName() %>" />
							<input type="hidden" name="subjectCode" value="<%=temp.getSubjectCode() %>" />
							<input type="hidden" name="studentId" value="<%=temp.getStudentId() %>" />
							<input type="hidden" name="rollno" value="<%=temp.getStudentRoll() %>" />
							<input type="hidden" name="subjectId" value="<%=temp.getSubjectId() %>" />
							<select class="form-control" name="result">
								<option>PRESENT</option>
								<option>ABSENT</option>
							</select>
						</td>
					</tr>
					<%} %>
				</table>
				<center>
					<button type="submit" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-book"></span> submit</button>
				</center>
				<%} %>
				</form:form>
				<hr>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
		</div>
	</div>

</body>
</html>