$(document).ready(function(){
    
    /** Função: Bloquear campos de senha **/ 
    function locker_field(){
        $(".field-psw").prop("disabled", true);
    }    
    /** Função: Desbloquear campos de senha **/ 
    function locker_psw_field(){
        $("#current_psw").prop("disabled", true);
    }
    /** Função: Desbloquear campos de senha **/ 
    function unlocker_field(){
        $(".field-psw").prop("disabled", false);
    }
    /** Função: Desbloquear campos de senha **/ 
    function unlocker_psw_field(){
        $("#current_psw").prop("disabled", false);
    }
    /*
     * Mostrar mensagem de SUCESSO ou ERRO
     * @param type: informa o tipo de mensagem
     */
    function mensagem(type, form, msg){
        
        switch(type){
            case "error":
                $(form +" .alerta-msg").addClass("alerta-error").html(msg).fadeIn();
                setTimeout(function(){
                    $(form +" .alerta-msg").fadeOut().removeClass("alerta-error");
                },3000);
            break;
            case "success":                
                $(form +" .alerta-msg").addClass("alerta-success").html(msg).fadeIn();
                setTimeout(function(){
                    $(form +" .alerta-msg").fadeOut().removeClass("alerta-success");
                },3000);
            break;
        }
    }
    /*
     * Funçao: Fazer a atualização dos dados
     * @param form - formulário 
     * @param link - caminho do arquivo que trata os dados
     */
    function update(form, link, op) {
        formu = $(form);
        
        $.ajax({
            type: "post",
            url: link,
            dataType: "html",
            data: formu.serialize(),
            success: function (result) {
                result = parseInt(result);

                if (result === 1) {
                    //opção 2 =>update senhas                    
                    if (op === 2) {
                        formu.each(function () {
                            this.reset();
                        });//apagar senhas digitadas
                        locker_field();
                        unlocker_psw_field();
                        
                        $("#btt-update-pwd").fadeOut('fast', function(){
                            $("#btt-cfm-pwd").fadeIn();
                            $("#current_psw").focus();
                       }); 
                    }
                    mensagem("success", form, "Dados atualizados com sucesso!");
                } else {
                    mensagem("error", form, "Houve um erro ao atualizar. Tente novamente");
                }
            }
        });
    }    
    locker_field();//bloquear campo de senha
    /*
    * Validar e Atualizar Usuário
    */
    $("#form-update").submit(function(e){
	e.preventDefault();	 	        
    }).validate({
         //validar os campos        	
        rules:{
            name: {
                required: true
            },
            email:{
                required: true,
                email:true
            },
            phone_1:{
                required: true,
                minlength: 13,
                maxlength: 14
            }
        },
        messages:{
            name:{
                required: "Campo nome não pode ser vazio"
            },
            email:{
                required: "Campo email não pode ser vazio",
                email: "Insira um email válido"
            },
            phone_1:{
                required: "Insira pelo menos um telefone",
                minlength: "Digite corretamente o telefone",
                maxlength: "Digite corretamente o telefone"
            }
        },
        submitHandler: function(){
            var form = ("#form-update");            
            update(form, "UpdateClient", 1);           
        }
        });
        
    //atualizar senha
    $("#up-password").submit(function(ev){
        ev.preventDefault();//evita ação padrão no clique        
    }).validate({
        rules:{            
            new_password:{
                required: true,
                maxlength:8
            },
            new_pass_again:{
                required: true,
                maxlength:8,
                equalTo: "#up_pass"
            }
        },
        messages:{
            new_password:{
                required: "Informe a nova senha",
                maxlength: "Apenas 8 caracteres são permitidos"
            },
            new_pass_again:{
                required: "Confirme a senha",
                maxlength: "Apenas 8 caracteres são permitidos",
                equalTo: "As senhas devem ser iguais"
            }
        }, 
        submitHandler: function(){
            var form = ("#up-password");
            update(form, "AlterPassword", 2);
        }
    });
    
    /*CONFIRMAR SENHA*/
    $('#btt-cfm-pwd').click(function(e){
        e.preventDefault();
        form = "#up-password";
        
        psw = $('#current_psw').val();//pega a senha digitada
        if(psw.length === 0){
            mensagem("error", form, "Por favor informe sua senha");
        }else{
            $.ajax({
                type:"post",
                url:"InputPasswordAlter",
                dataType:"html",
                data:{current_psw: psw},
                success: function(result){  
                    /*senha encontrada*/
                    if(result == 1){
                       locker_psw_field();
                       unlocker_field();//desbloquea inputs
                       $("#btt-cfm-pwd").fadeOut('fast', function(){
                           $("#btt-update-pwd").fadeIn();
                            $("#up_pass").focus();
                       });                   
                    }else{
                       mensagem("error", form, "Senha não confere! Verifique sua senha");
                    }
                }
            });
        }
    });
});


