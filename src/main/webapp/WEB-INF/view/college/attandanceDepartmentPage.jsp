<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/collegeHomePage.css" rel="stylesheet">
</head>
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
        <li><a href="${pageContext.request.contextPath}/setFeedback"><span class="glyphicon glyphicon-tag"></span> Set Feedback</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/Attendance"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/collegeLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 
<% String[] dept = (String[])request.getAttribute("dept"); %>
<div class="container">
		<div class="row">
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
			<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
				<table class="table table-responsive">
					<%for(String temp:dept){ %>
					<tr class="rowborder">
						<td><%=temp %></td>
						<td>
							<form action="setAttendanceSet" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="department" value="<%=temp %>" />
								<button type="submit" class="btn btn-default"><span class="fa fa-exclamation"></span> Set Attendance</button>
							</form>
						</td>
						<td>
							<form action="fullAttendanceReportClass" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="department" value="<%=temp %>" />
								<button type="submit" class="btn btn-default"><span class="fa fa-exclamation"></span> Attendance Report</button>
							</form>
						</td>
					</tr>
					<% }%>
				</table>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
		</div>
	</div>

</body>
</html>