<%@ page import="java.util.*,student.kce.erp.model.Admin" %>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/manageAdmin.css" rel="stylesheet">
<style type="text/css">
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
<%List<Admin> admins = (List<Admin>) request.getAttribute("admins"); %>
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
        <li class="activeTab"><a href="${pageContext.request.contextPath}/manageAdmin"><span class="glyphicon glyphicon-tower"></span> Manage Admin</a></li>
         <li><a href="${pageContext.request.contextPath}/collegeFeedback"><span class="glyphicon glyphicon-book"></span> Feedback</a></li>
         <li><a href="${pageContext.request.contextPath}/setFeedback"><span class="glyphicon glyphicon-tag"></span> Set Feedback</a></li>
                 <li><a href="${pageContext.request.contextPath}/Attendance"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=request.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/collegeLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 

<div class="container">
		<div class="row">
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
			<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
				<table class="table table-bordered table-responsive">
					<thead>
						<th>Admin Name</th>
						<th>Username</th>
						<th>Password</th>
						<th>Department</th>
						<th colspan="2">Action</th>
					</thead>
					<%for(Admin theAdmin:admins) {%>
					<tr>
						<td><%=theAdmin.getAdminName() %></td>
						<td><%=theAdmin.getUsername() %></td>
						<td><%=theAdmin.getPassword() %></td>
						<td><%=theAdmin.getDepartment() %></td>
						<td>
							<form  action="editAdmin" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="aid" value="<%=theAdmin.getId() %>" />
								<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-edit"></span> Edit</button>
							</form>
						</td>
						<td>
							<form onsubmit="return confirm('Do you really want to delete ?')" action="deleteAdmin" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="aid" value="<%=theAdmin.getId() %>" />
								<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</button>
							</form>
						</td>
					</tr>
					<%} %>
				</table>
				<hr>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
		</div>
	</div>


</body>
</html>