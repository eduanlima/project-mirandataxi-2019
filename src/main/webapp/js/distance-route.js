"use strict";

var distance = 0, map, origin, destination,
    distancePrev = document.getElementById("txt-add-km-prev"),
    iframeMap = document.getElementById("iframe-map"),
    vetAddress = [" "," "," "," "," "," "],
    vetFrom = [" "," "," "],
    vetTo = [" "," "," "],
    setNullDistance = function(){
        distancePrev.value = 0;
        txtValueAddKm.value = 0;
    },
    setNullVetAdress = function(){
        for(var i = 0; i < vetAddress.length; i++){
            vetAddress[i] = " ";
        }
    },        
    calculateRunAddKm = function(flag){
        $.ajax({
            type: "post",
            url: "CalculateRunningAddKm",
            data: {
                valueAddKm: txtAddKm.value,
                hourRun: txtValueRun.value,
                flagAux: flag
            },
            success: function(result){
                spnTotal.innerHTML = result;
            }
        });
    };

    function calculateDistance(origin, destination){
        //Here is instantiated the object of google.maps.DistanceMatrixService()  
        var service = new google.maps.DistanceMatrixService();
        
        service.getDistanceMatrix(
        { 
                //Here is isntantiated the value origin 
                origins: [origin],
                //Here is isntantiated the value destination
                destinations: [destination],
                //Here is isntantiated mode locomotion
                travelMode: google.maps.TravelMode.DRIVING,
                //Here is isntantiated the distance unit 
                unitSystem: google.maps.UnitSystem.METRIC
        }, callback);
    }
    
    function calculateDistanceStart(origin, destination){
        //Here is instantiated the object of google.maps.DistanceMatrixService()  
        var service = new google.maps.DistanceMatrixService();
        
        service.getDistanceMatrix(
        { 
                //Here is isntantiated the value origin 
                origins: [origin],
                //Here is isntantiated the value destination
                destinations: [destination],
                //Here is isntantiated mode locomotion
                travelMode: google.maps.TravelMode.DRIVING,
                //Here is isntantiated the distance unit 
                unitSystem: google.maps.UnitSystem.METRIC
        }, callbackStart);
    }
    
    function callbackStart(response, status){
        var distance = 0;
        try{

            distance = response.rows[0].elements[0].distance.value / 1000;
            
            if(distance >= 20){
                if (distance !== distancePrev.value) {
                    $('#showMessage #infoMensagem').html("Deslocamento até ponto de partida é maior que 20km");
                    $('#showMessage').modal();
                    txtValueAddKm.value = distance;
                    calculateRun();
                    distancePrev.value = distance;
                }
                
            }else if(distance < 20){
                if(distancePrev.value !== 0){
                    txtValueAddKm.value = 0;
                    calculateRun();
                    distancePrev.value = 0;
                }
            }
            
        }catch(error){
            alert("An error occurred: "+error);
        }
    }
    
    function callback (response, status) {
        var distanceCalc = 0;
        try{

            distanceCalc = response.rows[0].elements[0].distance.value;
            
            document.getElementById("txt-total-km").value = distanceCalc / 1000;
            setRunKm();
            
            iframeMap.src = "https://www.google.com/maps/embed/v1/directions?key=AIzaSyAAuoodPmKSm1KfKc8RKsWKjY15Mdjqap8&origin="+encodeURI(response.originAddresses)+"&destination="+encodeURI(response.destinationAddresses);

        }catch(error){
            alert("An error occurred: "+error);
        }
    } 
    
    function initMap(){}
            
    function calculateRoute(typeRoute){
        /*type: 0 = standard route | 1 - spgru | 2 - grusp | 3 - spcamp | 4 - campsp*/
        if(typeRoute === 0){
            origin = txtStreetFrom.value + " - " + txtNeighborFrom.value + ", " + " " + txtCityFrom.value + " - SP, Brasil";
            destination = txtStreetTo.value + " - " + txtNeighborTo.value+", " + " " + txtCityTo.value + " - SP, Brasil";
            
        } else if(typeRoute === 1){
            origin = txtStreetFrom.value + " - " + txtNeighborFrom.value + ", " + " " + txtCityFrom.value + " - SP, Brasil";
            destination = "aeroporto internacional de cumbica guarulhos - sp, Brasil";
            
        }else if(typeRoute === 2){
            origin = "aeroporto internacional de cumbica guarulhos - sp, Brasil";
            destination = txtStreetTo.value + " - " + txtNeighborTo.value+", " + " " + txtCityTo.value + " - SP, Brasil";
            
        }else if(typeRoute === 3){
            origin = txtStreetFrom.value + " - " + txtNeighborFrom.value + ", " + " " + txtCityFrom.value + " - SP, Brasil";
            destination = "aeroporto internacional de vira copos campinas - sp, Brasil";
            
        }else if(typeRoute === 4){
            origin = "aeroporto internacional de vira copos campinas - sp, Brasil";
            destination = txtStreetTo.value + " - " + txtNeighborTo.value+", " + " " + txtCityTo.value + " - SP, Brasil";
        }
    
      calculateDistance(origin,destination);  
    };
    
    
    function filterRoute(){
        var flagFrom = 0, flagTo = 0, flag = 0, typeRoute = 0;
        
        for (var i = 0; i < vetTextFrom.length; i++){
            if((vetTextFrom[i].value === "") || (vetTextFrom[i].value === "undefined")){
                flagFrom++;
            }
        }
        
        for (var i = 0; i < vetTextTo.length; i++){
            if((vetTextTo[i].value === "") || (vetTextTo[i].value === "undefined")){
                flagTo++;
            }
        }
        
        if((flagFrom === 0) && (radioTypeService[0].checked)){
            if((txtValueAddKm.value === "0") && (distancePrev.value === "0")){
                var destinationStart = txtStreetFrom.value + " - " + txtNeighborFrom.value + ", " + " " + txtCityFrom.value + " - SP, Brasil";
                calculateDistanceStart("Rua Rocha, 23 - Bela Vista, São Paulo - SP, Brasil", destinationStart);  
            }
        }

        if((flagFrom === 0) && (flagTo === 0) && (radioTypeService[0].checked)){
            for (var i = 0; i < vetTextFrom.length; i++){
                if (vetAddress[i] !== vetTextFrom[i].value) {
                    flag++;
                }
            }
            
            for (var i = 0; i < vetTextTo.length; i++){
                if (vetAddress[i+3] !== vetTextTo[i].value) {
                    flag++;
                }
            }
            
            if (flag > 0){
                
                for (var i = 0; i < vetTextFrom.length; i++){
                    vetAddress[i] = vetTextFrom[i].value;
                }

                for (var i = 0; i < vetTextTo.length; i++){
                    vetAddress[i+3] = vetTextTo[i].value;
                }
                
                calculateRoute(typeRoute);
                bttNext.disabled = false;
                bttNext.style.backgroundColor = "#000000";
                
            }else if (flag === 0){}
            
        }else if(radioTypeService[1].checked){
              var pointStart = sctTrip.value;
                    
              switch(pointStart){
                    case "spgru":
                        typeRoute = 1;
                    break;

                    case "grusp":
                        typeRoute = 2;
                    break;

                    case "spcamp":
                        typeRoute = 3;
                    break;

                    case "campsp":
                        typeRoute = 4;
                    break;
               }
                            
              if(((typeRoute === 1) || (typeRoute === 3)) && (flagFrom === 0)){
                  var flagF = 0;
                  for(var i = 0; i < vetTextFrom.length; i++){
                      if(vetFrom[i] !== vetTextFrom[i].value){
                          flagF++;
                      }
                  }
                  
                  if(flagF > 0){
                    for(var i = 0; i < vetTextFrom.length; i++){
                        vetFrom[i] = vetTextFrom[i].value;
                    }
                    calculateRoute(typeRoute);
                    bttNext.disabled = false;
                    bttNext.style.backgroundColor = "#000000";
                  }

              }else if(((typeRoute === 2) || (typeRoute === 4)) && (flagTo === 0)){
                  var flagT = 0;
                  for(var i = 0; i < vetTextTo.length; i++){
                      if(vetTo[i] !== vetTextTo[i].value){
                          flagT++;
                      }
                  }
                  
                  if(flagT > 0){
                    for(var i = 0; i < vetTextTo.length; i++){
                        vetTo[i] = vetTextTo[i].value;
                    }
                    calculateRoute(typeRoute);
                    bttNext.disabled = false;
                    bttNext.style.backgroundColor = "#000000";
                  }
              }
              
        }  
        
    }
            
    function activeClick(){
        divTypeService.click();
    }
    
    function setPointMap(){
       if((sctTrip.value === "spgru") || (sctTrip.value ==="grusp")){
           iframeMap.src = "https://www.google.com/maps/embed?pb=!1m23!1m12!1m3!1d58572.640783608986!2d-46.49288962923059!3d-23.4319501864562!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m8!3e6!4m0!4m5!1s0x94ce8af96f722a25%3A0x8071626c51a7154a!2sAeroporto+Internacional+de+S%C3%A3o+Paulo+-+Cumbica%2C+Guarulhos+-+SP!3m2!1d-23.4345529!2d-46.478125999999996!5e0!3m2!1spt-BR!2sbr!4v1507683487402";
       }else if((sctTrip.value === "spcamp") || (sctTrip.value === "campsp")){
           iframeMap.src = "https://www.google.com/maps/embed?pb=!1m23!1m12!1m3!1d58756.05191316183!2d-47.13211950067613!3d-23.01447205870358!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m8!3e6!4m0!4m5!1s0x94c8b5f3811e18b9%3A0x6960b9cdc437533b!2sAeroporto+de+Viracopos%2C+Campinas+-+SP!3m2!1d-23.0106049!2d-47.141216799999995!5e0!3m2!1spt-BR!2sbr!4v1507683284911";
       }
    }
    
    function setPointMapCheck(){
        if(radioTypeService[0].checked){
            iframeMap.src = "https://www.google.com/maps/embed?pb=!1m23!1m12!1m3!1d58520.78932032205!2d-46.658716033816994!3d-23.548706286961725!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m8!3e6!4m0!4m5!1s0x94ce448183a461d1%3A0x9ba94b08ff335bae!2sS%C3%A3o+Paulo+-+SP!3m2!1d-23.550519899999998!2d-46.633309399999995!5e0!3m2!1spt-BR!2sbr!4v1507684202404";
        }else if(radioTypeService[1].checked){
            setPointMap();
        }
    }
    
    
    divTypeService.addEventListener("click",filterRoute,false);
    
    txtStreetFrom.addEventListener("blur",activeClick,false);
    txtNeighborFrom.addEventListener("blur",activeClick,false);
    txtCityFrom.addEventListener("blur",activeClick,false);
    
    txtStreetTo.addEventListener("blur",activeClick,false);
    txtNeighborTo.addEventListener("blur",activeClick,false);
    txtCityTo.addEventListener("blur",activeClick,false);
    
    sctTrip.addEventListener("change",setPointMap,false);
    
    radioTypeService[0].addEventListener("click",setPointMapCheck,false);
    radioTypeService[1].addEventListener("click",setPointMapCheck,false);
    radioTypeService[1].addEventListener("click",setNullDistance,false);
    radioTypeService[1].addEventListener("click",setNullVetAdress,false);