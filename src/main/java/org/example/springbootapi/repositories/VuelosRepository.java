package org.example.springbootapi.repositories;

import org.example.springbootapi.models.Vuelo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VuelosRepository extends MongoRepository<Vuelo, String> {
    List<Vuelo> findByOrigen(String origen);
    List<Vuelo> findByDestino(String destino);
    List<Vuelo> findByDuracionIsBetween(Integer duracionAfter, Integer duracionBefore);
}
