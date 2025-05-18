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
@Document(collection = "series")
public class Serie {

    @Id
    private String id; // ej. "SER654321"
    
    private String titulo;
    private String descripcion;
    private String categoria; // referencia a Categorias
    
    private List<Temporada> temporadas;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Temporada {
        private int anioInicio;
        private int anioFin;
    }
}
