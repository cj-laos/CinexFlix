package com.cinexflix.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
                rol 
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
}
