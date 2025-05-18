package com.cinexflix.api.service;

import com.cinexflix.api.model.Admin;
import com.cinexflix.api.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Registrar nuevo admin usando constructor con todos los campos
    public Admin registrarAdmin(String email, String contrasena, String nombre, String apellidos) {
        String contrasenaEncriptada = passwordEncoder.encode(contrasena);
        Date fechaCreacion = new Date();

        Admin admin = new Admin(
            null, // id generado por MongoDB
            email,
            contrasenaEncriptada,
            nombre,
            apellidos,
            fechaCreacion,
            "ADMIN"  // rol fijo
        );

        return adminRepository.save(admin);
    }

    // Obtener todos los admins
    public List<Admin> obtenerTodos() {
        return adminRepository.findAll();
    }

    // Login admin sin JWT
    public Optional<Admin> iniciarSesion(String email, String contrasena) {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent() && passwordEncoder.matches(contrasena, admin.get().getContrasena())) {
            return admin;
        }
        return Optional.empty();
    }
}
