<%-- 
    Document   : memberAddition
    Created on : Dec 23, 2022, 6:01:37 PM
    Author     : loc12345
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="top">Thêm thành viên</h1>

<c:url var="action" value="/manage/addMember" />
<form:form method="post" action="${action}" modelAttribute="member" cssClass="col-md-6 offset-md-3" >
    <div class="form-group">
        <label for="fullName">Tên thành viên: </label>
        <form:input type="text" id="fullName" path="fullName" 
                    cssClass="form-control"  />
    </div>

    <div class="form-group">
        <label for="roomID">Mã phòng <strong class="text text-danger">(P + số phòng 3 chữ số, ví dụ P001)</strong>: </label>
        <form:input type="text" id="roomID" path="roomID" cssClass="form-control" />
    </div>
        
    <div class="form-group">
        <label for="role">Vai trò: </label>
        <form:select id="role" path="homeRole" cssClass="form-control" >
            <option>Thành viên</option>
            <option>Bà chủ</option>
            <option>Ông chú</option>
        </form:select>
    </div>

    <div class="form-group offset-md-4">
        <input type="submit" value="Thêm thành viên" class="btn btn-danger" />
    </div>    
</form:form>

<style>
    .top {
        text-align: center;
    }
</style>
