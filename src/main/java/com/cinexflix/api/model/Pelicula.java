package com.cinexflix.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "peliculas")
public class Pelicula {

    @Id
    private String id; // ej. "MOV123456"
    
    private String titulo;
    private String descripcion;
    private int duracion; // en minutos
    private int anio;
    private String categoria; // referencia al _id en Categorias
    
    private List<String> actores;
    private List<String> directores;
    
    private double rating;
}
