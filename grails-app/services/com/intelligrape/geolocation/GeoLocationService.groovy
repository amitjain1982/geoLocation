package com.intelligrape.geolocation

import com.javadocmd.simplelatlng.LatLng
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit
import groovy.json.JsonSlurper;

class GeoLocationService {
    static transactional = false
    def grailsApplication

    public List listNearestLocationInOrder(GeoAddressDTO addressDTO, List<GeoDistance> locations){
        GeoPosition geoPosition = getLocation(addressDTO)
        List<GeoDistance> nearestLocations = calculateDistanceFromLocations(geoPosition,locations)
        nearestLocations = sortAsPerDistance(nearestLocations)
        log.debug "Near locations in asc order from given location ${nearestLocations*.hubId}"
        return nearestLocations
    }

    private GeoPosition getLocation(GeoAddressDTO addressDTO) {
        String url = generateLocationAccessURL(addressDTO)
        def json = new JsonSlurper().parseText( new URL( url ).text )
        json = json?.results?.geometry?.location

        GeoPosition geoPosition = new GeoPosition(json.lat.find{it} as Double,json.lng.find{it} as Double)
        log.debug "Current location lat ${geoPosition.latitude} & lng ${geoPosition.longitude}"
        if(!geoPosition.longitude || !geoPosition.latitude){
            throw new RuntimeException("Location couldn't be identified, no results found")
        }
        return geoPosition
    }

    private String generateLocationAccessURL(GeoAddressDTO addressDTO) {
        String url = getMapsAPIURL()
        url += addressDTO.addressComponent
        url += "key=" + getMapsAPIKey()
        log.debug "User location access URL $url"
        return url
    }

    private List<GeoDistance> calculateDistanceFromLocations(GeoPosition currentPos, List<GeoDistance> locations){
        LatLng currentPosLatLng = new LatLng(currentPos.latitude, currentPos.longitude);
        locations.each{loc->
            LatLng destination = new LatLng(loc.latitude, loc.longitude)

            loc.distance = calculateDistance(currentPosLatLng, destination)
            log.debug "Distance from given location to loc $loc.hubId is $loc.distance"
        }
        return locations
    }

    private double calculateDistance(LatLng source, LatLng destination){
        LatLngTool.distance(source,destination,LengthUnit.KILOMETER)
    }

    private String getMapsAPIURL(){
        String url = grailsApplication.config.grails.plugins.geolocation.map.api.url
        if(!url.endsWith("?")){
            url += "?"
        }
        return url
    }

    private String getMapsAPIKey(){
        grailsApplication.config.grails.plugins.geolocation.map.api.key
    }

    private List<GeoDistance> sortAsPerDistance(List<GeoDistance> locations){
        locations.sort{it.distance}
    }
}
