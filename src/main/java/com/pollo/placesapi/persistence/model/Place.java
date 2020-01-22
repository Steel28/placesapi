package com.pollo.placesapi.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {
    private final String name;
    private final String id;
    private Location location;
    private List<Review> reviews;


    //Required for json deserialization
    public Place(){
        this(null,null, null);
    }

    public Place(String id, String name, Location location) {
       this(id, name, location, new ArrayList<>());
    }

    public Place(String id, String name, Location location, List<Review> reviews) {
        this.name = name;
        this.id = id;
        this.location = location;
        this.reviews = reviews;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    @JsonIgnore
    public List<Review> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    @JsonProperty("score")
    public Float getScore(){
        if (reviews.isEmpty()){
            return 0.0f;
        }

        final Integer total = reviews.stream().reduce(0, (r1, review) -> r1 + review.getScore(), (t1, t2) -> t1 + t2);
        return (float)total / reviews.size();
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

}

