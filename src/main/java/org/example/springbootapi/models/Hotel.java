package org.example.springbootapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Hoteles")
@Data
public class Hotel {
    @Id
    private String _id;
    private String ciudad;
    private String direccion;
    private String email;
    private Integer estrellas;
    private String nombre;
    private Double precio;
    private String telefono;
}
