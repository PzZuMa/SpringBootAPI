package org.example.springbootapi;

import org.example.springbootapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de seguridad para la validación de tokens.
 */
@Service
public class SecurityService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Valida si un token existe en el repositorio de usuarios.
     *
     * @param token el token a validar
     * @return true si el token existe, false en caso contrario
     */
    public Boolean requestValidation(String token){
        return usuarioRepository.existsByToken(token);
    }
}
