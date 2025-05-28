package com.cinexflix.api.service;

import com.cinexflix.api.dto.HistorialDto;
import com.cinexflix.api.model.Historial;
import com.cinexflix.api.repository.HistorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialService {
    private final HistorialRepository historialRepository;

    public Historial guardar(HistorialDto dto) {
        var historial = new Historial(null, dto.getUsuarioId(), dto.getContenidoId(), LocalDateTime.now());
        return historialRepository.save(historial);
    }

    public List<Historial> obtenerPorUsuario(String usuarioId) {
        return historialRepository.findByUsuarioId(usuarioId);
    }
}