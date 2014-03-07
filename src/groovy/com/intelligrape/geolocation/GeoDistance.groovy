package com.intelligrape.geolocation

class GeoDistance {
    double distance
    String hubId
    double longitude
    double latitude

    GeoDistance(Long aHubId, double aLatitude,double aLongitude){
        hubId = aHubId
        longitude = aLongitude
        latitude = aLatitude
    }

    GeoDistance(){}

}
