package com.pollo.placesapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {
    private final Integer score;
    private final String comment;

    public Review(){
        this(null,null);
    }

    public Review(Integer score, String comment) {
        this.score=score;
        this.comment=comment;
    }

    @JsonProperty("score")
    public Integer getScore() {
        return score;
    }

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }
}
