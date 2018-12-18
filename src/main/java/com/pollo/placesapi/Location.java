package com.pollo.placesapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    private final Double latitude;
    private final Double longitude;

    public Location(){
       this(null, null);
    }

    public Location (Double latitude, Double longitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @JsonProperty("lng")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("lat")
    public Double getLatitude() {
        return latitude;
    }
}
