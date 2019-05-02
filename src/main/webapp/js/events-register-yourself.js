/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global validateBasic, validateTel */

"use strict";
$(document).ready(function () {
    var formRegister = document.getElementById("form-register"),
            bttFinish = document.getElementById("btt-finish");

    //Function to show message
    var showMessageForm = function (mainText, text, tipo = "falha") {
        pageEndVisible("#black-page");
        boxMessageShow("#box-message");

        if (tipo === "sucesso") {
            $("#btt-ok").hide();
            $("#btn-redirect").show();
        } else {
            $("#btn-redirect").hide();
            $("#btt-ok").show();
        }

        $("#txt-main").html(mainText);
        $("#txt-second").html(text);
    },
            messageClose = function () {
                pageEndVisible("#black-page");
                boxMessageShow("#box-message");
            },
            bttMessage = document.getElementById("btt-ok");

    document.getElementById("form-register").method = "post";
    document.getElementById("form-register").action = "RegisterClient";

    bttMessage.addEventListener("click", messageClose, false);
    
    function desbloquearBtn(){
        $(bttFinish).prop("disabled", false);//bloquear botão
        $(bttFinish).css("background-color", "#363636");
        $(".gif").css("display", "none");//mostrar gif
    }
    
    $("#form-register").on("submit", function (e) {
        e.preventDefault();
       
        if (validateBasic()) {
            var formIn = $("#form-register");
            $(bttFinish).prop("disabled", true);//bloquear botão
            $(bttFinish).css("background-color", "#ccc");
            $(".gif").css("display", "inline-block");//mostrar gif
            
            $.ajax({
                type: formIn.attr("method"),
                url: formIn.attr("action"),
                data: formIn.serialize(),
                success: function (data) {
                    var result = Number(data);

                    if (result === 0) {
                        showMessageForm("Não realizado.", "Ocorreu um erro. Tente mais tarde.", "falha");
                        desbloquearBtn();
                    } else if (result === 1) {
                        showMessageForm("Não realizado.", "Já existe um cadastro com este e-mail em nosso sistema.", "falha");
                        desbloquearBtn();
                    } else if (result === 2) {
                        showMessageForm("Parabéns!", "Cadastro realizado com sucesso!", "sucesso");
                        formRegister.reset();
                        $(bttFinish).prop("disabled", false);
                    }
                }
            });
        }
    });
});


