<%@ page import="java.util.*,student.kce.erp.model.*" %>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/addResourcePage.css" rel="stylesheet">
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
        <li><a href="${pageContext.request.contextPath}/staffClass"><span class="glyphicon glyphicon-blackboard"></span> classes</a></li>
        <li class="activeTab"><a href="${pageContext.request.contextPath}/subjectsHandled"><span class="glyphicon glyphicon-education"></span> subject handled</a></li>
                 <li><a href="${pageContext.request.contextPath}/staffAttendancePage"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/staffLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 
<%List<Attachment> attachments = (List<Attachment>)request.getAttribute("attachments"); %>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					 <form method="post" action="uploadToDB"  enctype="multipart/form-data">
       					 Select file to upload: 
       					 <p style="color:brown">
       					 	<b style="color:red">*</b> if file is not uploaded please reduce the size of the file to upload
       					 </p>
       					 <input type="file" name="file" class="form-control" />
        				<br/>
        				<input type="submit" value="Upload" class="btn btn-primary" />
   					 </form>
			</div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 left">
				<%if(attachments.size()==0){ %>
					<h3>No Files uploaded</h3>
				<%} %>
				<div class="row">
				<%for(Attachment temp:attachments){ %>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div style="height:150px;" class="panel-group">
  									<div class="panel panel-default">
   										 <div style="overflow:auto;height:50px" class="panel-body"><center><%=temp.getFileName() %></center></div>
 								    </div>
  									<div class="panel panel-default">
   										 <div class="panel-body">
   										 <center>
											<div class="btn-group">
  												<a href="${pageContext.request.contextPath}/downloadFile?id=<%=temp.getId() %>"><button class="btn btn-primary"><span class="glyphicon glyphicon-download"></span> download</button></a>
  												<a href="${pageContext.request.contextPath}/deleteFile?id=<%=temp.getId() %>"><button class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> delete</button></a>
											</div>
										 </center>
										 </div>
 									 </div>
								</div>
					</div>		
					<%} %>
				</div>
			</div>
		</div>
	</div>

</body>
</html>