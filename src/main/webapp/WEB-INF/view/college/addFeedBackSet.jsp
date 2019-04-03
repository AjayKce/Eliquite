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
        <li><a href="${pageContext.request.contextPath}/addAdmin"><span class="glyphicon glyphicon-plus"></span> Add Admin</a></li>
        <li><a href="${pageContext.request.contextPath}/manageAdmin"><span class="glyphicon glyphicon-tower"></span> Manage Admin</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/collegeFeedback"><span class="glyphicon glyphicon-book"></span> Feedback</a></li>
        <li><a href="${pageContext.request.contextPath}/setFeedback"><span class="glyphicon glyphicon-tag"></span> Set Feedback</a></li>
                <li><a href="${pageContext.request.contextPath}/Attendance"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/collegeLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 
<% List<FeedBackSet> feedsets = (List<FeedBackSet>)request.getAttribute("feedsets"); %>
<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
					<form:form method="post" action="processFeedSetForm" modelAttribute="feedset">
					<form:hidden path="id" />
					<label for="feedSet">Feed Set : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-calendar"></i></span> <form:input
							path="feedSet" cssClass="form-control form-control-sm"
							placeholder="enter feed set" required="required" />
					</span><br>
					<input class="btn btn-primary" type="submit" value="Add">
				</form:form>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-0 col-xs-0"></div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 left">
				<table class="table table-responsive table-bordered">
					<%if(feedsets.size()!=0){ %>
					<thead>
						<th>Feed Set</th>
						<th colspan="3">Action</th>
					</thead>
					<%} %>
					<%for(FeedBackSet temp:feedsets){ %>
					<tr class="rowborder">
						<td><%=temp.getFeedSet() %></td>
						<td>
						<form action="addFeed" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-book"></span> Feed</button>
						</form>
						</td>
						<%if(temp.getStatus().equals("ACTIVE")){ %>
						<td>
							<form action="stopFeed" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-tag"></span> Active</button>
							</form>
						</td>
						<%}else{ %>
						<td>
							<form action="startFeed" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-tag"></span> In-Active</button>
							</form>
						</td>
						<%} %>
						<td>
						<form onsubmit="return confirm('Do you really want to delete ?')" action="deleteFeedSet" method="post" style="margin:0px;padding:0px;border:0px;">
							<input type="hidden" name="id" value="<%=temp.getId() %>" />
							<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</button>
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