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
        <li class="activeTab"><a href="${pageContext.request.contextPath}/studentEnrollment"><span class="glyphicon glyphicon-blackboard"></span> Enrollment</a></li>
        <li><a href="${pageContext.request.contextPath}/studentFeedback"><span class="glyphicon glyphicon-book"></span> Feedback</a></li>
        <li><a href="${pageContext.request.contextPath}/studentCourse"><span class="glyphicon glyphicon-education"></span> Courses</a></li>
                <li><a href="${pageContext.request.contextPath}/studentGroupResource"><span class="glyphicon glyphicon-book"></span> Group Resource</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/studentLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 

<% 
List<Enrollment> enrollments = (List<Enrollment>)request.getAttribute("enrollments");
List<DepartmentSubject> subjects = (List<DepartmentSubject>)request.getAttribute("subjects");
String enrollStatus = (String)request.getAttribute("enrollStatus");
%>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<form:form method="post" action="processStudentEnrollForm" modelAttribute="enrollment">
					<form:hidden path="id" />
					<label for="subjectId">Subject : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-book"></i></span>
							 <form:select path="subjectId" cssClass="form-control form-control-sm" required="required">
							 	<%for(DepartmentSubject temp:subjects){ %>
							 	<form:option value="<%=temp.getId() %>" label="<%=temp.getSubjectTitle() %>"/>
							 	<%} %>
							 </form:select>
					</span><br>
					<%if(enrollStatus.equals("ACTIVE")){ %>
					<input class="btn btn-primary" type="submit" value="Add">
					<%} %>
				</form:form>
			</div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 left">
				<table class="table table-responsive">
					<thead>
						<th>Subject Code</th>
						<th>Subject Title</th>
						<%if(enrollStatus.equals("ACTIVE")){ %>
						<th>Action</th>
						<%} %>
					</thead>
					<%for(Enrollment temp:enrollments){ %>
					<tr class="rowborder">
						<td><%=temp.getSubjectCode() %></td>
						<td><%=temp.getSubjectTitle() %></td>
						<%if(enrollStatus.equals("ACTIVE")){ %>
						<td>
							<form onsubmit="return confirm('Do you really want to delete ?')" action="deleteStudentEnrollment" method="post" style="margin:0px;padding:0px;border:0px;">
							<input type="hidden" name="eid" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</button>
								</form>
						</td>
						<%} %>
					</tr>
					<% }%>
				</table>
				<hr>
			</div>
		</div>
	</div>

</body>
</html>