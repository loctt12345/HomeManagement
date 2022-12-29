<%-- 
    Document   : money
    Created on : Dec 22, 2022, 7:01:55 PM
    Author     : loc12345
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="top">Trang quản lí tiền nhà</h1>


<c:if test="${not empty date}">
    <h3 >Đã đặt ngày tạo mới tự động.<br/> Tiền nhà sẽ được tạo mới vào: 
        <strong style="color: green">
            ${date}
        </strong> </h3>
    <form action="<c:url value="/manage/removeAuto" />">
        <div class="form-group">
            <button type="submit" class="btn btn-danger col-md-2 reset">
                Hủy đặt tự động 
            </button>
        </div>
    </form>
</c:if>

<c:if test="${empty date}">
    <form action="<c:url value="/manage/setAll" />">
        <h3>Tạo mới thủ công: </h3>
        <div class="form-group">
            <button type="submit" class="btn btn-danger col-md-2 reset">
                Tạo mới tiền nhà   <i class="fa-solid fa-rotate-right"></i>
            </button>
        </div>
    </form>


    <form action="<c:url value="/manage/setAuto"/>">
        <h3>Hoặc đặt lịch tạo mới tự động: </h3>
        <div class="form-group col-md-4 second">
            <div class="input-group">
                <input class="form-control py-2 border-right-0 border" type="date" name="date">
                <span class="input-group-append ml-n1">
                    <div class="input-group-text bg-transparent"><i class="fa fa-calendar-alt"></i></div>
                </span>
            </div>
            <button type="submit" class="btn btn-danger offset-md-4 auto">Đặt tự động</button>
        </div>
    </form>
</c:if>
    
<style>

    h1[class$="top"] {
        text-align: center;
    }

    .input-group {
        margin-bottom: 2%;

    }

    .second {
        border: 1px gray solid !important;
        border-radius: 4%;
        padding: 1%;
        margin-bottom: 3%;
    }

</style>
