package com.cinexflix.api.repository;

import com.cinexflix.api.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SerieRepository extends MongoRepository<Serie, String> {
}
