package com.ms_tienda.controller;

import com.ms_tienda.model.Producto;
import com.ms_tienda.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Gestión del catálogo de productos.")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // --- READ ALL ---
    @Operation(
            summary = "Listar todos los productos",
            description = "Devuelve una lista completa de productos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Productos obtenidos",
                            content = @Content(schema = @Schema(implementation = Producto.class)))
            }
    )
    @GetMapping
    public List<Producto> listar() {
        return productoService.findAll();
    }

    // --- READ BY ID ---
    @Operation(
            summary = "Obtener un producto por ID",
            description = "Busca un producto por su ID.",
            parameters = {
                    @Parameter(name = "id", description = "ID del producto", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto encontrado",
                            content = @Content(schema = @Schema(implementation = Producto.class))),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- CREATE ---
    @Operation(
            summary = "Crear un nuevo producto",
            description = "Agrega un nuevo producto.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto creado",
                            content = @Content(schema = @Schema(implementation = Producto.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto nuevoProducto) {
        Producto guardado = productoService.save(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // --- UPDATE ---
    @Operation(
            summary = "Actualizar un producto",
            description = "Actualiza un producto existente por ID.",
            parameters = {
                    @Parameter(name = "id", description = "ID del producto a actualizar", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto actualizado",
                            content = @Content(schema = @Schema(implementation = Producto.class))),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto p) {

        Optional<Producto> existente = productoService.findById(id);

        if (existente.isPresent()) {
            Producto prod = existente.get();
            prod.setNombre(p.getNombre());
            prod.setPrecio(p.getPrecio());
            prod.setImagen(p.getImagen());
            prod.setDescripcion(p.getDescripcion());
            prod.setDestacado(p.getDestacado());
            prod.setCategoria(p.getCategoria());

            Producto guardado = productoService.save(prod);
            return ResponseEntity.ok(guardado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- DELETE ---
    @Operation(
            summary = "Eliminar un producto",
            description = "Elimina un producto por su ID.",
            parameters = {
                    @Parameter(name = "id", description = "ID del producto", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Producto eliminado"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
            }
    )
            @DeleteMapping("/{id}")
            public ResponseEntity<Object> eliminarProducto(@PathVariable Long id) {
                return productoService.findById(id)
                        .map(p -> {
                            productoService.deleteById(id);
                            return ResponseEntity.noContent().build();
                        })
                        .orElse(ResponseEntity.notFound().build());
            }
        
}