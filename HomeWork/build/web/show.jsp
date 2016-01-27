<%-- 
    Document   : show
    Created on : Jan 27, 2016, 10:25:06 PM
    Author     : theba
--%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>

    <body>
        <jsp:include page="/template/nav.jsp"/>

        <div class="container">
            <p class="flow-text">รายการ User</p>
            <div class="row">
                <table class="striped">
                    <thead>
                        <tr>
                            <th data-field="id">ID</th>
                            <th data-field="fName">First Name</th>
                            <th data-field="lName">Last Name</th>
                            <th data-field="company">Company</th>
                            <th data-field="mobile">Mobile</th>
                            <th data-field="email">Email</th>
                            <th data-field="address">address</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
                            ResultSet rs = (ResultSet) request.getAttribute("customers");

                            while (rs.next()) {
                        %>
                        <tr>
                            <td><% out.write(String.valueOf(rs.getInt("id"))); %></td>
                            <td><% out.write(rs.getString("firstname")); %></td>
                            <td><% out.write(rs.getString("lastname")); %></td>
                            <td><% out.write(rs.getString("company")); %></td>
                            <td><% out.write(rs.getString("mobile")); %></td>
                            <td><% out.write(rs.getString("email")); %></td>
                            <td><% out.write(rs.getString("address")); %></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

        <%@include file="template/footer.jsp" %>
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>