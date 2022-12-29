<%-- 
    Document   : manage
    Created on : Dec 20, 2022, 5:38:36 PM
    Author     : loc12345
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="offset-md-4">Quản lí thành viên</h1>
<div class="row offset-md-1" >
    <form class="col-md-3" action="<c:url value="/manage/money"/>">
        <input type="submit" style="height: 200px" 
               class="btn btn-info col-md-12 func "  value="Quản lí tiền nhà"/>
    </form>
    <form class="col-md-3" action="<c:url value="/manage/addMemberPage"/>">
        <input type="submit" style="height: 200px" 
               class="btn btn-success col-md-12 func" value="Thêm thành viên"/>
    </form>
    <form class="col-md-3" action="<c:url value="/manage"/>">
        <input type="submit" id="update" style="height: 200px" 
               class="btn btn-danger col-md-12 func" value="Đang cập nhật ..."/>
    </form>
</div>

<script>
    document.getElementById("update").addEventListener("click", noty);
    
    function noty() {
        alert("Tao ghi đang cập nhật, sao mày lì như trâu zậy ?");
    }
</script>

<style>
    form {
        margin: 2%;
    }
</style>