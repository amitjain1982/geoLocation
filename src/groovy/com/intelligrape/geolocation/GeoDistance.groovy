package com.intelligrape.geolocation

class GeoDistance {
    double distance
    String locationId
    double longitude
    double latitude

    GeoDistance(Long aLocationId, double aLatitude,double aLongitude){
        locationId = aLocationId
        longitude = aLongitude
        latitude = aLatitude
    }

    GeoDistance(){}

}
