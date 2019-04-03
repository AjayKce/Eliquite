<%@ page import="java.util.*,java.text.*,student.kce.erp.model.*"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/staffHomePage.css" rel="stylesheet">
<style>
	th{
	font-size:18px;
	text-align:center;
	font-weight:bolder;
	color:blue;
	}
	td{
	font-size:13px;
	text-align:center;
	font-weight:bold;
	}
</style>
</head>
<body>

	
	<%
		String dater = (String) request.getAttribute("date");

	
		String[] periods=(String[])request.getAttribute("periods");
		String batch=(String)request.getAttribute("batch");
		String semester=(String)request.getAttribute("semester");
		String department = session.getAttribute("department").toString();
		String section = (String)request.getAttribute("section");
		String subjectCode = (String) request.getAttribute("subjectCode");
		String subjectId = (String) request.getAttribute("subjectId");
		
		List<AttendanceResult> attendanceResults = (List<AttendanceResult>) request.getAttribute("attendanceResults");
	%>

	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<br><br>
				<h5 align="center"><b style="color:brown;">Batch : </b><%=batch %></h5>
				<h5 align="center"><b style="color:brown;">Semester : </b><%=semester %></h5>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<br><br>
				<h5 align="center"><b style="color:brown;">Date : </b><%=dater %></h5>
				<form action="showStaffAttendanceRecord" method="post"	style="margin: 0px; padding: 0px; border: 0px;">
					<input type="hidden" name="date" value="<%=dater%>" />
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
				<h5 align="center"><b style="color:brown;">Class : </b><%=department %> <%=section %></h5>
				<h5 align="center"><b style="color:brown;">SubjectCode : </b><%=subjectCode %></h5>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><hr></div>
			
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<table class="table">
					<%if(attendanceResults.size()!=0){ %>
					<thead>
						<th>Roll No</th>
						<th>Name</th>
						<th>Period</th>
						<th>Status</th>
					</thead>
					<%} %>
					<%for(AttendanceResult temp:attendanceResults){ %>
						<tr>
							<%if(temp.getStatus().equals("PRESENT")){ %>
							<td><%=temp.getRollno() %></td>
							<%}else{ %>
							<td style="color:red;"><%=temp.getRollno() %></td>
							<%} %>
							<td><%=temp.getStudentName() %></td>
							<td><%=temp.getPeriod() %></td>
							<%if(temp.getStatus().equals("PRESENT")){ %>
							<td style="color:green;"><%=temp.getStatus() %></td>
							<%}else{ %>
							<td style="color:red;"><%=temp.getStatus() %></td>
							<%} %>
						</tr>
					<%} %>
				</table>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0">
				<form>
</form>
			</div>
		</div>
	</div>

</body>
</html>

