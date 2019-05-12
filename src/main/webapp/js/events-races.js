$(document).ready(function(){
    var formatNumbers = function(num,place){

        var n = Number(num).toFixed(place), aux = 0;
        var nText = n.replace('.',','), txtShow = '';

        txtShow = nText.slice(nText.length - (place + 1), nText.length);

        for (var i = nText.length - (place + 2); i > -1; i--){
                aux++;

                if((aux === 3) && (i !== 0)){
                        txtShow = '.' + nText.charAt(i) + txtShow;
                        aux = 0;
                }else{
                        txtShow = nText.charAt(i) + txtShow;
                }
        }

       return txtShow;
    };
    
   function hideScroll(){
       $('html, body').animate({scrollTop:0}).css({'overflow-y':'hidden'});
   }
   function showScroll(){
       $('html, body').css({'overflow-y':'auto'});
   }    
    /*
     * Função detailOn
     * @returns {null}
     */
   detailOn = function(content){
       content.addClass('details');
       $(".overlay").fadeIn();        
   };
   /*
    * Função detailOff
    * @returns {null}
    */
   function detailOff(content){
       content.removeClass('details');
       $(".overlay").fadeOut('fast');
    } 
  /*
   * Função cancelAction
   * @returns {null}
   */
    function cancelAction(){
        /*ao clicar no botão de cancelar, mostrar a confirmação*/
        $(".btn_cancel").bind("click", function(){
            dataHora = $(this).attr('value');
            cod = $(this).attr('name');
            //mandar os dados da corrida para o botão SIM
            $('#accept_yes').attr('name', cod).attr('value', dataHora);
                        
            hideScroll();
            $(".overlay, #boxCancel").fadeIn();
        });
    }
   
    /*quando a opção NÃO for clicada*/
    $("#accept_no").bind("click", function(){
       $("#boxCancel, .overlay").fadeOut();
       showScroll();
    });
    
    function loadPage(href) {
        var content = $('#content');
        
        //$("html, body").animate({scrollTop:0}, "slow");//subir para o top
        $.ajax({
            url: href,
            beforeSend: function(){
                content.html( '<img src="img/icons/loader.gif" alt="Loading..." class="img-responsive loading"/>' );//gif loading
            },
            success: function (response) {                  
                content.html(response);
            },
            error: function(error){
                content.html('<div class="error">Erro ao carregar a página! Tente novamente</div>');                
            }
        });
    }
    $('#btn-close').bind('click', function(){
        $('#frm-race, .overlay').fadeOut();
        loadPage('races.jsp');
        showScroll();
    });
    /*
     * Função: Mostrar mensagem ao excluir uma corrida
     * @param type - mensagem de sucesso ou falha
     * return - {null}
     */
    function showMessage(msg, type){
        
        frame = '#frm-race';
        switch(type){
            case "error":

                $('#boxCancel').fadeOut('fast');
                
                setTimeout(function () {
                    $(frame).fadeIn();
                    $(frame + ' #frm-content').html(msg).css({'color':'#f20000'});
                }, 500);
                
                break;
            case "success":          
                $('#boxCancel').fadeOut('fast');
                setTimeout(function(){
                    $(frame).fadeIn();
                    $(frame + ' #frm-content').html(msg).css({'color':'#07cb07'}); 
                }, 500);
            break;
        }
        
    }
    /*quando a opção NÃO for clicada*/
    $("#accept_yes").bind("click", function(){       
        codigo = $(this).attr('name');
        dataHora = $(this).attr('value');
        //desabilitar botão de excluir
        $(this).prop("disabled", true);
        $(this).css("opacity", "0.8");
        $(".loading_box").fadeIn();
       
        $.ajax({
            type:'POST',
            url: 'CancelRunning',
            data: {
                codigo: codigo,
                dataHora: dataHora
            },
            success: function(retorno){
                
                if(retorno){
                    mensagem = 'Corrida excluída com sucesso';
                    tipo = 'success';                    
                }else{
                    $(".loading_box").fadeOut();
                    $("#accept_yes").prop("disabled", false);
                    mensagem = 'Erro ao excluir a corrida! Tente novamente';
                    tipo = 'error';
                }
                showMessage(mensagem, tipo);
                //habilitar botão de excluir
                $("#accept_yes").prop("disabled", false);
                $("#accept_yes").css("opacity", "1");
            }
       });      
    });
    
    /*
     * Função carousel 
     * esconde Div's caso a tela seja menor que 992px
     */
    function carousel() {
        var widthTela = $(window).width();
        if (widthTela < 992) { 
            $('.info-detail:not(:first)').fadeOut();
            $('.top-detail:first').addClass('infoActive');
        }
    }
    carousel();//chamar função ao carregar a página
    /*
     * Quando a tela for redimensionada
     */
    function redimencionaTela(){
        $(window).resize(function(){
            var tamanho = $(window).width(); //pega a largura da tela 
            if (tamanho > 992) { //se maior que 992px                          
                $('.info-detail').fadeIn(); //mostra todos os detalhes
                $('.top-detail').each(function () { 
                    if ($(this).hasClass('infoActive')) {
                        $(this).removeClass('infoActive');//remove classe de ativado
                    }
                });
            }else{           
                $('.info-detail:not(:first)').fadeOut(); //esconde todos os detalhes, exceto o primeiro        
                $('.top-detail:first').addClass('infoActive');//add class que ativa o primeiro código
            }
        });
    }
    function showContent(){
       var wd = $(window).width();//tamanho da tela     
       
       if(wd<992){
           $('.info-detail:not(:first)').fadeOut(); //esconde todos os detalhes, exceto o primeiro
       }
       
        $('.top-detail').bind('click', function () { //ao clicar no código           
            if ($(window).width() < 992) {
                if ($(this).hasClass('infoActive')) { //se o cod tiver ativado, não faça nada
                }else { 
                    $('.info-detail').fadeOut();//esconder detalhes
                    $('.top-detail').each(function () {
                        if ($(this).hasClass('infoActive')) {
                            $(this).removeClass('infoActive');
                        }
                    });
                    $(this).addClass('infoActive');
                    $(this).next('.info-detail').fadeIn(); //mostrar os detalhes do cod clicado
                }
            }       
        });
    }
    function showDetails(){
        $(".btn_detail").bind("click", function () {
       
            var conteudo = $(this).parent().parent().parent().find('.more-detail');
           
            hideScroll();
            detailOn(conteudo);
        });
    }
    
    function closeDetails(){   
        $("i.close").bind("click", function () {
            var conteudo = $(this).parent();
            showScroll();
            detailOff(conteudo);
        });
    }
    
    function generateStringRace(texto){
        var retorno = '', situacao = Number(texto);
        switch (situacao) {
            case 1:
                retorno = 'À concluir';
                break;
            case 2:
                retorno = 'Concluida';
                break;
            case 0:
                retorno = 'Cancelada';
                break;
            default :
                retorno = '';
                break;
        }
        return retorno;
    }
    
    function generateStringPayment(texto){
        var retorno = '';
        switch (texto){
            case 'in_process':
                retorno = '<span style="color:#ae9e06">Processando</span>';
            break;
            case 'approved':
                retorno = '<span style="color:#168b16">Aprovado</span>';
            break;
            case 'cancelled':
                retorno = '<span style="color:#c30606">Cancelado</span>';
            break;
            case 'refunded':
                retorno = '<span style="color:#757575">Devolvido</span>';
            break;
            default :
                retorno = '<span style="color:#d98a02">Analisando...</span>';
            break;
        }
        return retorno;
    }
    //ler informações das corridas
    function readRaces() {
        receiver = $('#tableBody');
        $.ajax({
            type: "post",
            url: "ReadRunning",
            dateType:'json',
            beforeSend: function(){
                receiver.html( '<img src="img/icons/loader.gif" alt="Loading..." class="img-responsive loading"/>' );//gif loading
            },
            success: function (response) {
                //limpar box que recebe conteúdo
                receiver.html('');
                //converter para JSON 
                json = JSON.parse(response);
                
                //se não estiver vazio, display                
                if(json.length > 0){
                    $(json).each(function(index, info){
                        //se false, bloquear botão cancelar
                        estadoAtual = Number(info.status);
                        if(estadoAtual !== 1){
                            formata = 'style="opacity:0.6" disabled';
                        }else{formata='';}
                        
                        paymentStatus = info.payment.status;
                        raceStatus = info.status;

                        var box = '<div class="col-lg-12 each-tr nopadding">';
                        box += '<div class="top-detail noselect"><div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 downLine"><span class="glyphicon glyphicon-barcode visible-xs-inline-block" style="color:white"></span> <span class="info">#'+info.id+'</span></div></div>'; 
                        box += '<div class="info-detail">';
                        box += '<div class="col-lg-3 col-md-2 col-sm-12 col-xs-12 downLine"><button type="button" class="btn_black btn_detail">Detalhes<span class="glyphicon glyphicon-search"></span></button></div>';
                        box += '<div class="col-lg-2 col-md-3 col-sm-12 col-xs-12 downLine"><span class="info">'+generateStringPayment(paymentStatus)+'</span></div>';
                        box += '<div class="col-lg-2 col-md-3 col-sm-12 col-xs-12 downLine"><span class="info">'+generateStringRace(raceStatus)+'</span></div>';
                        box += '<div class="col-lg-3 col-md-2 col-sm-12 col-xs-12 downLine"><button type="button" class="btn_black btn_cancel" name="'+info.payment.id+'" value="'+info.dataHour+'" '+formata+'>Cancelar<i class="cancelclose"><img src="img/icons/delete-close.png"></i></button></div></div>';
                        box += '<div class="more-detail" id="detail_1">';
                        box += '<i class="close"><img src="img/icons/delete-close.png"></i>';
                        box += '<header><h5>Detalhes da corrida</h5></header>';
                        box += '<div class="md-line"><span class="md-title font18">Classe:</span><span class="md-content">'+info.classCar.name+'</span></div>';
                        box += '<div class="md-line"><span class="md-title">Preço hora: R$</span><span class="md-content">'+formatNumbers(info.classCar.priceHour,2)+'</span></div>';
                        box += '<div class="md-line"><span class="md-title">Preço quilômetro: R$</span><span class="md-content">'+formatNumbers(info.classCar.priceKm,2)+'</span></div>';
                        box += '<div class="md-line"><span class="md-title">Quilômetros adicionais:</span><span class="md-content">'+formatNumbers(info.valueAddKm,3)+'km</span></div>';
                        box += '<div class="md-line"><span class="md-title">Quilômetros contratados:</span><span class="md-content">'+formatNumbers(info.kmCont,3)+'km</span></div>';
                        box += '<div class="md-line"><span class="md-title">Horas contratadas:</span><span class="md-content">'+info.hourCont+'</span></div>';
                        box += '<div class="md-line"><span class="md-title">Nome do passageiro:</span><span class="md-content">'+info.namePassenger+'</span></div>';
                        box += '<br>';
                        
                        box += '<div class="md-line"><span class="md-title font18">Informações do cartão:</span></div><br/>';
                        box += '<div class="md-line"><span class="md-title">Descrição:</span><span class="md-content">'+info.payment.description+'</span><span class="md-title"> / Cartão:</span><span class="md-content"><img src="img/icons/'+info.payment.flagCard+'.png" alt="'+info.payment.flagCard+'" style="width:35px; margin-top:-15px;"></span></div>';
                        
                        box += '<br>';
                        box += '<div class="md-line"><span class="md-title font18">Origem: </span><span class="md-content">'+info.origin+'</span></div>';
                        box += '<div class="md-line"><span class="md-title font18">Destino: </span><span class="md-content">'+info.destination+'</span></div>';
                        box += '<div class="holder">';
                        box += '<div class="md-line inline-title"><span class="md-title font18">Data:</span><span class="md-content">'+info.data+'</span></div>';
                        box += '<div class="md-line inline-title"><span class="md-title font18">Hora:</span><span class="md-content">'+info.hour+'</span></div>';
                        box += '<div class="md-line inline-title"><span class="md-title font18">Vôo</span><span class="md-content">'+info.numFlight+'</span></div>';
                        box += '</div>';
                        box += '<br>';
                        
                        servico = info.service.item;
                        //listar serviços extras se tiver
                        if(servico.length>0){
                            box += '<div class="md-line"><span class="md-title font18">Serviços Adicionais: </span></div>';
                            $(servico).each(function(i, service){
                                var detalhe = service.addService.description;
                                if(detalhe==="Cadeira para bebê."){
                                    box += '<div class="md-line"><span class="md-title">'+service.addService.description+'</span><span class="md-content"></span></div>';
                                }else{
                                    box += '<div class="md-line"><span class="md-title">'+service.addService.description+':</span><span class="md-content">'+service.details+'</span></div>';
                                }                                
                            });
                        }           
                        box += '<div class="divider"></div><br>';
                        box +=  '<div class="md-line"><span class="md-title">Total quilômetros adicionais: R$ </span><span class="md-content">'+formatNumbers(info.totalKmAdd,2)+'</span></div>';
                        box +=  '<div class="md-line"><span class="md-title">Subtotal: R$ </span><span class="md-content">'+formatNumbers(info.totalPartial,2)+'</span></div>';
                        box +=  '<span class="md-line"><span class="md-title" id="total-final">Total: R$ '+formatNumbers(info.total,2)+'</span></span>';
                                                
                        //incluir o conteúdo na página
                        $(box).appendTo(receiver);
                    });
                }
                showDetails();//mostrar os detalhes
                closeDetails();//esconder os detalhes
                cancelAction();//cancelar corrida
                showContent();
                redimencionaTela();
            }            
        }).fail(function(){
            receiver.html('<div class="error"><span>Desculpe-nos! Ocorreu um erro na requisição. Tente novamente</span></div>');
        });   
    }
    /*Carregar as corridas do usuário logado*/
    readRaces();
});