var imgCar = document.getElementById("img-car"),
    lblNameClass = document.getElementById("name-class"),
    spmHora = document.getElementById("price-hour"),
    spmKm = document.getElementById("price-km"),
    
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
            
    setInfCar = function(carClass){
        imgCar.src = "img/cars/"+carClass[0]+".jpg";
        lblNameClass.innerHTML = carClass[1];
        spmHora.innerHTML = formatNumbers(carClass[2],2);
        spmKm.innerHTML = formatNumbers(carClass[3],2);
        
    };
    
    $(document).ready(function(){
        $.ajax({
           type: "post",
           url: "ReturnCarSession",
           dataType: "json",
           success: function(data){
               setInfCar(data);
           }
        });
    });
    


