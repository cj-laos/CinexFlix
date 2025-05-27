package com.cinexflix.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "sesiones")
public class Sesion {
    @Id
    private String id;
    private String userId;
    private Date inicioSesion;
    private Date finSesion;

    public Sesion() {

    }

    private Sesion(Builder builder) {
        this.userId = builder.userId;
        this.inicioSesion = builder.inicioSesion;
        this.finSesion = builder.finSesion;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getInicioSesion() {
        return inicioSesion;
    }

    public void setInicioSesion(Date inicioSesion) {
        this.inicioSesion = inicioSesion;
    }

    public Date getFinSesion() {
        return finSesion;
    }

    public void setFinSesion(Date finSesion) {
        this.finSesion = finSesion;
    }

    public static class Builder {
        private String userId;
        private Date inicioSesion;
        private Date finSesion;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder inicioSesion(Date inicioSesion) {
            this.inicioSesion = inicioSesion;
            return this;
        }

        public Builder finSesion(Date finSesion) {
            this.finSesion = finSesion;
            return this;
        }

        public Sesion build() {
            return new Sesion(this);
        }
    }
}
