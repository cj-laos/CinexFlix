package com.cinexflix.api.repository;

import com.cinexflix.api.model.Sesion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SesionRepository extends MongoRepository<Sesion, String> {

}
