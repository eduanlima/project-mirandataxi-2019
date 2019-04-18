var passenger, dataCor, hourCor, numFlight, origin, destination, note, babyChair, wedding, nameWedding, reception, nameReception, termosAceite,
    setValuesRunning = function(){
        passenger = document.getElementById("txt-passenger").value;
        dataCor = document.getElementById("txt-date").value;
        hourCor = document.getElementById("txt-hours").value;
        numFlight = document.getElementById("txt-flight").value;
        
        if(document.getElementById("txt-from-street").value !== ""){
            origin = document.getElementById("txt-from-street").value + ", " + 
                     document.getElementById("txt-from-neighbor").value + ", "+
                     document.getElementById("txt-from-city").value;
        }else{
            origin = "";
        }
        
        if (document.getElementById("txt-to-street").value !== ""){
            destination = document.getElementById("txt-to-street").value + ", " + 
                          document.getElementById("txt-to-neighbor").value + ", "+
                          document.getElementById("txt-to-city").value;
        }else{
          destination = "";
        }
        
        note = document.getElementById("cmp-comment").value;
    },
            
    setChekcValues = function(){
        if(document.getElementById("checkbox-kid").checked){
            babyChair = 1;
        }else{
            babyChair = 0;
        }
                
        if(document.getElementById("checkbox-board").checked){
            wedding = 2;
            nameWedding = document.getElementById("check-board-we").value;
        }else{
            wedding = 0;
            nameWedding = "";
        }
        
        if(document.getElementById("checkbox-reception").checked){
            reception = 3;
            nameReception = document.getElementById("check-board-ps").value;
        }else{
            reception = 0;
            nameReception = "";
        }
    },
            
    showTest = function(){
        alert(dataCor +"\n"+ hourCor +"\n"+ numFlight +"\n"+ origin +"\n"+ 
              destination +"\n"+ note +"\n"+ babyChair +"\n"+ wedding +"\n"+ nameWedding +"\n"+ 
              reception +"\n"+ nameReception);
    },
            
    sendRunning = function(){
            setValuesRunning();
            setChekcValues();
            
            $.ajax({
                type: "post",
                url: "CreateRunning",
                data: {
                    passenger: passenger,
                    dataCor: dataCor,
                    hourCor: hourCor,
                    numFlight: numFlight,
                    origin: origin,
                    destination: destination,
                    note: note,
                    babyChair: babyChair,
                    wedding: wedding,
                    nameWedding: nameWedding,
                    reception: reception,
                    nameReception: nameReception
                },
                success: function(){}
            });
    };
    
    document.getElementById("btt-next").addEventListener("click",sendRunning,false);
    