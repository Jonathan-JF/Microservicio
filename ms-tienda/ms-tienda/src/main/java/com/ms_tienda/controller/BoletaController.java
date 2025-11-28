package com.ms_tienda.controller;

import com.ms_tienda.Repository.BoletaRepository;
import com.ms_tienda.model.Boleta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletaRepository boletaRepository;

    // Crear una nueva boleta (Compra)
    @PostMapping
    public ResponseEntity<Boleta> crearBoleta(@RequestBody Boleta nuevaBoleta) {
        nuevaBoleta.setFecha(LocalDateTime.now());
        Boleta guardada = boletaRepository.save(nuevaBoleta);
        return ResponseEntity.ok(guardada);
    }
    
    // Ver historial de compras (Opcional pero recomendado para el Historial del Cliente)
    @GetMapping("/usuario/{correo}")
    public List<Boleta> obtenerPorUsuario(@PathVariable String correo) {
        return boletaRepository.findByUsuarioCorreo(correo);
    }
}