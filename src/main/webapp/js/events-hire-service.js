"use strict";

var bttBuy = document.getElementById("btt-confirm"),
    bttAccess = document.getElementById("btt-access"),
    bttRegisterNow = document.getElementById("btt-register"),
    divClose = document.getElementById("div-close-not-user"),
    
    redirectRegisterUser = function(){
        location.href = "register-yourself.jsp";
    },
    
    redirectRegisterService = function(){
        var formCar = $("#form-car");
        
        $.ajax({
            type: "post",
            url: "CarSession",
            data: formCar.serialize(),
            success: function(data){
                location.href = "register-service.jsp";
            }
        });
    },
    
    visibleNotUser = function(){
        pageEndVisible("#black-page");
        boxMessageShow("#modal-not-user");
    },
    
    accessNow = function(){  
        boxMessageShow("#modal-not-user");
        boxMessageShow("#box-modal");
    },
            
    checkSessionHire = function(){   
       $.ajax({
            type: "post",
            url: "UserSession",
            success: function(data){
                var result = String(data);

                if(result !== ""){
                    redirectRegisterService();
                }else if(result === ""){
                    pageEndVisible("#black-page");
                    boxMessageShow("#modal-not-user");
                }                
            }
        });
    };
    
    /*ao clicar no botão Contratar*/
    $(".bttConfirm").bind("click", function(){
        if($(this).hasClass("mini_btt")){
            checkSessionHire();
        }else{
            checkSessionHire();
        }        
    });
    
    bttAccess.addEventListener("click", accessNow, false);
    
    divClose.addEventListener("click", visibleNotUser, false);
    bttRegisterNow.addEventListener("click", redirectRegisterUser, false);
    
    $("#form-car").on("submit",function(e){
        e.preventDefault();
    });


