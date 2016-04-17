<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resources</title>
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
                <ul class="list-group">
                    <li class="list-group-item">
                        <button type="button" class="btn btn-danger pull-right" data-toggle="modal" data-target="#cancelModal">Cancel</button>
                        <div>
                            <b>Resource Name: </b>Ultimate 5000<br/>
                            <b>Resource Type: </b>Projector<br/>
                            <b>Booked time slot: </b><br/>
                            <ul>
                                <li>04-25-2016, 3pm to 4pm<br/></li>
                            </ul>
                        </div>
                    </li>
                    <li class="list-group-item">
                            <button type="button" class="btn btn-danger pull-right" data-toggle="modal" data-target="#cancelModal">Cancel</button>
                            <div>
                                <b>Resource Name: </b>Machine 200<br/>
                                <b>Resource Type: </b>Fax Machine<br/>
                                <b>Booked time slot: </b><br/>
                                <ul>
                                    <li>04-25-2016, 3pm to 4pm<br/></li>
                                </ul>
                            </div>
                    </li>
                    <li class="list-group-item">
                        <button type="button" class="btn btn-danger pull-right" data-toggle="modal" data-target="#cancelModal">Cancel</button>
                        <div>
                            <b>Resource Name: </b>WCH 220<br/>
                            <b>Resource Type: </b>Conference Room<br/>
                            <b>Booked time slot: </b><br/>
                            <ul>
                                <li>04-25-2016, 3pm to 4pm<br/></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

        <div id="cancelModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Cancel Reservation</h4>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to cancel reservation for:<br/><b>Resource Name: </b>Machine 200<br/>
                            <b>Resource Type: </b>Fax Machine<br/>
                            <b>Booked time slot: </b><br/>
                        <ul>
                            <li>04-25-2016, 3pm to 4pm<br/></li>
                        </ul></p>
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