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
td{
font-size:16px;
}
#link,#demo{
display:none;
}
</style>
<body>
<%
List<Tutor> tutors = (List<Tutor>)request.getAttribute("tutors"); 
String collegeId = session.getAttribute("collegeId").toString();
String adminId = session.getAttribute("adminId").toString();
String staffId = session.getAttribute("staffId").toString();
String department = session.getAttribute("department").toString();
%>
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

<div class="container">
		<div class="row">
			<div class="col-lg-1 col-md-1 col-sm-0 col-xs-0"></div>
			<div class="col-lg-10 col-md-10 col-sm-12 col-xs-12">
				<table class="table table-bordered table-responsive">
					<%if(tutors.size()==0){ %>
					<h3>No Class Rooms Allocated</h3>
					<%}else{ %>
					<thead>
						<th>Batch</th>
						<th>Semester</th>
						<th>Department</th>
						<th>Section</th>
						<th colspan="6">Manage Students</th>
					</thead>
					<%} %>
					<%for(Tutor temp:tutors){ 
					%>
					<tr class="rowborder">
						<td><%=temp.getBatch() %></td>
						<td><%=temp.getSemester() %></td>
						<td><%=temp.getDepartment() %></td>
						<td><%=temp.getSection() %></td>
						<td>
							<form action="addStudent" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="batch" value="<%=temp.getBatch() %>" />
								<input type="hidden" name="semester" value="<%=temp.getSemester() %>" />
								<input type="hidden" name="section" value="<%=temp.getSection() %>" />
								<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Add Student</button>
							</form>
						</td>
						<td>
							<form action="uploadAddStudent" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="batch" value="<%=temp.getBatch() %>" />
								<input type="hidden" name="semester" value="<%=temp.getSemester() %>" />
								<input type="hidden" name="section" value="<%=temp.getSection() %>" />
								<button type="submit" class="btn btn-primary"><span class="fa fa-upload"></span> Upload</button>
							</form>
						</td>
						<td>
							<form action="viewStudents" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="batch" value="<%=temp.getBatch() %>" />
								<input type="hidden" name="semester" value="<%=temp.getSemester() %>" />
								<input type="hidden" name="section" value="<%=temp.getSection() %>" />
								<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-tag"></span> View Students</button>
							</form>
						</td>
						<td>
							<%if(temp.getStatus().equals("ACTIVE")){ %>
							<form action="stopEnrollment" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-book"></span> Stop Enrollment</button>
							</form>
							<%}else{ %>
							<form action="startEnrollment" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-book"></span> Start Enrollment</button>
							</form>
							<%} %>
						</td>
						<td>
							<form action="staffFilterStudents" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="batch" value="<%=temp.getBatch() %>" />
								<input type="hidden" name="semester" value="<%=temp.getSemester() %>" />
								<input type="hidden" name="section" value="<%=temp.getSection() %>" />
								<input type="hidden" name="department" value="<%=temp.getDepartment() %>" />
								<button type="submit" class="btn btn-default"><span class="fa fa-exclamation"></span> Filter Students</button>
							</form>
						</td>
						<td>
							<%if(request.getAttribute("linkReady")==null){ %>
							<form action="getStudentExcel" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="batch" value="<%=temp.getBatch() %>" />
								<input type="hidden" name="semester" value="<%=temp.getSemester() %>" />
								<input type="hidden" name="section" value="<%=temp.getSection() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-book"></span> Excel</button>
							</form>
							<%}else{ %>
								<p class="btn btn-warning" id="demo"></p>
								<a id="link" href="/files/<%=adminId+staffId+department+collegeId %>.xls" download>
								<button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-book"></span> click here</button>
								</a>
							<%} %>
						</td>
					</tr>
					<%} %>
				</table>
				<hr>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-0 col-xs-0"></div>
		</div>
	</div>

<script>
	var myVar = setInterval(myTimer,1000);
	count = 6
	function myTimer() {
		document.getElementById("demo").style.display="block";
	 count=count-1
 if(count==0){
	 document.getElementById("demo").style.display="none";
 	 clearInterval(myVar)
 	document.getElementById("link").style.display="block";
 }
 document.getElementById("demo").innerHTML = count;
}
</script>

</body>
</html>