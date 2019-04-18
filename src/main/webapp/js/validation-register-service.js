//"use strict";

var radioTypeService = document.getElementsByName("service-type"),
    pTxtLegal = document.getElementById("legal-text-service"),
    fldFrom = document.getElementById("fld-from"),
    fldTo = document.getElementById("fld-to"),
    txtDate = document.getElementById("txt-date"),
    txtHours = document.getElementById("txt-hours"),
    txtFlight = document.getElementById("txt-flight"),
    lblTripHours = document.getElementById("lbl-trip-hours"),
    lblTripFlight = document.getElementById("lbl-trip-flight"),
    txtPassenger = document.getElementById("txt-passenger"),
    vetTextMain = [txtDate,txtHours,txtPassenger],
    legendFrom = document.getElementById("legend-from"),
    txtStreetFrom = document.getElementById("txt-from-street"),
    txtNeighborFrom = document.getElementById("txt-from-neighbor"),
    txtCityFrom = document.getElementById("txt-from-city"),
    lblUfFrom = document.getElementById("lbl-from-uf"),
    lblTextFrom = document.getElementById("lbl-text-legal-from"),
    vetTextFrom = [txtStreetFrom,txtNeighborFrom,txtCityFrom],
    vetLblFrom = [lblUfFrom,lblTextFrom],
    legendTo = document.getElementById("legend-to"),
    txtStreetTo = document.getElementById("txt-to-street"),
    txtNeighborTo = document.getElementById("txt-to-neighbor"),
    txtCityTo = document.getElementById("txt-to-city"),
    lblUfTo = document.getElementById("lbl-to-uf"),
    lblTextTo = document.getElementById("lbl-text-legal-to"),
    lblDate = document.getElementById("lbl-date"),
    lblHours = document.getElementById("lbl-hour"),
    lblFlight = document.getElementById("lbl-flight"),
    lblPassenger = document.getElementById("lbl-passenger"),
    vetTextTo = [txtStreetTo,txtNeighborTo,txtCityTo],
    vetLblTo = [lblUfTo,lblTextTo],
    sctHours = document.getElementById("sct-hours"),
    sctTrip = document.getElementById("sct-trip"),
    h4Distance = document.getElementById("h4-distance"),
    spnHours = document.getElementById("spn-hours"),
    divTypeService = document.getElementById("div-main-type-service"),
    bttNext = document.getElementById("btt-next"),
    
    //This function is used to clean inputs texts 
    setTextMainColorBorder = function(){
        this.style.borderColor = "#000000";
    },
    
    focusTextMain = function(){
        var textAux = "";
        
        for(var i = 0; i < vetTextMain.length; i++){
            if(vetTextMain[i].value === ""){
                vetTextMain[i].style.borderColor = "#FF0000";
                
                if(textAux === ""){
                    textAux = vetTextMain[i];
                    textAux.focus();
                }
            }
        }
        
    },
        
    cleanText = function(arrayClean){
        for(var i = 0; i < arrayClean.length; i++){
            arrayClean[i].value = "";
        }
    },
    
    elementsDisable = function(elementText, elementLabel, state){
        var colorElement = "";
        
        (state) ? colorElement = "#B5B5B5" : colorElement = "#000000";
        
        for (var i = 0; i < elementText.length; i++) {
            elementText[i].disabled = state;
            elementText[i].style.borderColor = colorElement;
        }
        
        for (var i = 0; i < elementText.length; i++){
            elementLabel[i].style.color = colorElement;
        }
    },
    
    serviceSelected = function(service){
        var vetText, vetLabel;
        
        if (service === 'h'){
            
            vetText = [txtDate];
            vetLabel = [lblDate];  
            elementsDisable(vetText,vetLabel,false);
            
            vetText = [txtHours];
            vetLabel = [lblHours];  
            elementsDisable(vetText,vetLabel,false);
            
            vetText = [txtFlight];
            vetLabel = [lblFlight];
            elementsDisable(vetText,vetLabel,true);
            
            vetText = [txtPassenger];
            vetLabel = [lblPassenger];
            elementsDisable(vetText,vetLabel,false);
            
            cleanText(vetTextFrom);
            cleanText(vetTextTo);
            
        }else{
            
            vetText = [txtFlight];
            vetLabel = [lblFlight];
            elementsDisable(vetText,vetLabel,false);
            
            cleanText(vetTextFrom);
            cleanText(vetTextTo);
        }
    },
            
    selectTypeService = function(typeService){
        serviceSelected(typeService);
    },
    
    setServiceHour = function(){
        bttNext.disabled = true; 
        bttNext.style.backgroundColor = "#B5B5B5";
        selectTypeService(radioTypeService[0].value);
        pTxtLegal.innerHTML = "O serviço por hora deverá ser utilizado dentro do município da cidade São Paulo, caso ocorra alguma viagem para outro município o valor a ser cobrado será por quilômetros (km).";
    },
            
    setServiceFlight = function(){
        bttNext.disabled = true; 
        bttNext.style.backgroundColor = "#B5B5B5";
        selectTypeService(radioTypeService[1].value);
        pTxtLegal.innerHTML = "Somente para corridas com trajeto de no máximo 60km, acima desta distância o valor a ser cobrado será por quilômetros (km).";
    },
            
    disableItensTrip = function(elementFld,elementLegend,elementText,elementLabel,state){
        var colorElement = "";
        
       (state) ?  colorElement = "#B5B5B5" : colorElement = "#000000";
       
       elementFld.style.borderColor = colorElement;
       elementLegend.style.color = colorElement;
       
       for(var i = 0; i < elementText.length; i++){
           elementText[i].disabled = state;
           elementText[i].style.borderColor = colorElement;
       }
       
       for(var i = 0; i < elementLabel.length; i++){
           if(i === 0){
               elementLabel[i].style.backgroundColor = colorElement;
           }else{
               elementLabel[i].style.color = colorElement;
           }
       }
    },
         
    tripSelected = function(){
       bttNext.disabled = true;
       bttNext.style.backgroundColor = "#B5B5B5";
       
       if ((sctTrip.value === "spgru") || (sctTrip.value ==="spcamp")){
           disableItensTrip(fldTo,legendTo,vetTextTo,vetLblTo,true); 
           disableItensTrip(fldFrom,legendFrom,vetTextFrom,vetLblFrom,false);
           cleanText(vetTextTo);
           
       }else if((sctTrip.value === "grusp") || (sctTrip.value === "campsp")){
           disableItensTrip(fldFrom,legendFrom,vetTextFrom,vetLblFrom,true);
           disableItensTrip(fldTo,legendTo,vetTextTo,vetLblTo,false);
           cleanText(vetTextFrom);
       }
    },
            
    disableTypeService = function(elementLbl,elementSct,state){
        var colorElement = "";
        
        (state) ? colorElement = "#000000" : colorElement = "#B5B5B5"; 
        
        elementLbl.style.color = colorElement;
        elementSct.style.borderColor = colorElement;
        elementSct.disabled = !state;
    },
            
    selectDisableTypeService = function(){
        cleanText(vetTextFrom);
        cleanText(vetTextTo);
        
        if(radioTypeService[0].checked){
            disableTypeService(lblTripFlight,sctTrip,false);
            disableTypeService(lblTripHours,sctHours,true);
           
            if((txtStreetFrom.disabled) || (txtStreetTo.disabled)){
                disableItensTrip(fldTo,legendTo,vetTextTo,vetLblTo,false); 
                disableItensTrip(fldFrom,legendFrom,vetTextFrom,vetLblFrom,false);
            }
            
        }else if (radioTypeService[1].checked){
            disableTypeService(lblTripFlight,sctTrip,true);
            disableTypeService(lblTripHours,sctHours,true);
            
            disableTypeService(lblTripFlight,sctTrip,true);
            disableTypeService(lblTripHours,sctHours,false);
            tripSelected();
        }
    },
            
    clearValuesKm = function(){
        document.getElementById("txt-total-km").value = 0;
        
        if(radioTypeService[0].checked){
            document.getElementById("txt-value-run").value = sctHours.value;
            document.getElementById("txt-type-run").value = "h";
            
        }else if(radioTypeService[1].checked){
            document.getElementById("txt-value-run").value = "3";
            document.getElementById("txt-type-run").value = "t";
        }
    },
    
    clearEditAddress = function(input){
        var flag = 0;

        if(input.value === ""){
            for (var i = 0; i < vetTextFrom.length; i++){
                if (input === vetTextFrom[i]){
                    flag++;
                }
            }
            
            if(flag === 0){
                clearValuesKm();
                txtStreetTo.value = "";
                txtNeighborTo.value = "";
                txtCityTo.value = "";
                bttNext.disabled = true;
                bttNext.style.backgroundColor = "#B5B5B5";
                document.getElementById("txt-add-km").value = 0;
                document.getElementById("txt-add-km-prev").value = 0;

            }else if (flag !== 0){
                clearValuesKm();
                txtStreetFrom.value = "";
                txtNeighborFrom.value = "";
                txtCityFrom.value = "";
                bttNext.disabled = true;
                bttNext.style.backgroundColor = "#B5B5B5";
                document.getElementById("txt-add-km").value = 0;
                document.getElementById("txt-add-km-prev").value = 0;
            }
        }
    },
            
    setInput = function(){
        clearEditAddress(document.getElementById(this.id));
    };
    
    bttNext.disabled = true;
    bttNext.style.backgroundColor = "#B5B5B5";
    
    disableItensTrip(fldTo,legendTo,vetTextTo,vetLblTo,false); 
    disableItensTrip(fldFrom,legendFrom,vetTextFrom,vetLblFrom,false);
    
    radioTypeService[0].checked = true;
    selectTypeService(radioTypeService[0].value);
    
    radioTypeService[0].addEventListener("click",setServiceHour,false);
    radioTypeService[1].addEventListener("click",setServiceFlight, false);
    
    radioTypeService[0].addEventListener("click",selectDisableTypeService,false);
    radioTypeService[1].addEventListener("click",selectDisableTypeService,false);
    
    sctTrip.addEventListener("change",tripSelected,false);
    disableTypeService(lblTripFlight,sctTrip,false);
    
    txtStreetFrom.addEventListener("blur",setInput,false);
    txtNeighborFrom.addEventListener("blur",setInput,false);
    txtCityFrom.addEventListener("blur",setInput,false);
    
    txtStreetTo.addEventListener("blur",setInput,false);
    txtNeighborTo.addEventListener("blur",setInput,false);
    txtCityTo.addEventListener("blur",setInput,false);
    
    txtStreetFrom.addEventListener("click",focusTextMain,false);
    txtNeighborFrom.addEventListener("click",focusTextMain,false);
    txtCityFrom.addEventListener("click",focusTextMain,false);
    
    txtStreetTo.addEventListener("click",focusTextMain,false);
    txtNeighborTo.addEventListener("click",focusTextMain,false);
    txtCityTo.addEventListener("click",focusTextMain,false);
    
    txtDate.addEventListener("blur",setTextMainColorBorder,false);
    
    txtDate.addEventListener("keydown",setTextMainColorBorder,false);
    txtHours.addEventListener("keydown",setTextMainColorBorder,false);
    txtPassenger.addEventListener("keydown",setTextMainColorBorder,false);

    