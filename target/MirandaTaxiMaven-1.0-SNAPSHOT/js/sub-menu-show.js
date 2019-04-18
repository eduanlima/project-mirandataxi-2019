var linkSubIn = document.getElementById("li-access"),
    ulSubIn = document.getElementById("sub-access"),
    lblAlert = document.getElementById("h-alert"),
    formIn = document.getElementById("form-in"),
    divClose = document.getElementById("div-close"),
    linkOpen = document.getElementById("link-access-in");
    
    clearEdit = function(){
        formIn.reset();
        lblAlert.style.visibility = "hidden";
    };
            
    showModal = function(){
        pageEndVisible("#black-page");
        boxMessageShow("#box-modal");
        clearEdit();
    };
                
    subMenuShow = function(){
        if($("#li-access").is(":hover") || $("#sub-access").is(":hover")){
            if(!$("#sub-access").is(":visible")){
                $("#sub-access").slideDown("fast");
            }
        }else{
            if($("#sub-access").is(":visible")){
                $("#sub-access").slideUp("fast");
            }
        }
    };

linkSubIn.addEventListener("mouseover",subMenuShow,false);
linkSubIn.addEventListener("mouseout",subMenuShow,false);
ulSubIn.addEventListener("mouseover",subMenuShow,false);
ulSubIn.addEventListener("mouseout",subMenuShow,false);
divClose.addEventListener("click",showModal,false); 
linkOpen.addEventListener("click",showModal,false);


