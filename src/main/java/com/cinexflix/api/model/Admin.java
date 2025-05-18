package com.cinexflix.api.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_admin")
public class Admin {

    @Id
    private String id;

    private String email;

    private String contrasena;

    // Otros campos opcionales
    private String nombre;
    private String apellidos;
    private Date fechaCreacionCuenta;

    private String rol = "ADMIN"; // ← Aquí también 
}
// package com.cinexflix.api.model;