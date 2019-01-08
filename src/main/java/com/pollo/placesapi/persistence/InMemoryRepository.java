package com.pollo.placesapi.persistence;

import com.pollo.placesapi.persistence.model.Place;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepository implements PlacesRepository {
    final Map<String,Place> places = new HashMap<>();

    @Override
    public Collection<Place> list(){
        return places.values();
    }

    @Override
    public void add(final Place place){
        places.put(place.getId(), place);
    }

    @Override
    public Optional<Place> find(String id){
        if (places.containsKey(id)){
           return Optional.of(places.get(id));
        }
        return Optional.empty();
    }

    @Override
    public void remove(String id) {
        places.remove(id);
    }
}
