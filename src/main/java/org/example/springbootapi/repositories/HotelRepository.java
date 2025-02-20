package org.example.springbootapi.repositories;

import org.example.springbootapi.models.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HotelRepository extends MongoRepository<Hotel, String> {

}
