package com.pollo.placesapi.persistence.mongo;

import com.pollo.placesapi.persistence.model.Location;
import com.pollo.placesapi.persistence.model.Place;
import com.pollo.placesapi.persistence.model.Review;
import org.bson.types.ObjectId;

import java.util.List;

public class PlaceMongo {
    private String name;
    private ObjectId id;
    private String idPublic;
    private LocationMongo location;
    private List<Review> reviews;


   public PlaceMongo(){

    }

    public PlaceMongo(Place p) {
        this.setName(p.getName());
        this.setIdPublic(p.getId());
        this.setLocation(p.getLocation());
        this.setReviews(p.getReviews());
    }

    public Place toPlace(){

       return new Place(idPublic, name, location.toLocation(), reviews);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getIdPublic() {
        return idPublic;
    }

    public void setIdPublic(String idPublic) {
        this.idPublic = idPublic;
    }

    public Location getLocation() {
        return location.toLocation();
    }

    public void setLocation(Location location) {
        this.location = new LocationMongo(location);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
