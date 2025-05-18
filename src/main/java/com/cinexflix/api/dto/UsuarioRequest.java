package com.cinexflix.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UsuarioRequest {

    private String nombre;
    private String apellidos;
    private String email;
    private String contrasena;
    private Date fechaNacimiento;
    private String telefono;
    private String foto;

    private String plan_seleccionado;
    private String modalidad_plan;
    private String fecha_inicio_plan;
    private String fecha_fin_plan;
}
