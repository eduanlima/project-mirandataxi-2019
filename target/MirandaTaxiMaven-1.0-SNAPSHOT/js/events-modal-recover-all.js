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
            
            $.ajax({
                type: formRecover.attr("method"),
                url: formRecover.attr("action"),
                data: formRecover.serialize(),
                success: function(data){
                   hLabelInfo.innerHTML = data; 
                }
            });
        }else{
            hLabelInfo.innerHTML = "Por favor, insira o seu endereço de e-mail.";
        }
    });
    
  