<%-- 
    Document   : dashdriver
    Created on : 16/04/2018, 17:26:49
    Author     : Familia
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no" />
        <link rel="stylesheet" media="screen" href="style/fonts.css" />
        <link rel="stylesheet" media="screen" href="style/style-dashdriver.css" />
        <link rel="stylesheet" media="screen" href="style/style-menu-main.css" />
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css"/>             
        <link rel="stylesheet" media="screen" href="style/style-box-modal-in.css" />

        <link rel="stylesheet" media="screen" href="style/style-box-modal-in.css" />
        <link rel="stylesheet" media="screen" href="style/style-message.css"/>
        <link rel="stylesheet" href="style/style-footer.css"/>
        <link rel="shortcut icon" type="image/x-icon" href="img/icons/favicon.ico">
        <title>Minha área - Motorista</title>
    </head>
    <body>
        <%
            String driver = (String) session.getAttribute("idDriver");
            String redirectURL = "driver.jsp";
            if (driver == null) {
                response.sendRedirect(redirectURL);
            }
        %>
        <%@page language="java"%>
        
        <div class="container-fluid" id="driver-content">
            <div class="row driver">
                <div class="col-lg-2 col-md-3 col-lg-push-1 col-md-push-1 nopadding">
                    <div class="driver-sidebar">
                        <!-- SIDEBAR USERPIC -->                        
                        <!--<div class="driver-userpic">
                            <img src="img/icons/user.png" class="img-responsive" alt="Meu perfil">
                        </div>-->
                        <!-- END SIDEBAR USERPIC -->
                        <!-- SIDEBAR USER TITLE -->
                        <div class="driver-usertitle">
                            <div class="driver-usertitle-name">
                                Motorista
                            </div>                            
                        </div>
                        <!-- END SIDEBAR USER TITLE -->
                        <!-- SIDEBAR MENU -->
                        <div class="driver-menu">                   
                            <nav class="container-mn">
                                <ul class="nav-mini">
                                    <li class="each-class active" id="all">
                                        <span class="car-name">Todos</span>
                                    </li>
                                    <li class="each-class" id="ftc">
                                        <span class="car-name">First Class</span>
                                    </li>
                                    <li class="each-class" id="sdc">                               
                                        <span class="car-name">Shield Class</span>
                                    </li>
                                    <li class="each-class" id="ecc">
                                        <span class="car-name">Executive Class</span>
                                    </li>
                                    <li class="each-class" id="svc">
                                        <span class="car-name">SUV Class</span>
                                    </li>
                                    <li class="each-class" id="eyc">
                                        <span class="car-name">Economy Class</span>
                                    </li>
                                </ul>
                            </nav>
                            <div class="box-exit">
                                <button id="btn-exit">Sair</button>
                            </div>
                        </div>
                        <!-- END MENU -->
                    </div>
                </div>
                <div class="col-lg-8 col-md-7 col-lg-push-1 col-md-push-1">
                    <div id="filtros" class="row">
                        <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                            <label for="codigo" class="lbl-standard">Código</label>
                            <input type="text" id="codigo" name="codigo" class="form-control searching" placeholder="Código" onkeyup="search(this)"/>
                        </div>
                        <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                            <label for="data" class="lbl-standard">Data</label>
                            <input type="text" id="data" name="data" class="form-control searching" placeholder="Data" onkeyup="search(this)"/>
                        </div>
                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                            <label for="cliente" class="lbl-standard">Cliente</label>
                            <input type="text" id="cliente" name="cliente" class="form-control searching" placeholder="Cliente" onkeyup="search(this)"/>
                        </div>
                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                            <label for="passageiro" class="lbl-standard">Passageiro</label>
                            <input type="text" id="passageiro" name="passageiro" class="form-control searching" placeholder="Passageiro" onkeyup="search(this)"/>
                        </div>
                        <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                            <button class="btn" id="btnsearch">Pesquisar</button>
                        </div>
                    </div>
                    
                    <div class="driver-content" id="content"></div>
                </div>
            </div>

            <div class="row">
                <div id="boxCancel">     
                    <header><h5>Tem certeza que deseja cancelar?</h5></header>
                    <div class="optionButton">            
                        <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12"><button type="button" id="accept_no" class="btn_option">Não</button></div>
                        <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12"><button type="button" id="accept_yes" class="btn_option">Sim</button></div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div id="boxConcluir">     
                    <header><h5>Tem certeza que deseja concluir a corrida?</h5></header>
                    <div class="optionButton">            
                        <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12"><button type="button" id="conclude_no" class="btn_option">Não</button></div>
                        <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12"><button type="button" id="conclude_yes" class="btn_option">Sim</button></div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div id="boxLastStep">     
                    <header><h5 id="mensagemConfirma"></h5></header>
                    <div class="optionButton">            
                        <div class="col-lg-4 col-md-12 col-sm-12 col-xs-12"><button type="button" class="btn_option" id="cancelarCancel">Cancelar</button></div>
                        <div class="col-lg-4 col-md-12 col-sm-12 col-xs-12"><button type="button" class="btn_option" id="cancelNo" onclick="cancelarCorrida(this, 'no')">Não</button></div>
                        <div class="col-lg-4 col-md-12 col-sm-12 col-xs-12"><button type="button" class="btn_option" id="cancelYes" onclick="cancelarCorrida(this, 'yes')">Sim</button></div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div id="frm-race">
                    <span id="frm-title" class="frm-line">Mensagem de retorno</span>
                    <span id="frm-content" class="frm-line"></span>
                    <button id="btn-close">Confirmar</button>
                </div>
            </div>
            <div class="overlay"></div><!--/ div sombra que será mostrada quando uma mensagem for exibida-->      
        </div>               
        <%@include file="footer.jsp" %>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
        <script src="js/events-dashdriver.js" ></script>
        <script>
            $("#btn-exit").click(function(){
                    $.ajax({
                    type: "post",
                    url: "InvalidateDriver",
                    success: function(){
                       location.href="driver.jsp";
                    }
                });
            });
        </script>
    </body>
</html>
