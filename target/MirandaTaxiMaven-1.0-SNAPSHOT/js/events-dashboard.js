/* 
 * Funções da Dashboard
 */
'use strict';

$(document).ready(function(){
    
    function reload(href) {
        var content = $('#content');
        
        $("html, body").animate({scrollTop:0}, "slow");//subir para o top
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
    /*
     *Função first_load()
     *Carrega a primeira página ao entrar na dashboard
     */
    function first_load(){
        var url = window.location.href;
        var res = url.split("#");
        var link = res[1];
        
        if(!link){            
            link = $('.profile-usermenu .nav li:first').find('a').attr('href');
            $('.profile-usermenu .nav li:first').addClass('active');//ativa o link clicado
        }else{
            $('#'+link).addClass('active');
            link = link+'.jsp';            
        }        
        var first_link = link;
        reload(first_link);
    }
    first_load();//carregar uma página no load
    
    $('.profile-usermenu .nav li').bind('click', function(ev){
        ev.preventDefault();//evita ação padrão
        var atual = $(this);
        
        if(atual.hasClass('active')){/*não faça nada*/}else{
            /*procura e retira a classe ACTIVE do link ativo*/
            $('.profile-usermenu .nav li').each(function(){
                if($(this).hasClass('active')){
                    $(this).removeClass('active');
                }
            });
            $(this).addClass('active');//ativa o link clicado
            var href = $(this).find('a').attr('href');//pega a href para redirecionar a página

            reload(href);//chama a função que faz o reload
        }
    });
});


