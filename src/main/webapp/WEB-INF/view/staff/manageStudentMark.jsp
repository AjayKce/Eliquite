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

<% 
List<StudentMark> studentMarks = (List<StudentMark>)request.getAttribute("studentMarks");
List<SetExam> setExams = (List<SetExam>)request.getAttribute("setExams");
%>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<h4 align="center" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<b style="color:blue;">Roll Number : </b><b><%=session.getAttribute("studentRoll") %></b>
					</h4>
					<form:form method="post" action="processStudentMarkForm" modelAttribute="studentMark">
					<form:hidden path="id" />
					<label for="examTitle">Exam : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-book"></i></span>
							 <form:select path="examTitle" cssClass="form-control form-control-sm" required="required">
							 	<%for(SetExam temp:setExams){ %>
							 	<form:option value="<%=temp.getExamTitle() %>" label="<%=temp.getExamTitle() %>"/>
							 	<%} %>
							 </form:select>
					</span><br>
					<label for="obtainedMark">Mark : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span>
							 <input type="number" name="obtainedMark" class="form-control form-control-sm" required="required"/>
					</span><br>
					<input class="btn btn-primary" type="submit" value="Save">
				</form:form>
			</div>
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
		</div>
	</div>

</body>
</html>