package com.pollo.placesapi.persistence.mongo;

import com.pollo.placesapi.persistence.model.Review;

public class ReviewMongo {
    private  Integer score;
    private  String comment;

    public ReviewMongo(){

    }

    public ReviewMongo(Review review) {
        this.setScore(review.getScore());
        this.setComment(review.getComment());
    }


    public void setScore(Integer score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Review toReview(){return  new Review(score,comment);}
}
