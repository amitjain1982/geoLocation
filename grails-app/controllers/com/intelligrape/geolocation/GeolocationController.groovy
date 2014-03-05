package com.intelligrape.geolocation

class GeolocationController {
    def geoLocationService

    def getLocation(){
        // Test URL : https://maps.googleapis.com/maps/api/geocode/json?address=60,+Ansari+roa,+Darya+Ganj,+Delhi,+India&sensor=false&key=AIzaSyBMxEnf4R54RtQErWdz3Qq3ajDhsr2Nl3s
        List locations =[]
        locations << [hubId:'DelhiGate', longitude :77.24177259999999 , latitude :28.6405645 ]
        locations << [hubId:'ChandniChowk', longitude :77.23189339999999 , latitude :28.6505535 ]
        locations << [hubId:'Sec-49Noida', longitude :77.3740732 , latitude :28.5621974 ]
        locations << [hubId:'jaypeeAman', longitude :77.46575469999999 , latitude :28.4446394 ]

//        GeoAddressDTO dto = new GeoAddressDTO(addressLine1: 'Sab mall, sector-18', city: 'Noida', state: 'uttar pradesh', country: 'India')
        GeoAddressDTO dto = new GeoAddressDTO(addressLine1: 'golcha', city: 'new delhi', state: 'Delhi', country: 'India')
//        GeoAddressDTO dto = new GeoAddressDTO(addressLine1: '60, Ansari Road', addressLine2: 'darya ganj', city: 'new delhi', state: 'Delhi', country: 'India')
//        GeoAddressDTO dto = new GeoAddressDTO(addressLine1: 'TilakMarg#$%^&*!@~_+', city: 'new delhi', state: 'Delhi', country: 'India')

        try {
            List nearestLocations = geoLocationService.listNearestLocationInOrder(dto,locations)
            render nearestLocations*.hubId
        } catch (RuntimeException e) {
            render e.message
        }
    }
}
