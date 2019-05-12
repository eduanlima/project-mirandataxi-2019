<%@page import="Model.Client"%>
<%@page import="ModelDAO.ClientDAO"%>
<%@page import="Model.Phone"%>
<%             
    int userID = Integer.parseInt((String)session.getAttribute("userId"));
    Client client = new Client();//novo cliente

    client.setId(userID);//setar id do cliente
    ClientDAO clientDAO = new ClientDAO();//novo cliente DAO
    client = clientDAO.readClient(client);//ler dados do cliente
%>
<div id="page-update">    
    <div class="row nopadding">
        <h1 class="title-main noselect">Dados da conta</h1>
        <form id="form-update" class="forms" method="POST">
            <div class="row nopadding">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <label for="up-name" class="lbl-standard">Nome</label>
                    <input type="text" autocomplete="name" id="up-name" name="name" placeholder="Insira seu nome completo" class="form-control" value="<%=client.getName()%>"/>
                </div>

                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <label for="up-email" class="lbl-standard">E-mail</label>
                    <input type="email" autocomplete="email" id="up-email" name="email" class="form-control" placeholder="minha-conta-email@servico.com" value="<%=client.getEmail()%>"/>
                </div>
            </div>

            <div class="row nopadding">
                <%
                    int i = 1;
                    for (Phone phone: client.getPhone()){
                %>
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <label for="up-phone-one" class="lbl-standard">Telefone (<%= i%>)</label>
                    <input type="tel" autocomplete="tel" name="phone_<%=i%>" placeholder="(00) 00000-0000" class="form-control" id="up-phone-<%=i%>" value="<%= phone.getNumber()%>"/>
                </div>
                <%  i++;
                        }
                %>                
            </div>     
            <div class="row nopadding">
                <div class="box-btt">
                    <button id="btt-update" type="submit" name="nBttUpdate" class="btn-update">Atualizar</button>
                </div>
            </div>        
            <div class="row">
                <span class="alerta-msg"></span>                        
            </div>
        </form>
        <div class="line"></div>      
        <form id="up-password" class="forms">
            <div class="row nopadding">
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <label for="up-pass-conf" class="lbl-standard">Senha Atual</label>
                    <input type="password" autocomplete="off" name="current_psw" id="current_psw" class="form-control" placeholder="Digite a senha atual" maxlength="8"/>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6" id="div-space-pass">
                    <label for="up-pass" class="lbl-standard">Nova Senha</label>
                    <input type="password" autocomplete="off" id="up_pass" name="new_password" class="form-control field-psw" placeholder="Insira uma senha" maxlength="8"/>
                </div>

                <div class="col-lg-4 col-md-6 col-sm-6">
                    <label for="up-pass-conf" class="lbl-standard">Confirme sua senha</label>
                    <input type="password" autocomplete="off" id="up_pass_conf" name="new_pass_again" class="form-control field-psw" placeholder="Digite novamente a senha" maxlength="8"/>
                </div>               
            </div>
            <div class="row nopadding">
                <div class="box-btt">
                    <button id="btt-cfm-pwd" type="button" name="nBttConfirm" class="btn-update">Confirmar senha</button>
                </div>
                <div class="box-btt">
                    <button id="btt-update-pwd" type="submit" name="nBttUpdate" class="btn-update">Atualizar</button>
                </div>
            </div> 
            <div class="row">
                <span class="alerta-msg"></span>                        
            </div>
        </form>
    </div>
</div>
<div id="box-modal">
    <form id="form-in">
        <div id="div-close"><img src="img/icons/delete-close.png" /></div>
        <input type="email" id="txt-email-in" class="txt-in" name="nEmail" placeholder="minha-conta-email@servico.com" />
        <input type="password" autocomplete="off" id="txt-password-in" class="txt-in" name="nPass" placeholder="Digite sua senha" />
        <h5 id="h-alert"><img src="img/icons/attetion.png" />Atenção, login ou senha inválidos.</h5>
        <button type="submit" id="btt-go">Entrar</button>
        <a id="re-password" href="">Esqueci minha senha</a>
    </form>
</div>

<div id="box-message">
    <h1 id="txt-main" class="h-message"></h1>
    <h2 id="txt-second" class="h-message"></h2>
    <button id="btt-ok">OK</button>
</div>

<footer id="footer-page"></footer>
<div id="black-page"></div>

<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
<script src="js/jquery.mask.min.js"></script>
<script src="js/all-masks.js"></script>  
<script src="js/box-message-show.js"></script>
<script src="js/update-client.js"></script>