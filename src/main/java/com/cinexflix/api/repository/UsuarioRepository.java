package com.cinexflix.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cinexflix.api.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);  // Buscar usuario por email
}
