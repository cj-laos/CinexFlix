package com.cinexflix.api.service;

import com.cinexflix.api.model.Categoria;
import com.cinexflix.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> obtenerPorId(String id) {
        return categoriaRepository.findById(id);
    }

    public void eliminarPorId(String id) {
        categoriaRepository.deleteById(id);
    }
}
