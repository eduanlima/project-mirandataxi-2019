      var placeSearch, autocomplete, vetInput = [];
    
      var requestPlace = {
        route: 'long_name',
        street_number: 'long_name',
        sublocality_level_1: 'long_name',
        administrative_area_level_2: 'long_name',
        administrative_area_level_1: 'short_name'
      };
      
      vetInput[0] = txtStreetFrom;
      
      function clearInput(){
        vetInput[0].value = "";
        vetInput[1].value = "";
        vetInput[2].value = "";
      }
      
      function setVetInput(txtStreet,txtNeighboor,txtCity){
          vetInput[0] = txtStreet;
          vetInput[1] = txtNeighboor;
          vetInput[2] = txtCity;
      }
      
      function setTxtFrom(){
          setVetInput(txtStreetFrom,txtNeighborFrom,txtCityFrom);
      }
      
      function setTxtTo(){
          setVetInput(txtStreetTo,txtNeighborTo,txtCityTo);
      }
      
      function initAutocomplete(){  
        var defaultBounds = new google.maps.LatLngBounds(
            new google.maps.LatLng(-23.5505, -46.6333));

        autocomplete = new google.maps.places.Autocomplete((vetInput[0]),{types: [],componentRestrictions: {country: 'br'},bounds: defaultBounds});
        autocomplete.addListener('place_changed', fillInAddress);
        
      }

      function fillInAddress() {
        // Get the place details from the autocomplete object.
        var place = autocomplete.getPlace(),val = [], aux = 0;
            val[4] = "undefined";
          
         for (var i = 0; i < place.address_components.length; i++){
          var addressType = place.address_components[i].types[0];
          
          
          if (requestPlace[addressType]) {          
            val[aux] = place.address_components[i][requestPlace[addressType]];
            aux++; 
          }
         
        }
        
        if(val[4] === "SP"){
            clearInput();
            vetInput[0].value = val[1]+", "+val[0];
            vetInput[1].value = val[2];
            vetInput[2].value = val[3];
        }else if(val[4] === "undefined"){
            clearInput();
            vetInput[0].value = "indefinido";
            vetInput[1].value = "indefinido";
            vetInput[2].value = "indefinido";
            document.getElementById("btt-next").disabled = true;
        }else if(val[4] !== "SP"){
            clearInput();
            vetInput[0].value = "indefinido";
            vetInput[1].value = "indefinido";
            vetInput[2].value = "indefinido";
            document.getElementById("btt-next").disabled = true;
        }else{
            clearInput();
            vetInput[0].value = "indefinido";
            vetInput[1].value = "indefinido";
            vetInput[2].value = "indefinido";
            document.getElementById("btt-next").disabled = true;
        }

        $("#div-main-type-service").click();
      }
      
      function geolocate() {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var geolocation = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
            var circle = new google.maps.Circle({
              center: geolocation,
              radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
          });
        }
      }
      
      txtStreetFrom.addEventListener("focus",setTxtFrom,false);
      txtStreetFrom.addEventListener("focus",initAutocomplete,false);
      txtStreetTo.addEventListener("focus",setTxtTo,false);
      txtStreetTo.addEventListener("focus",initAutocomplete,false)
      
	  
/**
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places&callback=initAutocomplete"
        async defer></script>
  </body>
</html> **/

//https://developers.google.com/maps/documentation/geocoding/intro?hl=pt#Types

// vetor place there are going the positions this order: [0] - street_number, [1] - route, [2] - sublocality_1, [3] - admistrative_area_level_2