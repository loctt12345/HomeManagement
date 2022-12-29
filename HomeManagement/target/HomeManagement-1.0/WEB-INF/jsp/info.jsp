<%-- 
    Document   : info
    Created on : Dec 20, 2022, 4:58:09 PM
    Author     : loc12345
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<h1 class="offset-md-4">Thông tin cá nhân</h1>



<c:if test="${not empty member}">
    <c:if test="${member.homeRole == 'Chủ nhà'}"> 
        <script>
            alert("Mày là chủ nhà mà, vô đây làm cc gì? Lo coi thành viên kìa.");
        </script>
    </c:if> 
    <c:if test="${member.homeRole != 'Chủ nhà'}"> 
        <div class="media">
            <img src="https://i.etsystatic.com/24010889/r/il/df7daf/2445258956
                 /il_fullxfull.2445258956_35fl.jpg" class="align-self-center mr-3" 
                 style="width:385px;margin-right: 4% !important;">
            <div class="media-body">
                <h4>${member.fullName} 
                    <a 
                        style="font-size: 15px; font-style: italic;
                        text-decoration: underline;" 
                        href="<c:url 
                            value="/updatePage?member_id=${member.id}" />"
                        >(Cập nhật)</a>

                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">
                        Xóa thành viên
                    </button>

                    <!-- The Modal -->
                    <div class="modal fade" id="myModal">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h5 class="modal-title">Bạn có chấp nhận xóa ${member.fullName} không?</h5>
                                    <button type="button" class="close" data-dismiss="modal">×</button>
                                </div>

                                <!-- Modal body -->
                                <div class="modal-body">
                                    <h6>
                                        Người ta thiếu tiền nhà thì nới nới cho người ta cái anh chai.
                                    </h6>
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <form action="<c:url value="/deleteMember"/>">
                                        <input type="submit" class="btn btn-success" 
                                                value="Xóa mẹ đi"/>
                                        <input type="hidden" name="id" value="${member.id}" />
                                        <button type="button" 
                                                class="btn btn-danger" 
                                                data-dismiss="modal">Hủy</button>
                                    </form> 
                                </div>
                            </div>
                        </div>
                    </div>
                </h4>

                <c:if test="${member.isPaying}">
                    <p style="color: green">Đã đóng tiền nhà!!!!</p>
                    <a href="<c:url value="/pay?rollback=true&id=${member.id}" />" 
                       style="color: red; text-decoration: underline;
                       font-style: italic" >Hủy đóng tiền nhà.</a>
                </c:if>
                <c:if test="${not member.isPaying}">
                    <p style="color: red;">Chưa đóng tiền nhà.</p>
                    <form action="<c:url value="/pay"/>">
                        <input type="hidden" name="id" value="${member.id}" />
                        <input type="submit" class="btn btn-success"  
                               value="Xác nhận đóng tiền nhà"/>
                    </form>
                </c:if>
                <br/>
                <br/>
                <h5>Lịch sử đóng tiền:</h5>
                <br/>
                <table class="table">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Ngày đóng tiền</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="row" items="${listPayingHistory}"
                                   varStatus="x">
                            <tr>
                                <td>${x.count}</td>
                                <td>${row.payingDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if> 
</c:if>

<c:if test="${empty member}">
    <h5>No infomation</h5>
</c:if>


<style>
    .media {
        margin: 5%;
    }
</style>