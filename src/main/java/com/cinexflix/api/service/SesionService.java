package com.cinexflix.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinexflix.api.dto.SesionRequest;
import com.cinexflix.api.dto.SesionUpdateRequest;
import com.cinexflix.api.model.Sesion;
import com.cinexflix.api.repository.SesionRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    public Sesion registrarInicioSesion(SesionRequest request) {
        Sesion sesion = new Sesion();
        sesion.setUserId(request.getUserId());
        sesion.setInicioSesion(new Date());
        sesion.setFinSesion(null);
        return sesionRepository.save(sesion);
    }

    public Sesion cerrarSesion(SesionUpdateRequest request) {
        Optional<Sesion> optionalSesion = sesionRepository.findById(request.getSesionId());
        if (optionalSesion.isPresent()) {
            Sesion sesion = optionalSesion.get();
            sesion.setFinSesion(new Date());
            return sesionRepository.save(sesion);
        }
        return null;
    }
}
