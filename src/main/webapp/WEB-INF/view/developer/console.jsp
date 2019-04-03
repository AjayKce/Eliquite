<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
<link href="/stylesheet/superUserHomePage.css" rel="stylesheet">
</head>
<style>
.container {
	margin-top: 70px;
}

.card {
	/*background: rgba(255, 255, 255, 0.1);*/
	box-shadow: -8px 20px 21px #ced6e0;
	border-color: black;
	border-radius: 10px;
}

.col-md-3 {
	margin-bottom: 5%;
}

h5 {
	font-size: 15px;
	font-weight: bolder;
	text-shadow: -2px 2px 3px #ced6e0;
}

.navbar {
	border: 1px solid #ced6e0;
}

body {
	background: url("img/bg.jpg");
	background-attachment: fixed;
}
</style>

<body>

	<nav class="navbar fixed-top navbar-expand-sm navbar-default"
		style="background-color: #f8f8f8;">
		<a class="navbar-brand" href="${pageContext.request.contextPath }/"
			style="color: grey">Eliquite</a>
	</nav>

	<div class="container">

		<div class="row">
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">SuperUser</h5>
						<p class="card-text">Manage the Superuser for the product
							management</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/superUser"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">College</h5>
						<p class="card-text">Manage the Colleges Created By You</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/college"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">HOD</h5>
						<p class="card-text">Manage the HOD's Created by colleges</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/collegeAdmin"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Batch</h5>
						<p class="card-text">Manage the Batches Created By HOD</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/batch"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Class</h5>
						<p class="card-text">Manage the Class Rooms of the Students</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/studentClass"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Staff</h5>
						<p class="card-text">Manage the staffs in every department of
							each college</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/staff"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Subject</h5>
						<p class="card-text">Manage the Subject created by the Head of
							the Department</p>
						<a target="_blank"
							href="${pageContext.request.contextPath }/console/departmentSubject"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">SetExam</h5>
						<p class="card-text">Manage the different exam for each
							subject</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/setExam"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Subject Allocation</h5>
						<p class="card-text">Manage the Allocated Subject for Every
							Staff</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/subjectAlloc"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Tutor</h5>
						<p class="card-text">Manage the Tutor for each class rooms in
							respective Department</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/tutor"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Student</h5>
						<p class="card-text">Manage the Student in each department created by tutor</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/student"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Enrollment</h5>
						<p class="card-text">Manage the Course Enrollment for Each
							Student</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/enrollment"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Student Mark</h5>
						<p class="card-text">Manage the marks scored by the student</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/studentMark"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Attachment</h5>
						<p class="card-text">Manage the Attachment Uploaded by the
							Staff</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/attachment"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Attendance Scheduler</h5>
						<p class="card-text">Schedule the Attendance for Student</p>
						<a target="_blank"
							href="${pageContext.request.contextPath }/console/attendanceScheduler"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Attendance Result</h5>
						<p class="card-text">Manage the Result of Attendance</p>
						<a target="_blank"
							href="${pageContext.request.contextPath }/console/attendanceResult"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>



			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">feedBack Set</h5>
						<p class="card-text">Manage the FeedSet created by the
							Colllege</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/feedBackSet"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">College Feed</h5>
						<p class="card-text">Manage the Feed's Created by colleges</p>
						<a target="_blank" href="${pageContext.request.contextPath }/console/collegeFeed"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">feedBack Allocation</h5>
						<p class="card-text">Manage the Allocation of FeedSet for Each
							Department</p>
						<a target="_blank"
							href="${pageContext.request.contextPath }/console/feedBackAlloc"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Create Feedback</h5>
						<p class="card-text">Manage the Feedback Submitted by students</p>
						<a target="_blank"
							href="${pageContext.request.contextPath }/console/createFeedback"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Department FeedSet</h5>
						<p class="card-text">Manage the FeedBack Set setted by the HOD</p>
						<a target="_blank"
							href="${pageContext.request.contextPath }/console/departmentFeedbackSet"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Department Feed</h5>
						<p class="card-text">Manage the Feed Created by the Department</p>
						<a target="_blank"
							href="${pageContext.request.contextPath }/console/departmentFeed"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Department FeedResult</h5>
						<p class="card-text">Manage the feedback submitted by student
							for department programme</p>
						<a target="_blank"
							href="${pageContext.request.contextPath }/console/departmentFeedbackResult"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>



			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Group Attachment</h5>
						<p class="card-text">Manage the attachment shared among
							students</p>
						<a target="_blank"
							href="${pageContext.request.contextPath }/console/groupAttachment"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Resource Group</h5>
						<p class="card-text">Manage the Group Code Created for Each
							Student Attachment</p>
						<a target="_blank"
							href="${pageContext.request.contextPath }/console/resourceGroup"
							class="btn btn-primary">Manage</a>
					</div>
				</div>
			</div>






		</div>



	</div>
</body>
</html>
