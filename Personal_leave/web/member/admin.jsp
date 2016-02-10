<%-- 
    Document   : admin
    Created on : Feb 10, 2016, 9:37:43 AM
    Author     : Administrator
--%>
<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Customer cus = (Customer) request.getAttribute("customer");   
%>
<!DOCTYPE html>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/materialize.min.css"  media="screen,projection"/>

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>

    <body>
        <jsp:include page="/template/nav.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col s6 offset-s3" >
                    <p class="flow-text">Welcome - <% out.write(cus.getfName()); %></p>
                    <p class="flow-text">Role ID :: <% out.write(String.valueOf(cus.getType()));%> | <% out.write(cus.getRoleName()); %></p>
                </div>
            </div>
        </div>

        <jsp:include page="/template/footer.jsp" />
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/materialize.min.js"></script>
    </body>
</html>