package com.ms_tienda.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private Double precio;
    private String imagen; // URL de la imagen
    
    @Column(length = 1000)
    private String descripcion;
    
    private Boolean destacado;
}