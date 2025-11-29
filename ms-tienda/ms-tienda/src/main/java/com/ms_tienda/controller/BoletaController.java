package com.ms_tienda.controller;

import com.ms_tienda.Repository.BoletaRepository;
import com.ms_tienda.model.Boleta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/boletas")
@Tag(name = "Boletas", description = "Gestión de boletas (compras) de los clientes.")
public class BoletaController {

    @Autowired
    private BoletaRepository boletaRepository;

    // Crear una nueva boleta (Compra)
    @Operation(
        summary = "Crea una nueva boleta (Compra)",
        description = "Registra una nueva transacción de compra. Requiere autenticación de usuario (CLIENTE o ADMIN).",
        security = @SecurityRequirement(name = "BearerAuth"), // Requiere autenticación
        responses = {
            @ApiResponse(responseCode = "200", description = "Boleta creada exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado / Token ausente o inválido")
        }
    )
    @PostMapping
    public ResponseEntity<Boleta> crearBoleta(@RequestBody Boleta nuevaBoleta) {
        // En un entorno real, se debería validar el token JWT y obtener el correo/ID del usuario autenticado.
        // Aquí se simula la lógica.
        nuevaBoleta.setFecha(LocalDateTime.now());
        Boleta guardada = boletaRepository.save(nuevaBoleta);
        return ResponseEntity.ok(guardada);
    }
    
    // Ver historial de compras (Opcional pero recomendado para el Historial del Cliente)
    @Operation(
        summary = "Obtiene historial de compras por correo",
        description = "Obtiene la lista de boletas asociadas a un correo de usuario específico. Requiere autenticación.",
        security = @SecurityRequirement(name = "BearerAuth"), // Requiere autenticación
        responses = {
            @ApiResponse(responseCode = "200", description = "Historial de boletas obtenido exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado / Token ausente o inválido")
        }
    )
    @GetMapping("/usuario/{correo}")
    public List<Boleta> obtenerPorUsuario(@PathVariable String correo) {
        // En un entorno real, se debería verificar que el usuario autenticado sea el mismo que el del 'correo' o un 'ADMIN'.
        return boletaRepository.findByUsuarioCorreo(correo);
    }
}