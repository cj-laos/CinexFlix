package com.cinexflix.api.repository;

import com.cinexflix.api.model.Historial;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface HistorialRepository extends MongoRepository<Historial, String> {
    List<Historial> findByUsuarioId(String usuarioId);
}