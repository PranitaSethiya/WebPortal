<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Announcements</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
    <link href="css/announcement.css" rel="stylesheet">
</head>
<body onload="updateMinDate();">
<%@ include file="header.jsp" %>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <div class="jumbotron">
                    <center><h2>Course Announcements</h2></center>


                <table id="keywords" cellspacing="0" cellpadding="0">
                    <thead>
                    <tr>
                        <th><span>Course</span></th>
                        <th><span>TA</span></th>
                        <th><span>Course Syllabus</span></th>
                        <th><span>Office Hours</span></th>
                        <th><span>Edit</span></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="lalign">CS 203</td>
                        <td class="lalign">Hanuman Singh</td>
                        <td><button type="button" class="btn" data-toggle="modal" data-target="#syllabusModal">Click to show</button></td>
                        <td class="lalign">Monday, Tuesday 10am - 11am</td>
                        <td><button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editModal"><i class="glyphicon-edit glyphicon"></i></button></td>
                    </tr>
                    <tr>
                        <td class="lalign">CS 215</td>
                        <td class="lalign">Phulan Singh</td>
                        <td><button type="button" class="btn" data-toggle="modal" data-target="#syllabusModal">Click to show</button></td>
                        <td class="lalign">Thursday, Tuesday 10am - 11am</td>
                        <td><button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editModal"><i class="glyphicon-edit glyphicon"></i></button></td>
                    </tr>
                    <tr>
                        <td class="lalign">CS 211</td>
                        <td class="lalign">Si Singh</td>
                        <td><button type="button" class="btn" data-toggle="modal" data-target="#syllabusModal">Click to show</button></td>
                        <td class="lalign">Monday, Wednesday 10am - 11am</td>
                        <td><button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editModal"><i class="glyphicon-edit glyphicon"></i></button></td>
                    </tr>
                    </tbody>
                </table>
                </div>
            </div>
        </div>

        <div id="syllabusModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Syllabus for CS203</h4>
                    </div>
                    <div class="modal-body">
                        <p>
                            CS 218 Design and Analysis of Algorithms, 4 units, Lecture, 3 hours; outside research, 3 hours. Prerequisite(s): CS 141. A study of efficient data structures and algorithms for solving problems from a variety of areas such as sorting, searching, selection, linear algebra, graph theory, and computational geometry. Also covers worst-case and average-case analysis using recurrence relations, generating functions, upper and lower bounds, and other methods. May be taken Satisfactory (S) or No Credit (NC) with consent of instructor and graduate advisor.
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>

        <div id="editModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Cancel Reservation</h4>
                    </div>
                    <div class="modal-body">
                        <p>

                        </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" data-dismiss="modal">Save</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                    </div>
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
    $(function(){
        $('#keywords').tablesorter();
    });
</script>
<%--<script src="js/reserve_resource.js"></script>--%>
</body>

</html>