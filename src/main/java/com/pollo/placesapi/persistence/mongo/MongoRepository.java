package com.pollo.placesapi.persistence.mongo;

import com.mongodb.client.MongoClient;
import com.pollo.placesapi.persistence.PlacesRepository;
import com.pollo.placesapi.persistence.model.Place;

import java.util.Collection;
import java.util.Optional;

public class MongoRepository implements PlacesRepository {
    final MongoClient client;

    public MongoRepository(final MongoClient client){
        this.client = client;
    }

    @Override
    public Collection<Place> list() {
        return null;
    }

    @Override
    public void add(Place place) {

    }

    @Override
    public Optional<Place> find(String id) {
        return Optional.empty();
    }

    @Override
    public void remove(String id) {

    }
}
