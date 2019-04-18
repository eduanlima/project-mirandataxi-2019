$(document).ready(function(){
    /*Máscara página register-yourself*/
    $('#txt-phone-one').mask('(00)00000-0000');
    $('#txt-phone-two').mask('(00)00000-0000');
    $('#txt-phone-tree').mask('(00)00000-0000');

    /*Máscara página contact*/
    $('#txt-phone').mask('(00)00000-0000');
    
    /*Máscara página profile*/
    $('#up-phone-1').mask('(00)00000-0000');
    $('#up-phone-2').mask('(00)00000-0000');
    $('#up-phone-3').mask('(00)00000-0000');
    $('#up_pass, #current_psw, #up_pass_conf').mask('00000000');
    
    /*Máscara página register-service*/
    $('#txt-hours').mask('00:00');
   // $('#docNumber').mask('000.000.000-00');
    $('#cardExpirationMonth').mask('00');
    $('#cardExpirationYear').mask('0000');
});