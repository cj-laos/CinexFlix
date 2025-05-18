package com.cinexflix.api.dto;

import lombok.Data;

@Data
public class AdminRequest {
    private String email;
    private String contrasena;
    private String nombre;
    private String apellidos;
}
