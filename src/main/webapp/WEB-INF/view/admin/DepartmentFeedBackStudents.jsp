<%@ page import="java.util.*,student.kce.erp.model.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/addFeedSet.css" rel="stylesheet">
<style>
.activeTab a {
	font-weight: bolder;
}

.activeTab a span {
	color: blue;
}
.container{
margin-top:70px;
}
</style>
</head>
<body id="body">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">Eliquite</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/addBatch"><span
							class="glyphicon glyphicon-plus"></span> Add Batch</a></li>
					<li><a href="${pageContext.request.contextPath}/addClass"><span
							class="glyphicon glyphicon-plus"></span> Add Class</a></li>
					<li><a href="${pageContext.request.contextPath}/addStaff"><span
							class="glyphicon glyphicon-plus"></span> Add Staff</a></li>
					<li><a href="${pageContext.request.contextPath}/addSubject"><span
							class="glyphicon glyphicon-apple"></span> Add Subject</a></li>
					<li><a href="${pageContext.request.contextPath}/setTutor"><span
							class="glyphicon glyphicon-adjust"></span> Set Tutor</a></li>
					<li><a
						href="${pageContext.request.contextPath}/subjectAllocation"><span
							class="glyphicon glyphicon-file"></span> Allocate Subject</a></li>
					<li class="activeTab"><a
						href="${pageContext.request.contextPath}/departmentFeedback"><span
							class="glyphicon glyphicon-tag"></span> Feedback</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a><span class="glyphicon glyphicon-user"></span>
							Hello,<%=session.getAttribute("user")%></a></li>
					<li><a href="${pageContext.request.contextPath}/AdminLogOut"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<%
		List<Batch> batches = (List<Batch>) request.getAttribute("batches");
		List<Student> students = (List<Student>) request.getAttribute("students");
		String department = session.getAttribute("department").toString();
		int i = 0;
	%>
	<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<form method="post" action="getDepartmentFeedBackStudents">
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

							<label for="batch">Batch : </label> <span class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-education"></i></span> <select id="batch"
								name="batch" class="form-control form-control-sm"
								required="required">
									<%
										for (Batch temp : batches) {
									%>
									<option value="<%=temp.getYear()%>"><%=temp.getYear()%></option>
									<%
										}
									%>
							</select>
							</span>

						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<label for="semester">Semester : </label> <span
								class="input-group"> <span class="input-group-addon"><i
									class="glyphicon glyphicon-education"></i></span> <select
								id="semester" name="semester"
								class="form-control form-control-sm" required="required">
									<%
										for (i = 1; i <= 8; i++) {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
									%>
							</select>
							</span>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<label for="department">Department : </label> <span
								class="input-group"> <span class="input-group-addon">
									<i class="glyphicon glyphicon-tag"></i>
							</span> <select name="department" id="department"
								class="form-control form-control-sm" required="required">
									<option value="<%=department%>"><%=department%></option>
							</select>
							</span>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
							<label for="section">section : </label> <span class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-tag"></i>
							</span> <select name="section" id="section"
								class="form-control form-control-sm" required="required">
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="E">E</option>
									<option value="F">F</option>
							</select>
							</span>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<br>
							<center><input class="btn btn-primary"	type="submit" value="search"></center>
						</div>
					</div>
				</form>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12"></div>
		</div>
		
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<table class="table table-responsive">
				<%if(students.size()!=0){ %>
					<thead>
						<th>Roll Number</th>
						<th>Student Name</th>
						<th>Feed</th>
					</thead>
					<%} %>
					<%for(Student temp:students){ 
					%>
					<tr class="rowborder">
						<td><%=temp.getRollno() %></td>
						<td><%=temp.getFirstname()+" "+temp.getLastname() %></td>
						<td>
							<form action="viewDepartmentFeedBackReport" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="studentId" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-tag"></span> feedback Report</button>
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