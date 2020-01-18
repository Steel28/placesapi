package com.pollo.placesapi.persistence;

import com.pollo.placesapi.persistence.model.Place;

import java.util.Collection;
import java.util.Optional;

public interface PlacesRepository {
    Collection<Place> list();

    void add(Place place);

    Optional<Place> find(String id);

    void remove(String id);
}
