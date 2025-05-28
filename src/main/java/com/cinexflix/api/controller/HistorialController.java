package com.cinexflix.api.controller;

import com.cinexflix.api.dto.HistorialDto;
import com.cinexflix.api.model.Historial;
import com.cinexflix.api.service.HistorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor
public class HistorialController {

    private final HistorialService historialService;

    @PostMapping
    public ResponseEntity<Historial> registrar(@RequestBody HistorialDto dto) {
        return ResponseEntity.ok(historialService.guardar(dto));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Historial>> obtener(@PathVariable String usuarioId) {
        return ResponseEntity.ok(historialService.obtenerPorUsuario(usuarioId));
    }
}
