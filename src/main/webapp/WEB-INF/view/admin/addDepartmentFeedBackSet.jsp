<%@ page import="java.util.*,student.kce.erp.model.*" %>
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
.activeTab a span{
color:blue;
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
      	<li><a href="${pageContext.request.contextPath}/subjectAllocation"><span class="glyphicon glyphicon-file"></span> Allocate Subject</a></li>
      	<li class="activeTab"><a href="${pageContext.request.contextPath}/departmentFeedback"><span class="glyphicon glyphicon-tag"></span> Feedback</a></li>
    </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/AdminLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 
<% List<DepartmentFeedbackSet> feedsets = (List<DepartmentFeedbackSet>)request.getAttribute("departmentFeedsets"); %>
<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
					<form:form method="post" action="processDepartmentFeedSetForm" modelAttribute="departmentFeedset">
					<form:hidden path="id" />
					<label for="feedSet">Feed Set : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-calendar"></i></span> <form:input
							path="feedSet" cssClass="form-control form-control-sm"
							placeholder="enter program set" required="required" />
					</span><br>
					<input class="btn btn-primary" type="submit" value="Add">
				</form:form>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-0 col-xs-0"></div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 left">
			<hr>
				<table class="table table-responsive">
					<%for(DepartmentFeedbackSet temp:feedsets){ %>
					<tr class="rowborder">
						<td><%=temp.getFeedSet() %></td>
						<td>
							<form action="addDepartmentFeed" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-book"></span> Feed</button>
							</form>
						</td>
						<td>
							<form action="DepartmentFeedReportBatch" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-book"></span> Batches</button>
							</form>
						</td>
						<td>
							<form onsubmit="return confirm('Do you really want to delete ?')" action="deleteDepartmentFeedSet" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</button>
							</form>
						</td>
						<td>
							<form action="DepartmentFeedFullReport" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-book"></span> Report</button>
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