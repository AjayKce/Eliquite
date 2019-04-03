<%@ page import="java.util.*,student.kce.erp.model.*"%>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
	
<style>
	#outer{
		overflow:auto;
	}
</style>
</head>

<body onload="myFunction()">

	<%
		List<CreateFeedback> createFeedbacks = (List<CreateFeedback>) request.getAttribute("createFeedbacks");
	%>
	<br>
	<br>

	<table id="example" class="table table-bordered">
		<thead>
			<tr>
				<th>Id</th>
				<th>CollegeId</th>
				<th>AdminId</th>
				<th>StaffId</th>
				<th>StudentId</th>
				<th>SubjectId</th>
				<th>Batch</th>
				<th>Semester</th>
				<th>Department</th>
				<th>Section</th>
				<th>Student Name</th>
				<th>Student Roll</th>
				<th>StaffName</th>
				<th>SubjectCode</th>
				<th>SubjectTitle</th>
				<th>feed</th>
				<th>Result</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (CreateFeedback temp : createFeedbacks) {
			%>
			<tr>
				<td><%=temp.getId()%></td>
				<td><%=temp.getCollegeId() %></td>
				<td><%=temp.getAdminId() %></td>
				<td><%=temp.getStaffId() %></td>
				<td><%=temp.getStudentId() %></td>
				<td><%=temp.getSubjectId() %></td>
				<td><%=temp.getBatch() %></td>
                <td><%=temp.getSemester() %></td>
                <td><%=temp.getDepartment() %></td>
                <td><%=temp.getSection() %></td>
				<td><%=temp.getStudentName() %></td>
				<td><%=temp.getStudentRoll() %></td>
				<td><%=temp.getStaffName() %></td>
				<td><%=temp.getSubjectCode() %></td>
				<td><%=temp.getSubjectTitle() %></td>
				<td><%=temp.getFeed() %></td>
				<td><%=temp.getResult() %></td>
				<td>
					<form action="/console/editCreateFeedback" method="post"
						style="margin: 0px; padding: 0px; border: 0px;">
						<input type="hidden" name="id" value="<%=temp.getId()%>" />
						<button type="submit" class="btn btn-info">
							<span class="glyphicon glyphicon-edit"></span> Edit
						</button>
					</form>
				</td>
				<td>
					<form onsubmit="return confirm('Do you really want to delete ?')"
						action="/console/deleteCreateFeedback" method="post"
						style="margin: 0px; padding: 0px; border: 0px;">
						<input type="hidden" name="id" value="<%=temp.getId()%>" />
						<button type="submit" class="btn btn-danger">
							<span class="glyphicon glyphicon-trash"></span> Delete
						</button>
					</form>
				</td>
			</tr>
			<%
				}
			%>
		</tbody>
		<tfoot>
			<tr>
				<th>Id</th>
				<th>CollegeId</th>
				<th>AdminId</th>
				<th>StaffId</th>
				<th>StudentId</th>
				<th>SubjectId</th>
				<th>Batch</th>
				<th>Semester</th>
				<th>Department</th>
				<th>Section</th>
				<th>Student Name</th>
				<th>Student Roll</th>
				<th>StaffName</th>
				<th>SubjectCode</th>
				<th>SubjectTitle</th>
				<th>feed</th>
				<th>Result</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</tfoot>
	</table>

	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>

</body>
</html>
