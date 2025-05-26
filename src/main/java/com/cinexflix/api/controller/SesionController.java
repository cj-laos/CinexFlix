package com.cinexflix.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cinexflix.api.dto.SesionRequest;
import com.cinexflix.api.dto.SesionUpdateRequest;
import com.cinexflix.api.model.Sesion;
import com.cinexflix.api.service.SesionService;

@RestController
@RequestMapping("/api/sesiones")
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @PostMapping("/inicio")
    public Sesion registrarInicio(@RequestBody SesionRequest request) {
        return sesionService.registrarInicioSesion(request);
    }

    @PutMapping("/cierre")
    public Sesion registrarCierre(@RequestBody SesionUpdateRequest request) {
        return sesionService.cerrarSesion(request);
    }
}
