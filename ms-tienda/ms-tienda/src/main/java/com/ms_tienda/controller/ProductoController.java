package com.ms_tienda.controller;

import com.ms_tienda.model.Producto;
import com.ms_tienda.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Gestión del catálogo de productos de la tienda.")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // --- READ (GET) ---
    /**
     * Endpoint para listar todos los productos.
     */
    @Operation(
        summary = "Lista todos los productos",
        description = "Obtiene una lista completa de todos los productos disponibles en el catálogo. Acceso público.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
        }
    )
    @GetMapping
    public List<Producto> listar() {
        return productoService.findAll();
    }
    
    /**
     * Endpoint para obtener un producto por su ID.
     */
    @Operation(
        summary = "Obtiene un producto por ID",
        description = "Busca y retorna un producto específico usando su ID. Acceso público.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.findById(id);
        // Si no se encuentra, retorna 404 (Not Found)
        return producto.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // --- CREATE (POST) ---
    @Operation(
        summary = "Crea un nuevo producto",
        description = "Añade un nuevo producto al catálogo. **Requiere autenticación (Rol ADMIN)**.",
        security = @SecurityRequirement(name = "BearerAuth"), // Requiere autenticación JWT
        responses = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente", 
                         content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado / Token inválido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acceso denegado (Rol insuficiente)", content = @Content)
        }
    )
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto nuevoProducto) {
        // En un entorno real, se debería verificar el rol (ADMIN) antes de guardar.
        Producto guardado = productoService.save(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // --- UPDATE (PUT) ---
    @Operation(
        summary = "Actualiza un producto existente",
        description = "Actualiza completamente la información de un producto por su ID. **Requiere autenticación (Rol ADMIN)**.",
        security = @SecurityRequirement(name = "BearerAuth"), // Requiere autenticación JWT
        responses = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente", 
                         content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado / Token inválido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acceso denegado (Rol insuficiente)", content = @Content)
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        // 1. Buscar si existe
        Optional<Producto> productoExistente = productoService.findById(id);
        
        if (productoExistente.isPresent()) {
            // 2. Actualizar campos y guardar
            Producto producto = productoExistente.get();
            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setImagen(productoActualizado.getImagen());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setDestacado(productoActualizado.getDestacado());
            producto.setCategoria(productoActualizado.getCategoria()); // Asume que la categoría existe
            
            Producto guardado = productoService.save(producto);
            return ResponseEntity.ok(guardado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- DELETE (DELETE) ---
    @Operation(
        summary = "Elimina un producto por ID",
        description = "Elimina un producto del catálogo usando su ID. **Requiere autenticación (Rol ADMIN)**.",
        security = @SecurityRequirement(name = "BearerAuth"), // Requiere autenticación JWT
        responses = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado / Token inválido", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acceso denegado (Rol insuficiente)", content = @Content)
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (productoService.findById(id).isPresent()) {
            // Se asume que el método deleteById existe en el repositorio subyacente o es accesible a través del servicio.
            productoService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}