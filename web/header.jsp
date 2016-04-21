<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.se.pranita.termproject.model.User" %><%--
  Created by IntelliJ IDEA.
  User: sachin
  Date: 15/4/16
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    User currentUser = (User) (session.getAttribute("currentSessionUser"));
%>
<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role = "navigation">
    <div class="container">
        <div class="navbar-header">
            <span class="navbar-brand">WebPortal</span>
            <button type = "button" class = "navbar-toggle"
            data-toggle = "collapse" data-target = "#example-navbar-collapse">
            <span class = "sr-only">Toggle navigation</span>
            <span class = "icon-bar"></span>
            <span class = "icon-bar"></span>
            <span class = "icon-bar"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active" id="nav-home-btn"><a href="/home">Home <span class="sr-only">(current)</span></a></li>
                <li><a href="#" id="nav-res-btn">Resources</a></li>
                <li><a href="#" id="nav-crs-btn">Courses</a></li>
                <li><a href="#" id="nav-post-btn">Posts</a></li>
                <%--<c:if test="${currentUser.getType() == 'Faculty'}">--%>
                <%--<c:set var="zones" value="${currentUser.getType()}" scope="session" />--%>
                <%if(currentUser.getType().getValue() == 1){
                    out.print("<li><a href=\"/phd_students\" id=\"nav-student-btn\">Ph.D. Students</a></li>");
                }%>
                    <%--<li><a href="/phd_students" id="nav-student1-btn"><c:out value="${currentUser.getType()}"/></a></li>--%>
                <%--</c:if>--%>

                <li><a href="/alumni" id="nav-alumni-btn">Alumni</a></li>
            </ul>

            <p class="navbar-text navbar-right">Signed in as <a href="#" class="navbar-link"><%= currentUser.getFirstName() + " " + currentUser.getLastName()%></a></p>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>