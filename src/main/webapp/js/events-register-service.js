var txtValueRun = document.getElementById("txt-value-run"),
    txtTypeRun = document.getElementById("txt-type-run"),
    txtValueAddKm = document.getElementById("txt-add-km"),
    spnTotal = document.getElementById("price-first"),
    formMain = document.getElementById("form-main"),
    txtTotalKm = document.getElementById("txt-total-km"),
    bttTest = document.getElementById("btt-prev"),
    bttMessage = document.getElementById("btt-ok"),
    h4KmAdd = document.getElementById("h4-km-add"),
    h4TotalKmAdd = document.getElementById("h4-total-km-add"),
    
    formatNumbers = function(num,place){
        var n = Number(num).toFixed(place), aux = 0;
        var nText = n.replace('.',','), txtShow = '';

        txtShow = nText.slice(nText.length - (place + 1), nText.length);

        for (var i = nText.length - (place + 2); i > -1; i--){
                aux++;

                if((aux === 3) && (i !== 0)){
                        txtShow = '.' + nText.charAt(i) + txtShow;
                        aux = 0;
                }else{
                        txtShow = nText.charAt(i) + txtShow;
                }
        }
       return txtShow;
    },

    showDistance = function(flag, result){
        if(flag){
            h4Distance.innerHTML = formatNumbers(result[1],3) + "km";
            h4Distance.style.visibility = "visible";
        
        }else if(!flag){
            h4Distance.innerHTML = "";
            h4Distance.style.visibility = "hidden";
        }
        
        h4KmAdd.innerHTML = "*Adicional: " + formatNumbers(result[2],3) + "km";
        h4TotalKmAdd.innerHTML = "*Adicional: R$ " + formatNumbers(result[3],2);
    },
    
    calculateRun = function(){
        $.ajax({
            type: "post",
            url: "CalculateRunning",
            dataType: "json",
            data: {
                typeRun: txtTypeRun.value,
                valueRun: txtValueRun.value,
                valueKmAdd: txtValueAddKm.value
            },
            success: function(result){
                spnTotal.innerHTML = formatNumbers(result[0],2);
              
                if(result[1] !== "0"){
                    showDistance(true,result);
                }else if (result[1] === "0"){
                    showDistance(false,result);
                }
            }
        });
    },
            
    setValueRun = function(typeRun){
      
        if(typeRun === "h"){
            txtValueRun.value = sctHours.value;
        }else if(typeRun === "t"){
            
            if((sctTrip.value === "spgru") || (sctTrip.value === "grusp")){
                txtValueRun.value = "3";
                
            }else if((sctTrip.value === "spcamp") || (sctTrip.value === "campsp")){
                txtValueRun.value = "5";
            }
        }
        
        calculateRun();
    },
    
    setTypeRun = function(){
      document.getElementById("txt-add-km-prev").value = 0;  
        
      if (radioTypeService[0].checked){
          txtTypeRun.value = radioTypeService[0].value;
          setValueRun(txtTypeRun.value);
          
      }else if(radioTypeService[1].checked){
          document.getElementById("txt-add-km").value = 0;
          txtTypeRun.value = radioTypeService[1].value;
          setValueRun(txtTypeRun.value);
      }
      
    },
    
    showMessageForm = function(mainText,text){
        pageEndVisible("#black-page");
        boxMessageShow("#box-message");
        $("#txt-main").html(mainText);
        $("#txt-second").html(text);
    },
    
    messageClose = function(){
        pageEndVisible("#black-page");
        boxMessageShow("#box-message");
    },
            
    setTotalPayment = function(){
        document.getElementById("lb_value_pay").innerText = document.getElementById("h3-price-total").innerText;
    };
            
    function setRunKm(){
        var totalKm = txtTotalKm.value;
        
        if (totalKm >= 60){
            txtTypeRun.value = "k";
            txtValueRun.value = totalKm;            
            showMessageForm("Atenção!","O percurso inserido ultrapassa 60km de distância da origem ao destino, a taxa aplicada será reais por quilômetro.");
        }  
        calculateRun();
    };
    
    sctHours.addEventListener("change",setTypeRun,false);
    sctTrip.addEventListener("change",setTypeRun,false);
    radioTypeService[0].addEventListener("click",setTypeRun,false);
    radioTypeService[1].addEventListener("click",setTypeRun,false);
    bttMessage.addEventListener("click",messageClose,false);
    document.getElementById("btt-next").addEventListener("click",setTotalPayment);
    
$(document).ready(function(){
    txtValueAddKm.value = 0; 
    setTypeRun();

    /*HABILITAR E DESABILITAR OPÇÕES EXTRAS NA COMPRA*/
    function disable_all(){
        $('.input-extra').prop("disabled", true).css("background", "#eeeeee");
    }
    /*função que bloquea campos*/
    function field_disable(item){        
        item.prop("disabled", true);
        item.css("background", "#eeeeee");
    }
    /*função que desbloquea campos*/
    function field_able(item){
        item.prop("disabled", false);
        item.css("background", "transparent");
    }
    disable_all();//desabilitar todos no load    
    
    //quando clicar em qualquer CHECKBOX
    $('.checkbox-add').on('change', function(e){
        e.preventDefault();
        
        pegar_id = $(this).attr("id");//pegar o id
        
        if ($(this).is(':checked')){ //Quando marcado
            switch (pegar_id){
                case "checkbox-board":
                    input = $('#check-board-we');
                    field_able(input);//habilita
                break;
                case "checkbox-reception":
                    input = $('#check-board-ps');
                    field_able(input);
                break;
            }
        }else{
           switch (pegar_id){
                case "checkbox-board":
                    input = $('#check-board-we');
                    field_disable(input);//desabilita
                break;
                case "checkbox-reception":
                    input = $('#check-board-ps');
                    field_disable(input);
                break;
            }  
        }
    });
    
    /*sctHours = document.getElementById("sct-hours"),
    sctTrip = document.getElementById("sct-trip")*/
});//FIM DO DOCUMENT READER