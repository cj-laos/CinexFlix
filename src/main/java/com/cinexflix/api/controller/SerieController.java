package com.cinexflix.api.controller;

import com.cinexflix.api.model.Serie;
import com.cinexflix.api.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @PostMapping("/crear")
    public ResponseEntity<Serie> crearSerie(@RequestBody Serie serie) {
        Serie creada = serieService.crearSerie(serie);
        return ResponseEntity.ok(creada);
    }

    @GetMapping
    public ResponseEntity<List<Serie>> listarSeries() {
        return ResponseEntity.ok(serieService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Serie> obtenerPorId(@PathVariable String id) {
        return serieService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable String id) {
        serieService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/temporadas")
    public ResponseEntity<Serie> agregarTemporada(@PathVariable String id, @RequestBody Serie.Temporada temporada) {
        Serie serieActualizada = serieService.agregarTemporada(id, temporada);
        return ResponseEntity.ok(serieActualizada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Serie> actualizarSerie(@PathVariable String id, @RequestBody Serie serieActualizada) {
        return ResponseEntity.ok(serieService.actualizarSerie(id, serieActualizada));
    }

}
