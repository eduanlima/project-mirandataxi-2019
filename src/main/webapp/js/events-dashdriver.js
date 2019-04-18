/* 
 * Funções da Dashboard
 */


$(document).ready(function () {
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

    window.search = function (obj) {
        //$(":input, a").attr("tabindex", "-1"); //se quiser bloquear a tecla TAB, descomentar essa linha
        $(obj).removeClass('search');
        $('.search').each(function (index, el) {
            $(this).val('');
        });
        $(obj).addClass('search');
    };

    $("#btnsearch").on("click", function (ev) {
        ev.preventDefault();
        //chama função para pesquisa
        reload();
    });

    function generateStringRace(texto) {
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

    function generateStringPayment(texto) {
        var retorno = '';
        switch (texto) {
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


    function hideScroll() {
        $('html, body').animate({scrollTop: 0}).css({'overflow-y': 'hidden'});
    }
    function showScroll() {
        $('html, body').css({'overflow-y': 'auto'});
    }
    /*
     * Função detailOn
     * @returns {null}
     */
    function detailOn(content) {
        content.addClass('details');
        $(".overlay").fadeIn();
    }
    ;
    /*
     * Função detailOff
     * @returns {null}
     */
    function detailOff(content) {
        content.removeClass('details');
        $(".overlay").fadeOut('fast');
    }
    /*
     * Função concluirAction
     * @returns {null}
     */
    function concluirAction() {
        /*ao clicar no botão de concluir, pedir confirmação*/
        $(".btn_concluir").bind("click", function () {
            var dataHora = $(this).attr('value');
            var cod = $(this).attr('name');
            //mandar os dados da corrida para o botão SIM
            $('#conclude_yes, #cancelNo, #cancelYes').attr('name', cod).attr('value', dataHora);

            hideScroll();
            $(".overlay, #boxConcluir").fadeIn();
        });
    }

    /*
     * Função cancelAction
     * @returns {null}
     */
    function cancelAction() {
        /*ao clicar no botão de cancelar, pedir a confirmação*/
        $(".btn_cancel").bind("click", function () {
            var dataHora = $(this).attr('value');
            var cod = $(this).attr('name');
            //mandar os dados da corrida para o botão SIM
            $('#accept_yes, #cancelNo, #cancelYes').attr('name', cod).attr('value', dataHora);

            hideScroll();
            $(".overlay, #boxCancel").fadeIn();
        });
    }

    function excluirCorrida(codigo, situacao) {

        $.ajax({
            type: 'POST',
            url: 'CancelRunningDriver',
            data: {
                codigo: codigo,
                situacao: situacao
            },
            success: function (retorno) {
                if (retorno) {
                    $("#boxLastStep").fadeOut();
                    showMessage('Corrida cancelada com sucesso', 'success');
                }
            }
        });
    }

    /*Concluir corrida - quando a opção SIM for clicada*/
    $("#conclude_yes").bind("click", function () {
        var codigo = $(this).attr('value');

        $.ajax({
            type: 'POST',
            url: 'FinishRunning',
            data: {
                codigo: codigo
            },
            success: function (retorno) {
                if (retorno) {
                    //desabilitar botão de excluir
                    $(this).prop("disabled", true);
                    $(this).css("opacity", "0.8");
                    $("#boxConcluir").hide();
                    $("#frm-content").css("color", "#137900").text("Corrida finaliza com sucesso!");
                    $("#frm-race").show();
                } else {
                    $("#boxConcluir").hide();
                    $("#frm-content").css("color", "#ff4444").text("Ocorreu um erro ao finalizar a corrida. Tente novamente");
                    $("#frm-race").show();
                }
            }
        });
    });

    /*Cancelar corrida - quando a opção SIM for clicada*/
    $("#accept_yes").bind("click", function () {
        var codigo = $(this).attr('name');
        var dataHora = $(this).attr('value');

        //desabilitar botão de excluir
        $(this).prop("disabled", true);
        $(this).css("opacity", "0.8");

        $.ajax({
            type: 'POST',
            url: 'ReadTimeRunning',
            data: {
                codigo: codigo,
                dataHora: dataHora
            },
            success: function (retorno) {
                dados = $.parseJSON(retorno);
                if (dados) {
                    if (dados[0].situacao === 0) {
                        $("#boxCancel").hide();
                        $("#mensagemConfirma").html(dados[0].mensagem);
                        $("#boxLastStep").fadeIn();
                        //habilitar botão de excluir
                        $("#accept_yes").prop("disabled", false);
                        $("#accept_yes").css("opacity", "1");
                    } else {
                        excluirCorrida(codigo, 1);
                    }
                }
            }
        });
    });
    /*
     * Ao clicar no botão confirmar, fechar todos os boxes
     */
    $("#btn-close").on("click", function () {
        $("#frm-race, .overlay").fadeOut();
        reload();
    });

    $("#cancelarCancel").on("click", function () {
        $("#boxLastStep, .overlay").fadeOut();
    });
    window.cancelarCorrida = function (obj, type) {
        obj = $(obj); //botão
        var situacao = 1;//tipo de cancelamento
        if (type === "no")
            situacao = 0;

        codigo = obj.attr("name");//código da corrida
        //dataHora = obj.attr("value");//data e hora

        $("#boxLastStep").fadeOut();
        //showMessage('Corrida cancelada com sucesso', 'success');
        excluirCorrida(codigo, situacao);
    };

    /*quando a opção NÃO for clicada*/
    $("#accept_no, #conclude_no").bind("click", function () {
        $("#boxCancel, .overlay, #boxConcluir").fadeOut();
        showScroll();
    });

    /*
     * Função: Mostrar mensagem ao excluir uma corrida
     * @param type - mensagem de sucesso ou falha
     * return - {null}
     */
    function showMessage(msg, type) {

        frame = '#frm-race';
        switch (type) {
            case "error":

                $('#boxCancel').fadeOut('fast');

                setTimeout(function () {
                    $(frame).fadeIn();
                    $(frame + ' #frm-content').html(msg).css({'color': '#f20000'});
                }, 500);

                break;
            case "success":
                $('#boxCancel').fadeOut('fast');
                setTimeout(function () {
                    $(frame).fadeIn();
                    $(frame + ' #frm-content').html(msg).css({'color': '#119011'});
                }, 500);
                break;
        }

    }

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
    function redimencionaTela() {
        $(window).resize(function () {
            var tamanho = $(window).width(); //pega a largura da tela 
            if (tamanho > 992) { //se maior que 992px                          
                $('.info-detail').fadeIn(); //mostra todos os detalhes
                $('.top-detail').each(function () {
                    if ($(this).hasClass('infoActive')) {
                        $(this).removeClass('infoActive');//remove classe de ativado
                    }
                });
            } else {
                $('.info-detail:not(:first)').fadeOut(); //esconde todos os detalhes, exceto o primeiro        
                $('.top-detail:first').addClass('infoActive');//add class que ativa o primeiro código
            }
        });
    }
    function showContent() {
        var wd = $(window).width();//tamanho da tela     

        if (wd < 992) {
            $('.info-detail:not(:first)').fadeOut(); //esconde todos os detalhes, exceto o primeiro
        }

        $('.top-detail').bind('click', function () { //ao clicar no código           
            if ($(window).width() < 992) {
                if ($(this).hasClass('infoActive')) { //se o cod tiver ativado, não faça nada
                } else {
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
    function showDetails() {
        $(".btn_detail").bind("click", function () {

            var conteudo = $(this).parent().parent().parent().find('.more-detail');

            hideScroll();
            detailOn(conteudo);
        });
    }

    function closeDetails() {
        $("i.close").bind("click", function () {
            var conteudo = $(this).parent();
            showScroll();
            detailOff(conteudo);
        });
    }

    /*
     *Função first_load()
     *Carrega a primeira página ao entrar na dashboard
     */
    function first_load() {
        var url = window.location.href;
        var res = url.split("#");
        var link = res[1];

        if (!link) {
            link = $('.profile-usermenu .nav li:first').find('a').attr('href');
            $('.profile-usermenu .nav li:first').addClass('active');//ativa o link clicado
        } else {
            $('#' + link).addClass('active');
            link = link + '.jsp';
        }
        var first_link = link;
        reload();
    }
    first_load();
    $('.container-mn .nav-mini .each-class').bind('click', function (ev) {
        ev.preventDefault();//evita ação padrão
        var atual = $(this);

        if (atual.hasClass('active')) {/*não faça nada*/
        } else {
            /*procura e retira a classe ACTIVE do link ativo*/
            $('.container-mn .nav-mini .each-class').each(function () {
                if ($(this).hasClass('active')) {
                    $(this).removeClass('active');
                }
            });
            $(this).addClass('active');//ativa o link clicado
            reload();//chama a função que faz o reload
        }
    });

    function reload() {
        var classe = '';
        $(".each-class").each(function (key, el) {
            if ($(this).hasClass("active")) {
                classe = $(this).attr("id");
            }
        });

        //buscar dados para pesquisa
        var idservico = $("#codigo").val();
        var cliente = $("#cliente").val();
        var passageiro = $("#passageiro").val();
        var data = $("#data").val();

        receiver = $('#content');  //tela        

        if (idservico != "" || cliente != "" || passageiro != "" || data != "") {
            $(".searching").css("border-color", "green");
            classe = (classe != 'all') ? classe : 'none';
            idservico = (idservico) ? idservico : 0;
            cliente = (cliente) ? cliente : 'none';
            passageiro = (passageiro) ? passageiro : 'none';
            data = (data) ? data : 'none';

            if (idservico && idservico > 0) {
                classe = "none";
                $(".each-class").removeClass("active");
                $("#all").addClass("active");
            }
            var href = 'ReadRunningDriver';

            receiver.html('<img src="img/icons/loader.gif" alt="Loading..." class="img-responsive loading"/>');

            $.ajax({
                type: 'post',
                url: href,
                dateType: 'json',
                data: {classe: classe, idservico: idservico, cliente: cliente, passageiro: passageiro, data: data},

                success: function (response) {
                    json = JSON.parse(response);
                    window.setTimeout(function () {

                        if (json.length > 0) {
                            var head = '';
                            head += '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 nopadding" style="margin-bottom:15px !important;">';
                            head += '    <div class="col-lg-12 nopadding" id="tableHead">';
                            head += '        <div class="col-lg-12 each-tr noselect nopadding">';
                            head += '            <div class="col-lg-2 col-md-2 col-sm-3 topLine">Código<span class="icons"><img src="img/icons/icon-cod.png" alt="Código"></span></div>';
                            head += '            <div class="col-lg-3 col-md-2 col-sm-3 topLine">Detalhes<span class="icons"><img src="img/icons/icon-details.png" alt="Detalhes"></span></div>';
                            head += '            <div class="col-lg-2 col-md-3 col-sm-3 topLine">Pagamento<span class="icons"><img src="img/icons/icon-pay.png" alt="Pagamento"></span></div>';
                            head += '            <div class="col-lg-2 col-md-3 col-sm-3 topLine">Status<span class="icons"><img src="img/icons/attention.png" alt="Status"></span></div>';
                            head += '           <div class="col-lg-3 col-md-2 col-sm-3 topLine">Ação<span class="icons"><img src="img/icons/icon-action.png" alt="Ação"></span></div>';
                            head += ' </div></div></div>';

                            $(receiver).html('');
                            $(head).appendTo(receiver);
                            $(json).each(function (index, info) {

                                //se false, bloquear botão cancelar
                                var estadoAtual = Number(info.status);
                                var formata = '';
                                if (estadoAtual !== 1) {
                                    formata = 'style="opacity:0.6" disabled';
                                } else {
                                    formata = '';
                                }

                                var paymentStatus = info.payment.status;
                                var raceStatus = info.status;

                                var box = '<div class="col-lg-12 each-tr nopadding">';
                                box += '<div class="top-detail noselect"><div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 downLine"><span class="glyphicon glyphicon-barcode visible-xs-inline-block" style="color:white"></span> <span class="info">#' + info.id + '</span></div></div>';
                                box += '<div class="info-detail">';
                                box += '<div class="col-lg-3 col-md-2 col-sm-12 col-xs-12 downLine"><button type="button" class="btn_black btn_detail">Detalhes<span class="glyphicon glyphicon-search"></span></button></div>';
                                box += '<div class="col-lg-2 col-md-3 col-sm-12 col-xs-12 downLine"><span class="info">' + generateStringPayment(paymentStatus) + '</span></div>';
                                box += '<div class="col-lg-2 col-md-3 col-sm-12 col-xs-12 downLine"><span class="info">' + generateStringRace(raceStatus) + '</span></div>';
                                box += '<div class="col-lg-3 col-md-2 col-sm-12 col-xs-12 downLine"><button type="button" class="btn_black btn_cancel" name="' + info.payment.id + '" value="' + info.dataHour + '" ' + formata + '>Cancelar<span class="glyphicon glyphicon-remove"></span></button><button type="button" class="btn_black btn_concluir" value="' + info.id + '" ' + formata + '>Concluir<span class="glyphicon glyphicon-ok"></span></button></div></div>';
                                box += '<div class="more-detail" id="detail_1">';
                                box += '<i class="close"><span class="glyphicon glyphicon-remove"></span></i>';
                                box += '<header><h5>Detalhes da corrida</h5></header>';
                                box += '<div class="md-line"><span class="md-title font18">Classe:</span><span class="md-content">' + info.classCar.name + '</span></div>';
                                box += '<div class="md-line"><span class="md-title">Preço hora: R$</span><span class="md-content">' + formatNumbers(info.classCar.priceHour,2) + '</span></div>';
                                box += '<div class="md-line"><span class="md-title">Preço quilômetro: R$</span><span class="md-content">' + formatNumbers(info.classCar.priceKm,2) + '</span></div>';
                                box += '<div class="md-line"><span class="md-title">Quilômetros adicionais:</span><span class="md-content">' + formatNumbers(info.valueAddKm,3)+ 'km</span></div>';
                                box += '<div class="md-line"><span class="md-title">Quilômetros contratados:</span><span class="md-content">' + formatNumbers(info.kmCont,3) + 'km</span></div>';
                                box += '<div class="md-line"><span class="md-title">Horas contratados:</span><span class="md-content">' + info.hourCont + '</span></div>';
                                box += '<div class="md-line"><span class="md-title">Nome do cliente:</span><span class="md-content">' + info.client.name + '</span></div>';
                                box += '<div class="md-line"><span class="md-title">Nome do passageiro:</span><span class="md-content">' + info.namePassenger + '</span></div>';

                                box += '<br>';
                                box += '<div class="md-line"><span class="md-title font18">Informações do cartão:</span></div><br/>';
                                box += '<div class="md-line"><span class="md-title">Descrição:</span><span class="md-content">' + info.payment.description + '</span><span class="md-title"> / Cartão:</span><span class="md-content"><img src="img/icons/' + info.payment.flagCard + '.png" alt="' + info.payment.flagCard + '" style="width:35px; margin-top:-15px;"></span></div>';

                                box += '<br>';
                                box += '<div class="md-line"><span class="md-title font18">Origem: </span><span class="md-content">' + info.origin + '</span></div>';
                                box += '<div class="md-line"><span class="md-title font18">Destino: </span><span class="md-content">' + info.destination + '</span></div>';
                                box += '<div class="holder">';
                                box += '<div class="md-line inline-title"><span class="md-title font18">Data:</span><span class="md-content">' + info.data + '</span></div>';
                                box += '<div class="md-line inline-title"><span class="md-title font18">Hora:</span><span class="md-content">' + info.hour + '</span></div>';
                                box += '<div class="md-line inline-title"><span class="md-title font18">Vôo</span><span class="md-content">' + info.numFlight + '</span></div>';
                                box += '</div>';
                                box += '<br>';

                                var servico = info.service.item;
                                //listar serviços extras se tiver
                                if (servico.length > 0) {
                                    box += '<div class="md-line"><span class="md-title font18">Serviços Adicionais</span></div>';
                                    $(servico).each(function (i, service) {
                                        var detalhe = service.addService.description;
                                        if (detalhe === "Cadeira para bebê") {
                                            box += '<div class="md-line"><span class="md-title">' + service.addService.description + ':</span><span class="md-content"></span></div>';
                                        } else {
                                            box += '<div class="md-line"><span class="md-title">' + service.addService.description + ':</span><span class="md-content">' + service.details + '</span></div>';
                                        }
                                    });
                                }
                                box += '<div class="divider"></div><br>';
                                box += '<div class="md-line"><span class="md-title">Total quilômetros adicionais: R$</span><span class="md-content">' +formatNumbers(info.totalKmAdd,2)+ '</span></div>';
                                box += '<div class="md-line"><span class="md-title">Subtotal: R$</span><span class="md-content">' + formatNumbers(info.totalPartial,2) + '</span></div>';
                                box += '<span class="md-line"><span class="md-title" id="total-final">Total: R$ ' + formatNumbers(info.total,2) + '</span></span>';

                                //incluir o conteúdo na página                                
                                $(box).appendTo(receiver);
                            });

                        } else {
                            receiver.html('<div class="none">Nenhum resultado encontrado para a pesquisa </div>');
                        }
                        showDetails();//mostrar os detalhes
                        closeDetails();//esconder os detalhes
                        cancelAction();//cancelar corrida                        
                        concluirAction();//cancelar corrida
                        showContent();
                        redimencionaTela();
                    }, 200);
                },
                error: function (error) {
                    receiver.html('<div class="error">Erro ao carregar a página! Tente novamente</div>');
                }
            });
        } else {
            $(".searching").css("border-color", "#ff0000");
            receiver.html('<div class="error">Informe algum dos campos para pesquisar</div>');
        }
    }
});

