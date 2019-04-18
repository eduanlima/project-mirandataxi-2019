<%-- 
    Document   : recover-password
    Created on : 03/03/2018, 20:14:03
    Author     : Miranda Taxi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
        <link rel="stylesheet" href="style/style-recover-password.css">
        <title>Recuperar Senha</title>
    </head>
    <body>
        <div class="row nopadding" >
            <div class="col-lg-4 col-md-6 col-sm-10 col-xs-10 col-lg-push-4 col-md-push-3 col-sm-push-1 col-xs-push-1 nopadding">
                <form id="frm-psw">
                    <h1 id="psw-title" class="text-center">Nova senha</h1>
                    <div class="form-group">
                        <label for="new-psw">Senha:</label>
                        <input type="password" name="new-psw" id="new-psw" class="form-control" placeholder="Nova senha">
                    </div>
                    <div class="form-group">
                        <label for="psw-again">Repetir Senha:</label>
                        <input type="password" name="psw-again" id="psw-again" class="form-control" placeholder="Insira a senha novamente">
                    </div>
                    <button class="btn btn-warning" id="btn-back">Voltar a tela principal</button>
                    <button class="btn btn-success" id="btn-acess">Confirmar</button>
                </form>
            </div>
        </div>
        <script src="js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.js"></script>
    </body>
</html>
