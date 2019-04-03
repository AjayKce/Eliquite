<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.*,student.kce.erp.model.*,student.kce.erp.controller.*" %>
<html>
<head>
<title>Eliquite</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link href="/stylesheet/staffClassPage.css" rel="stylesheet">
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
td{
font-size:16px;
}
#link,#demo{
display:none;
}

#outer{
	position:absolute;
	vertical-align: bottom;
	height:50px;
	width:50px;
	border:5px solid rgb(194, 186, 186);
	border-bottom: 5px solid red;
	border-radius:50%;
	animation:rot 0.5s linear infinite;

}

@keyframes rot{
	0%{
		border-bottom: 5px solid red;
	}
	25%{
		border:5px solid rgb(194, 186, 186);
		border-bottom: 5px solid rgb(194, 186, 186);
		border-left: 5px solid red;
	}
	50%{
		border:5px solid rgb(194, 186, 186);
		border-left: 5px solid rgb(194, 186, 186);
		border-top: 5px solid red;
	}
	75%{
		border:5px solid rgb(194, 186, 186);
		border-top: 5px solid rgb(194, 186, 186);
		border-right: 5px solid red;
	}
	100%{
		border:5px solid rgb(194, 186, 186);
		border-right: 5px solid rgb(194, 186, 186);
		border-bottom: 5px solid red;
	}
}

#demo{
	position: relative;;
	top:30%;
	left:40%;
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
String staffName = session.getAttribute("user").toString();
String batch = (String)request.getAttribute("batch");
String semester = (String)request.getAttribute("semester");
String department = (String)request.getAttribute("department");
String section = (String)request.getAttribute("section");
String collegeId = session.getAttribute("collegeId").toString();
String adminId = session.getAttribute("adminId").toString();
String staffId = session.getAttribute("staffId").toString();
%>
<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Batch : </b><%=batch %></h5>
				<h5 align="center"><b style="color:brown;">Semester : </b><%=semester %></h5>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">Staff Name : </b><%=staffName %></h5>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
				<h5 align="center"><b style="color:brown;">department : </b><%=department %></h5>
				<h5 align="center"><b style="color:brown;">Section : </b> <%=section %></h5>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><hr/></div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
				<form:form method="post" action="filterStudentResult" modelAttribute="temp">
					<input type="hidden" name="batch" value="<%=batch %>"/>
					<input type="hidden" name="semester" value="<%=semester %>"/>
					<input type="hidden" name="department" value="<%=department %>"/>
					<input type="hidden" name="section" value="<%=section %>"/>
					<div class="row">
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="email"/>
							<label style="margin:0px;border:0px;" for="values1"> Email</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="dob"/>
							<label style="margin:0px;border:0px;" for="values2"> Birth Date</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="gender"/>
							<label style="margin:0px;border:0px;" for="values3"> Gender</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="phone"/>
							<label style="margin:0px;border:0px;" for="values4"> Phone</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="religion"/>
							<label style="margin:0px;border:0px;" for="values5"> Religion</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="bloodgroup"/>
							<label style="margin:0px;border:0px;" for="values6"> Blood Group</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="nationality"/>
							<label style="margin:0px;border:0px;" for="values7"> Nationality</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="accomadation"/>
							<label style="margin:0px;border:0px;" for="values8"> Accomodation</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="quota"/>
							<label style="margin:0px;border:0px;" for="values9"> Quota</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="fathersname"/>
							<label style="margin:0px;border:0px;" for="values10"> Father's Name</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="mothersname"/>
							<label style="margin:0px;border:0px;" for="values11"> Mother's Name</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="gaurdiansname"/>
							<label style="margin:0px;border:0px;" for="values12"> Gaurdians Name</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="fathersphone"/>
							<label style="margin:0px;border:0px;" for="values13"> father's Phone</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="mothersphone"/>
							<label style="margin:0px;border:0px;" for="values14"> Mother's Phone</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="gaurdiansphone"/>
							<label style="margin:0px;border:0px;" for="values15"> Gaurdian's Phone</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="aadharnumber"/>
							<label style="margin:0px;border:0px;" for="values16"> Aadhar Number</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="bankaccountnumber"/>
							<label style="margin:0px;border:0px;" for="values17"> Bank Account Number</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="ifsccode"/>
							<label style="margin:0px;border:0px;" for="values18"> Ifsc Code</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="accountholdername"/>
							<label style="margin:0px;border:0px;" for="values19"> Account Holder Name</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="pancardnumber"/>
							<label style="margin:0px;border:0px;" for="values20"> Pancard Number</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="registerednumber"/>
							<label style="margin:0px;border:0px;" for="values21"> Account Registered Number</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="whatsappnumber"/>
							<label style="margin:0px;border:0px;" for="values22"> Whatsapp Number</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="facebookaccount"/>
							<label style="margin:0px;border:0px;" for="values23"> Facebook Account</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="linkedinaccount"/>
							<label style="margin:0px;border:0px;" for="values24"> LinkedIn Account</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="githubaccount"/>
							<label style="margin:0px;border:0px;" for="values25"> Github Account</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="hackerankaccount"/>
							<label style="margin:0px;border:0px;" for="values26"> Hackerrank Account</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="hackerearthaccount"/>
							<label style="margin:0px;border:0px;" for="values27"> Hackerearth Account</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="codechefaccount"/>
							<label style="margin:0px;border:0px;" for="values28"> Codechef Account</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="instagramaccount"/>
							<label style="margin:0px;border:0px;" for="values29"> Instagram Account</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="twitteraccount"/>
							<label style="margin:0px;border:0px;" for="values30"> Twitter Account</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="state"/>
							<label style="margin:0px;border:0px;" for="values31"> State</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="district"/>
							<label style="margin:0px;border:0px;" for="values32"> District</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 des">
							<form:checkbox path="values" value="address"/>
							<label style="margin:0px;border:0px;" for="values33"> Address</label>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
							<br>
							<center>
								<button type="submit" class="btn btn-success">Submit</button>
							</center>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
						</div>
						</div>
					</div>
				</form:form>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-0 col-xs-0"></div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><hr/></div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
			<%if(request.getAttribute("linkReady")!=null){ %>
				<div id="outer">
					<div id="demo"></div>
				</div>
				<a id="link" href="/files/<%=adminId+staffId+department+collegeId %>.xls" download>
					<button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-book"></span> click here</button>
				</a>
				<%} %>	
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
		</div>
	</div>

<script>
	var myVar = setInterval(myTimer,1000);
	count = 6
	function myTimer() {
		document.getElementById("demo").style.display="block";
	 count=count-1
 if(count==0){
	 document.getElementById("demo").style.display="none";
	 document.getElementById("outer").style.display="none";
 	 clearInterval(myVar)
 	document.getElementById("link").style.display="block";
 }
 document.getElementById("demo").innerHTML = count;
}
</script>

</body>
</html>