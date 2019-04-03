<%@ page import="java.util.*,student.kce.erp.model.College" %>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/manageColleges.css" rel="stylesheet">
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
<%List<College> colleges = (List<College>) request.getAttribute("colleges"); %>
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
    	<li><a href="${pageContext.request.contextPath}/console"><span class="glyphicon glyphicon-tag"></span> Console</a></li>
        <li><a href="${pageContext.request.contextPath}/addCollege"><span class="glyphicon glyphicon-plus"></span> Add College</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/manageColleges"><span class="glyphicon glyphicon-tower"></span> Manage Colleges</a></li>
        <li><a href="${pageContext.request.contextPath}/extendSuperUser"><span class="glyphicon glyphicon-plus"></span> Extend SuperUser</a></li>
      	<li><a href="${pageContext.request.contextPath}/viewRequisition"><span class="glyphicon glyphicon-file"></span> Requisition</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=request.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/superUserLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 

<div class="container">
		<div class="row">
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>
			<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
				<table class="table table-justified">
				<%if(colleges.size()!=0){ %>
					<thead>
						<th>college</th>
						<th>email</th>
						<th>username</th>						
						<th>password</th>
						<th colspan="2">action</th>
					</thead>
					<%} %>
					<%for(College theCollege:colleges) {%>
					<tr id="rowborder">
						<td><%=theCollege.getCollegeName() %></td>
						<td><%=theCollege.getEmail() %></td>
						<td><%=theCollege.getUsername() %></td>
						<td><%=theCollege.getPassword() %></td>
						<td>
							<form action="editCollege" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="cid" value="<%=theCollege.getId() %>" />
								<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-edit"></span> Edit</button>
							</form>
						</td>
						<td>
							<form onsubmit="return confirm('Do you really want to delete ?')" action="deleteCollege" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="cid" value="<%=theCollege.getId() %>" />
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