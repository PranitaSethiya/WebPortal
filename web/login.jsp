<%--
  Created by IntelliJ IDEA.
  User: pranita
  Date: 14/4/16
  Time: 7:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to Pranita's WebPortal</title>
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
</head>
<body>
<div id="wrap">
    <div id="main" class="container">
        <h1 class="heading text-center">Welcome</h1>
        <p class="heading-desc text-center">Sign in to continue</p>
        <form name="loginForm" class="form-signin" action="LoginServlet">
            <center>
                <i class="img-circle glyphicon glyphicon-user icon-large icon-white"
                   style="font-size: 70px; padding: 20px;"></i>
            </center>
            <input name="" type="text" class="form-control" placeholder="Net ID" autofocus>
            <input type="password" class="form-control" placeholder="Password">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
        <a href="/SignUp" class="text-center account-creation">Create account</a>
    </div>
</div>

<script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</body>

</html>
