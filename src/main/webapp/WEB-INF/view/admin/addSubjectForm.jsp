<%@ page import="java.util.*,student.kce.erp.model.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/addSubjectPage.css" rel="stylesheet">
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
        <li class="activeTab"><a href="${pageContext.request.contextPath}/addSubject"><span class="glyphicon glyphicon-apple"></span> Add Subject</a></li>
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
<% List<Batch> batches = (List<Batch>)request.getAttribute("batches");
int i=0;
List<DepartmentSubject> subjects = (List<DepartmentSubject>)request.getAttribute("subjects");
%>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<form:form method="post" action="processAddSubjectForm" modelAttribute="subject">
					<form:hidden path="id" />
					<form:hidden path="adminId"/>
					<form:hidden path="department"/>
					<label for="batch">Batch : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-calendar"></i></span>
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
					<label for="subjectCode">subject code : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-book"></i></span>
							 <form:input path="subjectCode" cssClass="form-control form-control-sm" required="required"/>
					</span><br>
					<label for="subjectTitle">subject title : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-book"></i></span>
							 <form:input path="subjectTitle" cssClass="form-control form-control-sm" required="required"/>
					</span><br>
					<label for="subjectGroup">subject group : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-book"></i></span>
							 <form:select path="subjectGroup" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="HS" label="HS"/>
							 	<form:option value="BS" label="BS"/>
							 	<form:option value="PC" label="PC"/>
							 	<form:option value="PE" label="PE"/>
							 	<form:option value="OE" label="OE"/>
							 	<form:option value="ES" label="ES"/>
							 	<form:option value="EEC" label="EEC"/>
							 </form:select>
					</span><br>
					<label for="credit">credit : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-flag"></i></span>
							 <form:select path="credit" cssClass="form-control form-control-sm" required="required">
							 	<%for(i=1;i<=10;i++){ %>
							 	<option value="<%=i %>"><%=i %></option>
							 	<%} %>
							 </form:select>
					</span><br>
					<input class="btn btn-primary" type="submit" value="Add">
				</form:form>
			</div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 left">
				<table class="table table-responsive">
					<thead>
						<th>Batch</th>
						<th>Semester</th>
						<th>Subject code</th>
						<th>Credit</th>
						<th colspan="2">Action</th>
					</thead>
					<%for(DepartmentSubject temp:subjects){ %>
						<tr>
							<td><%=temp.getBatch() %></td>
							<td><%=temp.getSemester() %></td>
							<td title="<%=temp.getSubjectTitle()%>(<%=temp.getSubjectGroup()%>)"><%=temp.getSubjectCode() %></td>
							<td><%=temp.getCredit() %></td>
							<td>
								<form action="addExam" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-scale"></span> add Exam</button>
							</form>
							</td>
							<td>
							<form onsubmit="return confirm('Do you really want to delete ?')" action="deleteSubject" method="post" style="margin:0px;padding:0px;border:0px;">
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