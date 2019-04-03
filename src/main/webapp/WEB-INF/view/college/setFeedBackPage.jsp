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
td{
font-size:16px
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
        <li><a href="${pageContext.request.contextPath}/addAdmin"><span class="glyphicon glyphicon-plus"></span> Add Admin</a></li>
        <li><a href="${pageContext.request.contextPath}/manageAdmin"><span class="glyphicon glyphicon-tower"></span> Manage Admin</a></li>
        <li><a href="${pageContext.request.contextPath}/collegeFeedback"><span class="glyphicon glyphicon-book"></span> Feedback</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/setFeedback"><span class="glyphicon glyphicon-tag"></span> Set Feedback</a></li>
      	        <li><a href="${pageContext.request.contextPath}/Attendance"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/collegeLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 
<% 
List<FeedBackSet> feedbackSets = (List<FeedBackSet>)request.getAttribute("feedbackSets");
List<FeedBackAlloc> feedBackAllocs = (List<FeedBackAlloc>)request.getAttribute("feedBackAllocs");
int i=0;
%>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<form:form method="post" action="processSetFeedbackAlloc" modelAttribute="feedAlloc">
					<form:hidden path="id" />
					<label for="feedsetId">Feed Set : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-education"></i></span>
							 <form:select path="feedsetId" cssClass="form-control form-control-sm" required="required">
							 	<%for(FeedBackSet temp:feedbackSets){ %>
							 	<form:option value="<%=temp.getId() %>" label="<%=temp.getFeedSet() %>"/>
							 	<%} %>
							 </form:select>
					</span><br>
						<label for="department">Department : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span>
							 <select id="department" name="department" class="form-control form-control-sm" required="required">
							 	<option value="CSE">CSE</option>
							 	<option value="IT">IT</option>
							 	<option value="EEE">EEE</option>
							 	<option value="ECE">ECE</option>
							 	<option value="ETE">ETE</option>
							 	<option value="CIVIL">CIVIL</option>
							 	<option value="MECH">MECH</option>
							 	<option value="AUTOMOBILE">AUTOMOBILE</option>
							 </select>
					</span>
					<br />
			
					<input class="btn btn-primary" type="submit" value="Add">
				</form:form>
			</div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 left">
				<table class="table table-responsive table-bordered">
				<%if(feedBackAllocs.size()!=0){ %>
					<thead>
					<th>Department</th>
					<th>Set</th>
					<th colspan="3">Action</th>
					</thead>
				<%} %>
					<%for(FeedBackAlloc temp:feedBackAllocs){ %>
					<tr>
						<td><%=temp.getDepartment() %></td>
						<td><%=temp.getFeedsetTitle() %></td>
						<td>
							<form action="BatchesForfeedBack" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="department" value="<%=temp.getDepartment() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-tag"></span> Batches</button>
							</form>
						</td>
						<td>
						<form onsubmit="return confirm('Do you really want to delete ?')" action="deleteFeedAlloc" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</button>
						</form>
						</td>
						<td>
							<form action="feedbackStaffs" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="department" value="<%=temp.getDepartment() %>" />
								<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-tag"></span> Staffs</button>
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