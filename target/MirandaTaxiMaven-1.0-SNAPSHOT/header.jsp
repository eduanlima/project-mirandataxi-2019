<header>
    <nav class="navbar navbar-default topHeader">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-navbar" aria-expanded="false" style="border-color:#fff; border-radius: initial; background:#ff6600; ">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="index.jsp" title="Home" class="navbar-brand logo">MIRANDATAXI</a>
            </div>

            <div class="collapse navbar-collapse" id="main-navbar">
                <ul class="nav navbar-nav navbar-right container-menu">  
                    <li class="menu menu-register-service"><a href="hire-service.jsp" title="Agende uma corrida" class="link-main">Contratar</a></li>
                    <li class="menu menu-service"><a href="services.jsp" title="Conheça nossos serviços" class="link-main">Serviços</a></li>
                    <li class="menu menu-contact"><a href="contact.jsp" title="Nos mande uma mensagem" class="link-main">Contato</a></li>
                    <li class="dropdown sub-login">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img class="group-img-log img-logged" src="img/icons/user-logged.png" /><img class="group-img-log img-not-log" src="img/icons/user.png" /><span id="link-in" class="link-main">Login</span><span class="caret"></span></a>
                        <ul class="dropdown-menu">  
                            <li class="sub-menu area-no-user"><a href="#" id="link-access-in">ENTRAR <img src="img/icons/icon-log-in.png" alt="Entrar" class="icon-in-out"/></a></li>
                            <li class="sub-menu area-no-user"><a href="register-yourself.jsp">CADASTRAR-SE <img src="img/icons/icon-register.png" alt="Cadastre-se" class="icon-in-out"/></a></li>             
                            <div id="div-user-general" class="area-user">
                                <img id="img-log" src="img/icons/user.png" />
                                <h2 id="h-name-user"></h2>
                            </div>
                            <li role="separator" class="divider area-user"></li>
                            <li class="itens-access area-user sub-menu"><a href="dashboard.jsp#profile" class="sub-options">MINHA CONTA</a></li>
                            <li class="itens-access area-user sub-menu"><a href="dashboard.jsp#races" class="sub-options">MINHAS CORRIDAS</a></li>
                            <li class="itens-access area-user"><a href="" class="sub-options btn_logout" id="logout">SAIR <img src="img/icons/icon-log-out.png" alt="Sair" class="icon-in-out"/></a></li>                           
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>           
</header>
<div id="box-modal">
    <form id="form-in">
        <div id="div-close"><img src="img/icons/delete-close.png" /></div>
        <div class="form-group">
            <input type="email" id="txt-email-in" class="txt-in form-control" name="nEmail" placeholder="minha-conta-email@servico.com" />
        </div>
        <div class="form-group">
            <input type="password" id="txt-password-in" class="txt-in form-control" name="nPass" placeholder="Digite sua senha" />
        </div>
        <h5 id="h-alert"><img src="img/icons/attetion.png" />Atenção, login ou senha inválidos.</h5>
        <button type="submit" id="btt-go">ENTRAR</button>
        <a id="re-password" href="">Esqueci minha senha</a>
    </form>
</div>

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

<div id="black-page"></div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>        
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/box-modal-all.js"></script>
<script src="js/box-message-show.js"></script>
<script src="js/events-in-out.js"></script>
<script src="js/check-session.js" charset="UTF-8"></script>
<script src="js/events-modal-recover-all.js"></script>
