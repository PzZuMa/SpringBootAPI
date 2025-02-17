package org.example.springbootapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Usuarios")
@Data
public class Usuario {
    @Id
    private String _id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
}
