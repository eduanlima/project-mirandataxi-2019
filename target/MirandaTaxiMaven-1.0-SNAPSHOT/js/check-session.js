$(document).ready(function(){
var liUser = $(".area-user"),
    liNoUser = $(".area-no-user"),    
    imgNotUser = $(".img-not-log"),
    imgUser = $(".img-logged"),
    userName = $("#h-name-user");
    
    /*
     * Função: pega o primeiro nome do usuário logado
     * @param nameUser -> nome completo do usuário
     * */
    getFirstName = function(nameUser){
        var fName = false,
            sliceName = "",
            allName = "";

        for(var i = 0; i < nameUser.length; i++){
            sliceName = nameUser.substring(i, i+1);

            if(!fName){
                if((sliceName !== " ")){
                    allName = allName.concat(sliceName);
                }else if((sliceName === " ")){
                    fName = true;
                    return allName; 
                }
            } 
        }
    };
    /*
     * Função: Desabilitar opções para do usuário
     */
    disableUser = function(){       
        liUser.hide();
        liNoUser.show();
        
        $('#link-in').show();
        imgUser.hide();
        imgNotUser.show();        
    };
    /*
     * Função: habilitar opções para do usuário
     */        
    enableUser = function(){      
        liNoUser.hide();//info que o user não verá
        liUser.show();//info que o user verá

        imgUser.show();//img logado
        imgNotUser.hide();//esconde img não logado
        $('#link-in').hide();//esconde nome LOGIN
    };
            
    createMenuIn = function(action, user){        
        if(action){
            userName.text("Olá, " + user);
            enableUser();            
        }else if(!action){
            disableUser();
        }
    };
    
    /*
     * Função: verifica se o usuário está logado
     */
    checkSession = function(){   
       $.ajax({
            type: "post",
            url: "UserSession",
            success: function(data){
                var result = String(data);

                if(result !== ""){
                    result = getFirstName(result);
                    createMenuIn(true, result);   
                }else if(result === ""){
                   createMenuIn(false, "");
                }                
            }
        });
    };
    /*chama a função que verifica a SESSÃO*/
    checkSession();    
});