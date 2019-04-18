/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){    
    $('#btt-info').bind('click',function(){
        pageEndVisible("#black-page");
        boxMessageShow("#box-info");
    });
    
    $('#btn-close').bind('click', function(){        
        boxMessageShow("#box-info");
        pageEndVisible("#black-page");
    });
});

