$(document).ready(function(){    
    var divClose = document.getElementById("div-close-recover"),
        inputEmailRecover = document.getElementById("txt-recover-email"),
        hLabelInfo = document.getElementById("h-alert-recover"),

        showModalIndex = function(){
            boxMessageShow("#box-modal-recover");
            pageEndVisible("#black-page");
            hLabelInfo.innerHTML = "";
        };
        
    divClose.addEventListener("click",showModalIndex,false);  

    $("#re-password").on("click",function(e){
        e.preventDefault();
        
        $.when(
            boxMessageShow("#box-modal")
        ).then(function(){
            boxMessageShow("#box-modal-recover");
            inputEmailRecover.value = "";
        });
        
        document.getElementById("form-in-recover").method = "post";
        document.getElementById("form-in-recover").action = "RecoverPassword";
        
    });
    
    $("#form-in-recover").on("submit",function(e){
        e.preventDefault();
        
        if(inputEmailRecover.value !== ""){
            var formRecover = $("#form-in-recover");
            
            $("#box-recover-loader").fadeIn();
            $("#btt-go-recover").prop("disabled", true);
            
            $.ajax({
                type: formRecover.attr("method"),
                url: formRecover.attr("action"),
                data: formRecover.serialize(),
                success: function(data){
                    $("#box-recover-loader").fadeOut('fast', function(){
                        $("#btt-go-recover").prop("disabled", false);
                        hLabelInfo.innerHTML = data;
                    });                     
                }
            });
        }else{
            hLabelInfo.innerHTML = "Por favor, insira o seu e-mail.";
        }
    });
    
  
});