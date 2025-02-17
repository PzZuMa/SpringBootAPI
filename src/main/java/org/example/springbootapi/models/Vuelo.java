package org.example.springbootapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Vuelos")
@Data
public class Vuelo {
    @Id
    private String _id;
    private String codigo;
    private Integer duracion;
    private String fecha_salida;
    private String origen;
    private String destino;
    private String hora_salida;
}
