package com.cinexflix.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@Document(collection = "planes")
public class Plan {
    @Id
    private String id;
    private String nombre;
    private List<String> caracteristicas;
    private Map<String, Modalidad> modalidades;

    public static class Modalidad {
        private int precio;

        public int getPrecio() {
            return precio;
        }

        public void setPrecio(int precio) {
            this.precio = precio;
        }
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Map<String, Modalidad> getModalidades() {
        return modalidades;
    }

    public void setModalidades(Map<String, Modalidad> modalidades) {
        this.modalidades = modalidades;
    }
}
