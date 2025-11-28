package com.ms_auth.controller;

import com.ms_auth.model.Usuario;
import com.ms_auth.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    @Autowired
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String correo = credenciales.get("correo");
        String pass = credenciales.get("password");

        // 1. Buscar usuario en BD
        Usuario usuario = usuarioService.findByCorreo(correo);

        // 2. Validar contraseña (usamos BCrypt en el servicio)
        if (usuario != null && usuarioService.checkPassword(pass, usuario.getPassword())) {
            // 3. Generar Token (Simplificado)
            String token = "Bearer " + java.util.UUID.randomUUID().toString(); // TOKEN DE MENTIRA (TEMPORAL)

            // 4. Devolver respuesta a React
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("token", token);
            respuesta.put("usuario", usuario);
            return ResponseEntity.ok(respuesta);
        }

        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    // REGISTRO
    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        Usuario creado = usuarioService.register(usuario);
        return ResponseEntity.ok(creado);
    }
}