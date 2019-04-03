<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <html>

  <head>
    <title>Eliquite</title>
    <jsp:include page="bootstrap.jsp"></jsp:include>
    <link href="/stylesheet/requestErp.css" rel="stylesheet">
    <style type="text/css">
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
          <li><a href="${pageContext.request.contextPath}/superUserLogin"><span class="glyphicon glyphicon-log-in"></span> superuser</a></li>
        </ul>
        </div>
      </div>
    </nav>

    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
        	<img class="img img-responsive" src="img/logo.png" />
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 login">
        		<%
					if (request.getAttribute("invalidRequest") != null) {
				%>
				<p style="color: red">This college is already registered!!!!contact your college</p>
				<%
					}
				%>
        	<form:form method="post" action="processRequestErp" modelAttribute="requestErp">
                    	<form:hidden path="id"/>
                    	<label for="collegeName">College : </label>
                        <span class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-education"></i></span>
                        	<form:input path="collegeName" cssClass="form-control form-control-sm" placeholder="enter college name" required="required"/>
                        </span><br>
                        <label for="collegeEmail">College Mail : </label>
                        <span class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                        	<form:input path="collegeEmail" cssClass="form-control form-control-sm" placeholder="enter college email" required="required"/>
                        </span><br>
                        <label for="password">College Contact : </label>
                        <span class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
                        	<form:input path="collegeContact" cssClass="form-control form-control-sm" placeholder="enter college contact" required="required"/>
                        </span>
                        <br />
                        <input class="btn btn-primary" type="submit" value="send">
                    </form:form>
        </div>
      </div>
    </div>

  </body>

  </html>