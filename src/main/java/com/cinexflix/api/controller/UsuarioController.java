package com.cinexflix.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cinexflix.api.dto.LoginRequest;
import com.cinexflix.api.dto.UsuarioRequest;
import com.cinexflix.api.model.Usuario;
import com.cinexflix.api.service.UsuarioService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")


public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Registro de usuario
    @PostMapping("/registro")
    public Usuario registrarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.registrarUsuario(
                usuarioRequest.getNombre(),
                usuarioRequest.getApellidos(),
                usuarioRequest.getEmail(),
                usuarioRequest.getContrasena(),
                usuarioRequest.getFechaNacimiento(),
                usuarioRequest.getTelefono(),
                usuarioRequest.getFoto()
        );
    }
    @GetMapping
public List<Usuario> getAllUsuarios() {
    return usuarioService.obtenerTodos();
}

@GetMapping("/test")
public String test() {
    return "API funcionando";
}



    // Inicio de sesión de usuario
    @PostMapping("/login")
    public String iniciarSesion(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioService.iniciarSesion(loginRequest.getEmail(), loginRequest.getContrasena());

        if (usuario.isPresent()) {
            return "Bienvenido " + usuario.get().getNombre();
        } else {
            return "Email o contraseña incorrectos";
        }
    }
}
