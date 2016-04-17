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
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <b>Projector:</b> The ultimate 5000.<br/>
                                <b>Additional Info:</b> This is super awesome ultimate projector.
                            </h4>
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                                Reserve</a>
                        </div>
                        <div id="collapse1" class="panel-collapse collapse">
                            <div class="panel-body">
                                <form action="">
                                    <div class="pad">
                                        <label>Select a date: </label><br/>
                                        <input type="date" class="resource_additional" required><br><br>
                                    </div>
                                    <div class="pad">
                                        <label>Available timeslots: </label><br/>
                                        <input type="radio" name="timeslot" value="9-10" required> 9am - 10am<br>
                                        <input type="radio" name="timeslot" value="10-11"> 10am - 11am<br>
                                        <input type="radio" name="timeslot" value="11-12"> 11am - 12pm<br/>
                                        <input type="radio" name="timeslot" value="12-1"> 12pm - 1pm<br/>
                                        <input type="radio" name="timeslot" value="1-2"> 1pm - 2pm<br/>
                                        <input type="radio" name="timeslot" value="2-3"> 2pm - 3pm<br/>
                                        <input type="radio" name="timeslot" value="3-4"> 3pm - 4pm<br/>
                                        <input type="radio" name="timeslot" value="4-5"> 4pm - 5pm<br/>
                                        <input type="radio" name="timeslot" value="5-6"> 5pm - 6pm<br/>
                                        <input type="radio" name="timeslot" value="6-7"> 6pm - 7pm<br/><br/>
                                    </div>
                                    <button class="btn btn-lg btn-primary btn-block" type="submit">Reserve Resource
                                    </button>
                                </form>
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
<script src="js/reserve_resource.js"></script>
</body>

</html>