var txtName = document.getElementById("txt-name"),
    txtPhone = document.getElementById("txt-phone"),
    txtEmail = document.getElementById("txt-email"),
    txtMessage = document.getElementById("message"),
    bttSend = document.getElementById("btt-finish");
        
   // bttSend.addEventListener("click",sendEmail,false);

$(document).ready(function(){
    function showMessage(tipo, mensagem){
        if(tipo==='sucesso'){            
            $('#infoMensagem').html(mensagem).css('color', '#269801');
        }else{            
            $('#infoMensagem').html(mensagem).css('color', '#cc0909');
        }
        $("#sendMessageBox").modal('toggle');
    }
        
    $('#btt-finish').on('click', function(ev){
        ev.preventDefault();       
        sendEmail($(this));
    });
       
    function sendEmail(btn){
        var nome = txtName.value;
        var phone = txtPhone.value;
        var email = txtEmail.value;
        var mensagem = txtMessage.value;
        
        var erro = false;
        
        $("#form-register input, #form-register textarea").each(function(index,element){
            if($(element).val() === ""){
                $(this).css("border-color","#ff0000");
                erro = true;
            }else{               
                $(this).css("border-color","#008000");
            }
        });
        
        if(erro) return false;
        $(btn).prop("disabled", true);
        $.ajax({
            type: "post",
            url: "SendEmail",
            data: {
                name: nome,
                phone: phone,
                email: email,
                message: mensagem
            },
            success: function(result){
                if(!result){
                    showMessage('falha', 'Mensagem não enviada! Tente novamente');
                }else {
                    showMessage('sucesso','Sua mensagem foi enviada com sucesso. Entraremos em contato em breve');
                    $("#form-register").clear();
                    $(btn).prop("disabled", false);
                }
            }
        });
    };
});