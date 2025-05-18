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
@Document(collection = "usuarios_new")
public class Usuario {

    @Id
    private String id;

    private String nombre;
    private String apellidos;
    private String email;
    private String contrasena;
    private Date fechaNacimiento;
    private Date fechaCreacionCuenta;
    private String telefono;
    private String foto;

    // Nuevos campos para plan de suscripción
    private String plan_seleccionado;
    private String modalidad_plan;
    private Date fecha_inicio_plan;
    private Date fecha_fin_plan;

    private String rol = "USER"; // ← Aquí está el rol
}
