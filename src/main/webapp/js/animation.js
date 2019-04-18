$(document).ready(function (){
    
    /*Função que esconde as informações extras dos carros*/
    var hideInfo = function(){
        $('#mini-galeria nav #nav-mini .mini-pics a .sub_info').each(function(a){
            if($(this).hasClass('showIt')){
                $(this).removeClass('showIt');//esconde informações extras
            }
        }); 
    };
    
   var tamanho_tela = $(window).width();
   $(window).resize(function(){
       var nh = $(this).width();
       if(nh > 992){
            hideInfo();          
       }
   });
    var prev = $("#prev");//btn anterior
    var next = $("#next");//btn próximo
    
    var totWidth = 0; //total do tamanho das div's
    var positions = new Array();//posição de cada div
        
    if(tamanho_tela>=0){
        
        $("#slides #slideMaster .slide").each(function (i){
             positions[i] = totWidth;
             totWidth += $(this).width();

             if(!$(this).width()){
                 return false;
             }
        });
        $('#pic_one').addClass('act');
        $("#slides").width(totWidth);
        $('#mini-galeria nav #nav-mini .mini-pics a').click(function(e,keepScroll){
            e.preventDefault();            
            /* Quando clicar na miniatura */
            
            /*se tela menor/igual que 992*/
            if($(window).width() <= 992){
                hideInfo();/*chama a função que esconde as informações*/               
                $(this).find('.sub_info').addClass('showIt');//mostra info extras do carro selecionado
            }
            
            $('li.mini-pics').removeClass('act').addClass('inact');
            $(this).parent().addClass('act');

            var pos = $(this).parent().prevAll('.mini-pics').length;/*pega a posição atual do carro*/
            //inserir carro 
            setPositionNow(pos);
            setSelectIndex(pos);
            
            stopAnimation();/*para a animação ao chegar no primeiro ou último*/
            $('#slideMaster').stop().animate({marginLeft:-positions[pos]+'px'},1000);            
     });
     stopAnimation();
        function stopAnimation(){    
            $('#mini-galeria nav ul li').each(function(ev){
                 if($(this).hasClass('act')){
                     posicao = $(this).prevAll('.mini-pics').length;
                 };
             });
             if(posicao === 0){
                 prev.fadeOut();
                 next.fadeIn();
             }else if(posicao === 4){
                 next.fadeOut();
                 prev.fadeIn();
             }else{
                 $('#next, #prev').fadeIn();
             }
        };
        var animacaoRight = function(){
             $('#mini-galeria nav ul li').each(function(ev){
                 if($(this).hasClass('act')){
                     pos = $(this).prevAll('.mini-pics').length;   
                     $(this).removeClass('act');
                     $(this).prev().addClass('act');

                     return false;
                 }
             });      
             stopAnimation();
             $('#slideMaster').stop().animate({marginLeft:-positions[pos-1]+'px'},1000);

        };
        var animacaoLeft = function(){
             $('#mini-galeria nav ul li').each(function(ev){
                 if($(this).hasClass('act')){
                     pos = $(this).prevAll('.mini-pics').length;   
                     $(this).removeClass('act');
                     $(this).next().addClass('act');

                     return false;
                 }
             }); 
             stopAnimation();
             $('#slideMaster').stop().animate({marginLeft:-positions[pos+1]+'px'},1000);       
        };

        prev.bind('click', function(){
            animacaoRight();
        });
        next.bind('click', function(){
            animacaoLeft(); 
        });
    }
 });
