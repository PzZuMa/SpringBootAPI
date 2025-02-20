package org.example.springbootapi.repositories;

import org.example.springbootapi.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Boolean existsByToken(String token);
    Usuario findByToken(String token);
}
