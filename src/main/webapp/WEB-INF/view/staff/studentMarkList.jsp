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
        <li class="activeTab"><a href="${pageContext.request.contextPath}/subjectsHandled"><span class="glyphicon glyphicon-education"></span> subject handled</a></li>
                 <li><a href="${pageContext.request.contextPath}/staffAttendancePage"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/staffLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 

<% List<Enrollment> enrolledStudents = (List<Enrollment>)request.getAttribute("enrolledStudents");
List<SetExam> setExams = (List<SetExam>)request.getAttribute("setExams");
%>
<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<form:form method="post" action="getStudentListMark" modelAttribute="studentMarkList">
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<label for="examTitle">Exam : </label>
				<span class="input-group"> <span class="input-group-addon"><i
						class="glyphicon glyphicon-book"></i></span>
						 <form:select path="examTitle" cssClass="form-control form-control-sm" required="required">
						 	<%for(SetExam temp:setExams){ %>
						 	<form:option value="<%=temp.getExamTitle() %>" label="<%=temp.getExamTitle() %>"/>
						 	<%} %>
						 </form:select>
				</span><br>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12"></div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<table class="table table-responsive">
				<%if(enrolledStudents.size()==0){ %>
					<h3>No Students Enrolled</h3>
				<%}%>
					<%for(Enrollment temp:enrolledStudents){ %>
					<tr class="rowborder">
						<td><%=temp.getStudentRoll() %></td>
						<td><%=temp.getStudentName() %></td>
						<td>
							<input type="hidden" name="subjectCode" value="<%=temp.getSubjectCode() %>" />
							<input type="hidden" name="studentId" value="<%=temp.getStudentId() %>" />
							<input type="hidden" name="studentRoll" value="<%=temp.getStudentRoll() %>" />
							<input type="hidden" name="subjectId" value="<%=temp.getSubjectId() %>" />
							<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <input type="number" name="obtainedMark" class="form-control form-control-sm" required="required"/>
							</span><br>
						</td>
					</tr>
					<%} %>
				</table>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
					<%if(enrolledStudents.size()!=0){ %>
				<center>
					<button type="submit" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-book"></span> submit</button>
				</center>
				<%}%>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
				</form:form>
				<hr>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
		</div>
	</div>

</body>
</html>