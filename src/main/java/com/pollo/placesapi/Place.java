package com.pollo.placesapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {
    private final String name;
    private final String id;

    //Required for json deserialization
    public Place(){
        this(null,null);
    }

    public Place(String id, String name ) {
        this.name = name;
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }
}
