package com.cinexflix.api.service;

import com.cinexflix.api.dto.ListaDto;
import com.cinexflix.api.model.Lista;
import com.cinexflix.api.repository.ListaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListaService {
    private final ListaRepository listaRepository;

    public Lista agregar(ListaDto dto) {
        var lista = new Lista(null, dto.getUsuarioId(), dto.getContenidoId(), LocalDateTime.now());
        return listaRepository.save(lista);
    }

    public List<Lista> obtenerPorUsuario(String usuarioId) {
        return listaRepository.findByUsuarioId(usuarioId);
    }

    public void eliminar(String usuarioId, String contenidoId) {
        listaRepository.findByUsuarioIdAndContenidoId(usuarioId, contenidoId)
                .ifPresent(listaRepository::delete);
    }
}