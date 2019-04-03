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
		List<Student> students = (List<Student>) request.getAttribute("students");
	%>
	<br>
	<br>

	<table id="example" class="table table-bordered">
		<thead>
			<tr>
				<th>Id</th>
				<th>CollegeId</th>
				<th>AdminId</th>
				<th>TutorId</th>
				<th>Batch</th>
				<th>Semester</th>
				<th>Department</th>
				<th>Section</th>
				<th>Username</th>
				<th>Password</th>
				<th>Roll Number</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Quota</th>
				<th>Gender</th>
				<th>Email</th>
				<th>Religion</th>
				<th>BloodGroup</th>
				<th>Nationality</th>
				<th>D.O.B</th>
				<th>Phone</th>
				<th>Accomodation</th>
				<th>Father's Name</th>
				<th>Father's Phone</th>
				<th>Mother's Name</th>
				<th>Mother's Phone</th>
				<th>Guardian's Name</th>
				<th>Guardian's Phone</th>
				<th>Account Number</th>
				<th>IFSC Code</th>
				<th>Account Holder Name</th>
				<th>Registered Mobile</th>
				<th>Pancard Number</th>
				<th>Aadhar Number</th>
				<th>Whatsapp Id</th>
				<th>Facebook Id</th>
				<th>LinkedIn Id</th>
				<th>Github Account</th>
				<th>Hackerrank Account</th>
				<th>Hackerrearth Account</th>
				<th>Codechef Account</th>
				<th>Instagram Account</th>
				<th>Twitter Account</th>
				<th>State</th>
				<th>District</th>
				<th>Present Address 1</th>
				<th>Present Address 2</th>
				<th>Present Address 3</th>
				<th>Permenent Address 1</th>
				<th>Permenent Address 2</th>
				<th>Permenent Address 3</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Student temp : students) {
			%>
			<tr>
				<td><%=temp.getId() %></td>
				<td><%=temp.getCollegeId() %></td>
				<td><%=temp.getAdminId() %></td>
				<td><%=temp.getStaffId() %></td>
				<td><%=temp.getBatch() %></td>
				<td><%=temp.getSemester() %></td>
				<td><%=temp.getDepartment() %></td>
				<td><%=temp.getSection() %></td>
				<td><%=temp.getUsername() %></td>
				<td><%=temp.getPassword() %></td>
				<td><%=temp.getRollno() %></td>
				<td><%=temp.getFirstname() %></td>
				<td><%=temp.getLastname() %></td>
				<td><%=temp.getQuota() %></td>
				<td><%=temp.getGender() %></td>
				<td><%=temp.getEmail() %></td>
				<td><%=temp.getReligion() %></td>
				<td><%=temp.getBloodgroup() %></td>
				<td><%=temp.getNationality() %></td>
				<td><%=temp.getDateOfBirth() %></td>
				<td><%=temp.getPhone() %></td>
				<td><%=temp.getAccomodation() %></td>
				<td><%=temp.getFatherName() %></td>
				<td><%=temp.getFatherPhone() %></td>
				<td><%=temp.getMotherName() %></td>
				<td><%=temp.getMotherPhone() %></td>
				<td><%=temp.getCustodianName() %></td>
				<td><%=temp.getCustodianPhone() %></td>
				<td><%=temp.getAccountNumber() %></td>
				<td><%=temp.getIfscCode() %></td>
				<td><%=temp.getAccountHolder() %></td>
				<td><%=temp.getRegisteredPhone() %></td>
				<td><%=temp.getPancardNumber() %></td>
				<td><%=temp.getAadharNumber() %></td>
				<td><%=temp.getWhatsappAccount() %></td>
				<td><%=temp.getFacebookAccount() %></td>
				<td><%=temp.getLinkedinAccount() %></td>
				<td><%=temp.getGithubAccount() %></td>
				<td><%=temp.getHackerrankAccount() %></td>
				<td><%=temp.getHackerearthAccount() %></td>
				<td><%=temp.getCodechefAccount() %></td>
				<td><%=temp.getInstagramAccount() %></td>
				<td><%=temp.getTwitterAccount() %></td>
				<td><%=temp.getState() %></td>
				<td><%=temp.getDistrict() %></td>
				<td><%=temp.getPresentAddress1() %></td>
				<td><%=temp.getPresentAddress2() %></td>
				<td><%=temp.getPresentAddress3() %></td>
				<td><%=temp.getPermanentAddress1() %></td>
				<td><%=temp.getPermanentAddress2() %></td>
				<td><%=temp.getPermanentAddress3() %></td>
				<td>
					<form action="/console/editStudent" method="post"
						style="margin: 0px; padding: 0px; border: 0px;">
						<input type="hidden" name="id" value="<%=temp.getId()%>" />
						<button type="submit" class="btn btn-info">
							<span class="glyphicon glyphicon-edit"></span> Edit
						</button>
					</form>
				</td>
				<td>
					<form onsubmit="return confirm('Do you really want to delete ?')"
						action="/console/deleteStudent" method="post"
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
				<th>TutorId</th>
				<th>Batch</th>
				<th>Semester</th>
				<th>Department</th>
				<th>Section</th>
				<th>Username</th>
				<th>Password</th>
				<th>Roll Number</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Quota</th>
				<th>Gender</th>
				<th>Email</th>
				<th>Religion</th>
				<th>BloodGroup</th>
				<th>Nationality</th>
				<th>D.O.B</th>
				<th>Phone</th>
				<th>Accomodation</th>
				<th>Father's Name</th>
				<th>Father's Phone</th>
				<th>Mother's Name</th>
				<th>Mother's Phone</th>
				<th>Guardian's Name</th>
				<th>Guardian's Phone</th>
				<th>Account Number</th>
				<th>IFSC Code</th>
				<th>Account Holder Name</th>
				<th>Registered Mobile</th>
				<th>Pancard Number</th>
				<th>Aadhar Number</th>
				<th>Whatsapp Id</th>
				<th>Facebook Id</th>
				<th>LinkedIn Id</th>
				<th>Github Account</th>
				<th>Hackerrank Account</th>
				<th>Hackerrearth Account</th>
				<th>Codechef Account</th>
				<th>Instagram Account</th>
				<th>Twitter Account</th>
				<th>State</th>
				<th>District</th>
				<th>Present Address 1</th>
				<th>Present Address 2</th>
				<th>Present Address 3</th>
				<th>Permenent Address 1</th>
				<th>Permenent Address 2</th>
				<th>Permenent Address 3</th>
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
