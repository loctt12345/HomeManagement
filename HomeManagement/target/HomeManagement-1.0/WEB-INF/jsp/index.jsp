<%-- 
    Document   : index
    Created on : Dec 18, 2022, 5:27:02 PM
    Author     : loc12345
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasAnyAuthority('admin')">
    <h1 class="font-weight-bold" style="text-align: center">Thành Viên Nhà</h1>


    <form action="" style="margin-bottom: 2%">
        <div class="row offset-md-2"> 
            <input type="text" class="form-control col-md-8" 
                   placeholder="Nhập tên thành viên" name="kw" value="${kw}"/>
            <input type="submit" class="btn btn-success col-md-1" 
                   style="margin-left: 1%" value="Tim"/>

        </div>
    </form>
    <div class="offset-md-5">
        <button type="button" id="filter" style="margin-left: 1%" class="btn btn-info">
            Chưa đóng tiền nhà
        </button>
    </div>


    <c:if test="${not empty memberList}">
        <div class="row tags">
            <c:forEach var="member" items="${memberList}">
                <div class="media border col-md-3 tag"
                     <c:if test="${member.homeRole == 'Chủ nhà'}"> 
                         style="background-color: #b3b303"
                         id="green"
                     </c:if> 
                     <c:if test="${member.isPaying}">
                         style="background-color: #63cf7c"
                         id="green"
                     </c:if>
                     <c:if test="${not member.isPaying}">
                         style="background-color: #e72727"
                         id="red"
                     </c:if>
                     >
                    <div class="">
                        <img src="https://i.etsystatic.com/24010889/r/il/df7daf/2445258956/il_fullxfull.2445258956_35fl.jpg"
                             alt="John Doe" class="mr-3 mt-3 rounded-circle" style="width:60px;display: inline-block">
                        <div class="media-body" style="display: inline-block">
                            <c:url value="/info?id=${member.id}" var="url"/>
                            <a href="${url}" style="color:white">
                                <h4>${member.fullName} - ${member.homeRole}</h4>
                            </a>
                        </div>
                    </div>
                    <button type="button" class="btn btn-info room">Phòng:${member.roomID}</button>
                </div>
            </c:forEach>
        </div>        

        <ul class="pagination justify-content-center" style="margin:20px 0">
            <li class="page-item
                <c:if test="${current_page == 1}">
                    disabled 
                </c:if>">
                <a class="page-link" 
                   href="<c:url 
                       value="/?page=${Math.max(current_page - 1, 1)}" />"> Previous</a>
            </li>
            <c:forEach begin="1" end="${Math.ceil(number_member / max_page)}" var="i">
                <li class="page-item"><a class="page-link" href="<c:url value="/?page=${i}"/>">${i}</a></li>
                </c:forEach>

            <li class="page-item
                <c:if test="${current_page == Math.ceil(number_member / max_page)}">
                    disabled
                </c:if>
                "><a class="page-link" href="<c:url 
                     value="/?page=${Math.min(current_page + 1, 
                                     Math.round(Math.ceil(number_member / max_page)))}" />">
                    Next</a></li>
        </ul>
    </c:if>

    <c:if test="${empty memberList}">
        <h5 class="">No information!!!</h5>
    </c:if>

    <script>
        isFilter = true
        document.getElementById("filter").addEventListener("click", filter);

        function filter() {
            const collection = document.getElementsByClassName("media border col-md-3 tag");
            for (let i = 0; i < collection.length; ++i) {
                if (isFilter) {
                    if (collection[i].id === "green")
                        collection[i].style.display = "none";
                } else {
                    collection[i].style.display = "flex";
                }
            }
            if (isFilter)
                isFilter = false;
            else
                isFilter = true;
        }
    </script>    

    <style>
        .room {
            position: absolute;
            right: 2px;
            margin: 2%;
        }

        div[class$="tags"] {
            margin-left: 2%;
        }

        div[class$="tag"] {
            margin-top: 2%;
            margin-right: 1%;
            margin-left: 5%;
            margin-bottom: 1%;
            height: 150px;
            border-radius: 4%;
            border-bottom: 3px solid !important;
            border-left: 2px solid !important;
            border-right: 1px solid !important;
            border-top: 1px solid !important;
            /*        width: 300px;
                    
                    display: inline-block;
                    padding: 1%;*/
        }

    </style>
</sec:authorize>

<sec:authorize access="hasAnyAuthority('user')">

    <h1 class="offset-md-4">Thông tin cá nhân</h1>

    <div class="media">
        <img src="https://i.etsystatic.com/24010889/r/il/df7daf/2445258956
             /il_fullxfull.2445258956_35fl.jpg" class="align-self-center mr-3" 
             style="width:385px;margin-right: 4% !important;">
        <div class="media-body">
            <h4>${member.fullName} (${member.homeRole}) 

            </h4>
            <c:if test="${member.isPaying}">
                <p style="color: green">Đã đóng tiền nhà!!!!</p>
            </c:if>
            <c:if test="${not member.isPaying}">
                <p style="color: red;">Chưa đóng tiền nhà.</p>
            </c:if>
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
    <style>
        .media {
            margin: 5%;
        }
    </style>
</sec:authorize>