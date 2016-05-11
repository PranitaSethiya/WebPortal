<%@ page import="com.se.pranita.termproject.model.user.Student" %>
<%@ page import="com.se.pranita.termproject.model.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
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
                <% if ((student.getNetID().equalsIgnoreCase(currentUser.getNetID())) /*|| currentUser.getType().getValue() != 0*/) {%>
                <div>
                    <button type="button" class="btn btn-warning pull-right" data-toggle="modal"
                            data-target="#editModal">Edit
                    </button>
                    <br/>
                </div>
                <br/>
                <%}%>
                <div class="jumbotron">

                    <h1><%=student.getFirstName() + " " + student.getLastName()%>
                    </h1>
                    <hr>
                    <p class="profile">
                    <div class="row">
                        <div class="column-12">
                            <%--<h2 class="listing-name">Hotel Fair</h2>--%>

                            <table class="property-data">
                                <tbody>
                                <tr>
                                    <td>
                                        <b>Email: </b>
                                    </td>
                                    <td>
                                        <%= student.getNetID() %>@webportal.edu
                                    </td>
                                    <td><b>Phone: </b></td>
                                    <td>
                                        <%= student.getPhoneNumber().isEmpty() ? "-" : student.getPhoneNumber() %>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Advisor: </b>
                                    </td>
                                    <td>
                                        <%= student.getAdvisorName().isEmpty() ? "-" : student.getAdvisorName() %>
                                    </td>
                                    <td><b>Program: </b></td>
                                    <td>
                                        <%= student.getProgram() %>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Department: </b>
                                    </td>
                                    <td>
                                        <%= student.getDepartment() %>
                                    </td>
                                    <td><b>Starting Term: </b></td>
                                    <td>
                                        <%= student.getStartTerm() + " " + student.getStartYear() %>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <b>Status: </b>
                                    </td>
                                    <td>
                                        <%--<%= student.getStatus()%>--%>
                                        <button class="btn">Click to Show</button>
                                    </td>
                                    <td></td>
                                    <td>
                                    </td>
                                </tr>

                                </tbody>
                            </table>

                        </div>
                    </div>

                    <hr/>
                    <hr/>
                    <div class="row">
                        <div class="column-12">
                            <center><h2 class="listing-name">Courses</h2></center>

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
                                        <td><%= course.getStatus().toString() %></td>
                                    </tr>
                                <% } %>
                                </tbody>
                            </table>

                        </div>
                    </div>
                    <%--<div class="row">--%>
                    <%--<div class="col-lg-4">--%>
                    <%--<h3><b>Program: </b><%= student.getProgram() %><br/></h3>--%>
                    <%--<h3><b>Department: </b><br/><%= student.getDepartment() %><br/></h3>--%>
                    <%--<h3><b>Starting Term: </b><%= student.getStartTerm() + " " + student.getStartYear() %><br/></h3>--%>
                    <%--<h3><b>Status: </b><br/><%= student.getStatus()%><br/></h3>--%>
                    <%--</div>--%>
                    <%--<div class="col-lg-8">--%>
                    <%--<center><h3><b>Course Details:</b></h3></center>--%>

                    <%--<table id="keywords" class="" cellspacing="0" cellpadding="0">--%>
                    <%--<thead>--%>
                    <%--<tr>--%>
                    <%--<th><span>Course Number</span></th>--%>
                    <%--<th><span>Course Name</span></th>--%>
                    <%--<th><span>Term</span></th>--%>
                    <%--<th><span>Status</span></th>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<tbody>--%>
                    <%--<% for (int i = 0; i < student.getCourses().size(); i++) {--%>
                    <%--Course course = (Course) student.getCourses().get(i);--%>
                    <%--%>--%>
                    <%--<tr>--%>
                    <%--<td class="lalign"><%= course.getNumber() %></td>--%>
                    <%--<td><%= course.getName() %></td>--%>
                    <%--<td><%= course.getTerm() + " " + course.getYear() %></td>--%>
                    <%--<td><%= course.getStatus().toString() %></td>--%>
                    <%--</tr>--%>
                    <%--<% } %>--%>
                    <%--</tbody>--%>
                    <%--</table>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    </p>
                </div>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
    </div>


    <div id="editModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Edit Details</h4>
                </div>
                <div class="modal-body">
                    <p>
                        <input id="netID" type="text"
                               hidden value="<%= student.getNetID() %>">
                    <div class="pad" id="name_parent">
                        <label for="name">Name</label>
                        <div class="row" id="name">
                            <div class="col-lg-6">
                                <input name="firstName" id="firstName" type="text" class="form-control col-lg-6"
                                       placeholder="First" value="<%= student.getFirstName() %>">
                            </div>
                            <div class="col-lg-6">
                                <input name="lastName" id="lastName" type="text" class="form-control col-lg-6"
                                       placeholder="Last" value="<%= student.getLastName() %>">
                            </div>
                        </div>
                    </div>

                    <div class="pad" id="oldPassParent">
                        <label for="oldPass">Old password</label>
                        <input name="oldPassword" type="password" class="form-control" id="oldPass" placeholder="">
                    </div>

                    <div class="pad" id="passParent">
                        <label for="pass">Create a password</label>
                        <div class="alert alert-danger" role="alert" id="pass_error" hidden>
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <span class="sr-only">Error:</span>
                            Passwords do not match
                        </div>
                        <input name="password" type="password" class="form-control" id="pass" placeholder="">
                    </div>
                    <div class="pad" id="cnfPassParent">
                        <label for="cnfPass">Confirm your password</label>
                        <input name="confirmPassword" type="password" onblur="passwordError();" class="form-control"
                               id="cnfPass" placeholder="">
                    </div>

                    <div class="pad" id="phoneNumberParent">
                        <label for="phoneNumber">Phone number</label>
                        <input name="phoneNumber" type="tel" class="form-control" id="phoneNumber"
                               placeholder="xxxxxxxxxx" value="<%= student.getPhoneNumber() %>">
                    </div>

                    <hr>

                    <h4>Advisor Information</h4>
                    <div class="pad" id="advisor_name_parent">
                        <label for="advisor_firstName">Full Name</label>
                        <input name="firstName" id="advisor_firstName" type="text" class="form-control col-lg-6"
                               placeholder="Name" value="<%= student.getAdvisorName() %>">
                    </div>

                    <%--<div class="pad" id="advisor_netID">--%>
                    <%----%>
                    <%--</div>--%>


                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" onclick="updateStudent();">Update</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>


</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.25.8/js/jquery.tablesorter.min.js"></script>
<script src="js/vendor/jquery.toaster.js"></script>
<script src="js/view_student.js"></script>
<script src="js/sidebar.js"></script>
<script>
    $(function () {
        $('#keywords').tablesorter();
    });
</script>
</body>

</html>