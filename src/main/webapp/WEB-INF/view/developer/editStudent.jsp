<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link
	href="/stylesheet/createAdminForm.css"
	rel="stylesheet">
</head>
<style type="text/css">
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
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<form:form method="post" action="/console/processEditStudent" modelAttribute="student">
				<form:hidden path="id" />
				
				<label for="collegeId">College Id : </label>
				<span class="input-group"> 
				<span class="input-group-addon">
				<i class="glyphicon glyphicon-tag"></i></span>
					<form:input	path="collegeId" cssClass="form-control form-control-sm" required="required" />
				</span>
				<br />
				
				<label for="adminId">Admin Id : </label>
				<span class="input-group"> 
				<span class="input-group-addon">
				<i class="glyphicon glyphicon-tag"></i></span>
					<form:input	path="adminId" cssClass="form-control form-control-sm" required="required" />
				</span>
				<br />
				
				<label for="staffId">Tutor Id : </label>
				<span class="input-group"> 
				<span class="input-group-addon">
				<i class="glyphicon glyphicon-tag"></i></span>
					<form:input	path="staffId" cssClass="form-control form-control-sm" required="required" />
				</span>
				<br />
				
				<label for="batch"><b style="color:red">*</b> Batch : </label>
						<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-calendar"></i></span>
							<form:input path="batch" cssClass="form-control form-control-sm" required="required" placeholder="enter batch" />
						</span><br />
						
				<label for="semester"><b style="color:red">*</b> Semester : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:input path="semester" cssClass="form-control form-control-sm" required="required" placeholder="enter semester" />
						</span><br />
						

						<label for="semester"><b style="color:red">*</b> Department : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:select path="department" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="CSE" label="CSE"/>
							 	<form:option value="IT" label="IT"/>
							 	<form:option value="EEE" label="EEE"/>
							 	<form:option value="ECE" label="ECE"/>
							 	<form:option value="ETE" label="ETE"/>
							 	<form:option value="CIVIL" label="CIVIL"/>
							 	<form:option value="MECH" label="MECH"/>
							 	<form:option value="AUTOMOBILE" label="AUTOMOBILE"/>
							 </form:select>
						</span><br />

						<label for="section"><b style="color:red">*</b> Section : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:input path="section" cssClass="form-control form-control-sm" required="required" placeholder="enter section" />
						</span><br />

						<label for="accomodation"><b style="color:red">*</b> Accommodation : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:select path="accomodation" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="HOSTELLER" label="HOSTELLER"/>
							 	<form:option value="DAYSCHOLAR" label="DAYSCHOLAR"/>
							 </form:select>
						</span><br />

						<label for="quota"><b style="color:red">*</b> Quota : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fa fa-exclamation"></i></span>
							 <form:select path="quota" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="SWS" label="SWS"/>
							 	<form:option value="MANAGEMENT" label="MANAGEMENT"/>
							 </form:select>
						</span><br />
						
						<label for="rollno"><b style="color:red">*</b> Roll Number : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:input path="rollno" cssClass="form-control form-control-sm" required="required" placeholder="enter rollno" />
						</span><br />
						
						<label for="username"><b style="color:red">*</b> Username : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							 <form:input path="username" cssClass="form-control form-control-sm" required="required" placeholder="enter username" />
						</span><br />
						
						<label for="password"><b style="color:red">*</b> Password : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							 <form:input path="password" cssClass="form-control form-control-sm" required="required" placeholder="enter password" />
						</span><br />

						<label for="firstname"><b style="color:red">*</b> First Name : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="firstname" cssClass="form-control form-control-sm" required="required" placeholder="enter firstname" />
						</span><br />

						<label for="lastname"><b style="color:red">*</b> Last Name : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="lastname" cssClass="form-control form-control-sm" required="required" placeholder="enter lastname" />
						</span><br />
						
						<label for="email"><b style="color:red">*</b> Email : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
							 <form:input path="email" cssClass="form-control form-control-sm" placeholder="enter email" required="required"/>
						</span><br />

						<label for="gender"><b style="color:red">*</b> Gender : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							 <form:select path="gender" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="MALE" label="MALE"/>
							 	<form:option value="FEMALE" label="FEMALE"/>
							 	<form:option value="OTHER" label="OTHER"/>
							 </form:select>
						</span><br />

						<label for="religion">Religion : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:select path="religion" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="HINDU" label="HINDU"/>
							 	<form:option value="CHRISTIAN" label="CHRISTIAN"/>
							 	<form:option value="MUSLIM" label="MUSLIM"/>
							 	<form:option value="OTHER" label="OTHER"/>
							 </form:select>
						</span><br />

						<label for="bloodgroup"><b style="color:red">*</b> Blood Group : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:select path="bloodgroup" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="A+" label="A+"/>
							 	<form:option value="A-" label="A-"/>
							 	<form:option value="B+" label="B+"/>
							 	<form:option value="B-" label="B-"/>
							 	<form:option value="O+" label="O+"/>
							 	<form:option value="O-" label="O-"/>
							 	<form:option value="AB+" label="AB+"/>
							 	<form:option value="AB-" label="AB-"/>
							 </form:select>
						</span><br />

						<label for="nationality">Nationality : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="nationality" cssClass="form-control form-control-sm" placeholder="enter nationality" />
						</span><br />

						<label for="dateOfBirth">D.O.B : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <input type="date" name="dateOfBirth" class="form-control form-control-sm" />
						</span><br />

						<label for="phone"><b style="color:red">*</b> Phone </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <form:input path="phone" cssClass="form-control form-control-sm" placeholder="enter phone number" />
						</span><br />

						<label for="fatherName">Father's Name </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="fatherName" cssClass="form-control form-control-sm" placeholder="enter father's name" />
						</span><br />

						<label for="fatherPhone">Father's Phone </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <form:input path="fatherPhone" cssClass="form-control form-control-sm" placeholder="enter father's phone number" />
						</span><br />

						<label for="motherName">Mother's Name </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="motherName" cssClass="form-control form-control-sm" placeholder="enter mother's name" />
						</span><br />

						<label for="motherPhone">Mother's Phone </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <form:input path="motherPhone" cssClass="form-control form-control-sm" placeholder="enter mother's phone number" />
						</span><br />

						<label for="gaurdianName">Gaurdian's Name </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="custodianName" cssClass="form-control form-control-sm" placeholder="enter gaurdian's name" />
						</span><br />

						<label for="gaurdianPhone">Gaurdian's Phone </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <form:input path="custodianPhone" cssClass="form-control form-control-sm" placeholder="enter gaurdian's phone number" />
						</span><br />

						<label for="accountNumber">Account Number </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="accountNumber" cssClass="form-control form-control-sm" placeholder="enter account number" />
						</span><br />

						<label for="ifscCode">Ifsc Code </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="ifscCode" cssClass="form-control form-control-sm" placeholder="enter ifsc code" />
						</span><br />

						<label for="accountHolder">Account Holder Name </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="accountHolder" cssClass="form-control form-control-sm" placeholder="account holder name" />
						</span><br />

						<label for="pancardNumber">Pancard Number </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="pancardNumber" cssClass="form-control form-control-sm" placeholder="enter pancard number" />
						</span><br />

						<label for="registeredPhone">Registered Number </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="registeredPhone" cssClass="form-control form-control-sm" placeholder="enter registered number" />
						</span><br />

						<label for="aadharNumber">Aadhar Number </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="aadharNumber" cssClass="form-control form-control-sm" placeholder="enter aadhar number" />
						</span><br />

						<label for="whatsappAccount">Whatsapp Number </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-whatsapp"></i></span>
							 <form:input path="whatsappAccount" cssClass="form-control form-control-sm" placeholder="enter whatsapp number" />
						</span><br />

						<label for="facebookAccount">Facebook Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-facebook-f"></i></span>
							 <form:input path="facebookAccount" cssClass="form-control form-control-sm" placeholder="enter facebook id" />
						</span><br />

						<label for="linkedinAccount">LinkedIn Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-linkedin-in"></i></span>
							 <form:input path="linkedinAccount" cssClass="form-control form-control-sm" placeholder="enter linkedin id" />
						</span><br />

						<label for="githubAccount">Github Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-github"></i></span>
							 <form:input path="githubAccount" cssClass="form-control form-control-sm" placeholder="enter github id" />
						</span><br />

						<label for="hackerrankAccount">Hackerrank Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-hackerrank"></i></span>
							 <form:input path="hackerrankAccount" cssClass="form-control form-control-sm" placeholder="enter hackerrank id" />
						</span><br />

						<label for="hackerearthAccount">Hackerearth Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-accusoft"></i></span>
							 <form:input path="hackerearthAccount" cssClass="form-control form-control-sm" placeholder="enter hackerearth id" />
						</span><br />

						<label for="codechefAccount">CodeChef Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fas fa-code"></i></span>
							 <form:input path="codechefAccount" cssClass="form-control form-control-sm" placeholder="enter codechef id" />
						</span><br />

						<label for="instagramAccount">Instagram Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-instagram"></i></span>
							 <form:input path="instagramAccount" cssClass="form-control form-control-sm" placeholder="enter instagram id" />
						</span><br />

						<label for="twitterAccount">Twitter Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-twitter"></i></span>
							 <form:input path="twitterAccount" cssClass="form-control form-control-sm" placeholder="enter twitter id" />
						</span><br />

						<label for="state">State </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fas fa-flag"></i></span>
							 <form:input path="state" cssClass="form-control form-control-sm" placeholder="enter state" />
						</span><br />

						<label for="district">District </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fas fa-flag"></i></span>
							 <form:input path="district" cssClass="form-control form-control-sm" placeholder="enter district" />
						</span><br />
						
													<label for="presentAddress1">presentAddress1 </label>
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="presentAddress1" cssClass="form-control form-control-sm" placeholder="Address line 1" />
							</span><br />

							<label for="presentAddress2">presentAddress2 </label>
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="presentAddress2" cssClass="form-control form-control-sm" placeholder="Address line 2" />
							</span><br />
							
							<label for="presentAddress3">presentAddress3 </label>
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="presentAddress3" cssClass="form-control form-control-sm" placeholder="pincode" />
							</span><br />

							<label for="permanentAddress1">permanentAddress1 </label>
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="permanentAddress1" cssClass="form-control form-control-sm" placeholder="Address line 1" />
							</span><br />

							<label for="permanentAddress2">permanentAddress2 </label>
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="permanentAddress2" cssClass="form-control form-control-sm" placeholder="Address line 2" />
							</span><br />

							<label for="permanentAddress3">permanentAddress3 </label>
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="permanentAddress3" cssClass="form-control form-control-sm" placeholder="pincode" />
							</span><br />
							
							<input class="btn btn-primary" type="submit" value="Update">
				
				</form:form>
					
			</div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
		</div>
	</div>

</body>
</html>