<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alumni Students</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
</head>
<body onload="updateMinDate();">
<%@ include file="header.jsp" %>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <h1>Alumni Students</h1>
                <div><button type="button" class="btn btn-primary pull-right">Add Student</button><br/></div><br/>
                <ul class="list-group">
                    <li class="list-group-item">
                        <table border="0">
                            <tr>
                                <td><i class="img-circle glyphicon glyphicon-user icon-large icon-white "
                                       style="font-size: 50px; padding: 20px;"></i></td>
                                <td> <a href="/view_student">Pranita Sethiya</a><br/>
                                    psethiya@albany.edu<br/>
                                </td>
                            </tr>
                        </table>
                    </li>
                    <li class="list-group-item">
                        <table border="0">
                            <tr>
                                <td><i class="img-circle glyphicon glyphicon-user icon-large icon-white "
                                       style="font-size: 50px; padding: 20px;"></i></td>
                                <td> <a href="/view_student">Pranita Sethiya</a><br/>
                                    psethiya@albany.edu<br/>
                                </td>
                            </tr>
                        </table>
                    </li>
                </ul>
            </div>
        </div>



        <%@ include file="footer.jsp" %>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<script src="js/sidebar.js"></script>
<%--<script src="js/reserve_resource.js"></script>--%>
</body>

</html>