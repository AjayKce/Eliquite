<%@ page import="java.util.*,java.text.*,student.kce.erp.model.*"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/staffHomePage.css" rel="stylesheet">
</head>
<body>

	<%
		String date = (String) request.getAttribute("date");
		
		String[] periods=(String[])request.getAttribute("periods");
		String batch=(String)request.getAttribute("batch");
		String semester=(String)request.getAttribute("semester");
		String department = session.getAttribute("department").toString();
		String section = (String)request.getAttribute("section");
		String subjectCode = (String) request.getAttribute("subjectCode");
		String subjectId = (String) request.getAttribute("subjectId");
	%>

	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<br><br>
				<h5 align="center"><b>Batch : </b><%=batch %></h5>
				<h5 align="center"><b>Semester : </b><%=semester %></h5>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<br><br>
				<h5 align="center"><b>Date : </b><%=date %></h5>
				<form action="showStaffAttendanceRecord" method="post"	style="margin: 0px; padding: 0px; border: 0px;">
					<input type="hidden" name="date" value="<%=date %>" />
					<input type="hidden" name="subjectId" value="<%=subjectId%>" />
					<input type="hidden" name="batch" value="<%=batch%>" />
					<input type="hidden" name="semester" value="<%=semester%>" />
					<input type="hidden" name="section" value="<%=section%>" />
					<input type="hidden" name="subjectCode"	value="<%=subjectCode%>" />
						<div class="input-group">
    						<select name="period" class="form-control" required>
    							<%for(String temp:periods){ %>
    								<option><%=temp %></option>
    							<%} %>
    						</select>
   					    	 <div class="input-group-btn">
     						 <button class="btn btn-default" type="submit">
        					  <i style="padding:3px;" class="fa fa-angle-double-right"> Report</i>
      						  </button>
    					      </div>
  						</div>
				</form>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<br><br>
				<h5 align="center"><b>Class : </b><%=department %> <%=section %></h5>
				<h5 align="center"><b>SubjectCode : </b><%=subjectCode %></h5>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><hr></div>
			</div>
		</div>

</body>
</html>

