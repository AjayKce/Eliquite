<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.*,student.kce.erp.model.*" %>
  <html>

  <head>
    <title>Home Page</title>
    <jsp:include page="bootstrap.jsp"></jsp:include>
    <link href="/stylesheet/home.css" rel="stylesheet">
  </head>
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
          <li><a href="${pageContext.request.contextPath}/getResourcePage"><span class="glyphicon glyphicon-book"></span> Resource</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="${pageContext.request.contextPath}/requestErp"><span class="glyphicon glyphicon-envelope"></span> Request</a></li>
          <li><a href="${pageContext.request.contextPath}/superUserLogin"><span class="glyphicon glyphicon-log-in"></span> superuser</a></li>
        </ul>
        </div>
      </div>
    </nav>
	<% List<College> colleges = (List<College>)request.getAttribute("colleges");
	%>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
        	<img class="img img-responsive" src="img/logo.png" />
        </div>
        <div class="col-lg-0 col-md-0 col-sm-1 col-xs-1"></div>
        <div class="col-lg-4 col-md-4 col-sm-10 col-xs-10 login">
          <ul class="nav nav-pills">
            <li class="active"><a data-toggle="tab" href="#college">College</a></li>
            <li><a data-toggle="tab" href="#admin">Admin</a></li>
            <li><a data-toggle="tab" href="#staff">Staff</a></li>
            <li><a data-toggle="tab" href="#student">Student</a></li>
          </ul>

          <div class="tab-content">
            <div id="college" class="active tab-pane">
              <br/>
              <form method="post" action="processCollege">
              <label for="college">College : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-education"></i></span>
                <select name="college" id="college" class="form-control">
                	<%for(College temp:colleges){ %>
                	<option><%=temp.getCollegeName() %></option>
                	<%} %>
                </select>
                </span><br>
                <label for="username">Username : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input type="text" name="username" class="form-control form-control-sm" placeholder="enter username" required/>
                </span>
                <br>
                <label for="password">Password : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input type="password" name="password" class="form-control form-control-sm" placeholder="enter password" required/>
                </span><br>
                <center>
                  <input style="width:50%;" type="submit" class="btn btn-default" value="login">
                </center>
              </form>
            </div>
            
            <div id="admin" class="tab-pane">
              <br/>
              <form method="post" action="processAdminLogin">
              	 <label for="college">College : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-education"></i></span>
                <select name="collegeId" id="college" class="form-control" required>
                	<%for(College temp:colleges){ %>
                	<option value="<%=temp.getId()%>"><%=temp.getCollegeName() %></option>
                	<%} %>
                </select>
                </span><br>
                <label for="department">Department : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span>
							 <select id="department" name="department" class="form-control form-control-sm" required="required">
							 	<option value="CSE">CSE</option>
							 	<option value="IT">IT</option>
							 	<option value="EEE">EEE</option>
							 	<option value="ECE">ECE</option>
							 	<option value="ETE">ETE</option>
							 	<option value="CIVIL">CIVIL</option>
							 	<option value="MECH">MECH</option>
							 	<option value="AUTOMOBILE">AUTOMOBILE</option>
							 </select>
					</span>
					<br />
                <label for="username">Admin : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input type="text" name="username" class="form-control form-control-sm" placeholder="enter username" required/>
                </span>
                <br>
                <label for="password">Password : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input type="password" name="password" class="form-control form-control-sm" placeholder="enter password" required/>
                </span><br>
                <center>
                  <input style="width:50%;" type="submit" class="btn btn-default" value="login">
                </center>
              </form>
            </div>
            
            <div id="staff" class="tab-pane">
              <br/>
              <form method="post" action="processStaffLogin">
               <label for="college">College : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-education"></i></span>
                <select name="collegeId" id="college" class="form-control" required>
                	<%for(College temp:colleges){ %>
                	<option value="<%=temp.getId() %>"><%=temp.getCollegeName() %></option>
                	<%} %>
                </select>
                </span><br>
                <label for="department">Department : </label>
					<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span>
							 <select id="department" name="department" class="form-control form-control-sm" required="required">
							 	<option value="CSE">CSE</option>
							 	<option value="IT">IT</option>
							 	<option value="EEE">EEE</option>
							 	<option value="ECE">ECE</option>
							 	<option value="ETE">ETE</option>
							 	<option value="CIVIL">CIVIL</option>
							 	<option value="MECH">MECH</option>
							 	<option value="AUTOMOBILE">AUTOMOBILE</option>
							 </select>
					</span><br>
                <label for="username">username : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input type="text" name="username" class="form-control form-control-sm" placeholder="enter username" required/>
                </span><br>
                <label for="password">Password : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input type="password" name="password" class="form-control form-control-sm" placeholder="enter password" required/>
                </span><br>
                <center>
                  <input style="width:50%;" type="submit" class="btn btn-default" value="login">
                </center>
              </form>
            </div>
            
            <div id="student" class="tab-pane">
              <br/>
              <form method="post" action="processStudentLogin">
              <label for="college">College : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-education"></i></span>
                <select name="collegeId" id="college" class="form-control" required>
                	<%for(College temp:colleges){ %>
                	<option value="<%=temp.getId() %>"><%=temp.getCollegeName() %></option>
                	<%} %>
                </select>
                </span><br>
                
                <div class="row">
                	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                		<label for="department">Department : </label>
						<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span>
							 <select id="department" name="department" class="form-control form-control-sm" required="required">
							 	<option value="CSE">CSE</option>
							 	<option value="IT">IT</option>
							 	<option value="EEE">EEE</option>
							 	<option value="ECE">ECE</option>
							 	<option value="ETE">ETE</option>
							 	<option value="CIVIL">CIVIL</option>
							 	<option value="MECH">MECH</option>
							 	<option value="AUTOMOBILE">AUTOMOBILE</option>
							 </select>
						</span>
                	</div>
                	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                		<label for="semester">Semester : </label>
						<span class="input-group"> <span class="input-group-addon"><i
							class="glyphicon glyphicon-tag"></i></span>
							 <select id="semester" name="semester" class="form-control form-control-sm" required="required">
							 	<option value="1">1</option>
							 	<option value="2">2</option>
							 	<option value="3">3</option>
							 	<option value="4">4</option>
							 	<option value="5">5</option>
							 	<option value="6">6</option>
							 	<option value="7">7</option>
							 	<option value="8">8</option>
							 </select>
						</span><br>
                	</div>
                </div>
                
                <label for="username">Student : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input type="text" name="username" class="form-control form-control-sm" placeholder="enter username" required/>
                </span>
                <br>
                <label for="password">Password : </label>
                <span class="input-group">
				  <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input type="password" name="password" class="form-control form-control-sm" placeholder="enter password" required/>
                </span><br>
                <center>
                  <input style="width:50%;" type="submit" class="btn btn-default" value="login">
                </center>
              </form>
            </div>
            
          </div>
        </div>
         <div class="col-lg-0 col-md-0 col-sm-1 col-xs-1"></div>
      </div>
    </div>

  </body>

  </html>