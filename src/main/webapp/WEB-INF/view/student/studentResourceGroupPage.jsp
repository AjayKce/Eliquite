<%@ page import="java.util.*,student.kce.erp.model.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/studentGroupPage.css" rel="stylesheet">
<style>
.activeTab a {
	font-weight: bolder;
}
.activeTab a span{
color:blue;
}
th{
font-size:18px;
}
td{
font-size:15px;
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
        <li><a href="${pageContext.request.contextPath}/studentEnrollment"><span class="glyphicon glyphicon-blackboard"></span> Enrollment</a></li>
        <li><a href="${pageContext.request.contextPath}/studentFeedback"><span class="glyphicon glyphicon-book"></span> Feedback</a></li>
        <li><a href="${pageContext.request.contextPath}/studentCourse"><span class="glyphicon glyphicon-education"></span> Courses</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/studentGroupResource"><span class="glyphicon glyphicon-book"></span> Group Resource</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/studentLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 

<% 
List<ResourceGroup> groupsets = (List<ResourceGroup>)request.getAttribute("groupsets");
%>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<form:form method="post" action="processStudentGroupSetForm" modelAttribute="groupset">
					<form:hidden path="id" />
					<label for="groupCode">Group : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-book"></i></span>
							 <form:input path="groupCode" cssClass="form-control form-control-sm" required="required" />
					</span><br>
					<input class="btn btn-primary" type="submit" value="Add Resource">
				</form:form>
			</div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 left">
				<%if(request.getAttribute("error")!=null){ %>
				<p style="color:red"><%=request.getAttribute("error") %></p>
				<%} %>
				<table class="table table-responsive">
					<thead>
						<th>Group Code</th>
						<th colspan="3">Action</th>
					</thead>
					<%for(ResourceGroup temp:groupsets){ %>
					<tr class="rowborder">
						<td><%=temp.getGroupCode() %></td>
						<td>
							<form action="studentsAddResourcePage" method="post"	style="margin: 0px; padding: 0px; border: 0px;">
								<input type="hidden" name="groupId" value="<%=temp.getId() %>" />
								<input type="hidden" name="groupCode" value="<%=temp.getGroupCode()%>" />
								<button type="submit" class="btn btn-primary form-control form-control-sm">
									<span class="glyphicon glyphicon-plus"></span> Add Resource
								</button>
  								</div>
							</form>
						</td>
						<td>
							<form action="editStudentGroupSet" method="post"	style="margin: 0px; padding: 0px; border: 0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-default form-control form-control-sm">
									<span class="glyphicon glyphicon-pencil"></span> Edit Group
								</button>
  								</div>
							</form>
						</td>
						<td>
							<form onsubmit="return confirm('Do you really want to Delete?');" action="deleteStudentGroupSet" method="post"	style="margin: 0px; padding: 0px; border: 0px;">
								<input type="hidden" name="groupId" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-danger form-control form-control-sm">
									<span class="glyphicon glyphicon-trash"></span> Delete Group
								</button>
  								</div>
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