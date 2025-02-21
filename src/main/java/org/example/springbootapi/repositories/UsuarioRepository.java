package org.example.springbootapi.repositories;

import org.example.springbootapi.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    /**
     * Verifica si existe un usuario por su token.
     *
     * @param token el token del usuario
     * @return true si existe un usuario con el token especificado, false en caso contrario
     */
    Boolean existsByToken(String token);

    /**
     * Encuentra un usuario por su token.
     *
     * @param token el token del usuario
     * @return el usuario con el token especificado
     */
    Usuario findByToken(String token);
}
