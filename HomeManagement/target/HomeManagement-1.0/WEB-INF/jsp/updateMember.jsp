<%-- 
    Document   : updateMember
    Created on : Dec 26, 2022, 12:18:18 PM
    Author     : loc12345
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<h1 class="top">Cập nhật thành viên</h1>

<c:url var="action" value="/updateMember" />
<form:form method="post" action="${action}" modelAttribute="member" cssClass="col-md-6 offset-md-3" >
    <div class="form-group">
        <label for="fullName">Tên thành viên: </label>
        <form:input type="text" id="fullName" path="fullName" 
                    cssClass="form-control"  value="${old_member.fullName}"/>
    </div>

    <div class="form-group">
        <label for="roomID">Mã phòng <strong class="text text-danger">(P + số phòng 3 chữ số, ví dụ P001)</strong>: </label>
        <form:input type="text" id="roomID" path="roomID" 
                    cssClass="form-control" value="${old_member.roomID}" />
    </div>
    
    <div class="form-group">
        <label for="role">Vai trò: </label>
        <form:select id="role" path="homeRole" cssClass="form-control"  >
            <option <c:if test="${old_member.homeRole == 'Thành viên'}"> 
                    selected </c:if>>Thành viên</option>
            <option <c:if test="${old_member.homeRole == 'Bà chủ'}"> 
                    selected </c:if>>Bà chủ</option>
            <option <c:if test="${old_member.homeRole == 'Ông chú'}"> 
                    selected </c:if>>Ông chú</option>
        </form:select>
    </div>
    
    <form:input type="hidden" path="id" value="${old_member.id}" />  
    
    <div class="form-group offset-md-5">
        <input type="submit" value="Cập nhật" class="btn btn-danger" />
    </div>    
</form:form>

<style>
    .top {
        text-align: center;
    }
</style>
