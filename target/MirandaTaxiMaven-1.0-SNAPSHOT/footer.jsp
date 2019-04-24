<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%! 
    //ano atual
    Date dataAtual = new Date(); 
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    String dataStr = sdf.format(dataAtual);
%>
<div id="footer">            
    <div class="row nopadding">
        <div class="container-fluid">
            <div class="col-lg-2 col-md-3 col-sm-6 col-xs-12 col-lg-push-2 nopadding">
                <div class="boxes">
                    <div class="titleBoxes">MENSAGEM</div>
                    <p>Para dúvidas, informações e etc, você pode enviar
                        uma mensagem diretamente em nosso <b class="text-bold">WhatsApp</b>                            
                    </p>
                    <p>Clique no número abaixo:</p>
                    <div id="whatsapp" class="titleBoxes"><a href="https://api.whatsapp.com/send?phone=5511998965109" target="blank"><img src="img/icons/icon-whats.png" alt="WhatsApp" class="social_img"> (11) 99896-5109</a></div>
                </div>
            </div>
            <div class="col-lg-2 col-md-3 col-sm-6 col-xs-12 col-lg-push-2 nopadding">
                <div class="boxes">
                    <div class="titleBoxes">CONTATO</div>
                    <p>contato@mirandataxi.com</p>
                    <p class="text-bold">SIGA-NOS NAS REDES SOCIAS</p>
                    <ul class="socialmedias">
                        <li><a href="#"><img src="img/icons/img-face.png" alt="Facebook" class="social_img"></a></li>
                        <li><a href="#"><img src="img/icons/img-inst.png" alt="Instagram" class="social_img"></a></li>
                    </ul> 
                    <p class="text-bold">© <%= dataStr %> - MirandaTaxi. <br/>All rights reserved.</p>
                    <ul class="cards">
                        <li class="card"><img src="img/icons/visa.png" alt="Facebook" class="cards_img" id="visacard"></li>
                        <li class="card"><img src="img/icons/master.png" alt="Instagram" class="cards_img" id="mastercard"></li>
                        <li class="card"><img src="img/icons/amex.png" alt="Instagram" class="cards_img" id="americancard"></li>
                    </ul>
                </div>
            </div> 
            <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 col-lg-push-2 nopadding">
                <div id="img_rodape">
                    <img src="img/windows/bmw-rodape.jpeg" alt="Rodapé">
                </div>
            </div>
        </div>
    </div>
</div>