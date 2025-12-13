package com.ms_tienda.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Representación del producto a crear/actualizar")
public class ProductoRequest {

    @NotBlank
    @Schema(description = "Nombre del producto", example = "Camiseta deportiva")
    private String nombre;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Precio del producto", example = "19.99")
    private Double precio;

    @Schema(description = "URL de la imagen del producto", example = "https://example.com/imagen.jpg")
    private String imagen;

    @Schema(description = "Descripción del producto", example = "Camiseta de algodón de alta calidad")
    private String descripcion;

    @Schema(description = "Indica si es producto destacado", example = "false")
    private Boolean destacado;

    @NotNull
    @Schema(description = "ID de la categoría asociada", example = "1")
    private Long categoriaId;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getDestacado() {
        return destacado;
    }

    public void setDestacado(Boolean destacado) {
        this.destacado = destacado;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
