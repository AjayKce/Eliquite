<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <html>

    <head>
        <title>Eliquite</title>
        <jsp:include page="bootstrap.jsp"></jsp:include>
        <link href="/stylesheet/createSuperUser.css" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <div class="row">
            	<h1 class="well well-sm" align="center">Create superuser</h1>
                <div class="col-lg-4 col-md-4 col-sm-0 col-xs-0"></div>
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <form:form method="post" action="processCreateSuperUser" modelAttribute="superuser">
                    	<form:hidden path="id"/>
                    	<label for="username">Username : </label>
                        <span class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        	<form:input path="username" cssClass="form-control form-control-sm" placeholder="enter username" required="required"/>
                        </span>
                        <label for="password">Password : </label>
                        <span class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        	<form:password path="password" cssClass="form-control form-control-sm" placeholder="enter password" required="required"/>
                        </span>
                        <br />
                        <input class="btn btn-primary" type="submit" value="create">
                    </form:form>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-0 col-xs-0"></div>
            </div>
        </div>
    </body>

    </html>