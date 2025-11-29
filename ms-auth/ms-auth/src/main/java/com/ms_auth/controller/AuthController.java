package com.ms_auth.controller;

import com.ms_auth.model.Usuario;
import com.ms_auth.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder; // Importar
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth") // IMPORTANTE: Coincide con el Gateway
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired // Inyectamos el PasswordEncoder
    private PasswordEncoder passwordEncoder;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorEmail(loginRequest.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // 1. Usar PasswordEncoder para verificar la contraseña
            if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
                
                // 2. Simulación de Generación de Token JWT (para el requisito IE3.3.1)
                // En un entorno real, aquí se usaría una librería como jjwt
                String tokenSimulado = "Bearer " + usuario.getEmail() + "_JWT_TOKEN_12345_" + usuario.getRol();
                
                Map<String, Object> response = new HashMap<>();
                response.put("ok", true);
                response.put("id", usuario.getId().toString()); // Añadimos el ID
                response.put("fullName", usuario.getNombre()); // Cambio a 'fullName' para el frontend
                response.put("role", usuario.getRol());
                response.put("token", tokenSimulado);
                
                return ResponseEntity.ok(response);
            }
        }
        // Credenciales incorrectas
        return ResponseEntity.status(401).body(Map.of("ok", false, "mensaje", "Correo o contraseña incorrectos"));
    }

    // REGISTRO
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario nuevoUsuario) {
        // Verificar si el usuario ya existe
        if (usuarioService.buscarPorEmail(nuevoUsuario.getEmail()).isPresent()) {
            return ResponseEntity.status(409).body(Map.of("ok", false, "mensaje", "El correo ya está registrado"));
        }
        
        // 1. Asignar rol por defecto y encriptar contraseña
        if (nuevoUsuario.getRol() == null || nuevoUsuario.getRol().isEmpty()) {
            nuevoUsuario.setRol("CLIENTE");
        }
        nuevoUsuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));

        Usuario guardado = usuarioService.guardarUsuario(nuevoUsuario);
        return ResponseEntity.ok(Map.of("ok", true, "id", guardado.getId(), "mensaje", "Registro exitoso"));
    }
}