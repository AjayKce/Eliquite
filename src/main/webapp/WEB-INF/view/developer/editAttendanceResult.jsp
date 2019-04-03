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
				<form:form method="post" action="/console/processEditAttendanceResult" modelAttribute="attendanceResult">
					<form:hidden path="id" />
					<label for="collegeId">CollegeId : </label>
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
							<form:input	path="studentId" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="subjectId">Subject Id : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="subjectId" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="batch">Batch : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="batch" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="semester">Semester : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="semester" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="department">Department : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
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
						</span>
					<br />
					
					<label for="section">Section : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="section" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="staffName">Staff Name : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="staffName" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="rollno">Student Roll Number : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="rollno" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="studentName">Student Name : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="studentName" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="subjectCode">Subject Code : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="subjectCode" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="subjectTitle">Subject Title : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="subjectTitle" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="date">Date : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="date" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="period">Period : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="period" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="startTime">Start Time: </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="startTime" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="endTime">End Time : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:input	path="endTime" cssClass="form-control form-control-sm" required="required" />
						</span>
					<br />
					
					<label for="status">Status : </label>
						<span class="input-group"> 
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-tag"></i></span>
							<form:select path="status" cssClass="form-control form-control-sm" required="required">
							 	<form:option value="PRESENT" label="PRESENT"/>
							 	<form:option value="ABSENT" label="ABSENT"/>
							 </form:select>
						</span>
					<br />
					
					<input class="btn btn-primary" type="submit" value="Update">
				</form:form>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
		</div>
	</div>

</body>
</html>