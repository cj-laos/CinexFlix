package com.cinexflix.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@Document(collection = "sesiones")
public class Sesion {
    @Id
    private String id;
    private String userId;
    private Date inicioSesion;
    private Date finSesion;
}
