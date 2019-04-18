/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global validateBasic, validateTel */

"use strict";
$(document).ready(function(){
    var formRegister = document.getElementById("form-register"),
        bttFinish = document.getElementById("btt-finish");

    //Function to show message
    var showMessageForm = function(mainText,text){
        pageEndVisible("#black-page");
        boxMessageShow("#box-message");
        $("#txt-main").html(mainText);
        $("#txt-second").html(text);
    },
    
    messageClose = function(){
        pageEndVisible("#black-page");
        boxMessageShow("#box-message");
    },
    
    bttMessage = document.getElementById("btt-ok");
    
    document.getElementById("form-register").method = "post";
    document.getElementById("form-register").action = "RegisterClient";
     
    bttMessage.addEventListener("click",messageClose,false);
    
    $("#form-register").on("submit", function(e){
        e.preventDefault();
        
        if(validateBasic()){
            var formIn = $("#form-register");
            $(bttFinish).prop("disabled", true);
            $.ajax({
                type: formIn.attr("method"),
                url: formIn.attr("action"),
                data: formIn.serialize(),
                success: function(data){
                    var result = Number(data);

                    if(result === 0){
                      showMessageForm("Não realizado.","Ocorreu um erro. Tente mais tarde.");
                    }else if(result === 1){
                      showMessageForm("Não realizado.","Já existe um cadastro com este e-mail em nosso sistema.");
                    }else if(result === 2){
                      showMessageForm("Parabéns!","Cadastro realizado com sucesso!");
                      formRegister.reset();
                      $(bttFinish).prop("disabled", false);

                    }
                }
            });
        }
    });
});


