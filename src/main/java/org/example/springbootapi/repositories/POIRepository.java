package org.example.springbootapi.repositories;

import org.example.springbootapi.models.POI;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface POIRepository extends MongoRepository<POI, String> {
    List<POI> findByCiudad(String ciudad);
    List<POI> findByTipo(String tipo);
    List<POI> findByPrecioBetween(Double min, Double max);
}
