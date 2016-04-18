<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Details</title>
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
                <div class="jumbotron">
                    <div class="pull-right">
                        <button type="button" class="btn btn-warning" onclick="location.href='/edit_student';">
                            <span class="glyphicon glyphicon-edit"></span> Edit
                        </button>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal">
                            <span class="glyphicon glyphicon-trash"></span> Delete
                        </button>
                    </div>
                    <h1>Pranita Sethiya</h1>
                    <p>
                        <b>Email: </b>psethiya@albany.edu<br/>
                        <b>Program: </b>MS<br/>
                        <b>Department: </b>Computer Science<br/>
                        <b>Starting Term: </b>Fall 2015<br/>
                        <b>Type: </b>Alumni/Enrolled<br/>
                        <b>Status: </b>2 Semesters Completed<br/>
                    </p>
                </div>
            </div>
        </div>

        <div id="deleteModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Cancel Reservation</h4>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete student:<br/><b>Pranita Sethiya</b>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Yes</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                    </div>
                </div>

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