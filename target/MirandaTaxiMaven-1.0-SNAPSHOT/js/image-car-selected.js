var imgCar = document.getElementById("img-car"),
    lblNameClass = document.getElementById("name-class"),
    spmHora = document.getElementById("price-hour"),
    spmKm = document.getElementById("price-km"),
    setInfCar = function(carClass){
        imgCar.src = "img/cars/"+carClass[0]+".jpg";
        lblNameClass.innerHTML = carClass[1];
        spmHora.innerHTML = carClass[2];
        spmKm.innerHTML = carClass[3];
        
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
    


