package org.example.springbootapi.repositories;

import org.example.springbootapi.models.Vuelo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VuelosRepository extends MongoRepository<Vuelo, String> {

}
