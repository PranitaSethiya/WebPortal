<%--
  Created by IntelliJ IDEA.
  User: sachin
  Date: 14/4/16
  Time: 7:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Testing</title>
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
</head>
<body>
    <%@ include file="header.jsp" %>



    <h1>Simple Java Web App Demo</h1>
    <p>To invoke the java servlet click <a href="MyServlet">here</a></p>
    <div id="wrap">
        <div id="main" class="container">
            <%--<div class="text-center"><img src="http://wsnippets.com/wp-content/uploads/2013/10/logo2_18011.png" /></div>--%>
            <h1 class="heading text-center">Welcome</h1>
            <p class="heading-desc text-center">Sign in to continue</p>
            <form class="form-signin" action="LoginServlet">
                <center>
                    <i class="img-circle glyphicon glyphicon-user icon-large icon-white" style="font-size: 70px; padding: 20px;"></i>
                </center>
                <input type="text" class="form-control" placeholder="Email" autofocus>
                <input type="password" class="form-control" placeholder="Password">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
            <a href="/login" class="text-center account-creation">Create an account</a>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
    <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</body>

</html>
