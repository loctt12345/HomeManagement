<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<div class="container col-md-6" 
     style="margin-top: 25px; margin-bottom: 20px; border: 5px black solid
     ;padding: 2%;border-radius: 5%;">
    <h1 class="text-danger text-center">Đổi mật khẩu</h1>
    <c:if test="${status == 'NotMatchConfirm'}" >
        <div class="alert alert-danger"> Xác nhận mật khẩu không chính xác!!! </div>
    </c:if>
    <c:if test="${status == 'NotMatchCurrentPassword'}" >
        <div class="alert alert-danger">Sai mật khẩu hiện tại!!!</div>
    </c:if>
    <c:if test="${status == 'changPasswordSuccessfully'}" >
        <div class="alert alert-success"> Đổi mật khẩu thành công!!! </div>
    </c:if>
    
    <c:url value="/changePassword" var="action"/>
    <form method="post" action="${action}">
        <div class="form-group col-md-6 offset-md-3">
            <label for="current_password"><strong>Mật khẩu hiện tại: </strong></label>
            <input type="password" id="current_password" name="current_password" class="form-control"/>
        </div>
        <div class="form-group col-md-6 offset-md-3">
            <label for="new_password"><strong>Mật khẩu mới: </strong></label>
            <input type="password" id="new_password" name="new_password" class="form-control"/>
        </div>
        <div class="form-group col-md-6 offset-md-3">
            <label for="new_password_confirm"><strong>Xác nhận mật khẩu mới: </strong></label>
            <input type="password" id="new_password_confirm" name="new_password_confirm" class="form-control"/>
        </div>
        <input type="hidden" name="id" value="${pageContext.request.userPrincipal.name}"/>
        <div class="form-group text-center">
            <input class="btn btn-danger" type="submit" value="Đổi mật khẩu" />
        </div>
    </form>
</div>
<style>
    .top {
        text-align: center;
    }
</style>