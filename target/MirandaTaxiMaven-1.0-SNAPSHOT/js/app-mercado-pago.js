    Mercadopago.setPublishableKey("TEST-ed795f55-6470-4fdc-83ee-91a01c808517");

    Mercadopago.getIdentificationTypes();

    function addEvent(el, eventName, handler){
        if (el.addEventListener) {
               el.addEventListener(eventName, handler);
        } else {
            el.attachEvent('on' + eventName, function(){
              handler.call(el);
            });
        }
    };

    function getBin() {
        var ccNumber = document.querySelector('input[data-checkout="cardNumber"]');
        return ccNumber.value.replace(/[ .-]/g, '').slice(0, 6);
    };

    function guessingPaymentMethod(event) {
        var bin = getBin();

        if (event.type === "keyup") {
            if (bin.length >= 6) {
                Mercadopago.getPaymentMethod({
                    "bin": bin
                }, setPaymentMethodInfo);
            }
        } else {
            setTimeout(function() {
                if (bin.length >= 6) {
                    Mercadopago.getPaymentMethod({
                        "bin": bin
                    }, setPaymentMethodInfo);
                }
            }, 100);
        }
    };

    function setPaymentMethodInfo(status, response) {
        if (status === 200) {
           // do somethings ex: show logo of the payment method
           var form = document.querySelector('#pay');

           if (document.querySelector("input[name=paymentMethodId]") === null) {
               var paymentMethod = document.createElement('input');
               paymentMethod.setAttribute('name', "paymentMethodId");
               paymentMethod.setAttribute('type', "hidden");
               paymentMethod.setAttribute('value', response[0].id);

               form.appendChild(paymentMethod);
           } else {
               document.querySelector("input[name=paymentMethodId]").value = response[0].id;
           }
       }
    };

    addEvent(document.querySelector('input[data-checkout="cardNumber"]'), 'keyup', guessingPaymentMethod);
    addEvent(document.querySelector('input[data-checkout="cardNumber"]'), 'change', guessingPaymentMethod);

    doSubmit = false;
        addEvent(document.querySelector('#pay'), 'submit', doPay);
        
        function doPay(event){
            event.preventDefault();
            if(!doSubmit){
                var $form = document.querySelector('#pay');

                Mercadopago.createToken($form, sdkResponseHandler); // The function "sdkResponseHandler" is defined below

                return false;
            }
        };
        
    function sdkResponseMessages(code){
        msg = '';

        if(code!==""){        
            switch(code){
                case('205'): msg = 'Digite o seu número de cartão'; break;
                case('208'): msg = 'Escolha um mês'; break;
                case('209'): msg = 'Escolha um ano'; break;
                case('212'): msg = 'Insira o seu documento'; break;
                case('213'): msg = 'Insira o seu documento'; break;
                case('214'): msg = 'Insira o seu documento'; break;
                case('220'): msg = 'Digite o seu banco emissor'; break;
                case('221'): msg = 'Insira o nome e o sobrenome'; break;
                case('224'): msg = 'Digite o código de segurança'; break;
                case('E301'): msg = 'Há algo errado com o número do cartão. Volte a digitá-lo'; break;
                case('E302'): msg = 'Revise o código de segurança'; break;
                case('316'): msg = 'Insira um nome válido'; break;
                case('322'): msg = 'Revise o seu documento'; break;
                case('323'): msg = 'Revise o seu documento'; break;
                case('324'): msg = 'Revise o seu documento'; break;
                case('325'): msg = 'Revise a data'; break;
                case('326'): msg = 'Revise a data'; break;
                default: msg = 'Revise os dados';
            }        
        }else{
            msg = '';
        }
        return msg;
    }    

    function sdkResponseHandler(status, response) {
        if (status !== 200 && status !== 201) {
            mensagemErro = sdkResponseMessages(response.cause[0].code);

            $("#msg-alert").html(mensagemErro);
            $("#box-msg-alert").fadeIn();
            
        }else{
            $("#btnpayment").css("background-color", "#ccc");
            $("#btnpayment").prop("disabled", true);
            
            var form = document.querySelector('#pay');
            var card = document.createElement('input');
            card.setAttribute('name', 'token');
            card.setAttribute('type', 'hidden');
            card.setAttribute('value', response.id);
            form.appendChild(card);
            doSubmit=true;            
            form.submit();
           
        }
    };