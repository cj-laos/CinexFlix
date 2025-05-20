package com.cinexflix.api.service;

import com.cinexflix.api.model.Pelicula;
import com.cinexflix.api.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public Pelicula crearPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public List<Pelicula> obtenerTodas() {
        return peliculaRepository.findAll();
    }

    public Optional<Pelicula> obtenerPorId(String id) {
        return peliculaRepository.findById(id);
    }

    public boolean existePorId(String id) {
        return peliculaRepository.existsById(id);
    }

    public void eliminarPorId(String id) {
        peliculaRepository.deleteById(id);
    }

}
