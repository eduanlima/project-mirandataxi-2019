<%-- 
    Document   : driver
    Created on : 22/02/2019, 12:58:54
    Author     : Rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css"/> 
        <link rel="stylesheet" media="screen" href="style/fonts.css" />
        <link rel="stylesheet" media="screen" href="style/driver.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/icons/favicon.ico">
        <title>Motorista Login</title>
    </head>
    <body>
        <div class="content">
            <div class="row nopadding">
                <div class="col-lg-6 col-md-6 col-sm-8 col-xs-10 col-lg-push-3 col-md-push-3 col-sm-push-2 col-xs-push-1 nopadding">
                    <div id="box_login">                       
                        <div class="brand-index"><img src="img/icons/logo-miranda-taxi-imagem.png" alt="MIRANDA TAXI" class="brand"></div>
                        <form id="form-in">                            
                            <div class="form-group">
                                <span class="titulo-campo">Email (*)</span>
                                <input type="email" id="txt-email-in" class="txt-in form-control" name="email" placeholder="minha-conta-email@servico.com" />
                            </div>
                            <div class="form-group">
                                <span class="titulo-campo">Senha (*)</span>
                                <input type="password" id="txt-password-in" class="txt-in form-control" name="password" placeholder="Digite sua senha" />
                            </div>
                            <button type="submit" id="btn-go">ENTRAR</button>
                           
                            <div class="alert alert-danger" id="msg-erro" style="display: none; "></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>   
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){
                $("#btn-go").on("click", function(ev){
                    ev.preventDefault();
                    
                    var email = $("input[name=email]").val();
                    var password = $("input[name=password]").val();
                    
                    var dados = $("#form-in").serialize();
                    
                    if(email==="" || password===""){
                        $(".txt-in").each(function(){
                            if($(this).val() === ""){
                                $(this).css("border-color","red");
                            }else{
                                $(this).css("border-color","green");
                            }
                        });
                        $("#msg-erro").fadeIn().html("Todos os campos são obrigatórios");
                    }else{
                        $(".txt-in").css("border-color","green");
                        $("#msg-erro").fadeOut();
                     
                        $.ajax({
                            type:'POST',
                            url: 'LoginDriver',
                            data: dados,
                            success: function(retorno){
                                if(retorno){
                                    //direciona para a página de motorista
                                    window.location.href = 'dashdriver.jsp';
                                }else{
                                    //mostrar mensagem de erro
                                    $("#msg-erro").fadeIn().html("Não foi possível logar. Verique seu email e senha e tente novamente");
                                    
                                    //esconder mensagem de erro após 4s
                                    setTimeout(function(){
                                        $("#msg-erro").fadeOut();
                                    }, 4000);
                                }
                            }
                       });
                    }
                });
            });
        </script>
    </body>
</html>
