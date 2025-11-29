package com.ms_auth.controller;

import com.ms_auth.model.Usuario;
import com.ms_auth.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth") // IMPORTANTE: Coincide con el Gateway
@Tag(name = "Autenticación", description = "Endpoints para Login y Registro de usuarios.")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired // Inyectamos el PasswordEncoder
    private PasswordEncoder passwordEncoder;

    // LOGIN
    @Operation(
        summary = "Inicia sesión de usuario",
        description = "Verifica credenciales y retorna un token JWT simulado y datos de usuario.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Inicio de sesión exitoso",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{\"ok\": true, \"id\": \"1\", \"fullName\": \"Admin Tienda\", \"role\": \"ADMIN\", \"token\": \"Bearer admin@tienda.cl_JWT_TOKEN_12345_ADMIN\"}"))
            ),
            @ApiResponse(
                responseCode = "401",
                description = "Credenciales incorrectas",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{\"ok\": false, \"mensaje\": \"Correo o contraseña incorrectos\"}"))
            )
        }
    )
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
    @Operation(
        summary = "Registra un nuevo usuario",
        description = "Crea un nuevo usuario con rol por defecto 'CLIENTE' y encripta la contraseña.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Registro exitoso",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{\"ok\": true, \"id\": 3, \"mensaje\": \"Registro exitoso\"}"))
            ),
            @ApiResponse(
                responseCode = "409",
                description = "Conflicto: Correo ya registrado",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{\"ok\": false, \"mensaje\": \"El correo ya está registrado\"}"))
            )
        }
    )
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