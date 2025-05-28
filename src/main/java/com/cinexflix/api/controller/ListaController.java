package com.cinexflix.api.controller;

import com.cinexflix.api.dto.ListaDto;
import com.cinexflix.api.model.Lista;
import com.cinexflix.api.service.ListaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista")
@RequiredArgsConstructor
public class ListaController {

    private final ListaService listaService;

    @PostMapping
    public ResponseEntity<Lista> agregar(@RequestBody ListaDto dto) {
        return ResponseEntity.ok(listaService.agregar(dto));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Lista>> obtener(@PathVariable String usuarioId) {
        return ResponseEntity.ok(listaService.obtenerPorUsuario(usuarioId));
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestParam String usuarioId, @RequestParam String contenidoId) {
        listaService.eliminar(usuarioId, contenidoId);
        return ResponseEntity.noContent().build();
    }
}