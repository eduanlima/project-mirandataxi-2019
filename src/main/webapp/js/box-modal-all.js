    var divClose = document.getElementById("div-close"),
        linkOpen = document.getElementById("link-access-in");

        showModalIndex = function(){
            pageEndVisible("#black-page");
            boxMessageShow("#box-modal");
            document.getElementById("h-alert").style.visibility = "hidden";
            document.getElementById("form-in").reset();
        };

    divClose.addEventListener("click",showModalIndex,false); 
    linkOpen.addEventListener("click",showModalIndex,false);
