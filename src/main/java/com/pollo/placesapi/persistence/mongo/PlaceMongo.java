package com.pollo.placesapi.persistence.mongo;

import com.pollo.placesapi.persistence.model.Location;
import com.pollo.placesapi.persistence.model.Place;
import com.pollo.placesapi.persistence.model.Review;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class PlaceMongo {
    private String name;
    private ObjectId id;
    private String idPublic;
    private LocationMongo location;
    private List<ReviewMongo> reviews;


   public PlaceMongo(){

    }

    public PlaceMongo(Place p) {
        this.setName(p.getName());
        this.setIdPublic(p.getId());
        this.setLocation(new LocationMongo(p.getLocation()));
        this.setReviews(ConvertToListReviewsMogo(p.getReviews()));
    }

    public Place toPlace(){

       return new Place(idPublic, name, location.toLocation(), ConvertToListReviews(reviews));
    }

   public List<ReviewMongo> ConvertToListReviewsMogo(List<Review> reviews){
       List<ReviewMongo> result = new ArrayList<>();
       for (Review r : reviews){
           result.add(new ReviewMongo(r));
       }

       return result;
   }

    public List<Review> ConvertToListReviews(List<ReviewMongo> reviews){
        List<Review> result = new ArrayList<>();
        for (ReviewMongo r : reviews){
            result.add(r.toReview());
        }
        return result;
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

    public LocationMongo getLocation() {
        return location;
    }

    public void setLocation(LocationMongo location) {
        this.location = location;
    }

    public List<ReviewMongo> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewMongo> reviews) {
        this.reviews = reviews;
    }
}
