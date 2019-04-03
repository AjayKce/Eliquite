<%@ page import="java.util.*,student.kce.erp.model.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/addSubjectAllocationPage.css" rel="stylesheet">
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
        <li><a href="${pageContext.request.contextPath}/addClass"><span class="glyphicon glyphicon-plus"></span> Add Class</a></li>
        <li><a href="${pageContext.request.contextPath}/addStaff"><span class="glyphicon glyphicon-plus"></span> Add Staff</a></li>
        <li><a href="${pageContext.request.contextPath}/addSubject"><span class="glyphicon glyphicon-apple"></span> Add Subject</a></li>
        <li><a href="${pageContext.request.contextPath}/setTutor"><span class="glyphicon glyphicon-adjust"></span> Set Tutor</a></li>
      	<li class="activeTab"><a href="${pageContext.request.contextPath}/subjectAllocation"><span class="glyphicon glyphicon-file"></span> Allocate Subject</a></li>
      	<li><a href="${pageContext.request.contextPath}/departmentFeedback"><span class="glyphicon glyphicon-tag"></span> Feedback</a></li>
    </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=request.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/AdminLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 
<% 
List<Batch> batches = (List<Batch>)request.getAttribute("batches");
List<Staff> staffs = (List<Staff>)request.getAttribute("staffs");
List<DepartmentSubject> subjects = (List<DepartmentSubject>)request.getAttribute("subjects");
List<SubjectAlloc> subjectAllocs = (List<SubjectAlloc>)request.getAttribute("subjectAllocs");
int i=0;
%>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<form:form method="post" action="processAddSubjectAllocationForm" modelAttribute="subjectAlloc">
					<form:hidden path="id" />
					<form:hidden path="adminId"/>
					<form:hidden path="department"/>
					<label for="batch">Batch : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-education"></i></span>
							 <form:select path="batch" cssClass="form-control form-control-sm" required="required">
							 	<%for(Batch temp:batches){ %>
							 	<form:option value="<%=temp.getYear() %>" label="<%=temp.getYear() %>"/>
							 	<%} %>
							 </form:select>
					</span><br>
					<label for="semester">Semester : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-education"></i></span>
							 <form:select path="semester" cssClass="form-control form-control-sm" required="required">
							 	<%for(i=1;i<=8;i++){ %>
							 	<option value="<%=i %>"><%=i %></option>
							 	<%} %>
							 </form:select>
					</span><br>
						<label for="section">section : </label>
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
					<label for="staffName">staff name : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-user"></i></span>
							 <form:select path="staffName" cssClass="form-control form-control-sm" required="required">
							 	<%for(Staff temp:staffs){ %>
							 	<form:option value="<%=temp.getStaffName() %>" label="<%=temp.getStaffName() %>"/>
							 	<%} %>
							 </form:select>
					</span><br>
					<label for="subjectCode">allocated subject : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-book"></i></span>
							 <form:select path="subjectCode" cssClass="form-control form-control-sm" required="required">
							 	<%for(DepartmentSubject temp:subjects){ %>
							 	<form:option value="<%=temp.getSubjectCode() %>" label="<%=temp.getSubjectCode() %>"/>
							 	<%} %>
							 </form:select>
					</span><br>
			
					<input class="btn btn-primary" type="submit" value="Add">
				</form:form>
			</div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 left">
				<table class="table table-responsive">
					<thead>
						<th>batch</th>
						<th>semester</th>
						<th>class</th>
						<th>staff name</th>
						<th>subject handled</th>
						<th>action</th>
					</thead>
					<%for(SubjectAlloc temp:subjectAllocs){ %>
						<tr>
							<td><%=temp.getBatch() %></td>
							<td><%=temp.getSemester() %></td>
							<td><%=temp.getDepartment() %> <%=temp.getSection() %></td>
							<td><%=temp.getStaffName() %></td>
							<td><%=temp.getSubjectCode() %></td>
							<td>
							<form onsubmit="return confirm('Do you really want to delete ?')" action="deleteSubjectAlloc" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</button>
							</form>
							</td>
						</tr>
					<%} %>
				</table>
				<hr>
			</div>
		</div>
	</div>

</body>
</html>