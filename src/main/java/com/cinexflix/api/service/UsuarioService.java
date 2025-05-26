package com.cinexflix.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cinexflix.api.dto.UsuarioRequest;
import com.cinexflix.api.model.Usuario;
import com.cinexflix.api.repository.UsuarioRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Registrar nuevo usuario con plan, calculando fechas automáticamente
    public Usuario registrarUsuario(
            String nombre,
            String apellidos,
            String email,
            String contrasena,
            Date fechaNacimiento,
            String telefono,
            String foto,
            String planSeleccionado,
            String modalidadPlan
    ) {
        String contrasenaEncriptada = passwordEncoder.encode(contrasena);

        Date fechaInicioPlan = new Date(); // fecha actual

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fechaInicioPlan);

        if ("anual".equalsIgnoreCase(modalidadPlan)) {
            calendar.add(Calendar.YEAR, 1);
        } else if ("mensual".equalsIgnoreCase(modalidadPlan)) {
            calendar.add(Calendar.MONTH, 1);
        } else {
            // Por si se recibe otra modalidad, pones 1 mes como default
            calendar.add(Calendar.MONTH, 1);
        }

        Date fechaFinPlan = calendar.getTime();

        Usuario usuario = new Usuario(
                null,  // id generado por MongoDB
                nombre,
                apellidos,
                email,
                contrasenaEncriptada,
                fechaNacimiento,
                new Date(),  // fechaCreacionCuenta: hoy
                telefono,
                foto,
                planSeleccionado,
                modalidadPlan,
                fechaInicioPlan,
                fechaFinPlan,
                "USER" // Default role for a new user
        );

        return usuarioRepository.save(usuario);
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // Iniciar sesión sin JWT
    public Optional<Usuario> iniciarSesion(String email, String contrasena) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent() && passwordEncoder.matches(contrasena, usuario.get().getContrasena())) {
            return usuario;
        }

        return Optional.empty();
    }

    public Optional<Usuario> actualizarDatosBasicos(String id, UsuarioRequest usuarioRequest) {
    Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

    if (usuarioOptional.isEmpty()) {
        return Optional.empty();
    }

    Usuario usuario = usuarioOptional.get();

    // Validar si se quiere actualizar el email
    if (usuarioRequest.getEmail() != null && !usuarioRequest.getEmail().equals(usuario.getEmail())) {
        Optional<Usuario> usuarioConEmail = usuarioRepository.findByEmail(usuarioRequest.getEmail());
        if (usuarioConEmail.isPresent() && !usuarioConEmail.get().getId().equals(id)) {
            return Optional.empty(); // Email en uso por otro usuario
        }
        usuario.setEmail(usuarioRequest.getEmail());
    }

    // Actualizar solo los campos permitidos
    if (usuarioRequest.getNombre() != null) usuario.setNombre(usuarioRequest.getNombre());
    if (usuarioRequest.getApellidos() != null) usuario.setApellidos(usuarioRequest.getApellidos());
    if (usuarioRequest.getFoto() != null) usuario.setFoto(usuarioRequest.getFoto());
    if (usuarioRequest.getTelefono() != null) usuario.setTelefono(usuarioRequest.getTelefono());
    if (usuarioRequest.getContrasena() != null) usuario.setContrasena(usuarioRequest.getContrasena());    
{
        usuario.setContrasena(passwordEncoder.encode(usuarioRequest.getContrasena()));
    }
    return Optional.of(usuarioRepository.save(usuario));
}

}
