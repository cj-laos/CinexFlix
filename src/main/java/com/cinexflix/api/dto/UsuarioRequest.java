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
}
