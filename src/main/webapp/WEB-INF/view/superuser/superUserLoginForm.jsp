<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <html>

  <head>
    <title>Eliquite</title>
    <jsp:include page="bootstrap.jsp"></jsp:include>
    <link href="/stylesheet/superUserLoginForm.css" rel="stylesheet">
    <style>
    	.container{
margin-top:70px;
}
    </style>
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
        </ul>
        </div>
      </div>
    </nav>

    <div class="container">
      <div class="row">
       <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
        	<img class="img img-responsive" src="img/logo.png" />
        </div>
        <div class="col-lg-0 col-md-0 col-sm-1 col-xs-1"></div>
        <div class="col-lg-4 col-md-4 col-sm-10 col-xs-10 login">
        <% if(request.getAttribute("invalidSuperUser")!=null){ %>
        <p style="color:red">Invalid credentials!!!!</p>
        <%} %>
              <form method="post" action="processSuperUserLogin">
                <label for="username">Super-user : </label>
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
        <div class="col-lg-0 col-md-0 col-sm-1 col-xs-1"></div>
       </div>
    </div>
    </body>
    </html>