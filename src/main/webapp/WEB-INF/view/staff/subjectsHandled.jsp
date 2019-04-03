<%@ page import="java.util.*,student.kce.erp.model.*" %>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/staffHomePage.css" rel="stylesheet">
</head>
<style>
th{
text-align:center;
font-size:20px;
}
td{
text-align:center;
}
.activeTab a {
	font-weight: bolder;
}
.activeTab a span{
color:blue;
}
.container{
margin-top:70px;
}
td{
font-size:15px;
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

<% List<SubjectAlloc> subjectAllocs = (List<SubjectAlloc>)request.getAttribute("subjectsHandled"); %>

<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<table class="table table-responsive table-bordered">
				<%if(subjectAllocs.size()==0){ %>
					<h3>No Subject Allocated</h3>
				<%}else{ %>
					<thead>
						<th>Batch</th>
						<th>Semester</th>
						<th>Department</th>
						<th>Section</th>
						<th>Subject</th>
						<th colspan="3">Manage Subject</th>
					</thead>
				<%} %>
					<%for(SubjectAlloc temp:subjectAllocs){ %>
					<tr class="rowborder">
						<td><%=temp.getBatch() %></td>
						<td><%=temp.getSemester() %></td>
						<td><%=temp.getDepartment() %></td>
						<td><%=temp.getSection() %></td>
						<td><%=temp.getSubjectCode() %></td>
						<td>
							<form action="manageSubject" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="batch" value="<%=temp.getBatch() %>" />
								<input type="hidden" name="semester" value="<%=temp.getSemester() %>" />
								<input type="hidden" name="section" value="<%=temp.getSection() %>" />
								<input type="hidden" name="subjectCode" value="<%=temp.getSubjectCode() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-book"></span> Manage</button>
							</form>	
						</td>
						<td>
							<form action="addResource" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="batch" value="<%=temp.getBatch() %>" />
								<input type="hidden" name="semester" value="<%=temp.getSemester() %>" />
								<input type="hidden" name="section" value="<%=temp.getSection() %>" />
								<input type="hidden" name="subjectCode" value="<%=temp.getSubjectCode() %>" />
								<button type="submit" class="btn btn-default"><span class="fa fa-file-upload"></span> Add Resource</button>
							</form>	
						</td>
						<td>
							<form action="markList" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="batch" value="<%=temp.getBatch() %>" />
								<input type="hidden" name="semester" value="<%=temp.getSemester() %>" />
								<input type="hidden" name="section" value="<%=temp.getSection() %>" />
								<input type="hidden" name="subjectCode" value="<%=temp.getSubjectCode() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-tag"></span> Set Marks</button>
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