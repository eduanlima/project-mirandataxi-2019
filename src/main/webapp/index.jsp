<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" media="screen" href="style/css-reset.css" />
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no" />
        <link rel="stylesheet" media="screen" href="style/fonts.css" />
        <link rel="stylesheet" media="screen" href="style/style-index.css" />
        <link rel="stylesheet" media="screen" href="style/style-menu-main-index.css" />
        <link rel="stylesheet" media="screen" href="style/style-box-modal-in.css" />
        <link rel="stylesheet" media="screen" href="style/component-menu-hover-max-767px.css" />
        <link rel="stylesheet" media="screen" href="style/style-box-modal-recover.css" />
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css"/>
        <title>Miranda Taxi Teste Alteração</title>
    </head>
    <body>
        
        <div id="page">
            <nav class="navbar navbar-default topHeader" id="bar-menu-main">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-index" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="index.jsp" title="Home" class="navbar-brand logo">MIRANDATAXI</a>
                    </div>

                    <div class="collapse navbar-collapse" id="navbar-index">
                        <ul class="nav navbar-nav navbar-right container-menu">  
                            <li class="menu"><a href="hire-service.jsp" title="Agende uma corrida" class="link-main">Contratar</a></li>
                            <li class="menu"><a href="services.jsp" title="Conheça nossos serviços" class="link-main">Serviços</a></li>
                            <li class="menu"><a href="contact.jsp" title="" class="link-main">Contato</a></li>
                            <li class="dropdown sub-login">                                
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img class="group-img-log img-logged" src="img/icons/user-logged.png" /><img class="group-img-log img-not-log" src="img/icons/user.png" /><span id="link-in" class="link-main">Login</span><span class="caret"></span></a>
                                <ul class="dropdown-menu">  
                                    <li class="sub-menu area-no-user"><a href="#" id="link-access-in">ENTRAR <img src="img/icons/icon-log-in.png" alt="Entrar" class="icon-in-out"/></a></li>
                                    <li class="sub-menu area-no-user"><a href="register-yourself.jsp">CADASTRAR-SE <img src="img/icons/icon-register.png" alt="Cadastre-se" class="icon-in-out"/></i></a></li>             
                                    <div id="div-user-general" class="area-user">
                                        <img id="img-log" src="img/icons/user.png" />
                                        <h2 id="h-name-user"></h2>
                                    </div>
                                    <li role="separator" class="divider area-user"></li>
                                    <li class="itens-access area-user sub-menu"><a href="dashboard.jsp#profile" class="sub-options">MINHA CONTA</a></li>
                                    <li class="itens-access area-user sub-menu"><a href="dashboard.jsp#races" class="sub-options">MINHAS CORRIDAS</a></li>
                                    <li class="itens-access area-user sub-menu"><a href="" class="sub-options btn_logout" id="logout">SAIR <img src="img/icons/icon-log-out.png" alt="Sair" class="icon-in-out"/></a></li>                           
                                </ul>                                
                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>           
            <div id="page" class="main-page">               
                <div id="contorno-content">
                    <div class="brand-index"><img src="img/icons/logo-miranda-taxi-imagem.png" alt="MIRANDA TAXI"/></div>
                    <h3 class="title-index">Serviço de Taxi Blindado em São Paulo</h3>
                    <span class="content-index">Para deslocamento com maior agilidade eficiência e segurança, Vai de Taxi. 
                        Todos os Taxis podem andar no corredor de ônibus em São Paulo, assim você não perde tempo no trânsito! (vídeo)
                    </span>
                    <span class="content-index">
                        <div id="contact">
                            <a href="https://api.whatsapp.com/send?phone=5511998965109"><img src="img/icons/icon-whats.png" alt="WhatsApp" class="social_img"> (11) 99896-5109</a><br/>
                            <span class="text-email">contato@mirandataxi.com</span>
                        </div>
                    </span>
                </div>
                <div id="front-footer"></div>
                    <video autoplay muted loop id="videoprincipal">
                        <source src="style/video/miranda-video-alta.webm" type="video/webm">
			<source src="style/video/miranda-video-alta.mp4" type="video/mp4">
                    </video>
                <div id="box-modal">
                    <form id="form-in">
                        <div id="div-close"><img src="img/icons/delete-close.png" /></div>
                        <div class="form-group">
                            <input type="email" id="txt-email-in" class="txt-in form-control" name="nEmail" placeholder="minha-conta-email@servico.com" required/>
                        </div>
                        <div class="form-group">
                            <input type="password" id="txt-password-in" class="txt-in form-control" name="nPass" placeholder="Digite sua senha" required/>
                        </div>
                        <h5 id="h-alert"><img src="img/icons/attetion.png" />Atenção, login ou senha inválidos.</h5>
                        <button type="submit" id="btt-go">ENTRAR</button>
                        <a id="re-password" href="">Esqueci minha senha</a>
                    </form>
                </div>
                
                <!-- Box recover -->
                 <div id="box-modal-recover">
                    <form id="form-in-recover">
                        <div id="div-close-recover"><img src="img/icons/delete-close.png" /></div>
                        <div class="form-group">
                            <input type="email" id="txt-recover-email" class="txt-in form-control" name="nEmailRecover" placeholder="minha-conta-email@servico.com" />
                        </div>
                        <h5 id="h-alert-recover"></h5>
                        <button type="submit" id="btt-go-recover">NOVA SENHA</button>
                    </form>
                </div>
            </div>
        </div>        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>   
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="js/min-menu.js"></script>
        <script src="js/box-message-show.js"></script>
        <script src="js/box-modal-index.js"></script>
        <script src="js/check-session.js" charset="UTF-8"></script>
        <script src="js/events-in-out.js"></script>
        <script src="js/events-modal-recover.js"></script>
    </body>
</html>