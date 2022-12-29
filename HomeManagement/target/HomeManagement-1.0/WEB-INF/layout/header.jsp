<%-- 
    Document   : header
    Created on : Dec 18, 2022, 5:31:36 PM
    Author     : loc12345
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/" var="home" />
<c:url value="/contact" var="contact" />
<c:url value="/manage" var="manage" />

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${home}">Quản lí nhà</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${home}">Trang chủ<span class="sr-only">(current)</span></a>
            </li>
            <sec:authorize access="hasAnyAuthority('admin')">
                <li class="nav-item active">
                    <a class="nav-link" href="${manage}">Quản lí<span class="sr-only">(current)</span></a>
                </li>
            </sec:authorize>

            <li class="nav-item">
                <a class="nav-link" href="${contact}">Liên hệ</a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/changePasswordPage"/>">Đổi mật khẩu</a>
            </li>
            <li class="nav-item" style="position: absolute;right: 20px;top: 15px;">
                <p style="display: inline;margin-right: 20px;">Xin chào, ${pageContext.request.userPrincipal.name}</p>
                
                <a style="display: inline" class="nav-link" href="<c:url value="/logout"/>">Đăng xuất</a>
            </li>
        </ul>
    </div>
</nav>
