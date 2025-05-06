
package com.cinexflix.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {

    private String nombre;
    private String apellidos;
    private String email;
    private String contrasena;
    private Date fechaNacimiento;
    private String telefono;
    private String foto;
}
