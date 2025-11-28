package com.ms_tienda.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DetalleBoleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long productoId; // Guardamos el ID del producto
    private Integer cantidad;
    private Double precioUnitario; // Precio al momento de la compra
}