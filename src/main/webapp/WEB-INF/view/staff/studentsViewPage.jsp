<%@ page import="java.util.*,student.kce.erp.model.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/addStudentForm.css" rel="stylesheet">
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
        <li class="activeTab"><a href="${pageContext.request.contextPath}/staffClass"><span class="glyphicon glyphicon-blackboard"></span> classes</a></li>
        <li><a href="${pageContext.request.contextPath}/subjectsHandled"><span class="glyphicon glyphicon-education"></span> subject handled</a></li>
                 <li><a href="${pageContext.request.contextPath}/staffAttendancePage"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/staffLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 
<% 
List<Student> students = (List<Student>)request.getAttribute("students");
%>
<div class="container">
		<div class="row">
			<div class="col-lg-1 col-md-1 col-sm-0 col-xs-0"></div>
			<div class="col-lg-10 col-md-10 col-sm-12 col-xs-12">
				<%if(students.size()==0){ %>
					<h3>No students yet</h3>
				<%}else{ %>
					<table class="table table-responsive">
						<thead>
							<th>Roll Number</th>
							<th>Name</th>
							<th>Batch</th>
							<th>Semester</th>
							<th>Class</th>
							<th colspan="4">Action</th>
						</thead>
						<% for(Student temp:students){ %>
						<tr>
							<td><%=temp.getRollno() %></td>
							<td><%=temp.getFirstname() %> <%=temp.getLastname() %></td>
							<td><%=temp.getBatch() %></td>
							<td><%=temp.getSemester() %></td>
							<td><%=temp.getDepartment() %> <%=temp.getSection() %></td>
							<td>
							<form target="_blank" action="viewStudentDetails" method="post" style="margin:0px;padding:0px;border:0px;">
									<input type="hidden" name="id" value="<%=temp.getId() %>" />
									<button type="submit" class="btn btn-default"><i class="fas fa-street-view"></i> Full details</button>
								</form>
							</td>
							<td>
								<form action="editStudent" method="post" style="margin:0px;padding:0px;border:0px;">
									<input type="hidden" name="id" value="<%=temp.getId() %>" />
									<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> Edit</button>
								</form>
							</td>
							<td>
								<form action="deleteStudent" method="post" style="margin:0px;padding:0px;border:0px;">
									<input type="hidden" name="id" value="<%=temp.getId() %>" />
									<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> delete</button>
								</form>
							</td>
							<td>
								<form action="viewStudentEnrollment" method="post" style="margin:0px;padding:0px;border:0px;">
									<input type="hidden" name="sid" value="<%=temp.getId() %>" />
									<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-book"></span> Enrollment</button>
								</form>
							</td>
						</tr>
						<%} %>
					</table>
				<%} %>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-0 col-xs-0"></div>
		</div>
	</div>

</body>
</html>