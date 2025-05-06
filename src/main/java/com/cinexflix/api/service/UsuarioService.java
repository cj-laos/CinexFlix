package com.cinexflix.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cinexflix.api.model.Usuario;
import com.cinexflix.api.repository.UsuarioRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario registrarUsuario(String nombre, String apellidos, String email, String contrasena, Date fechaNacimiento, String telefono, String foto) {
        // Encriptar la contraseña
        String contrasenaEncriptada = passwordEncoder.encode(contrasena);

        // Crear el usuario con la fecha de creación
        Usuario usuario = new Usuario(nombre, apellidos, email, contrasenaEncriptada, fechaNacimiento, new Date(), telefono, foto);

        // Guardar el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }
    

    public Optional<Usuario> iniciarSesion(String email, String contrasena) {
        // Buscar el usuario por el email
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        // Si el usuario existe, verificar la contraseña
        if (usuario.isPresent() && passwordEncoder.matches(contrasena, usuario.get().getContrasena())) {
            return usuario;
        }
        return Optional.empty();  // Si no se encuentra el usuario o la contraseña es incorrecta
    }
}
