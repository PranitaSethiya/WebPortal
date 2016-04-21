<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <div class="panel-group" id="accordion">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                CS 201: Algorithms and Data Structures.
                            </h4>
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                                more details</a>
                        </div>
                        <div id="collapse1" class="panel-collapse collapse">
                            <div class="panel-body">
                                <b>Instructor:</b> Lorem Ipsum<br/>
                                <b>Office:</b> WCH 139<br/>
                                <b>Office Hours:</b> 10am - 11am, Tuesday and Thursday<br/>
                                <a href="http://mail.albany.edu" target="_blank">Send Email</a><br/>
                                <br/>
                                <b>TA:</b> Sit Amet<br/>
                                <b>Office:</b> WCH 234<br/>
                                <b>Office Hours:</b> 12pm - 1pm, Monday<br/>
                                <a href="http://mail.albany.edu" target="_blank">Send Email</a><br/>
                                <br/>
                                <b>Course Syllabus:</b><br/>
                                CS 218 Design and Analysis of Algorithms, 4 units, Lecture, 3 hours; outside research, 3 hours. Prerequisite(s): CS 141. A study of efficient data structures and algorithms for solving problems from a variety of areas such as sorting, searching, selection, linear algebra, graph theory, and computational geometry. Also covers worst-case and average-case analysis using recurrence relations, generating functions, upper and lower bounds, and other methods. May be taken Satisfactory (S) or No Credit (NC) with consent of instructor and graduate advisor.
                            </div>
                        </div>
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
</body>

</html>