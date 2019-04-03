<%@ page import="java.util.*,student.kce.erp.model.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/addClassPage.css" rel="stylesheet">
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
        <li><a href="${pageContext.request.contextPath}/addBatch"><span class="glyphicon glyphicon-plus"></span> Add Batch</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/addClass"><span class="glyphicon glyphicon-plus"></span> Add Class</a></li>
        <li><a href="${pageContext.request.contextPath}/addStaff"><span class="glyphicon glyphicon-plus"></span> Add Staff</a></li>
        <li><a href="${pageContext.request.contextPath}/addSubject"><span class="glyphicon glyphicon-apple"></span> Add Subject</a></li>
        <li><a href="${pageContext.request.contextPath}/setTutor"><span class="glyphicon glyphicon-adjust"></span> Set Tutor</a></li>
      	<li><a href="${pageContext.request.contextPath}/subjectAllocation"><span class="glyphicon glyphicon-file"></span> Allocate Subject</a></li>
      	<li><a href="${pageContext.request.contextPath}/departmentFeedback"><span class="glyphicon glyphicon-tag"></span> Feedback</a></li>
        </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=request.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/AdminLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 
<% List<StudentClass> classes = (List<StudentClass>)request.getAttribute("classes");
String department = (String)request.getAttribute("dept");
%>
<% List<Batch> batches = (List<Batch>)request.getAttribute("batches"); %>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<form:form method="post" action="processAddClassForm" modelAttribute="studentClass">
					<form:hidden path="id" />
					<form:hidden path="adminId"/>
					<label for="batch">Batch : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-calendar"></i></span>
							 <form:select path="batch" cssClass="form-control form-control-sm" required="required">
							 	<%for(Batch temp:batches){ %>
							 	<form:option value="<%=temp.getYear() %>" label="<%=temp.getYear() %>"/>
							 	<%} %>
							 </form:select>
					</span><br>
					<label for="department">Department : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span>
							 <form:select path="department" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="<%=department %>" label="<%=department %>"/>
							 </form:select>
					</span><br>
					<label for="section">Section : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span>
							 <form:select path="section" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="A" label="A"/>
							 	<form:option value="B" label="B"/>
							 	<form:option value="C" label="C"/>
							 	<form:option value="D" label="D"/>
							 	<form:option value="E" label="E"/>
							 	<form:option value="F" label="F"/>
							 </form:select>
					</span><br>
					<label for="semester">Semester : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span>
							 <form:select path="semester" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="1" label="1"/>
							 	<form:option value="2" label="2"/>
							 	<form:option value="3" label="3"/>
							 	<form:option value="4" label="4"/>
							 	<form:option value="5" label="5"/>
							 	<form:option value="6" label="6"/>
							 	<form:option value="7" label="7"/>
							 	<form:option value="8" label="8"/>
							 </form:select>
					</span><br>
					<input class="btn btn-primary" type="submit" value="Add">
				</form:form>
			</div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 left">
				<table class="table table-responsive">
					<thead>
						<th>Batch</th>
						<th>Department</th>
						<th>Section</th>
						<th>Semester</th>
						<th>Action</th>
					</thead>
					<%for(StudentClass temp:classes){ %>
					<tr class="rowborder">
						<td><%=temp.getBatch() %></td>
						<td><%=temp.getDepartment() %></td>
						<td><%=temp.getSection() %></td>
						<td><%=temp.getSemester() %></td>
						<td>
						<form onsubmit="return confirm('Do you really want to Delete?');" method="post" action="deleteClass" style="margin:0px,border:0px">
						<input type="hidden" name="id" value="<%=temp.getId() %>"/>
						<button type="submit" class="btn btn-danger"><span ></span> delete</button></a>
						</form>
						</td>
					</tr>
					<% }%>
				</table>
				<hr>
			</div>
		</div>
	</div>

</body>
</html>