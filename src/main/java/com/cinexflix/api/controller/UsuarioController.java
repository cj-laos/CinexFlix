package com.cinexflix.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cinexflix.api.dto.LoginRequest;
import com.cinexflix.api.dto.UsuarioRequest;
import com.cinexflix.api.model.Usuario;
import com.cinexflix.api.service.UsuarioService;

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
            usuarioRequest.getFoto(),
            usuarioRequest.getPlan_seleccionado(),
            usuarioRequest.getModalidad_plan()
    );
}


    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.obtenerTodos();
    }

    // Inicio de sesión de usuario (sin JWT)
    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioService.iniciarSesion(loginRequest.getEmail(), loginRequest.getContrasena());

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get()); // Devuelve solo los datos del usuario
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email o contraseña incorrectos");
        }
    }
    @PutMapping("/{id}/actualizar")
public ResponseEntity<?> actualizarDatosBasicos(@PathVariable String id, @RequestBody UsuarioRequest usuarioRequest) {
    Optional<Usuario> usuarioActualizado = usuarioService.actualizarDatosBasicos(id, usuarioRequest);

    if (usuarioActualizado.isPresent()) {
        return ResponseEntity.ok(usuarioActualizado.get());
    } else {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("El correo ya está en uso por otro usuario o el usuario no existe.");
    }
}

}
