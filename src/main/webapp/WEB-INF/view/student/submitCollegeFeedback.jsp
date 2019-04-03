<%@ page import="java.util.*,student.kce.erp.model.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link
	href="/stylesheet/addFeedSet.css"
	rel="stylesheet">
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
        <li class="activeTab"><a href="${pageContext.request.contextPath}/createCollegeFeedback"><span class="glyphicon glyphicon-blackboard"></span> College Feedback</a></li>
        <li><a href="${pageContext.request.contextPath}/createDepartmentFeedback"><span class="glyphicon glyphicon-book"></span> Department Feedback</a></li>
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/studentLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 
	<%
		List<SubjectAlloc> handledStaffs = (List<SubjectAlloc>) request.getAttribute("handledStaffs");
		List<CollegeFeed> collegeFeeds = (List<CollegeFeed>) request.getAttribute("feeds");
		String subjectTitle = (String) request.getAttribute("subjectTitle");
		String status = (String) request.getAttribute("status");
	%>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
				<%if(handledStaffs.size()==0){ %>
					<h3>You have successfully Submitted</h3>
				<%}else if(status.equals("INACTIVE")){%>
					<h3>FeedBack Not Active</h3>
				<%}else{ %>
				<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
					<form:form method="post" action="processSubmitCollegeFeedback"
						modelAttribute="feedback">
						<div class="row">
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">Staff Name : <b><%=handledStaffs.get(0).getStaffName() %></b></div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">Subject Code : <b><%=handledStaffs.get(0).getSubjectCode() %></b></div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">Subject Title : <b><%=subjectTitle %></b></div>
							<input type="hidden" name="staffName" value="<%=handledStaffs.get(0).getStaffName() %>">
							<input type="hidden" name="subjectCode" value="<%=handledStaffs.get(0).getSubjectCode() %>">
							<input type="hidden" name="subjectTitle" value="<%=subjectTitle %>">
						</div>
						<hr>
						<%for(CollegeFeed temp:collegeFeeds){ %>
						<div class="row">
							<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12"></div>
							<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12"><b><%=temp.getFeed() %></b></div>
							<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
								<select name="result" class="form-control">
									<option>Excellent</option>
									<option>Good</option>
									<option>Moderate</option>
									<option>Intermediate</option>
									<option>Poor</option>
									<option>Very Poor</option>
								</select>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12"></div>
							<input type="hidden" name="feed" value="<%=temp.getFeed() %>">
							<br><br>
						</div>
						<%} %>
						
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
							<br>
							<button style="width:100%;" class="btn btn-primary" type="submit">submit</button>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
						
					</form:form>
				</div>
				<%} %>
				<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			</div>
		</div>
	</div>

</body>
</html>