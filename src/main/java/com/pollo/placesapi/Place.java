package com.pollo.placesapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {
    private final String name;

    //Required for json deserialization
    public Place(){
        this(null);
    }

    public Place(String name) {
        this.name = name;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }
}
