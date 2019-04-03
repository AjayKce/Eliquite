<%@ page import="java.util.*,student.kce.erp.model.*,student.kce.erp.controller.*" %>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/staffClassPage.css" rel="stylesheet">
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
        <li><a href="${pageContext.request.contextPath}/addAdmin"><span class="glyphicon glyphicon-plus"></span> Add Admin</a></li>
        <li><a href="${pageContext.request.contextPath}/manageAdmin"><span class="glyphicon glyphicon-tower"></span> Manage Admin</a></li>
        <li><a href="${pageContext.request.contextPath}/collegeFeedback"><span class="glyphicon glyphicon-book"></span> Feedback</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/setFeedback"><span class="glyphicon glyphicon-tag"></span> Set Feedback</a></li>
                <li><a href="${pageContext.request.contextPath}/Attendance"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/collegeLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 

<%
List<StudentClass> studentClasses = (List<StudentClass>)request.getAttribute("studentClasses"); 
%>

<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<table class="table table-responsive">
					<thead>
						<th>Batch</th>
						<th>Semester</th>
						<th>Department</th>
						<th>Section</th>
						<th>Manage</th>
					</thead>
					<%for(StudentClass temp:studentClasses){ 
					%>
					<tr class="rowborder">
						<td><%=temp.getBatch() %></td>
						<td><%=temp.getSemester() %></td>
						<td><%=temp.getDepartment() %></td>
						<td><%=temp.getSection() %></td>
						<td>
							<form action="subjectsHandledForFeedBack" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="batch" value="<%=temp.getBatch() %>" />
								<input type="hidden" name="semester" value="<%=temp.getSemester() %>" />
								<input type="hidden" name="section" value="<%=temp.getSection() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-tag"></span> subjects</button>
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