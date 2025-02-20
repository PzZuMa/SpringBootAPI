package org.example.springbootapi;

import org.example.springbootapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Boolean requestValidation(String token){
        return usuarioRepository.existsByToken(token);
    }
}
