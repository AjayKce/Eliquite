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

<body>

	<%
		List<SubjectAlloc> subjectAllocs = (List<SubjectAlloc>) request.getAttribute("subjectAllocs");
	%>
	<br>
	<br>

	<table id="example" class="table table-bordered">
		<thead>
			<tr>
				<th>Id</th>
				<th>AdminId</th>
				<th>Batch</th>
				<th>Semester</th>
				<th>Department</th>
				<th>Section</th>
				<th>SubjectCode</th>
				<th>Staff Handled</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (SubjectAlloc temp : subjectAllocs) {
			%>
			<tr>
				<td><%=temp.getId()%></td>
				<td><%=temp.getAdminId() %></td>
				<td><%=temp.getBatch() %></td>
                <td><%=temp.getSemester() %></td>
                <td><%=temp.getDepartment() %></td>
                <td><%=temp.getSection() %></td>
				<td><%=temp.getSubjectCode() %></td>
				<td><%=temp.getStaffName() %></td>
				<td>
					<form action="/console/editSubjectAlloc" method="post"
						style="margin: 0px; padding: 0px; border: 0px;">
						<input type="hidden" name="id" value="<%=temp.getId()%>" />
						<button type="submit" class="btn btn-info">
							<span class="glyphicon glyphicon-edit"></span> Edit
						</button>
					</form>
				</td>
				<td>
					<form onsubmit="return confirm('Do you really want to delete ?')"
						action="/console/deleteSubjectAlloc" method="post"
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
				<th>AdminId</th>
				<th>Batch</th>
				<th>Semester</th>
				<th>Department</th>
				<th>Section</th>
				<th>SubjectCode</th>
				<th>Staff Handled</th>
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
