package com.ms_tienda.controller;

import com.ms_tienda.model.Producto;
import com.ms_tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    // Cambiado de Repository a Service para seguir buenas prácticas
    @Autowired
    private ProductoService productoService;

    /**
     * Endpoint para listar todos los productos.
     */
    @GetMapping
    public List<Producto> listar() {
        return productoService.findAll();
    }
    
    /**
     * Endpoint para obtener un producto por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.findById(id);
        // Si no se encuentra, retorna 404 (Not Found)
        return producto.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // NOTA: Los métodos de carga de datos iniciales están en MsTiendaApplication.java
}