<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" media="screen" href="style/css-reset.css" />
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no" />
        <meta name="keywords" content="parana, bahia, rio de janeiro, minas gerais, belo horizonte, aeroporto, aero porto, 
              guarulhos, brasilia, congonhas, taxi 5 estrelas, uber, uber blindado, motorista particular, táxi de luxo, fabio,
              mirandataxi, taxi executivo, taxi blindado, taxi blindado em sao paulo, carros blindado em sao paulo, aluguel 
              carro com motorista, carro blindado com motorista, Fabio Gomes Miranda, hotton cesar de melo gomes, taxi executivo, 
              azera blindado, taxi azera, shielded taxi in sao paulo, armored car in sao paulo, armored cab in BrazilTaxi Luxo,Taxi,
              Taxi Executivo, Taxi Blindado, Taxi Especial, Taxi Azera,Taxis Luxo, TaxiLuxo, Carros Luxo, Taxi Preto, Vans Luxo, Segurança, 
              Transporte, Micro onibus, Safety transfers, Safety, Transfers, Taxi Seguro, Táxi de Luxo Sao Paulo, Veiculos Blindados, Blindado, 
              blindados, Taxi em São Paulo, Taxi em São Paulo Executivo, Taxi em São Paulo de luxo, Taxi luxo em São Paulo, Serviço de 
              taxi blindado em São Paulo, taxi blindado, taxi executivo sao paulo, alugar carros de luxo, carros de luxo com morotista, 
              carro luxo motorista,Taxi executivo, taxis executivos São Paulo ,taxi executivo blindado,taxi blindado, taxi de luxo, taxi 
              de luxo blindado, transporte executivo SP, transporte executivo blindado, transporte seguro,transfer, Transfers com carros blindados, 
              Minivan, Minivan blindada, Van Executiva blindada, Vans, Sprinter Blindada, motoristas bilingüe, motoristas treinados, motoristas trajados,
              motoristas uniformizados,Transport, Transport de grupos, carros importados, aluguel de carros blindados com motorista, aluguel, motorista 
              blindado, carro blindado motorista">
        <link rel="stylesheet" media="screen" href="style/fonts.css" />
        <link rel="stylesheet" media="screen" href="style/style-index.css" />
        <link rel="stylesheet" media="screen" href="style/style-menu-main-index.css" />
        <link rel="stylesheet" media="screen" href="style/style-box-modal-in.css" />
        <link rel="stylesheet" media="screen" href="style/component-menu-hover-max-767px.css" />
        <link rel="stylesheet" media="screen" href="style/style-box-modal-recover.css" />
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css"/>
        <link rel="shortcut icon" type="image/x-icon" href="img/icons/favicon.ico">
        <title>Miranda Taxi</title>
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
                            <li id="li-final" class="dropdown sub-login">                                
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
            <div class="row nopadding">
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 nopadding">
                    <div class="main-bg left-side">
                        <img src="img/windows/title-index.png" alt="MirandaTaxi" class="image-bg" id="picture-text"/>
                        <div id="mobile-pic" class="hidden-lg hidden-md">
                            <img src="img/windows/car-desktop.png" alt="MirandaTaxi" class="image-bg" id="picture-mobile"/>
                        </div>
                        <div class="col-lg-12">
                             <span class="content-index" id="key-text">Para deslocamento com maior agilidade eficiência e segurança, vá de taxi. 
                                Todos os taxis podem andar no corredor de ônibus em São Paulo, assim você não perde tempo no trânsito!
                            </span>
                            <div class="box-buy">
                                <a href="hire-service.jsp" style="text-decoration: none;"><button class="btn-buy">CONTRATAR AGORA</button></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 nopadding">
                    <div class="main-bg right-side">
                        <img src="img/windows/car-desktop.png" alt="Carros Blindados" class="image-bg visible-lg visible-md" id="picture-car"/>
                        <span class="content-index">
                            <div id="contact">
                                <b><a href="https://api.whatsapp.com/send?phone=5511998965109"><img src="img/icons/icon-whats.png" alt="WhatsApp" class="social_img"> (11) 99896-5109</a>  </b><span id="clear-bar">|</span> 
                                <span class="text-email">  contato@mirandataxi.com.br</span>
                            </div>
                        </span>
                    </div>
                </div>
            </div>
            <!--
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
                            <span class="text-email">contato@mirandataxi.com.br</span>
                        </div>
                    </span>
                </div>
                <!--<div id="front-footer"></div>
                    <video autoplay muted loop id="videoprincipal">
                        <source src="style/video/miranda-video-alta.webm" type="video/webm">
			<source src="style/video/miranda-video-alta.mp4" type="video/mp4">
                    </video>-->
                
                <div id="box-modal">
                    <form id="form-in">
                        <div id="div-close"><img src="img/icons/delete-close.png" /></div>
                        <div class="form-group">
                            <input type="email" id="txt-email-in" class="txt-in form-control" name="nEmail" placeholder="minha-conta-email@servico.com" required/>
                        </div>
                        <div class="form-group">
                            <input type="password" id="txt-password-in" class="txt-in form-control" name="nPass" placeholder="Digite sua senha" required/>
                        </div>
                        <div id="box-loader"><img src="img/icons/loader.gif" style="width:50px;"></div>
                        <h5 id="h-alert"><img src="img/icons/attetion.png" />Atenção: login ou senha inválidos.</h5>
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
                        <div id="box-recover-loader"><img src="img/icons/loader.gif" style="width:50px;"></div>
                        <h5 id="h-alert-recover"></h5>
                        <button type="submit" id="btt-go-recover">NOVA SENHA</button>
                    </form>
                </div>
            </div>
        </div> 
        <div id="black-page"></div>
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