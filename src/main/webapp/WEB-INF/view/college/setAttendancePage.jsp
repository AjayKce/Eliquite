<%@ page import="java.util.*,student.kce.erp.model.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
td,th{
text-align:center;
}

.activeTab a span {
	color: blue;
}

input:focus{
box-shadow:none;
}

.container{
margin-top:70px;
}

#set{
overflow:auto;
height:400px;
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
<% List<Batch> batches = (List<Batch>)request.getAttribute("batches"); 
List<AttendanceScheduler> schedules = (List<AttendanceScheduler>)request.getAttribute("schedules"); 
int i=0;
%>
<div class="container">
		<div class="row">
				<form:form method="post" action="processAttendanceSetPage" modelAttribute="schedule">
				<form:hidden path="id"/>
				<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
					<label for="batch">Batch : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-calendar"></i></span>
							 <form:select path="batch" cssClass="form-control form-control-sm" required="required">
							 	<%for(Batch temp:batches){ %>
							 	<form:option value="<%=temp.getYear() %>" label="<%=temp.getYear() %>"/>
							 	<%} %>
							 </form:select>
					</span><br>
				</div>
				<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
					<label for="semester">Semester : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-book"></i></span>
							 <form:select path="semester" cssClass="form-control form-control-sm" required="required">
							 	<%for(i=1;i<=8;i++){ %>
							 	<form:option value="<%=Integer.toString(i) %>" label="<%=Integer.toString(i) %>"/>
							 	<%} %>
							 </form:select>
					</span><br>
				</div>
				<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
					<label for="hour">Period : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-book"></i></span>
							<input type="number" name="hour" class="form-control form-control-sm" placeholder="eg : 1" required>
					</span><br>
				</div>
				<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
					<label for="startTime">Start Time : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="fa fa-clock"></i></span>
							<input type="time" name="startTime" class="form-control form-control-sm" required>
					</span><br>
				</div>
				<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
					<label for="endTime">End Time : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="fa fa-clock"></i></span>
							<input type="time" name="endTime" class="form-control form-control-sm" required>
					</span><br>
				</div>
				<div class="col-lg-2 col-md-2 col-xs-12 col-sm-12">
					<label>&nbsp</label>
					<span class="input-group"> <span class="input-group-addon">
					<i class="fa fa-angle-double-right"></i></span>
							<input type="submit" value="SET" class="btn btn-primary form-control form-control-sm" required>
					</span><br>
				</div>
				</form:form>
		</div>
		<div class="row" id="set">
			<hr>
			<table class="table table-responsive">
				<%if(schedules.size()!=0){ %>
				<thead>
					<th>Batch</th>
					<th>Semester</th>
					<th>Period</th>
					<th>Starting Time</th>
					<th>Ending Time</th>
					<th>Action</th>
				</thead>
				<%} %>
				<%for(AttendanceScheduler temp:schedules){ %>
					<tr>
						<td><%=temp.getBatch() %></td>
						<td><%=temp.getSemester() %></td>
						<td><%=temp.getHour() %></td>
						<td><%=temp.getStartTime() %></td>
						<td><%=temp.getEndTime() %></td>
						<td>
							<form onsubmit="return confirm('Do you really want to delete ?')" action="deleteAttendanceSet" method="post" style="margin:0px;padding:0px;border:0px;">
								<input type="hidden" name="id" value="<%=temp.getId() %>" />
								<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</button>
							</form>
						</td>
					</tr>
				<%} %>
			</table>
		</div>
	</div>

</body>
</html>