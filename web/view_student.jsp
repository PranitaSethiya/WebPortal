<%@ page import="com.se.pranita.termproject.model.Student" %>
<%@ page import="com.se.pranita.termproject.model.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
    <link href="css/table_sorter.css" rel="stylesheet">
</head>
<body>
<%
    Student student = (Student) request.getAttribute("user");
%>
<%@ include file="header.jsp" %>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <div class="jumbotron">
                    <%--<div class="row">--%>
                        <%--<div class="col-lg-2">--%>
                            <%--<i class="img-circle glyphicon glyphicon-user icon-large icon-white "--%>
                               <%--style="font-size: 100px; padding: 20px;"></i>--%>
                        <%--</div>--%>
                        <%--<div class="col-lg-10">--%>
                            <h1><%=student.getFirstName() + " " + student.getLastName()%>
                            </h1>
                        <h4><%= student.getNetID() %>@webportal.edu<br/></h4>
                        <hr>
                        <%--</div>--%>
                    <%--</div>--%>
                    <p>
                    <div class="row">
                        <div class="col-lg-4">
                            <h3><b>Program: </b><%= student.getProgram() %><br/></h3>
                            <h3><b>Department: </b><br/><%= student.getDepartment() %><br/></h3>
                            <h3><b>Starting Term: </b><%= student.getStartTerm() + " " + student.getStartYear() %><br/></h3>
                            <h3><b>Status: </b><br/><%= student.getStatus()%><br/></h3>
                        </div>
                        <div class="col-lg-8">
                            <center><h3><b>Course Details:</b></h3></center>

                            <table id="keywords" class="" cellspacing="0" cellpadding="0">
                                <thead>
                                <tr>
                                    <th><span>Course Number</span></th>
                                    <th><span>Course Name</span></th>
                                    <th><span>Term</span></th>
                                    <th><span>Status</span></th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for (int i = 0; i < student.getCourses().size(); i++) {
                                    Course course = (Course) student.getCourses().get(i);
                                %>
                                <tr>
                                    <td class="lalign"><%= course.getNumber() %></td>
                                    <td><%= course.getName() %></td>
                                    <td><%= course.getTerm() + " " + course.getYear() %></td>
                                    <td><%= course.getStatus() %></td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <%--<div class="row">--%>
                        <%--<div class="col-lg-6">--%>

                        <%--</div>--%>
                        <%--<div class="col-lg-6">--%>

                        <%--</div>--%>
                    <%--</div>--%>


                    </p>


                </div>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.25.8/js/jquery.tablesorter.min.js"></script>

<script src="js/sidebar.js"></script>
<script>
    $(function () {
        $('#keywords').tablesorter();
    });
</script>
<%--<script src="js/view_student.js"></script>--%>
</body>

</html>