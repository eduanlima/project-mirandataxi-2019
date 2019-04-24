<%-- 
    Document   : dashboard
    Created on : 09/01/2018, 17:17:48
    Author     : Familia
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no" />
        <link rel="stylesheet" media="screen" href="style/fonts.css" />
        <link rel="stylesheet" media="screen" href="style/style-dashboard.css" />
        <link rel="stylesheet" media="screen" href="style/style-menu-main.css" />
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css"/>             
        <link rel="stylesheet" media="screen" href="style/style-box-modal-in.css" />
        <link rel="stylesheet" media="screen" href="style/style-box-modal-in.css" />
        <link rel="stylesheet" media="screen" href="style/style-profile.css" />
        <link rel="stylesheet" href="style/style-races.css">
        <link rel="stylesheet" media="screen" href="style/style-message.css"/>
        <link rel="stylesheet" href="style/style-footer.css"/>
        <link rel="stylesheet" media="screen" href="style/style-box-modal-recover.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/icons/favicon.ico">
        <title>Minha área</title>
    </head>
    <body>
        <%@page language="java" %>
        <%
            String userName = (String) session.getAttribute("userName");
            String redirectURL = "index.jsp";
            if (userName == null) {
                response.sendRedirect(redirectURL);
            }

        %>
        <%@include file="header.jsp"%>

        <div class="container-fluid">            
            <div class="row profile">
                <div class="col-lg-2 col-md-3 col-lg-push-2 col-md-push-1 nopadding">
                    <div class="profile-sidebar">
                        <!-- SIDEBAR USERPIC -->                        
                        <div class="profile-userpic">
                            <img src="img/icons/user.png" class="img-responsive" alt="Meu perfil">
                        </div>
                        <!-- END SIDEBAR USERPIC -->
                        <!-- SIDEBAR USER TITLE -->
                        <div class="profile-usertitle">
                            <div class="profile-usertitle-name">
                                <%=userName%>
                            </div>                            
                        </div>
                        <!-- END SIDEBAR USER TITLE -->
                        <!-- SIDEBAR MENU -->
                        <div class="profile-usermenu">                   
                            <ul class="nav">  
                                <li id="profile">
                                    <a href="profile.jsp">
                                        <i class="glyphicon"><img src="img/icons/user-logged.png" alt="Perfil" class="icones"></i>
                                        Minha conta </a>
                                </li>
                                <li id="races">
                                    <a href="races.jsp">
                                        <i class="glyphicon"><img src="img/icons/car-icon.png" alt="Corridas" class="icones"></i>
                                        Corridas </a>
                                </li>
                            </ul>
                        </div>
                        <!-- END MENU -->
                    </div>
                </div>
                <div class="col-lg-7 col-md-7 col-lg-push-2 col-md-push-1">
                    <div class="profile-content" id="content">                        
                    </div>
                </div>
            </div>
        </div>
        <div class="overlay"></div><!--/ div sombra que será mostrada quando uma mensagem for exibida-->
        
        <%@include file="footer.jsp" %>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
        <script src="js/events-dashboard.js"></script>
    </body>
</html>
