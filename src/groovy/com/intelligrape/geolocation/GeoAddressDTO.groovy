package com.intelligrape.geolocation

class GeoAddressDTO {
    static final String COMMA = ", "
    String addressLine1
    String addressLine2
    String city
    String state
    String postalCode
    String country
    String locality

    Boolean sensor = false

    public String getAddressComponent() {
        String addressComp = ""
        String value = getAddress()
        if (value) {
            addressComp += "address=" + value + "&"
        }

        value = getComponents()
        if (value) {
            addressComp += "components=" + value + "&"
        }

        addressComp += "sensor=" + sensor + "&"
        addressComp = addressComp.replace(" ", "+")
        return addressComp
    }

    private String getAddress() {
        String address = ""
        if (addressLine1) {
            address += addressLine1 + COMMA
        }
        if (addressLine2) {
            address += addressLine2 + COMMA
        }
        if (city) {
            address += city + COMMA
        }
        if (state) {
            address += state
        }

        address = address - COMMA
        return address
    }

    private String getComponents() {
        String components = postalCode ? "postal_code:" + postalCode + "|" : ""
        components += country ? "country:" + country + "|" : ""
        components += locality ? "locality:" + locality + "|" : ""
        components -= "|"
        return components
    }


}
