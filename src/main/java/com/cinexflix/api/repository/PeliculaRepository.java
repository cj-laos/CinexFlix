package com.cinexflix.api.repository;

import com.cinexflix.api.model.Pelicula;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PeliculaRepository extends MongoRepository<Pelicula, String> {
}
