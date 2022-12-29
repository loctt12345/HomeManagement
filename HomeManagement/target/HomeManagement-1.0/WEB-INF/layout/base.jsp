<%-- 
    Document   : base
    Created on : Dec 18, 2022, 5:32:05 PM
    Author     : loc12345
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/2e0789eef1.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <!--Header -->
            <tiles:insertAttribute name="header" />
            <!--Content-->
            <tiles:insertAttribute name="content" />
            <!--Footer-->
            <tiles:insertAttribute name="footer" />
        </div>

    </body>
</html>
