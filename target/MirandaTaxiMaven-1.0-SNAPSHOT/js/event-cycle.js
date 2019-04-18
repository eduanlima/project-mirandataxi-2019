var carSelected = function(){
    altert("Testando");
};

$(document).ready(function(){
    $(function (){
        $("#slides ul").cycle({
            fx: 'scrollHorz',
            speed: 3000,
            timeout: 0,
            prev: '#prev',
            next: '#next'
        });
    });
});
