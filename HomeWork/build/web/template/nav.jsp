<%-- 
    Document   : nav
    Created on : Jan 27, 2016, 10:06:54 AM
    Author     : theba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="light-blue accent-2">
    <div class="nav-wrapper">
        <div class="container">
            <a href="${pageContext.request.contextPath}" class="brand-logo"><img src="${pageContext.request.contextPath}/imgs/itkmitl.png" style="height: 60px;" /></a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="${pageContext.request.contextPath}/index">เพิ่มข้อมูลลูกค้า</a></li>
                <li><a href="${pageContext.request.contextPath}/show">แสดงข้อมูลลูกค้าทั้งหมด</a></li>
            </ul>
        </div>
    </div>
</nav>
