package com.pollo.placesapi.persistence.mongo;

import com.pollo.placesapi.persistence.model.Location;

public class LocationMongo {
    private  Double latitude;
    private  Double longitude;



    public LocationMongo (Location location){
        this.setLatitude(location.getLongitude());
        this.setLongitude(location.getLatitude());
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Location toLocation(){ return new Location(latitude,longitude); }
}
