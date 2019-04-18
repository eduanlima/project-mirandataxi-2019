var txtCarSelected = document.getElementById("txt-car-selected"),
    next = document.getElementById("next"),
    prev = document.getElementById("prev"),    
    setNavPrev = function(){
        setPositionNow(getPositionNow()-1);
        setSelectIndex(getPositionNow());
    },
    
    setNavNext = function(){
        setPositionNow(getPositionNow()+1);
        setSelectIndex(getPositionNow());
    };
            
$(document).ready(function(){
   txtCarSelected.value = "ftc";
   
   prev.addEventListener("click", setNavPrev,false);
   next.addEventListener("click",setNavNext,false);
   
   
   /*
    * 
    * @returns null
    * Função: Verifica o browser utilizado e aplicar ação específica
    */
   function browser() {
    // Return cached result if avalible, else get result then cache it.
    if (browser.prototype._cachedResult)
        return browser.prototype._cachedResult;

    // Opera 8.0+
    var isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;

    // Firefox 1.0+
    var isFirefox = typeof InstallTrigger !== 'undefined';

    // Safari 3.0+ "[object HTMLElementConstructor]" 
    var isSafari = /constructor/i.test(window.HTMLElement) || (function (p) { return p.toString() === "[object SafariRemoteNotification]"; })(!window['safari'] || safari.pushNotification);

    // Internet Explorer 6-11
    var isIE = /*@cc_on!@*/false || !!document.documentMode;

    // Edge 20+
    var isEdge = !isIE && !!window.StyleMedia;

    // Chrome 1+
    var isChrome = !!window.chrome && !!window.chrome.webstore;

    // Blink engine detection
    var isBlink = (isChrome || isOpera) && !!window.CSS;
    
    if(isIE || isEdge){
        //hack que modifica formatação das setas de movimento dos carros       
        $("#prev").css("margin-left", "0px");
    }
   
};
  browser();
});