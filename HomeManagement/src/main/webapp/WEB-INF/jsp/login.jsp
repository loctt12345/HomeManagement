<%-- 
    Document   : login
    Created on : Dec 27, 2022, 4:44:54 PM
    Author     : loc12345
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/2e0789eef1.js" crossorigin="anonymous"></script>

    </head>
    <body>
        <div class="container col-md-6" 
             style="margin-top: 150px; border: 5px black solid
             ;padding: 2%;border-radius: 5%;">
            <h1 class="text-danger text-center">Đăng nhập</h1>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger col-md-6 offset-md-3">
                    Sai id hoặc mật khẩu.
                </div>
            </c:if>
            <c:url value="/login" var="action"/>
            <form method="post" action="${action}">
                <div class="form-group col-md-6 offset-md-3">
                    <label for="username"><strong>Id của thành viên: </strong></label>
                    <input type="text" id="username" name="username" class="form-control"/>
                </div>
                <div class="form-group col-md-6 offset-md-3">
                    <label for="password"><strong>Mật khẩu: </strong></label>
                    <input type="password" id="password" name="password" class="form-control"/>
                </div>

                <div class="form-group text-center">
                    <input class="btn btn-danger" type="submit" value="Đăng nhập" />
                </div>
            </form>
        </div>
    </body>
</html>
