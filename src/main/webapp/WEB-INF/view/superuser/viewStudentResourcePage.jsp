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
          <li><a href="${pageContext.request.contextPath}/getResourcePage"><span class="glyphicon glyphicon-book"></span> Resource</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="${pageContext.request.contextPath}/requestErp"><span class="glyphicon glyphicon-envelope"></span> Request</a></li>
          <li><a href="${pageContext.request.contextPath}/superUserLogin"><span class="glyphicon glyphicon-log-in"></span> superuser</a></li>
        </ul>
        </div>
      </div>
    </nav>
<%List<GroupAttachment> groupAttachments = (List<GroupAttachment>)request.getAttribute("groupAttachments"); %>
<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 left">
				<%if(groupAttachments.size()==0){ %>
					<h3>No Files uploaded</h3>
				<%} %>
				<div class="row">
				<%for(GroupAttachment temp:groupAttachments){ %>
					<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<div style="height:150px;" class="panel-group">
  									<div class="panel panel-default">
   										 <div style="overflow:auto;height:50px" class="panel-body"><center><%=temp.getFileName() %></center></div>
 								    </div>
  									<div class="panel panel-default">
   										 <div class="panel-body">
   										 <center>
											<div class="btn-group">
  												<a href="${pageContext.request.contextPath}/downloadStudentResourceFile?id=<%=temp.getId() %>"><button class="btn btn-primary"><span class="glyphicon glyphicon-download"></span> download</button></a>
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