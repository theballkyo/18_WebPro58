<%-- 
    Document   : index
    Created on : Jan 27, 2016, 9:35:50 AM
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
            <p class="flow-text">สร้าง User ใหม่ !</p>
            <%
                if (request.getAttribute("is_error") != null) {
                    if ((Boolean) request.getAttribute("is_error") == true) {
            %>
            <p class="flow-text">Error: Can't Insert Data</p>
            <%
            } else {
            %>
            <p class="flow-text">Insert Data successfully</p>
            <%
                    }
                }
            %>
            <div class="row">
                <form class="col s12" method="post" action="index">
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="firstname" id="first_name" type="text" class="validate">
                            <label for="first_name">First Name</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="lastname" id="last_name" type="text" class="validate"> 
                            <label for="last_name">Last Name</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s4">
                            <input name="company" id="company" type="text" class="validate">
                            <label for="company">Company</label>
                        </div>
                        <div class="input-field col s4">
                            <input name="mobile" id="mobile" type="text" class="validate"> 
                            <label for="mobile">Mobile</label>
                        </div>
                        <div class="input-field col s4">
                            <input name="email" id="email" type="email" class="validate"> 
                            <label for="email">Email</label>
                        </div>

                    </div>

                    <div class="row">
                        <div class="input-field col s12">
                            <textarea name="addr" id="addr" class="materialize-textarea"></textarea>
                            <label for="addr">Address</label>
                        </div>
                    </div>
                    <button class="col s3 offset-s9 waves-effect waves-light orange darken-1 btn"><i class="material-icons left">send</i>Register</button>
                </form>
            </div>
        </div>

        <%@include file="template/footer.jsp" %>
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>