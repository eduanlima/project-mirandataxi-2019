$(document).ready(function(){
    var formLogin = document.getElementById("form-in"),
        txtEmail = document.getElementById("txt-email-in"),
        txtPass = document.getElementById("txt-password-in"),
        lblAlert = document.getElementById("h-alert"),
        vetEdit = [txtEmail, txtPass],        
        
        validate = function(){
            var answer = false,
                flag = 0;
            
            for(var i = 0; i < vetEdit.length; i++){
               if(vetEdit[i].value.length === 0){
                  vetEdit[i].style.outline = "#FF6600 solid 2px";
               }else{
                   flag++;
               }
            }  
            
            if (flag === 2){
                answer = true;
            }
            return answer;
        },
        
        clearEdit = function(object){
            if((object.style.outlineStyle === "solid") && (object.value.length > 0)){
                object.style.outline = "none";
            }
        },
        
        setTxtEmail = function(){
            clearEdit(txtEmail);
        },
        
        setTxtPass = function(){
            clearEdit(txtPass);
        },
        /*
        showMessageForm = function(mainText,text){
            boxMessageShow("#box-message","#black-page");
            $("#txt-main").html(mainText);
            $("#txt-second").html(text);
        },*/
        
        outSession = function(){            
            $.ajax({
                type: "post",
                url: "InvalidateUser",
                success: function(){
                   location.href="index.jsp";
                }
            });
        };

        formLogin.method = "post";
        formLogin.action = "LoginUser";       
        
        $("#form-in").on("submit", function(e){
            e.preventDefault();
            
            if(validate()){
                var formIn = $("#form-in");
                $("#box-loader").fadeIn();
                $("#btt-go").prop("disabled", true);
                
                $.ajax({
                    type: formIn.attr("method"),
                    url: formIn.attr("action"),
                    data: formIn.serialize(),
                    success: function(data){
                        var result = Number(data);
                        
                        if(result === 0){
                            $("#box-loader").fadeOut('fast', function(){
                                $("#btt-go").prop("disabled", false);
                                lblAlert.style.visibility = "visible";
                            });
                                                        
                        }else{
                            pageEndVisible("#black-page");
                            boxMessageShow("#box-modal");
                            checkSession();
                        }
                    }
                 });
            }

        });
        
        txtPass.maxLength = 8;
        txtEmail.addEventListener("input", setTxtEmail, false);
        txtPass.addEventListener("input", setTxtPass, false);
        //linkOut.addEventListener("click",outSession, false);
        
        //chamar a função de logout
        $(".btn_logout").bind('click',function(){
            outSession();
        });
        
        
        setTimeout(function(){           
            $(".left-side").fadeIn('slow', function(){
                $(".right-side").fadeIn('slow');
            });
        }, 1500);
});


