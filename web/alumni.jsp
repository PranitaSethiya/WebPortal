<%@ page import="java.util.ArrayList" %>
<%@ page import="com.se.pranita.termproject.model.Alumni" %>
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
<%
    ArrayList<Alumni> alumnis = (ArrayList) request.getAttribute("alumnis");
%>
<div id="wrapper" class="toggled">
    <%@ include file="sidebar.jsp" %>
    <div id="page-content-wrapper">
        <div id="wrap">
            <div id="main" class="container">
                <h1>Welcome to the Alumni Page.</h1>
                <% if(currentUser.getType().getValue() != 0) {%>
                <div><button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#editOrAddModal">Add Alumni</button><br/></div><br/>
                <% } %>
                <% if(alumnis.size() > 0) {%>
                <ul class="list-group">
                    <% for(int i = 0 ; i < alumnis.size() ; i++) {
                        Alumni alumni = alumnis.get(i);
                    %>
                    <li class="list-group-item">
                        <table border="0" width="100%">
                            <tr>
                                <% if(alumni.getImage() == null) {%>
                                <td><i class="img-circle glyphicon glyphicon-tower icon-large icon-white"
                                       style="font-size: 50px; padding: 20px;"></i></td>
                                <%} else {%>
                                <td><img src="<%= alumni.getImage() %>" width="100" height="100"></td>
                                <% } %>
                                <td><b><%= alumni.getName() %></b><br/>
                                    <a href="<%= alumni.getHomepage() %>"><%= alumni.getHomepage() %></a><br/>
                                    <p><%= alumni.getDescription() %></p>
                                </td>
                                <% if(currentUser.getType().getValue() != 0) {%>
                                <td><button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editModal"><i class="glyphicon-edit glyphicon"></i></button></td>
                                <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal"><i class="glyphicon-trash glyphicon"></i></button></td>
                                <% } %>
                            </tr>
                        </table>
                    </li>
                    <% } %>
                </ul>
                <% } else {%>
                <p>No Alumni Associations.</p>
                <% } %>
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

        <div id="editOrAddModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Alumni</h4>
                    </div>
                    <div class="modal-body">
                        <p>
                            <label for="name">Name</label>
                            <input id="name" name="name" type="text" class="form-control" placeholder="" autofocus>
                            <label for="homepage">Homepage</label>
                            <input id="homepage" name="homepage" type="text" class="form-control" placeholder="http://alumni.xyz">
                            <label for="description">Description</label>
                            <textarea id="description" name="description" type="text" class="form-control"></textarea>
                            <label for="image">Image</label>
                            <input id="image" name="image" type="text" class="form-control" placeholder="http://alumni.xyz/logo.png">
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
<script src="js/sidebar.js"></script>
<script src="js/alumni.js"></script>
</body>

</html>