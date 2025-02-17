package org.example.springbootapi.repositories;

import org.example.springbootapi.models.POI;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface POIRepository extends MongoRepository<POI, String> {

}
