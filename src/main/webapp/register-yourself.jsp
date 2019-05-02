<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" media="screen" href="style/css-reset.css" />
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no" />
        <link rel="stylesheet" media="screen" href="style/fonts.css" />
        <link rel="stylesheet" media="screen" href="style/style-menu-main.css" />
        <link rel="stylesheet" media="screen" href="style/style-box-modal-in.css" />
        <link rel="stylesheet" media="screen" href="style/style-register-yourself.css" />
        <link rel="stylesheet" media="screen" href="style/style-message.css" />
        <link rel="stylesheet" media="screen" href="style/style-radios.css" />
        <link rel="stylesheet" media="screen" href="style/style-footer.css" />
        <link rel="stylesheet" media="screen" href="style/style-box-modal-recover.css" />
        <link rel="stylesheet" media="screen" href="style/style-menu-main.css" />
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css"/>
        <link rel="shortcut icon" type="image/x-icon" href="img/icons/favicon.ico">
        <title>Miranda Taxi</title>               
    </head>
    <body>
        <div id="page">
            <%@include file="header.jsp"%>
            <div class="container">
                <form id="form-register">
                    <h1 class="title-main">Crie uma conta e aproveite nossos serviços</h1>
                    <div class="col-lg-10 col-lg-push-1">
                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                            <label class="lbl-standard" for="txt-name">Nome</label>
                            <input type="text" id="txt-name" name="name" placeholder="Insira seu nome completo" class="form-control"/>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                            <label for ="txt-email" class="lbl-standard">E-mail (*)</label>
                            <input type="email" id="txt-email" name="email" class="form-control" placeholder="minha-conta-email@servico.com"/>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                            <label for ="txt-email" class="lbl-standard">Confirmar E-mail</label>
                            <input type="email" id="txt-confirmaemail" name="confirmaemail" class="form-control" placeholder="confirma-email@servico.com"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                            <label for="txt-phone-one" class="lbl-standard">Telefone</label>
                            <input type="tel" name="phone-one" placeholder="(00)00000-0000" class="form-control" id="txt-phone-one"/>
                        </div>

                        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                            <label for="txt-phone-one" class="lbl-standard">Telefone(opcional)</label>
                            <input type="tel" name="phone-two" placeholder="(00)00000-0000" class="form-control" id="txt-phone-two"/>
                        </div>  

                        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                            <label for="txt-phone-one" class="lbl-standard">Telefone (opcional)</label>
                            <input type="tel" name="phone-tree" placeholder="(00)00000-0000" class="form-control" id="txt-phone-tree"/>
                        </div>
                    </div>                        
                    <div class="row">
                        <div class="col-lg-4 col-md-6 col-sm-6" id="div-space-pass">
                            <label for="txt-pass" class="lbl-standard">Senha (**)</label>
                            <input type="password" id="txt-pass" name="password" class="form-control" placeholder="Insira uma senha"/>
                        </div>

                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <label for="txt-conf-pass" class="lbl-standard">Confirme sua senha</label>
                            <input type="password" id="txt-conf-pass" class="form-control" placeholder="Digite novamente a senha" />
                        </div>
                    </div>
                    <div class="row">    
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="text-align:left; padding: 10px 17px;">(*) Email utilizado para processo de compra | (**) Senha de 8 caracteres</div>
                    </div>
                    <div class="row">
                        <div class="box-btt">
                            <button id="btt-finish" type="submit" name="nBttFinish">Finalizar</button>
                            <span class="gif"><img src="img/icons/loader.gif" alt="Processando..."/></span>
                        </div>
                    </div>
                    </div>
                </form>
            </div>
            <!--<div id="box-modal">
                <form id="form-in">
                    <div id="div-close"><img src="img/icons/delete-close.png" /></div>
                    <input type="email" id="txt-email-in" class="txt-in" name="nEmail" placeholder="minha-conta-email@servico.com" />
                    <input type="password" id="txt-password-in" class="txt-in" name="nPass" placeholder="Digite sua senha" />
                    <h5 id="h-alert"><img src="img/icons/attetion.png" />Atenção, login ou senha inválidos.</h5>
                    <button type="submit" id="btt-go">Entrar</button>
                    <a id="re-password" href="">Esqueci minha senha</a>
                </form>
            </div>-->
            <div id="box-message">
                <div class="col-lg-12 col-md-12">
                    <h1 id="txt-main" class="h-message"></h1>
                    <h2 id="txt-second" class="h-message"></h2>
                    <a href="index.jsp" id="btn-redirect"><button class="btn">Continuar</button></a>
                    <button id="btt-ok" class="btn">OK</button>
                </div>
            </div>               
            <div id="black-page"></div>
        </div>
        
        <%@include file="footer.jsp"%><!--rodapé-->  
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>-->
        <script src="js/box-message-show.js"></script>
        <script src="js/validation-yourself.js" charset="UTF-8"></script>
        <script src="js/events-register-yourself.js?v=" charset="UTF-8"></script>
        <script src="js/jquery-latest.min.js"></script>
        <script src="js/jquery.mask.min.js"></script>
        <script src="js/all-masks.js"></script>        
        <!--<script src="js/check-session.js" charset="UTF-8"></script>-->
    </body>
</html>