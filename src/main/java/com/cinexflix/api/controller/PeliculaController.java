package com.cinexflix.api.controller;

import com.cinexflix.api.model.Pelicula;
import com.cinexflix.api.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @PostMapping("/crear")
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody Pelicula pelicula) {
        Pelicula creada = peliculaService.crearPelicula(pelicula);
        return ResponseEntity.ok(creada);
    }

    @GetMapping
    public ResponseEntity<List<Pelicula>> listarPeliculas() {
        return ResponseEntity.ok(peliculaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable String id) {
        return peliculaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable String id) {
        peliculaService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
