package org.example.springbootapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Puntos de Interes")
@Data
public class POI {
    @Id
    private String _id;
    private String ciudad;
    private String horario_apertura;
    private String nombre;
    private Double precio;
    private String tipo;
}
