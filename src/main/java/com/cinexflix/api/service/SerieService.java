package com.cinexflix.api.service;

import com.cinexflix.api.model.Serie;
import com.cinexflix.api.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public Serie crearSerie(Serie serie) {
        return serieRepository.save(serie);
    }

    public List<Serie> obtenerTodas() {
        return serieRepository.findAll();
    }

    public Optional<Serie> obtenerPorId(String id) {
        return serieRepository.findById(id);
    }

    public void eliminarPorId(String id) {
        serieRepository.deleteById(id);
    }
}
