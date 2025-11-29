package com.ms_auth.controller;

import com.ms_auth.model.Usuario;
import com.ms_auth.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth") // IMPORTANTE: Coincide con el Gateway
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorEmail(loginRequest.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Validación simple de contraseña
            if (usuario.getPassword().equals(loginRequest.getPassword())) {
                
                Map<String, Object> response = new HashMap<>();
                response.put("ok", true);
                response.put("usuario", usuario.getNombre());
                response.put("role", usuario.getRol());
                response.put("token", "token-simulado-12345");
                
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(401).body(Map.of("ok", false, "mensaje", "Credenciales incorrectas"));
    }

    // REGISTRO
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario nuevoUsuario) {
        if (nuevoUsuario.getRol() == null) {
            nuevoUsuario.setRol("CLIENTE");
        }
        Usuario guardado = usuarioService.guardarUsuario(nuevoUsuario);
        return ResponseEntity.ok(guardado);
    }
}