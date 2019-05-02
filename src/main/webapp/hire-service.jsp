<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" media="screen" href="style/css-reset.css" />
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no" />
        <link rel="stylesheet" media="screen" href="style/fonts.css" />
        <link rel="stylesheet" media="screen" href="style/style-hire-service.css" />
        <link rel="stylesheet" media="screen" href="style/style-footer.css" />
        <link rel="stylesheet" media="screen" href="style/style-box-modal-in.css" />
        <link rel="stylesheet" media="screen" href="style/style-menu-main.css" />
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css"/>   
        <link rel="stylesheet" media="screen" href="style/style-box-modal-recover.css" />  
        <link rel="shortcut icon" type="image/x-icon" href="img/icons/favicon.ico">
        <style>
            .menu-register-service{background:#565656; }
        </style>
        <title>Miranda Taxi</title>
    </head>

    <body>
        <div id="page">
            <%@ include file="header.jsp"%>
            <div id="galeria"> 
                <a id="prev" href="#"></a>
                <div class="gallery" id="slides">                                
                    <ul id="slideMaster">
                        <li class="info-class slide">
                            <img src="img/cars/bmw-528i-m-sport-max.jpg" alt="BMW"/>
                            <h1 class="car-name">First Class</h1>
                            <h2 class="class-car">BMW 528i Sport e Mercedes Classe E250</h2>
                            <p class="info-car">Veículo blindado</p>
                            <div>
                                <h3>PREÇO DO SERVIÇO</h3>
                                <h4>Preço por hora: R$ 250,00*</h4>
                                <h4 class="desc-end">Preço por quilômetro (km): R$ 12,50**</h4>
                            </div>
                        </li>
                        <li class="info-class slide">
                            <img src="img/cars/hyundai-azera-max.jpg" alt="Hyundai Azera"/>
                            <h1 class="car-name">Shield Class</h1>
                            <h2 class="class-car">Hyundai Azera, Kia Cadenza, Toyota Camry</h2>
                            <p class="info-car">Veículos blindados</p>
                            <div>
                                <h3>PREÇO DO SERVIÇO</h3>
                                <h4>Preço por hora: R$ 150,00*</h4>
                                <h4 class="desc-end">Preço por quilômetro (km): R$ 9,50**</h4>
                            </div>	
                        </li>                        
                        <li class="info-class slide">
                            <img src="img/cars/dodge-journey-max.jpg" alt="Dodge Journey"/>
                            <h1 class="car-name">SUV Class</h1>
                            <h2 class="class-car">Dodge Journey, Hyundai Vera Cruz</h2>
                            <p class="info-car">Veículos blindados.</p>
                            <div>
                                <h3>PREÇO DO SERVIÇO</h3>
                                <h4>Preço por hora: R$ 200,00*</h4>
                                <h4 class="desc-end">Preço por quilômetro (km): R$ 11,00**</h4>	
                            </div>
                        </li>
                        <li class="info-class slide">
                            <img src="img/cars/honda-accord-max.jpg" alt="Honda Accord"/>
                            <h1 class="car-name">Executive Class</h1>
                            <h2 class="class-car">Honda Accord, Kia Optima, Ford Fusion</h2>
                            <p class="info-car unshielded">Veículos não blindados</p>
                            <div>
                                <h3>PREÇO DO SERVIÇO</h3>
                                <h4>Preço por hora: R$ 100,00*</h4>
                                <h4 class="desc-end">Preço por quilômetro (km): R$ 7,00**</h4>
                            </div>	
                        </li>
                        <li class="info-class slide">
                            <img src="img/cars/honda-civic-max.jpg" alt="Honda Civic"/>
                            <h1 class="car-name">Economy Class</h1>
                            <h2 class="class-car">Honda Civic, Nissan Sentra, Toyota Corola</h2>
                            <p class="info-car unshielded">Veículos não blindados.</p>
                            <div>
                                <h3>PREÇO DO SERVIÇO</h3>
                                <h4>Preço por hora: R$ 75,00*</h4>
                                <h4 class="desc-end">Preço por quilômetro (km): R$ 5,00**</h4>	
                            </div>
                        </li>
                    </ul>                                   
                </div>                        
                <!--MINIATURAS-->
                <div id="mini-galeria">
                    <nav>
                        <ul id="nav-mini">
                            <li class="mini-pics" id="pic_one">
                                <a href="">
                                    <img src="img/cars/ftc.jpg" alt="BMW"/>
                                    <span class="car-name">First Class</span>
                                    <div class="sub_info">
                                        <h2 class="class-car">BMW 528i Sport e Mercedes Classe E250</h2>
                                        <p class="info-car">Veículo blindado</p>
                                        <div>
                                            <h3 class="price-title">PREÇO DO SERVIÇO</h3>
                                            <h4 class="mini-price">Preço por hora: R$ 250,00*</h4>
                                            <h4 class="desc-end">Preço por quilômetro (km): R$ 12,50**</h4>
                                        </div> 
                                        <form method="post" action="" class="form-btt-register">
                                            <button type="button" class="mini_btt bttConfirm">CONTRATAR AGORA<img src="img/icons/next-minimum.png"/></button>
                                        </form>
                                    </div>
                                </a>
                            </li>
                            <li class="mini-pics">
                                <a href="">
                                    <img src="img/cars/sdc.jpg" alt="Hyundai Azera"/>
                                    <span class="car-name">Shield Class</span>
                                    <div class="sub_info">
                                        <h2 class="class-car">Hyundai Azera, Kia Cadenza, Toyota Camry</h2>
                                        <p class="info-car">Veículos blindados</p>
                                        <div>
                                            <h3 class="price-title">PREÇO DO SERVIÇO</h3>
                                            <h4 class="mini-price">Preço por hora: R$ 150,00*</h4>
                                            <h4 class="desc-end">Preço por quilômetro (km): R$ 9,50**</h4>
                                        </div>
                                        <form method="post" action="" class="form-btt-register">
                                            <button type="button" class="mini_btt bttConfirm">CONTRATAR AGORA<img src="img/icons/next-minimum.png"/></button>
                                        </form>
                                    </div>
                                </a>
                            </li>
                            
                            <li class="mini-pics">
                                <a href="">
                                    <img src="img/cars/svc.jpg" alt="Dodge Journey"/>
                                    <span class="car-name">SUV Class</span>
                                    <div class="sub_info">                                                
                                        <h2 class="class-car">Dodge Journey, Hyundai Vera Cruz</h2>
                                        <p class="info-car">Veículos blindados.</p>
                                        <div>
                                            <h3 class="price-title">PREÇO DO SERVIÇO</h3>
                                            <h4 class="mini-price">Preço por hora: R$ 200,00*</h4>
                                            <h4 class="desc-end">Preço por quilômetro (km): R$ 11,00**</h4>	
                                        </div>
                                        <form method="post" action="" class="form-btt-register">
                                            <button type="button" class="mini_btt bttConfirm">CONTRATAR AGORA<img src="img/icons/next-minimum.png"/></button>
                                        </form>
                                    </div>
                                </a>
                            </li>
                            <li class="mini-pics">
                                <a href="">
                                    <img src="img/cars/ecc.jpg" alt="Honda Accord"/>
                                    <span class="car-name">Executive Class</span>
                                    <div class="sub_info">
                                        <h2 class="class-car">Honda Accord, Kia Optima, Ford Fusion</h2>
                                        <p class="info-car unshielded">Veículos não blindados</p>
                                        <div>
                                            <h3 class="price-title">PREÇO DO SERVIÇO</h3>
                                            <h4 class="mini-price">Preço por hora: R$ 100,00*</h4>
                                            <h4 class="desc-end">Preço por quilômetro (km): R$ 7,00**</h4>
                                        </div>
                                        <form method="post" action="" class="form-btt-register">
                                            <button type="button" class="mini_btt bttConfirm">CONTRATAR AGORA<img src="img/icons/next-minimum.png"/></button>
                                        </form>
                                    </div>
                                </a>
                            </li>
                            <li class="mini-pics">
                                <a href="">
                                    <img src="img/cars/eyc.jpg" alt="Honda Civic"/>
                                    <span class="car-name">Economy Class</span>
                                    <div class="sub_info">                                               
                                        <h2 class="class-car">Honda Civic, Nissan Sentra, Toyota Corola</h2>
                                        <p class="info-car unshielded">Veículos não blindados.</p>
                                        <div>
                                            <h3 class="price-title">PREÇO DO SERVIÇO</h3>
                                            <h4 class="mini-price">Preço por hora: R$ 75,00*</h4>
                                            <h4 class="desc-end">Preço por quilômetro (km): R$ 5,00**</h4>	
                                        </div>
                                        <form method="post" action="" class="form-btt-register">
                                            <button type="button" class="mini_btt bttConfirm">CONTRATAR AGORA<img src="img/icons/next-minimum.png"/></button>
                                        </form>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <a id="next" href="#"></a>
            </div>
            <form class="form-btt-register" id="form-car">
                <input type="hidden" id="txt-car-selected" name="txt-car" />
                <button type="button" id="btt-confirm" class="bttConfirm">Contratar Agora<img src="img/icons/next-minimum.png"/></button>
            </form>                        

            <%@ include file="footer.jsp"%>
            <div id="modal-not-user">                       
                <div id="div-close-not-user"><img src="img/icons/delete-close.png" /></div>
                <div class="div-not-user col-lg-6 col-md-6 col-sm-12 col-xs-12" id="box-acessar">
                    <h1 class="h-not-user">Acesse sua conta</h1><br/>
                    <button type="button" id="btt-access" class="btt btt-not-user">Entrar</button>
                </div>

                <div class="div-not-user col-lg-6 col-md-6 col-sm-12 col-xs-12" id="box-cadastrar">
                    <h1 class="h-not-user">Crie uma conta agora!</h1><br/>
                    <button type="button" id="btt-register" class="btt-not-user" />Cadastrar-se</button>
                </div>
            </div>
           <div id="white-page"></div>  
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="js/min-menu.js"></script>
        <script src="js/box-message-show.js"></script>
        <script src="js/events-hire-service.js"></script>
        <script src="js/position-car.js"></script>
        <script src="js/select-car.js"></script>
        <script src="js/animation.js"></script>
    </body>
</html>
