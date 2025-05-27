package com.cinexflix.api.service;

import com.cinexflix.api.model.Serie;
import com.cinexflix.api.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Serie agregarTemporada(String idSerie, Serie.Temporada nuevaTemporada) {
        Optional<Serie> optionalSerie = serieRepository.findById(idSerie);
        if (optionalSerie.isPresent()) {
            Serie serie = optionalSerie.get();
            if (serie.getTemporadas() == null) {
                serie.setTemporadas(new ArrayList<>());
            }
            serie.getTemporadas().add(nuevaTemporada);
            return serieRepository.save(serie);
        } else {
            throw new RuntimeException("Serie no encontrada");
        }
    }

    public Serie actualizarSerie(String id, Serie serieActualizada) {
        Serie existente = serieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serie no encontrada"));

        existente.setTitulo(serieActualizada.getTitulo());
        existente.setDescripcion(serieActualizada.getDescripcion());
        existente.setRating(serieActualizada.getRating());
        existente.setAnioInicio(serieActualizada.getAnioInicio());
        existente.setAnioFin(serieActualizada.getAnioFin());
        existente.setCategoria(serieActualizada.getCategoria());
        existente.setImagen(serieActualizada.getImagen());

        return serieRepository.save(existente);
    }

}
