package com.cinexflix.api.controller;

import com.cinexflix.api.model.Admin;
import com.cinexflix.api.service.AdminService;
import com.cinexflix.api.dto.LoginRequest;
import com.cinexflix.api.dto.AdminRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Registro admin
    @PostMapping("/registro")
    public Admin registrarAdmin(@RequestBody AdminRequest adminRequest) {
        return adminService.registrarAdmin(
                adminRequest.getEmail(),
                adminRequest.getContrasena(),
                adminRequest.getNombre(),
                adminRequest.getApellidos()
        );
    }

    // Obtener todos los admins
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.obtenerTodos();
    }

    // Login admin
    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody LoginRequest loginRequest) {
        Optional<Admin> admin = adminService.iniciarSesion(loginRequest.getEmail(), loginRequest.getContrasena());
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email o contraseña incorrectos");
        }
    }
}
