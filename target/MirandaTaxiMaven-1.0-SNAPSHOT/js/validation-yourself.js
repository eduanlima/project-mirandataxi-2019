//Events for validation
var formRegister = document.getElementById("form-register"),
    txtName = document.getElementById("txt-name"),
    txtTelOne = document.getElementById("txt-phone-one"),
    txtTelTwo = document.getElementById("txt-phone-two"),
    txtTelTree = document.getElementById("txt-phone-tree"),
    txtEmail = document.getElementById("txt-email"),
    txtConfirmEmail = document.getElementById("txt-confirmaemail"),
    txtPass = document.getElementById("txt-pass"),
    txtConfPass = document.getElementById("txt-conf-pass"),
    vetEdit = [txtName,txtEmail,txtPass,txtConfPass],
    vetTel = [txtTelOne, txtTelTwo, txtTelTree],
        
    showMessageForm = function(mainText,text){
        pageEndVisible("#black-page");
        boxMessageShow("#box-message");
        $("#txt-main").html(mainText);
        $("#txt-second").html(text);
    },

    validateTel = function(){
        var flag = 0,
            confirm = false;

        for(var i = 0; i < vetTel.length; i++){
            if(vetTel[i].value.length === 0){
                    flag++;
            }
        }

        if(flag === 3){
            txtTelOne.style.outline = "#FF3030 solid 2px";
            confirm = false;

        }else{ 
            if(txtTelOne.style.outlineStyle === "solid"){
                txtTelOne.style.outline = "none";
            }
            confirm = true;
        }

        return confirm;
    },
    validaEmail = function(){
        var confirm = false;
        email = txtEmail.value;
        confmail = txtConfirmEmail.value;
        
        if(email.length>0 && confmail.length>0 && (email === confmail)){
            txtConfirmEmail.style.outline = "none";
            confirm = true;
        }else{
            showMessageForm("Atenção" , "O email deve ser idêntico. Verifique");
            txtConfirmEmail.style.outline = "#FF3030 solid 2px";
        }
        return confirm;
    },
            
    validateBasic = function(){
            var vetConfirm = [],
                confirm = false,
                confirmTel = validateTel(),
                confirmEmail = validaEmail();

            for(var i = 0; i < vetEdit.length; i++){	
                    vetEdit[i].style.outline = "none";
                    if(vetEdit[i].value.length === 0){
                            vetConfirm.push(vetEdit[i]);
                    }
            }

            if(vetConfirm.length > 0){
                    event.preventDefault();
                    for(var i = 0; i < vetConfirm.length; i++){
                            vetConfirm[i].style.outline = "#FF3030 solid 2px";
                    }
                    
            }else if((vetConfirm.length === 0) && (confirmTel) && (confirmEmail)){
                
                if(txtPass.value !== txtConfPass.value){
                    showMessageForm("Atenção!","Senhas divergentes, digite novamente.");
                    txtConfPass.value = "";
                    txtConfPass.disabled = true;
                    txtConfPass.style.backgroundColor = "#E8E8E8";
                    txtPass.value = "";
                    
                }else if(txtPass.value === txtConfPass.value){
                    confirm = true;
                }
            }            
            return confirm;

    },

    restoreTextTel = function(){
            for (var i = 0; i < vetTel.length; i++){
                    vetTel[i].disabled = true;
                    vetTel[i].style.backgroundColor = "#E8E8E8";
            }
    },
            
    clearOutline = function(object){
            if(object.style.outlineStyle === "solid"){
                    if(object.value.length > 0){
                            object.style.outline = "none";
                    }
                    else if(object.value.length === 0){
                            object.style.outline = "#FF3030 solid 1px";
                    }
            }
    },

    setDisabledConf = function(){
            if(txtPass.value.length < 8){
                    txtConfPass.disabled = true;
                    txtConfPass.style.backgroundColor = "#E8E8E8";
                    txtConfPass.value = "";
            } else if(txtPass.value.length === 8){
                    txtConfPass.disabled = false;
                    txtConfPass.style.backgroundColor = "transparent";
            }
    },

    setDisabledTel = function(object){
            object.disabled = true;
            object.style.backgroundColor = "#E8E8E8";
    },

    setEnableTel = function(object){
            object.disabled = false;
            object.style.backgroundColor = "transparent";
    },

    setTelOne = function(){
            setEnableTel(txtTelOne);
    },

    setTelTwo = function(){
            setEnableTel(txtTelTwo);
    },

    setTelTree = function(){
            setEnableTel(txtTelTree);
    },

    clearOutlineTel = function(){
            txtTelOne.style.outline = "none";
    },

    validatePassword = function(){

        if ((txtConfPass.value.length === 8) && (txtPass.value !== txtConfPass.value)){
                showMessageForm("Atenção!","Senhas divergentes, digite novamente.");
                txtConfPass.value = "";
                txtConfPass.disabled = true;
                txtConfPass.style.backgroundColor = "#E8E8E8";
                txtPass.value = "";
        }
    };

    setDisabledConf();
    txtPass.maxLength = 8;
    txtConfPass.maxLength = 8;

    function setObjectName(){
        clearOutline(txtName);
    }

    function setObjectEmail(){
        clearOutline(txtEmail);
    }

    function setObjectPass(){
        clearOutline(txtPass);
    }

    function setObjectConfPass(){
        clearOutline(txtConfPass);
    }

    txtName.addEventListener("input", setObjectName, false);
    txtEmail.addEventListener("input", setObjectEmail, false); 
    txtPass.addEventListener("input", setObjectPass, false);
    txtPass.addEventListener("input", setDisabledConf, false);		
    txtConfPass.addEventListener("input", setObjectConfPass, false);
    txtConfPass.addEventListener("input", validatePassword, false);
    txtTelOne.addEventListener("input", clearOutlineTel, false);