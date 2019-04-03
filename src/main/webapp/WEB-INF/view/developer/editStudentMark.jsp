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
				<form:form method="post" action="/console/processEditStudentMark" modelAttribute="studentMark">
				<form:hidden path="id" />
				
				<label for="adminId">Admin Id : </label>
				<span class="input-group"> 
				<span class="input-group-addon">
				<i class="glyphicon glyphicon-tag"></i></span>
					<form:input	path="adminId" cssClass="form-control form-control-sm" required="required" />
				</span>
				<br />
				
				<label for="staffId">Staff Id : </label>
				<span class="input-group"> 
				<span class="input-group-addon">
				<i class="glyphicon glyphicon-tag"></i></span>
					<form:input	path="staffId" cssClass="form-control form-control-sm" required="required" />
				</span>
				<br />
				
				<label for="studentId">Student Id : </label>
				<span class="input-group"> 
				<span class="input-group-addon">
				<i class="glyphicon glyphicon-tag"></i></span>
					<form:input	path="StudentId" cssClass="form-control form-control-sm" required="required" />
				</span>
				<br />
				
				<label for="studentRoll">Student Roll Number: </label>
				<span class="input-group"> 
				<span class="input-group-addon">
				<i class="glyphicon glyphicon-tag"></i></span>
					<form:input	path="studentRoll" cssClass="form-control form-control-sm" required="required" />
				</span>
				<br />
				
				<label for="batch"> Batch : </label>
						<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-calendar"></i></span>
							<form:input path="batch" cssClass="form-control form-control-sm" required="required" placeholder="enter batch" />
						</span><br />
						
				<label for="semester"> Semester : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:input path="semester" cssClass="form-control form-control-sm" required="required" placeholder="enter semester" />
						</span><br />
						

						<label for="semester"> Department : </label>
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

						<label for="section"> Section : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:input path="section" cssClass="form-control form-control-sm" required="required" placeholder="enter section" />
						</span><br />

						
						<label for="subjectCode"> subject Code : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							 <form:input path="subjectCode" cssClass="form-control form-control-sm" required="required" placeholder="enter rollno" />
						</span><br />
						
						<label for="examTitle"> Exam Title : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							 <form:input path="examTitle" cssClass="form-control form-control-sm" required="required" placeholder="enter username" />
						</span><br />
						
						<label for="obtainedMark"> Obtained Mark : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							 <form:input path="obtainedMark" cssClass="form-control form-control-sm" required="required" placeholder="enter password" />
						</span><br />

						<label for="fullMark"> Full Mark : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							 <form:input path="fullMark" cssClass="form-control form-control-sm" required="required" placeholder="enter firstname" />
						</span><br />

						<label for="status"> Status : </label>
						<span class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></span>
							<form:select path="status" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="FAIL" label="FAIL"/>
							 	<form:option value="PASS" label="PASS"/>
							 </form:select>
						</span><br />
							
						<input class="btn btn-primary" type="submit" value="Update">
				
				</form:form>
					
			</div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
		</div>
	</div>

</body>
</html>