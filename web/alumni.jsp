<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alumni</title>
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
                <h1>Welcome to the Alumni Page.</h1>
                <div><button type="button" class="btn btn-primary pull-right">Add Alumni</button><br/></div><br/>
                <ul class="list-group">
                    <li class="list-group-item">
                        <table border="0" width="100%">
                            <tr>
                                <td><i class="img-circle glyphicon glyphicon-tower icon-large icon-white"
                                       style="font-size: 50px; padding: 20px;"></i></td>
                                <td><b>Yo Alumni Association</b><br/>
                                    <a href="http://yoalumni">http://yoalumni</a><br/>
                                    <p>Yo was founded by these people and these people founded it and found it in December 2015. Go yo!!</p>
                                </td>
                                <td><button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editModal"><i class="glyphicon-edit glyphicon"></i></button></td>
                                <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal"><i class="glyphicon-trash glyphicon"></i></button></td>
                            </tr>
                        </table>
                    </li>
                    <li class="list-group-item">
                        <table border="0" width="100%">
                            <tr>
                                <td><i class="img-circle glyphicon glyphicon-tower icon-large icon-white"
                                       style="font-size: 50px; padding: 20px;"></i></td>
                                <td><b>Yo Alumni Association</b><br/>
                                <a href="http://yoalumni">http://yoalumni</a><br/>
                                    <p>Yo was founded by these people and these people founded it and found it in December 2015. Go yo!!</p>
                                </td>
                                <td><button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editModal"><i class="glyphicon-edit glyphicon"></i></button></td>
                                <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal"><i class="glyphicon-trash glyphicon"></i></button></td>
                            </tr>
                        </table>
                    </li>
                </ul>
            </div>
        </div>

        <div id="deleteModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Delete Alumni</h4>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete Alumni <b>Yo Alumni Association</b>?
                        </p>
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