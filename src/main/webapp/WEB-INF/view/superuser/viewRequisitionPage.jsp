<%@ page import="java.util.*,student.kce.erp.model.RequestErp" %>
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
<%List<RequestErp> requestErps = (List<RequestErp>) request.getAttribute("requestErps"); %>
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
        <li><a href="${pageContext.request.contextPath}/manageColleges"><span class="glyphicon glyphicon-tower"></span> Manage Colleges</a></li>
        <li><a href="${pageContext.request.contextPath}/extendSuperUser"><span class="glyphicon glyphicon-plus"></span> Extend SuperUser</a></li>
      	<li class="activeTab"><a href="${pageContext.request.contextPath}/viewRequisition"><span class="glyphicon glyphicon-file"></span> Requisition</a></li>
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
					<%if(requestErps.size()!=0){ %>
					<thead>
						<th>college name</th>
						<th>college email</th>
						<th>college contact</th>
						<th>action</th>
					</thead>
					<%} %>
					<%for(RequestErp temp:requestErps) {%>
					<tr id="rowborder">
						<td><%=temp.getCollegeName() %></td>
						<td><%=temp.getCollegeEmail() %></td>
						<td><%=temp.getCollegeContact() %></td>
						<td>
							<a href="${pageContext.request.contextPath}/deleteRequestErps?rid=<%=temp.getId() %>">
							   <button class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</button>
							</a>
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