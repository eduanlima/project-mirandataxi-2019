    var vetCar = ["ftc","sdc","svc","ecc","eyc"],
    positionNow = 0,
    setSelectIndex = function(i){
        txtCarSelected.value = vetCar[i];
    },
    
    setPositionNow = function(p){
        positionNow = p;
    },
            
    getPositionNow = function(){
        return positionNow;
    };