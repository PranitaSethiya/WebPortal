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
    <title>Create Course</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
</head>
<body onload="updateDisplay();">
<%@ include file="header.jsp" %>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
<div id="wrap">
    <div id="main" class="container">
        <h1 class="heading text-center">Create a course</h1>
        <form name="loginForm" class="form-signin" action="courses"
              onsubmit="validateForm();" method="post">
            <%--<center>--%>
            <%--<i class="img-circle glyphicon glyphicon-user icon-large icon-white" style="font-size: 70px; padding: 20px;"></i>--%>
            <%--</center>--%>
            <div class="pad" id="course_num_parent">
                <label for="course_num">Course Number</label>
                <input id="course_num" name="course_num" type="text" class="form-control" placeholder="XX 000" autofocus required>
            </div>
            <div class="pad" id="course_name_parent">
                <label for="course_name">Course Name</label>
                <input id="course_name" name="course_name" type="text" class="form-control" placeholder="" required>
            </div>

            <div class="pad" id="offered_sem">
                <label for="sem">Offered Term</label>
                <div class="row" id="sem">
                    <div class="col-lg-6">
                        <select name="sem" class="form-control">
                            <option>Fall</option>
                            <option>Spring</option>
                        </select>
                    </div>
                    <div class="col-lg-6">
                        <input name="year" id="start_year" type="number" class="form-control col-lg-6" placeholder="Year" required>
                    </div>
                </div>
            </div>

            <div class="pad" id="department">
                <label for="dept">Department</label>
                <select name="department" class="form-control" id="dept">
                    <option>Chemical Engineering</option>
                    <option>Computer Science</option>
                    <option>Computer Engineering</option>
                    <option>Electrical Engineering</option>
                    <option>Environmental Sciences</option>
                    <option>Mechanical Engineering</option>
                </select>
            </div>

            <div class="pad" id="course_syllabus_parent">
                <label for="course_syllabus">Course Syllabus</label>
                <textarea id="course_syllabus" name="course_syllabus" class="form-control" placeholder=""></textarea>
            </div>

            <div class="pad" id="ins_office_parent">
                <label for="ins_office">Instructor Office</label>
                <input id="ins_office" name="ins_office" type="text" class="form-control" placeholder="WCH 111">
            </div>

            <div class="pad" id="ins_office_hour_parent">
                <label for="ins_office_hour">Instructor Office Hour</label>
                <input id="ins_office_hour" name="ins_office_hour" type="text" class="form-control" placeholder="Monday, Wednesday - 10 am to 12 pm">
            </div>

            <div class="pad" id="ta_name_parent">
                <label for="ta_name">TA Name</label>
                <input id="ta_name" name="ta_name" type="text" class="form-control" placeholder="">
            </div>

            <div class="pad" id="ta_email_parent">
                <label for="ta_email">TA Email</label>
                <input id="ta_email" name="ta_email" type="text" class="form-control" placeholder="xyz@email.com">
            </div>

            <div class="pad" id="ta_office_parent">
                <label for="ta_office">TA Office</label>
                <input id="ta_office" name="ta_office" type="text" class="form-control" placeholder="WCH 111">
            </div>

            <div class="pad" id="ta_office_hour_parent">
                <label for="ta_office_hour">TA Office Hour</label>
                <input id="ta_office_hour" name="ta_office_hour" type="text" class="form-control" placeholder="Monday, Wednesday - 10 am to 12 pm">
            </div>

            <div class="alert alert-danger" role="alert" id="errors">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <span class="sr-only">Error:</span>
                <div id="error_msg"></div>
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Create Course</button>
        </form>
    </div>
</div>
        <%@ include file="footer.jsp" %>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<script src="js/sidebar.js"></script>
<script src="js/create_course.js"></script>

</body>

</html>
