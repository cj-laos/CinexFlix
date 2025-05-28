package com.cinexflix.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "lista")
public class Lista {
    @Id
    private String id;
    private String usuarioId;
    private String contenidoId;
    private LocalDateTime fechaVista;
}