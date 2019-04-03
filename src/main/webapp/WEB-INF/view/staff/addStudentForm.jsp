<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/addStudentForm.css" rel="stylesheet">
</head>
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
        <li class="activeTab"><a href="${pageContext.request.contextPath}/staffClass"><span class="glyphicon glyphicon-blackboard"></span> classes</a></li>
        <li><a href="${pageContext.request.contextPath}/subjectsHandled"><span class="glyphicon glyphicon-education"></span> subject handled</a></li>
                 <li><a href="${pageContext.request.contextPath}/staffAttendancePage"><span class="fa fa-exclamation"></span> Attendance</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a><span class="glyphicon glyphicon-user"></span> Hello,<%=session.getAttribute("user")  %></a></li>
        <li><a href="${pageContext.request.contextPath}/staffLogOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav> 
<% 
String batch = (String)request.getAttribute("batch");
String semester = (String)request.getAttribute("semester");
String department = session.getAttribute("department").toString();
String section  = (String)request.getAttribute("section");
%>
<div class="container">
		<div class="row">
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<form:form method="post" action="processAddStudentForm" modelAttribute="student">
				<form:hidden path="id" />
				<form:hidden path="adminId" />
				<form:hidden path="collegeId" />
				<form:hidden path="staffId" />
				
				<h2 class="topic">Academic Details</h2>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="batch"><b style="color:red">*</b> Batch : </label>
						<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-calendar"></i></span>
							 <form:select path="batch" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="<%=batch %>" label="<%=batch %>"/>
							 </form:select>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="semester"><b style="color:red">*</b> Semester : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:select path="semester" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="<%=semester %>" label="<%=semester %>"/>
							 </form:select>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="semester"><b style="color:red">*</b> Department : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:select path="department" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="<%=department %>" label="<%=department  %>"/>
							 </form:select>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="section"><b style="color:red">*</b> Section : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:select path="section" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="<%=section %>" label="<%=section  %>"/>
							 </form:select>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="accomodation"><b style="color:red">*</b> Accommodation : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:select path="accomodation" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="HOSTELLER" label="HOSTELLER"/>
							 	<form:option value="DAYSCHOLAR" label="DAYSCHOLAR"/>
							 </form:select>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="quota"><b style="color:red">*</b> Quota : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fa fa-exclamation"></i></span>
							 <form:select path="quota" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="SWS" label="SWS"/>
							 	<form:option value="MANAGEMENT" label="MANAGEMENT"/>
							 </form:select>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="rollno"><b style="color:red">*</b> Roll Number : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:input path="rollno" cssClass="form-control form-control-sm" required="required" placeholder="enter rollno" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="username"><b style="color:red">*</b> Username : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							 <form:input path="username" cssClass="form-control form-control-sm" required="required" placeholder="enter username" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="password"><b style="color:red">*</b> Password : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							 <form:password path="password" cssClass="form-control form-control-sm" required="required" placeholder="enter password" />
						</span>
					</div>
				</div>
				
				<h2 class="topic">Personal Details</h2>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="firstname"><b style="color:red">*</b> First Name : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="firstname" cssClass="form-control form-control-sm" required="required" placeholder="enter firstname" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="lastname"><b style="color:red">*</b> Last Name : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="lastname" cssClass="form-control form-control-sm" required="required" placeholder="enter lastname" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="email"><b style="color:red">*</b> Email : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
							 <form:input path="email" cssClass="form-control form-control-sm" placeholder="enter email" required="required"/>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="gender"><b style="color:red">*</b> Gender : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							 <form:select path="gender" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="MALE" label="MALE"/>
							 	<form:option value="FEMALE" label="FEMALE"/>
							 	<form:option value="OTHER" label="OTHER"/>
							 </form:select>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="religion">Religion : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:select path="religion" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="HINDU" label="HINDU"/>
							 	<form:option value="CHRISTIAN" label="CHRISTIAN"/>
							 	<form:option value="MUSLIM" label="MUSLIM"/>
							 	<form:option value="OTHER" label="OTHER"/>
							 </form:select>
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
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
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="nationality">Nationality : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="nationality" cssClass="form-control form-control-sm" placeholder="enter nationality" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="dateOfBirth">D.O.B : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <input type="date" name="dateOfBirth" class="form-control form-control-sm" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="phone"><b style="color:red">*</b> Phone </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <form:input path="phone" cssClass="form-control form-control-sm" placeholder="enter phone number" />
						</span>
					</div>
				</div>
				
					<h2 class="topic">Gaurdian Details</h2>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="fatherName">Father's Name </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="fatherName" cssClass="form-control form-control-sm" placeholder="enter father's name" />
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="fatherPhone">Father's Phone </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <form:input path="fatherPhone" cssClass="form-control form-control-sm" placeholder="enter father's phone number" />
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="motherName">Mother's Name </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="motherName" cssClass="form-control form-control-sm" placeholder="enter mother's name" />
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="motherPhone">Mother's Phone </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <form:input path="motherPhone" cssClass="form-control form-control-sm" placeholder="enter mother's phone number" />
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="gaurdianName">Gaurdian's Name </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="custodianName" cssClass="form-control form-control-sm" placeholder="enter gaurdian's name" />
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="gaurdianPhone">Gaurdian's Phone </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
							 <form:input path="custodianPhone" cssClass="form-control form-control-sm" placeholder="enter gaurdian's phone number" />
						</span>
					</div>
				</div>
				
				<h2 class="topic">Bank Details</h2>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="accountNumber">Account Number </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="accountNumber" cssClass="form-control form-control-sm" placeholder="enter account number" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="ifscCode">Ifsc Code </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="ifscCode" cssClass="form-control form-control-sm" placeholder="enter ifsc code" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="accountHolder">Account Holder Name </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="accountHolder" cssClass="form-control form-control-sm" placeholder="account holder name" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="pancardNumber">Pancard Number </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="pancardNumber" cssClass="form-control form-control-sm" placeholder="enter pancard number" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="registeredPhone">Registered Number </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="registeredPhone" cssClass="form-control form-control-sm" placeholder="enter registered number" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="aadharNumber">Aadhar Number </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
							 <form:input path="aadharNumber" cssClass="form-control form-control-sm" placeholder="enter aadhar number" />
						</span>
					</div>
				</div>
				
				<h2 class="topic">Social Details</h2>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="whatsappAccount">Whatsapp Number </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-whatsapp"></i></span>
							 <form:input path="whatsappAccount" cssClass="form-control form-control-sm" placeholder="enter whatsapp number" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="facebookAccount">Facebook Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-facebook-f"></i></span>
							 <form:input path="facebookAccount" cssClass="form-control form-control-sm" placeholder="enter facebook id" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="linkedinAccount">LinkedIn Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-linkedin-in"></i></span>
							 <form:input path="linkedinAccount" cssClass="form-control form-control-sm" placeholder="enter linkedin id" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="githubAccount">Github Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-github"></i></span>
							 <form:input path="githubAccount" cssClass="form-control form-control-sm" placeholder="enter github id" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="hackerrankAccount">Hackerrank Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-hackerrank"></i></span>
							 <form:input path="hackerrankAccount" cssClass="form-control form-control-sm" placeholder="enter hackerrank id" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="hackerearthAccount">Hackerearth Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-accusoft"></i></span>
							 <form:input path="hackerearthAccount" cssClass="form-control form-control-sm" placeholder="enter hackerearth id" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="codechefAccount">CodeChef Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fas fa-code"></i></span>
							 <form:input path="codechefAccount" cssClass="form-control form-control-sm" placeholder="enter codechef id" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="instagramAccount">Instagram Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-instagram"></i></span>
							 <form:input path="instagramAccount" cssClass="form-control form-control-sm" placeholder="enter instagram id" />
						</span>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<label for="twitterAccount">Twitter Account </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fab fa-twitter"></i></span>
							 <form:input path="twitterAccount" cssClass="form-control form-control-sm" placeholder="enter twitter id" />
						</span>
					</div>
				</div>
				
				<h2 class="topic">Address Details</h2>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="state">State </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fas fa-flag"></i></span>
							 <form:input path="state" cssClass="form-control form-control-sm" placeholder="enter state" />
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<label for="district">District </label>
						<span class="input-group"> <span class="input-group-addon"><i class="fas fa-flag"></i></span>
							 <form:input path="district" cssClass="form-control form-control-sm" placeholder="enter district" />
						</span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<h3 align="center" style="font-size:120%;" class="topic">Present Address</h3>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="presentAddress1" cssClass="form-control form-control-sm" placeholder="Address line 1" />
							</span>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="presentAddress2" cssClass="form-control form-control-sm" placeholder="Address line 2" />
							</span>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="presentAddress3" cssClass="form-control form-control-sm" placeholder="pincode" />
							</span>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<h3 align="center" style="font-size:120%;" class="topic">Permanent Address</h3>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="permanentAddress1" cssClass="form-control form-control-sm" placeholder="Address line 1" />
							</span>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="permanentAddress2" cssClass="form-control form-control-sm" placeholder="Address line 2" />
							</span>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<span class="input-group"> <span class="input-group-addon"><i class="far fa-address-card"></i></span>
							 	<form:input path="permanentAddress3" cssClass="form-control form-control-sm" placeholder="pincode" />
							</span>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<input style="width:100%;" type="submit" value="submit" class="btn btn-primary">
					</div>
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
				</div>
				
				</form:form>				
			</div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
		</div>
	</div>

</body>
</html>