<%@ page import="java.util.*,student.kce.erp.model.*" %>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/studentCoursePage.css" rel="stylesheet">
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

<% List<Enrollment> enrollments = (List<Enrollment>)request.getAttribute("enrollments"); %>

<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<table class="table table-responsive">
				<%if(enrollments.size()==0){ %>
					<h3>No Enrolled Subjects</h3>
				<%}else{ %>
					<thead>
						<th>Subject Code</th>
						<th>Subject Title</th>
						<th colspan="3">Manage Subject</th>
					</thead>
				<%} %>
					<%for(Enrollment temp:enrollments){ %>
					<tr class="rowborder">
						<td><%=temp.getSubjectCode() %></td>
						<td><%=temp.getSubjectTitle() %></td>
						<td>
							<form action="studentMark" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="subjectCode" value="<%=temp.getSubjectCode() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-tag"></span> Mark</button>
							</form>	
						</td>
						<td>
							<form action="attendanceStudentViewRecord" method="post"	style="margin: 0px; padding: 0px; border: 0px;">
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
							<form action="studentResource" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="subjectCode" value="<%=temp.getSubjectCode() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-book"></span> Resources</button>
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