package com.cinexflix.api.repository;

import com.cinexflix.api.model.Lista;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ListaRepository extends MongoRepository<Lista, String> {
    List<Lista> findByUsuarioId(String usuarioId);
    Optional<Lista> findByUsuarioIdAndContenidoId(String usuarioId, String contenidoId);
}