package com.intelligrape.geolocation

class GeolocationController {
    def geoLocationService

    def getLocation(){
        // Test URL : https://maps.googleapis.com/maps/api/geocode/json?address=60,+Ansari+roa,+Darya+Ganj,+Delhi,+India&sensor=false&key=AIzaSyBMxEnf4R54RtQErWdz3Qq3ajDhsr2Nl3s
        List<GeoDistance> locations =[]
        locations <<  new GeoDistance(1,28.6405645,77.24177259999999)// DelhiGate
        locations << new GeoDistance(3,28.5621974,77.3740732)//Sector-49 Noida
        locations << new GeoDistance(2,28.6505535, 77.23189339999999) //ChandniChowk
        locations << new GeoDistance(4,28.4446394,77.46575469999999) // JayPee Aman Sec -151 Noida

        GeoAddressDTO dto = new GeoAddressDTO(addressLine1: '199-A',addressLine2: 'plot no 1223', city: 'Noida', state: 'uttar pradesh', country: 'India', postalCode: '201305')
//        GeoAddressDTO dto = new GeoAddressDTO(addressLine1: 'Sab mall, sector-18', city: 'Noida', state: 'uttar pradesh', country: 'India')
//        GeoAddressDTO dto = new GeoAddressDTO(addressLine1: 'golcha', city: 'new delhi', state: 'Delhi', country: 'India')
//        GeoAddressDTO dto = new GeoAddressDTO(addressLine1: '60, Ansari Road', addressLine2: 'darya ganj', city: 'new delhi', state: 'Delhi', country: 'India')
//        GeoAddressDTO dto = new GeoAddressDTO(addressLine1: 'TilakMarg#$%^&*!@~_+', city: 'new delhi', state: 'Delhi', country: 'India')

        try {
            List nearestLocations = geoLocationService.listNearestLocationInOrder(dto,locations)
            render nearestLocations.collect{["Hub : " + it.locationId +" - distance " + it.distance]}
        } catch (RuntimeException e) {
            render e.message
        }
    }
}
