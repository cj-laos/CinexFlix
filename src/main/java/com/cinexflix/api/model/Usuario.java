package com.cinexflix.api.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data  // Lombok genera getters, setters, toString(), equals() y hashCode()
@AllArgsConstructor  // Lombok genera un constructor con todos los parámetros
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;  // MongoDB generará automáticamente el ID
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasena;
    private Date fechaNacimiento;
    private Date fechaCreacionCuenta;
    private String telefono;
    private String foto;

    public Usuario() {
    }
     // Constructor con todos los parámetros
     public Usuario(String nombre, String apellidos, String email, String contrasena, Date fechaNacimiento, Date fechaCreacionCuenta, String telefono, String foto) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCreacionCuenta = fechaCreacionCuenta;
        this.telefono = telefono;
        this.foto = foto;
    }
}

