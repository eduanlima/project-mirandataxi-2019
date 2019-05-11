<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" media="screen" href="style/css-reset.css" />
        <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no" />
        <link rel="stylesheet" media="screen" href="style/fonts.css" />
        <link rel="stylesheet" media="screen" href="style/style-menu-main.css" />
        <link rel="stylesheet" media="screen" href="style/style-radios.css" />
        <link rel="stylesheet" media="screen" href="style/style-message.css" />        
        <link rel="stylesheet" media="screen" href="style/style-footer.css" />
        <link rel="stylesheet" media="screen" href="style/style-box-modal-in.css" />
        <link rel="stylesheet" media="screen" href="js/jquery-ui-1.12.1/jquery-ui.css"/>
        <link rel="stylesheet" media="screen" href="js/jquery-ui-1.12.1/jquery-ui.theme.css"/>
        <link rel="stylesheet" media="screen" href="style/style-register-service.css" />
        <link rel="stylesheet" media="screen" href="style/style-box-modal-recover.css" />
        <link rel="shortcut icon" type="image/x-icon" href="img/icons/favicon.ico">
        <!--BOOTSTRAP-->
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css"/>
        <!--DATAPICKER-->
        <link rel="stylesheet" media="screen" href="datepicker/css/datepicker.css"/>
        <title>Miranda Taxi</title>
    </head>
    <body>
        <div id="page">
            <%
            String userName = (String) session.getAttribute("userName");
            String redirectURL = "index.jsp";
            if (userName == null) {
                response.sendRedirect(redirectURL);
            }
            %>
            <%@include file="header.jsp" %>            
            <div class="container">
                <article role="main">
                    <div class="row">
                        <form id="form-main">
                            <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
                                <div id="div-service">
                                    <h1 class="tl-title">SERVIÇO</h1>
                                    <img id="img-car" class="img-responsive"/>
                                    <h2 id="name-class">Excutive Class</h2>
                                    <h3 class="h3-standard">Preço do serviço</h3>
                                    <p class="text-price"> Hora: R$ <span id="price-hour"></span>*</p>
                                    <p class="text-price"> Quilômetro: R$ <span id="price-km"></span>*</p>
                                    <div id="div-price">
                                        <h2>VALOR TOTAL</h2>
                                        <h5 id="h4-km-add" class="text-price">*Adicional: 0 km</h5>
                                        <h5 id="h4-total-km-add" class="text-price">*Adicional: R$ 0,00 </h5>
                                        <!-- EDITAR APRESENTAÇÃO TEXTO-->
                                        <h3 id="h3-price-total">R$ <span id="price-first"></span></h3>
                                        <h4 id="h4-distance" class="text-price">000</h4>
                                        <!-- <h4><span id="spn-hours" class="text-price">00 horas e 00 minutos</span></h4> -->
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-lg-5 col-md-6 col-sm-12 col-xs-12">
                                <div id="div-main-type-service">	
                                    <div id="div-type-service">
                                        <div id="div-hour-trip">
                                            <div id="div-hour">
                                                <input type="radio" name="service-type" value="h" class="radio-type" />
                                                <label id="lbl-trip-hours" class="label-standard label-one">Horas</label>
                                                <div class="form-group">
                                                    <select id="sct-hours" class="sct-standard form-control">
                                                        <option value="3.0">03</option>
                                                        <option value="4.0">04</option>
                                                        <option value="5.0">05</option>
                                                        <option value="6.0">06</option>
                                                        <option value="7.0">07</option>
                                                        <option value="8.0">08</option>
                                                        <option value="9.0">09</option>
                                                        <option value="10.0">10</option>
                                                        <option value="11.0">11</option>
                                                    </select>
                                                </div>
                                            </div> 

                                            <div id="div-trip">
                                                <input type="radio" name="service-type" value="t" class="radio-type"/>
                                                <label id="lbl-trip-flight" class="label-standard label-one">Promoções</label>
                                                <div class="form-group">
                                                    <select id="sct-trip" class="sct-standard space-standard form-control">
                                                        <option name="sct-trip-itens" value="spgru">De São Paulo a Guarulhos (aeroporto) - 3 horas</option>
                                                        <option name="sct-trip-itens" value="grusp">De Guarulhos (aeroporto) a São Paulo - 3 horas</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <p id="legal-text-service" class="legal-text">O serviço por hora deverá ser utilizado dentro do município da cidade São Paulo, caso ocorra alguma viagem para outro município o valor a ser cobrado será por quilômetros (km).</p>
                                        </div>	
                                        <div id="div-itens-standard">
                                            <div class="row">
                                                <div class="col-lg-4 col-md-12 col-sm-4 col-xs-6">
                                                    <div id="div-date-service" class="div-standard form-group">
                                                        <label for="txt-date" id="lbl-date" class="label-standard label-one">Data</label>
                                                        <div class="input-group date" id="datepicker1">
                                                            <input type="text" id="txt-date" class="txt-standard form-control" name="day-service" placeholder="dd/mm/yyyy" readonly="readonly"/>
                                                            <span class="input-group-addon">
                                                                <span class="glyphicon glyphicon-calendar"></span>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 col-md-12 col-sm-4 col-xs-6">
                                                    <div id="div-hour-service" class="div-standard">
                                                        <label for="txt-hours" id="lbl-hour" class="label-standard label-one">Hora</label>
                                                        <input type="time" id="txt-hours" class="txt-standard form-control" name="date-service" placeholder="00:00"/>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 col-md-12 col-sm-4 col-xs-12">
                                                    <div id="div-flight-service" class="div-standard">
                                                        <label for="txt-flight" id="lbl-flight" class="label-standard label-one">N° do vôo</label>
                                                        <input type="text" id="txt-flight" class="txt-standard form-control" name="date-service"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-4 col-xs-12">
                                        <div id="passenger" class="div-standard">
                                            <label for="txt-flight" id="lbl-passenger" class="label-standard label-one">Passageiro</label>
                                            <input type="text" id="txt-passenger" class="txt-standard form-control" name="passenger" placeholder="Nome passageiro"/>                                    
                                        </div>
                                    </div>
                                    <fieldset id="fld-from" class="fld-standard">
                                        <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <legend id="legend-from" class="legend-standard">Origem</legend>
                                            <input type="text" id="txt-from-street" class="txt-standard form-control" name="from-street" placeholder="Pesquise o local (endereço)"/>
                                        </div>
                                        
                                        <div class="form-group col-lg-6 col-md-12 col-sm-12 col-xs-12">
                                            <input type="text" id="txt-from-neighbor" class="txt-standard form-control" name="from-neighbor" placeholder="Bairro"/>
                                        </div>
                                        <div class="form-group col-lg-6 col-md-12 col-sm-12 col-xs-12">
                                            <input type="text" id="txt-from-city" class="txt-standard form-control" name="from-city" placeholder="Cidade"/>
                                        </div>

                                        <label id="lbl-text-legal-from" class="legal-text lbl-right">Apenas <label id="lbl-from-uf" class="label-standard label-one lbl-text-trip">SP</label> Para cidades de outro estado, envie-nos um e-mail.</label>
                                    </fieldset>

                                    <fieldset id="fld-to" class="fld-standard">                                        
                                        <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <legend id="legend-to" class="legend-standard">Destino</legend>
                                            <input type="text" id="txt-to-street" class="txt-standard form-control" name="to-street" placeholder="Pesquise o local (endereço)"/>
                                        </div>
                                        
                                        <div class="form-group col-lg-6 col-md-12 col-sm-12 col-xs-12">
                                            <input type="text" id="txt-to-neighbor" class="txt-standard form-control" name="to-neighbor" placeholder="Bairro"/>
                                        </div>
                                        <div class="form-group col-lg-6 col-md-12 col-sm-12 col-xs-12">
                                            <input type="text" id="txt-to-city" class="txt-standard form-control" name="to-city" placeholder="Cidade"/>
                                        </div>                                    
                                        <label id="lbl-text-legal-to" class="legal-text lbl-right">Apenas <label id="lbl-to-uf" class="label-standard label-one lbl-text-trip">SP</label> Para cidades de outro estado, envie-nos um e-mail.</label>
                                    </fieldset>
                                    <label for="termosaceite" id="boxtermosaceite">
                                        <input type="checkbox" name="termosaceite" id="termosaceite" required/> Li e aceito os <a href="#modaltermos" data-toggle="modal">termos</a>
                                    </label>                                    
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
                                <div id="comments" class="fld-standard">
                                    <label for="cmp-comment" class="lbl-cmt">Observação</label>
                                    <textarea class="form-control" id="cmp-comment" name="comment" placeholder="Adicionar um comentário para o motorista"></textarea>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
                                <div id="div-map" class="div-standard">
                                    <iframe scrolling="no" id="iframe-map" class="div-standard" src="https://www.google.com/maps/embed?pb=!1m23!1m12!1m3!1d58520.78932032205!2d-46.658716033816994!3d-23.548706286961725!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m8!3e6!4m0!4m5!1s0x94ce448183a461d1%3A0x9ba94b08ff335bae!2sS%C3%A3o+Paulo+-+SP!3m2!1d-23.550519899999998!2d-46.633309399999995!5e0!3m2!1spt-BR!2sbr!4v1507684202404" allowfullscreen></iframe>                            
                                </div>                                                   
                            </div>
                            <div class="col-lg-4 col-md-12 col-sm-12 col-xs-12">
                                <div id="info_extra" class="fld-standard">
                                    <div class="div-color-add">
                                        <div id="div-add-kid-one" class="div-standard">
                                            <input type="checkbox" id="checkbox-kid" name="service-add" class="checkbox-add" />
                                            <label for="checkbox-kid" class="label-standard label-two">Utilizar cadeira para bebê;</label>
                                        </div>

                                        <div id="div-add-board-one" class="div-standard">
                                            <input type="checkbox" id="checkbox-board" name="service-add" class="checkbox-add"/>
                                            <label for="checkbox-board" class="label-standard label-two">Personalizar placa do veículo (Para casamentos, eventos, etc.)</label>
                                            <div id="div-add-board-two" class="div-standard">
                                                <input type="text" class="txt-standard form-control input-extra" id="check-board-we" placeholder="Nome a ser aplicado" name="name-board"/>
                                            </div>
                                        </div>

                                        <div id="div-add-reception-one" class="div-standard">
                                            <input type="checkbox" id="checkbox-reception" name="service-add" class="checkbox-add"/>
                                            <label for="checkbox-reception" class="label-standard label-two">Utilizar placa para recepção do passageiro.</label>
                                            <div id="div-add-reception-two" class="div-standard">
                                                <input type="text" class="txt-standard form-control input-extra" id="check-board-ps" placeholder="Nome a ser aplicado" name="name-board"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                            
                        
                            
                            <div class="col-lg-5 col-md-12 col-sm-12 col-xs-12 col-lg-push-3">
                                <button type="button" data-toggle="modal" data-target="#" class="btn" id="btt-next">Fazer pagamento</button>                               
                            </div>
                            
                            <input type="hidden" id="txt-value-run" name="txtValueRun"/>
                            <input type="hidden" id="txt-type-run" name="txtTypeRun"/>
                            <input type="hidden" id="txt-total-km" name="txtTotalKm"/>
                            <input type="hidden" id="txt-add-km" name="txtAddKm"/>
                            <input type="hidden" id="txt-add-km-prev" name="txtAddKmPrev"/>
                        </form>
                    </div>                
                </article>
                <!--Modal Tela Pagamento-->
                <div class="modal fade" id="payment" data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="title_modal">CONCLUIR COMPRA DO SERVIÇO</h4>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <form id="pay" name="pay">
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
                                            <label for="cardNumber" class="textLabel">Número do cartão</label>
                                            <input type="text" id="cardNumber" data-checkout="cardNumber" placeholder="0000 0000 0000 0000" onselectstart="return false" onpaste="return false" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" autocomplete=off  class="form-control" required/>
                                        </div>
                                        
                                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                            <label for="cardExpirationMonth" class="textLabel">Validade (Mês)</label>
                                            <input type="text" id="cardExpirationMonth" data-checkout="cardExpirationMonth" placeholder="12" onselectstart="return false" onpaste="return false" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" autocomplete=off  class="form-control" required/>
                                        </div>
                                        
                                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                            <label for="cardExpirationYear" class="textLabel">Validade (Ano)</label>
                                            <input type="text" id="cardExpirationYear" data-checkout="cardExpirationYear" placeholder="0000" onselectstart="return false" onpaste="return false" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" autocomplete=off  class="form-control" required />
                                        </div>
                                        
                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                            <label for="securityCode"class="textLabel">Código de segurança</label>
                                            <input type="text" id="securityCode" data-checkout="securityCode" placeholder="000" onselectstart="return false" onpaste="return false" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" autocomplete=off class="form-control" required />
                                        </div>
                                        
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <label for="cardholderName" class="textLabel">Nome do titular <small>(Exatamente como está no cartão)</small></label>
                                            <input type="text" id="cardholderName" data-checkout="cardholderName" placeholder="APRO"  class="form-control" required/>
                                        </div>
                                        
                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                            <label for="docType" class="textLabel">Tipo doc.</label>
                                            <select id="docType" data-checkout="docType" class="form-control"></select>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                            <label for="docNumber" class="textLabel">CPF</label>
                                            <input type="text" id="docNumber" data-checkout="docNumber" placeholder="000 000 000 00"  class="form-control" required/>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                            <span id="pay_value"><b>Total: </b><label id="lb_value_pay">R$999,99</label></span>
                                        </div>

                                        <div id="box-msg-alert" style="display: none;">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="alert alert-danger" id="msg-alert"></div>
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">    
                                            <input type="hidden" name="paymentMethodId" />
                                        </div>
                                        
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <input type="submit" value="PAGAR"  class="btn" id="btnpayment"/>
                                        </div>
                                        
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div id="brands">
                                                <div class="cards-title">Cartões de Crédito</div>
                                                <ul class="cards">
                                                    <li class="card"><img src="img/icons/visa.png" alt="Facebook" class="cards_img" id="visacard"></li>
                                                    <li class="card"><img src="img/icons/master.png" alt="Instagram" class="cards_img" id="mastercard"></li>
                                                    <li class="card"><img src="img/icons/amex.png" alt="Instagram" class="cards_img" id="americancard"></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>    
            </div>
            <div id="box-message">
                <h1 id="txt-main" class="h-message"></h1>
                <h2 id="txt-second" class="h-message"></h2>
                <button id="btt-ok">OK</button>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="showMessage" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content" style="padding-bottom: 10px;">
                        <div class="modal-header nopadding">                            
                            <span class="alert" id="box-alerta">Atenção</span>
                        </div>
                        <div class="modal-body" id="infoMensagem"></div>
                        <button type="button" class="btn-standard" data-dismiss="modal">FECHAR</button>
                    </div>
                </div>
            </div>
            
            <div class="modal fade" id="modaltermos" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal">&times;</button>
                          <h4 class="modal-title">TERMOS E CONDIÇÕES DE USO MIRANDATAXI</h4>
                        </div>
                        <div class="modal-body">
                            <%@include file="termos.jsp" %>
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn-standard" data-dismiss="modal">Fechar</button>
                        </div>
                    </div>
                </div>
            </div>
            
            
            <div id="black-page"></div>
            <%@include file="footer.jsp"%><!--rodapé-->   
        </div>

        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAiv7v_-hsRqyvaKraPEcxKy2OVzLC9I48&callback=initMap&address=BR&key=AIzaSyAiv7v_-hsRqyvaKraPEcxKy2OVzLC9I48&libraries=places&callback=initAutocomplete" type="text/javascript"></script>
        <script src="js/validation-register-service.js" charset="UTF-8"></script>
        <script src="js/box-message-show.js"></script>
        <script src="js/check-session.js" charset="UTF-8"></script>
        <script src="js/events-register-service.js" charset="UTF-8"></script>
        <script src="js/distance-route.js" charset="UTF-8"></script>
        <script src="js/autocomplete-places.js" charset="UTF-8"></script>
        <script src="js/image-car-selected.js"></script>
        <script src="js/jquery.mask.min.js"></script>
        <script src="js/all-masks.js"></script>
        <script src="datepicker/js/bootstrap-datepicker.js" charset="utf-8"></script>
        <script src="js/jquery-ui-1.12.1/jquery-ui.js" charset="utf-8"></script>
        <script src="https://secure.mlstatic.com/sdk/javascript/v1/mercadopago.js"></script>
        <script src="js/app-mercado-pago.js" charset="UTF-8"></script>    
        <script src="js/payment.js"></script>
        <script src="js/create-running.js"></script>
        
        <script type="text/javascript">
            $(function () {
                $('#txt-date').datepicker({                    
                    dateFormat: "dd/mm/yy",                
                    numberOfMonths: 1,
                    minDate: 2,
                    monthNames: ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
                    monthNamesShort: ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"],
                    dayNames: ["Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"],
                    dayNamesShort: ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
                    dayNamesMin: ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"],
                    onSelect: function(selectedDate) {
                       
                    }
                });                
                
            });
            
        </script>
    </body>
</html>