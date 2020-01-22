package com.pollo.placesapi.persistence.mongo;

import com.mongodb.client.MongoClient;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pollo.placesapi.persistence.PlacesRepository;
import com.pollo.placesapi.persistence.model.Place;
import org.bson.Document;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MongoRepository implements PlacesRepository {
    final MongoClient client;
    final MongoDatabase database;
    MongoCollection<PlaceMongo> collection;


    public MongoRepository(final MongoClient client){
        this.client = client;
        database = client.getDatabase("places_api");
        collection = database.getCollection("places", PlaceMongo.class);
    }

    @Override
    public Collection<Place> list() {
        return StreamSupport.stream(collection.find().spliterator(), false)
                .map(pm -> pm.toPlace())
                .collect(Collectors.toList());
    }

    @Override
    public void add(final Place place) {

        collection.insertOne(new PlaceMongo(place));

    }

    @Override
    public Optional<Place> find(String id) {
        return Optional.empty();
    }

    @Override
    public void remove(String id) {

    }
}
