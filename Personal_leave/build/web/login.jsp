<%-- 
    Document   : login
    Created on : Jan 27, 2016, 9:38:57 AM
    Author     : theba
--%>
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
            <div class="row">
                <form class="col s6 offset-s3" method="post" action="login">
                    <%
                        if (request.getAttribute("is_error") != null) {
                    %>
                    <p class="flow-text">Username or Password is wrong.</p>
                    <%
                        }
                    %>
                    <p class="flow-text">Login - PLSystem</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">account_circle</i>
                            <input name="username" id="username" type="text" class="validate">
                            <label for="username">Username</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">vpn_key</i>
                            <input name="password" id="password" type="password" class="validate">
                            <label for="password">Password</label>
                        </div>
                    </div>
                    <button class="col s3 offset-s9 waves-effect waves-light orange darken-1 btn"><i class="material-icons left">send</i>Login</button>
                    <p>
                        Username && Password for test.
                        <br>
                        user :: user
                        <br>
                        admin ::admin
                        <br>
                        boss ::boss
                    </p>
                </form>
            </div>
        </div>

        <%@include file="template/footer.jsp" %>
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>
