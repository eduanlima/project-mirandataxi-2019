<%-- 
    Document   : Contato do Usuário
    Created on : 08/12/2017, 22:19:52
    Author     : Rafael Alves
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no" />
        <link rel="stylesheet" media="screen" href="style/css-reset.css" />
        <link rel="stylesheet" media="screen" href="style/style-contact.css" />    
        <link rel="stylesheet" media="screen" href="style/fonts.css" />
        <link rel="stylesheet" media="screen" href="style/style-service.css" /> 
        <link rel="stylesheet" media="screen" href="style/style-footer.css" />
        <link rel="stylesheet" media="screen" href="style/style-menu-main.css" />
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css"/>             
        <link rel="stylesheet" media="screen" href="style/style-box-modal-in.css" />
        <link rel="stylesheet" media="screen" href="style/style-box-modal-recover.css" /> 
        <link rel="shortcut icon" type="image/x-icon" href="img/icons/favicon.ico">
        
        <style>
            .menu-contact{background:#565656; }
        </style>
        <title>Miranda Taxi</title>
    </head>
    </head>
    <body>
        <div id="page">
            <%@ include file="header.jsp" %>
            <div class="container">
                <div id='box-contato'>
                    <div class='top-contato'>
                        <h3>Contato</h3>
                        <label>Sinta-se a vontade para entrar em contato, e solicitar um orçamento</label>
                    </div>
                    <article role="main"> 
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                <form id="form-register">
                                    <h2 class="title-main">Deixe-nos uma mensagem</h2>
                                    <div id="div-name" class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <label class="lbl-standard" for="txt-name">Nome <span class="required">(*)</span></label>
                                        <input type="text" id="txt-name" name="name" placeholder="Nome completo" class="form-control cmp-text"/>
                                    </div>
                                    <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <label for="txt-phone" class="lbl-standard">Telefone <span class="required">(*)</span></label>
                                        <input type="text" id="txt-phone" name="phone" placeholder="(00)00000-0000" class="form-control cmp-text"/>
                                    </div>

                                    <div id="div-email" class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <label for="txt-email" class="lbl-standard">E-mail <span class="required">(*)</span></label>
                                        <input type="text" id="txt-email" name="email" class="form-control cmp-text" placeholder="minha-conta-email@servico.com"/>
                                    </div>                    
                                    <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <label for="message" class="lbl-standard">Mensagem <span class="required">(*)</span></label>
                                        <textarea class="message form-control cmp-text"  id="message" placeholder="Deixe sua mensagem..."></textarea>   
                                    </div>
                                    
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="text-align:left;">(*) Campos obrigatórios</div>
                                    
                                    <div class="box-btt">
                                        <button id="btt-finish" type="submit" name="nBttFinish">Enviar</button>                                
                                    </div>
                                </form>
                            </div>
                            <div class="col-lg-6 col-md-6 visible-lg visible-md ">
                                <div id="box-img-contact">
                                    <img src="img/cars/bmw_drivers.jpeg" alt="Deixe sua mensagem" id="img-contact"/>
                                </div>
                            </div>
                        </div>
                    </article>  
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="sendMessageBox" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header nopadding">                            
                        <span class="alert" id="box-alerta"><strong>Mensagem</strong></span>
                    </div>
                    <div class="modal-body" id="infoMensagem">
                        
                    </div>
                    <div class="row" style="text-align:center;">
                    <button type="button" class="btn-standard" data-dismiss="modal">FECHAR</button>
                    </div>
                </div>
            </div>
        </div>
        
         <%@include file="footer.jsp"%><!--rodapé-->
        
        <script src="js/box-message-show.js"></script>
        <script src="js/jquery.mask.min.js"></script>
        <script src="js/events-service.js"></script>
        <script src="js/all-masks.js"></script>
        <script src="js/form-cleaner.js"></script>
        <script src="js/send-email.js"></script>
    </body>
</html>
