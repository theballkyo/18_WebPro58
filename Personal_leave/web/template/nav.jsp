<%-- 
    Document   : nav
    Created on : Jan 27, 2016, 10:06:54 AM
    Author     : theba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Boolean is_login = (Boolean) session.getAttribute("is_login");
%>
<nav class="light-blue accent-2">
    <div class="nav-wrapper">
        <div class="container">
            <a href="${pageContext.request.contextPath}" class="brand-logo"><img src="${pageContext.request.contextPath}/imgs/itkmitl.png" style="height: 60px;" /></a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="${pageContext.request.contextPath}/index.jsp">หน้าแรก</a></li>
                <% if (is_login != null) {%>
                <li><a href="${pageContext.request.contextPath}/logout">ออกจากระบบ</a></li>
                <%  } else { %>
                <li><a href="${pageContext.request.contextPath}/login.jsp">เข้าสู่ระบบ</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
