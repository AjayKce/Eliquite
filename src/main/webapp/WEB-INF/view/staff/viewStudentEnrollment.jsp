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
List<Enrollment> enrollments = (List<Enrollment>)request.getAttribute("enrollments");
Student student = (Student)request.getAttribute("student");
int credit = (int)request.getAttribute("credit");
int HS = (int)request.getAttribute("HS");
int BS = (int)request.getAttribute("BS");
int PC = (int)request.getAttribute("PC");
int PE = (int)request.getAttribute("PE");
int OE = (int)request.getAttribute("OE");
int ES = (int)request.getAttribute("ES");
int EEC = (int)request.getAttribute("EEC");
%>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Batch : </b><%=student.getBatch() %></h5>
				<h5 align="center"><b style="color:brown;">Semester : </b><%=student.getSemester() %></h5>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Student Name : </b><%=student.getFirstname()+" "+student.getLastname() %></h5>
				<h5 align="center"><b style="color:brown;">Roll Number : </b><%=student.getRollno() %></h5>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Department : </b><%=student.getDepartment() %></h5>
				<h5 align="center"><b style="color:brown;">Section : </b><%=student.getSection() %></h5>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><hr></div>
			
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<table class="table table-responsive table-bordered">
						<thead>
							<th>HS</th>
							<th>BS</th>
							<th>PC</th>
							<th>PE</th>
							<th>OE</th>
							<th>ES</th>
							<th>EEC</th>
							<th>Total Credits</th>
						</thead>
						<tr>
							<td><%=HS %></td>
							<td><%=BS %></td>
							<td><%=PC %></td>
							<td><%=PE %></td>
							<td><%=OE %></td>
							<td><%=ES %></td>
							<td><%=EEC %></td>
							<td><%=credit %></td>
						</tr>
				</table>
			</div>
			
			<div class="col-lg-1 col-md-1 col-sm-0 col-xs-0"></div>
			<div class="col-lg-10 col-md-10 col-sm-12 col-xs-12">
				<%if(enrollments.size()==0){ %>
					<h3>No enrollment yet</h3>
				<%}else{ %>
					<table class="table table-responsive table-bordered">
						<thead>
							<th>Subject Code</th>
							<th>Subject Title</th>
							<th>Subject Group</th>
							<th>Subject Credit</th>
							<th colspan="3">Action</th>
						</thead>
						<% for(Enrollment temp:enrollments){ %>
						<tr>
							<td><%=temp.getSubjectCode() %></td>
							<td><%=temp.getSubjectTitle() %></td>
							<td><%=temp.getSubjectGroup() %></td>
							<td><%=temp.getCredit() %></td>
							<td>
							<form action="tutorManageMark" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="subjectId" value="<%=temp.getSubjectId() %>" />
								<input type="hidden" name="studentId" value="<%=temp.getStudentId() %>" />
								<input type="hidden" name="studentRoll" value="<%=temp.getStudentRoll() %>" />
								<input type="hidden" name="subjectCode"	value="<%=temp.getSubjectCode() %>" />
								<button class="btn btn-sm btn-default" type="submit">
        							<i style="padding:3px;color:red;" class="fa fa-book"></i>
        							<i style="padding:3px;" class="fa fa-angle-double-right"></i> Subject Mark
      							</button>
							</form>	
						</td>
						<td>
							<form target="_blank" action="attendanceTutorStudentViewRecord" method="post"	style="margin: 0px; padding: 0px; border: 0px;">
								<input type="hidden" name="studentId" value="<%=temp.getStudentId() %>" />
								<input type="hidden" name="subjectId" value="<%=temp.getSubjectId() %>" />
								<input type="hidden" name="studentName" value="<%=temp.getStudentName() %>" />
								<input type="hidden" name="studentRoll" value="<%=temp.getStudentRoll() %>" />
								<input type="hidden" name="batch" value="<%=temp.getBatch() %>" />
								<input type="hidden" name="semester" value="<%=temp.getSemester() %>" />
								<input type="hidden" name="section" value="<%=temp.getSection() %>" />
								<input type="hidden" name="subjectCode"	value="<%=temp.getSubjectCode() %>" />
								<input type="hidden" name="subjectTitle" value="<%=temp.getSubjectTitle() %>" />
     							<button class="btn btn-sm btn-default" type="submit">
        							<i style="padding:3px;color:red;" class="fa fa-exclamation"></i>
        							<i style="padding:3px;" class="fa fa-angle-double-right"></i> Attendance
      							</button>
							</form>
							</td>
							<td>
								<form onsubmit="return confirm('Do you really want to delete ?')" action="deleteStudentEnrollment" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="eid" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</button>
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