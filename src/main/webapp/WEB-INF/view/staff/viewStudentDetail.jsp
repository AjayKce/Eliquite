<%@ page import="java.util.*,student.kce.erp.model.*" %>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/viewStudentForm.css" rel="stylesheet">
<style>
.activeTab a {
	font-weight: bolder;
}
.activeTab a span{
color:blue;
}

</style>
</head>
<body>
<% 
Student student = (Student)request.getAttribute("student");
%>
<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">				
				<h2 class="topic">Academic Details</h2>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="batch">Batch : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i
							class="glyphicon glyphicon-calendar"></i></span>
							 <input type="text" value="<%=student.getBatch() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="semester">Semester : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							  <input type="text" value="<%=student.getSemester() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="semester">Department : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <input type="text" value="<%=student.getDepartment() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="section">Section : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <input type="text" value="<%=student.getSection() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="accomodation">Accommodation : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							<input type="text" value="<%=student.getAccomodation() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="quota">Quota : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							<input type="text" value="<%=student.getQuota() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="rollno">Roll Number : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <input type="text" value="<%=student.getRollno() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="username">Username : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<input type="text" value="<%=student.getUsername() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="password">Password : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							 <input type="text" value="<%=student.getPassword() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
				</div>
				
				<h2 class="topic">Personal Details</h2>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="firstname">First Name : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <input type="text" value="<%=student.getFirstname() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="lastname">Last Name : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <input type="text" value="<%=student.getLastname() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="email">Email : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
							 <input type="text" value="<%=student.getEmail() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="gender">Gender : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							 <input type="text" value="<%=student.getGender() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="religion">Religion : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <input type="text" value="<%=student.getReligion() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="bloodgroup">Blood Group : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <input type="text" value="<%=student.getBloodgroup() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="nationality">Nationality : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <input type="text" value="<%=student.getNationality() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="dateOfBirth">D.O.B : </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <input type="text" value="<%=student.getDateOfBirth() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="phone">Phone </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <input type="text" value="<%=student.getPhone() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
				</div>
				
					<h2 class="topic">Gaurdian Details</h2>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="fatherName">Father's Name </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <input type="text" value="<%=student.getFatherName() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="fatherPhone">Father's Phone </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <input type="text" value="<%=student.getFatherPhone() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="motherName">Mother's Name </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <input type="text" value="<%=student.getMotherName() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="motherPhone">Mother's Phone </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <input type="text" value="<%=student.getMotherPhone() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="gaurdianName">Gaurdian's Name </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <input type="text" value="<%=student.getCustodianName() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="gaurdianPhone">Gaurdian's Phone </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <input type="text" value="<%=student.getCustodianPhone() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
				</div>
				
				<h2 class="topic">Bank Details</h2>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="accountNumber">Account Number </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <input type="text" value="<%=student.getAccountNumber() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="ifscCode">Ifsc Code </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <input type="text" value="<%=student.getIfscCode() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="accountHolder">Account Holder Name </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <input type="text" value="<%=student.getAccountHolder() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="pancardNumber">Pancard Number </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <input type="text" value="<%=student.getPancardNumber() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="registeredPhone">Registered Number </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <input type="text" value="<%=student.getRegisteredPhone() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="aadharNumber">Aadhar Number </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <input type="text" value="<%=student.getAadharNumber() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
				</div>
				
				<h2 class="topic">Social Details</h2>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="whatsappAccount">Whatsapp Number </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="fab fa-whatsapp"></i></span>
							 <input type="text" value="<%=student.getWhatsappAccount() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="facebookAccount">Facebook Account </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="fab fa-facebook-f"></i></span>
							 <input type="text" value="<%=student.getFacebookAccount() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="linkedinAccount">LinkedIn Account </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="fab fa-linkedin-in"></i></span>
							 <input type="text" value="<%=student.getLinkedinAccount() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="githubAccount">Github Account </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="fab fa-github"></i></span>
							 <input type="text" value="<%=student.getGithubAccount() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="hackerrankAccount">Hackerrank Account </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="fab fa-hackerrank"></i></span>
							 <input type="text" value="<%=student.getHackerrankAccount() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="hackerearthAccount">Hackerearth Account </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="fab fa-accusoft"></i></span>
							 <input type="text" value="<%=student.getHackerearthAccount() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="codechefAccount">CodeChef Account </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="fas fa-code"></i></span>
							 <input type="text" value="<%=student.getCodechefAccount() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="instagramAccount">Instagram Account </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="fab fa-instagram"></i></span>
							 <input type="text" value="<%=student.getInstagramAccount() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="twitterAccount">Twitter Account </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="fab fa-twitter"></i></span>
							 <input type="text" value="<%=student.getTwitterAccount() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
				</div>
				
				<h2 class="topic">Address Details</h2>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="state">State </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="fas fa-flag"></i></span>
							 <input type="text" value="<%=student.getState() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="district">District </label>
						<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="fas fa-flag"></i></span>
							<input type="text" value="<%=student.getDistrict() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<h3 align="center" style="font-size:120%;" class="topic">Present Address</h3>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<input type="text" value="<%=student.getPresentAddress1() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
							</span>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<input type="text" value="<%=student.getPresentAddress2() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
							</span>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<input type="text" value="<%=student.getPresentAddress3() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
							</span>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<h3 align="center" style="font-size:120%;" class="topic">Permanent Address</h3>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<input type="text" value="<%=student.getPermanentAddress1() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
							</span>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<input type="text" value="<%=student.getPermanentAddress2() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
							</span>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span style="background-color:white;" class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<input type="text" value="<%=student.getPermanentAddress3() %>" style="background-color:white;color:blue;font-weight:bolder" class="form-control" readonly>
							 </span>
						</div>
					</div>
				</div>		
			</div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
		</div>
	</div>
</body>
</html>