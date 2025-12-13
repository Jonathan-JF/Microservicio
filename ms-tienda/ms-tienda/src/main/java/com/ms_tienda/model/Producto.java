package com.ms_tienda.model;

import jakarta.persistence.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del producto", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @Schema(description = "Nombre del producto", example = "Camiseta deportiva")
    private String nombre;
    @Schema(description = "Precio del producto", example = "19.99")
    private Double precio;
    @Schema(description = "URL de la imagen del producto", example = "https://example.com/imagen.jpg")
    private String imagen; // URL de la imagen
    
    @Column(length = 1000)
    @Schema(description = "Descripción del producto", example = "Camiseta de algodón de alta calidad")
    private String descripcion;
    
    @Schema(description = "Indica si el producto es destacado", example = "false")
    private Boolean destacado;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id") // Columna de clave foránea
    private Categoria categoria;
}