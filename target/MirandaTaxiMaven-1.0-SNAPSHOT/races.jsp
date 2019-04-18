<%-- 
    Document   : Minhas Corridas
    Created on : 24/10/2017, 12:12:24
    Author     : Rafael Alves
--%>
<div id="page">                        
    <div class="row nopadding">
        <h2 id="titleRaces" class="noselect">Minhas corridas</h2>               
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 nopadding" >
            <div class="col-lg-12 nopadding" id="tableHead">
                <div class="col-lg-12 each-tr noselect nopadding">
                    <div class="col-lg-2 col-md-2 col-sm-3 topLine">Código<span class="icons"><img src="img/icons/icon-cod.png" alt="Código"></span></div>
                    <div class="col-lg-3 col-md-2 col-sm-3 topLine">Detalhes<span class="icons"><img src="img/icons/icon-details.png" alt="Detalhes"></span></div>
                    <div class="col-lg-2 col-md-3 col-sm-3 topLine">Pagamento<span class="icons"><img src="img/icons/icon-pay.png" alt="Pagamento"></span></div>
                    <div class="col-lg-2 col-md-3 col-sm-3 topLine">Status<span class="icons"><img src="img/icons/attention.png" alt="Status"></span></div>
                    <div class="col-lg-3 col-md-2 col-sm-3 topLine">Ação<span class="icons"><img src="img/icons/icon-action.png" alt="Ação"></span></div>
                </div>
            </div>
            <!--/tableBody recebe o conteúdo da corrida-->
            <div class="row nopadding" id="tableBody"></div>
        </div>
    </div>
</div>

<div class="row">
    <div id="boxCancel">     
        <header><h5>Tem certeza que deseja cancelar?</h5></header>
        <div class="optionButton">            
            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12"><button type="button" id="accept_no" class="btn_option">Não</button></div>
            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12"><button type="button" id="accept_yes" class="btn_option">Sim</button></div>
        </div>
    </div>
</div>

<div class="row">
    <div id="frm-race">
        <span id="frm-title" class="frm-line">Mensagem de retorno</span>
        <span id="frm-content" class="frm-line"></span>
        <button id="btn-close">Confirmar</button>
    </div>
</div>

<script src="js/events-races.js"></script>



