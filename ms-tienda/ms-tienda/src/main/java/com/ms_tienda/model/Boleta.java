package com.ms_tienda.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String usuarioCorreo; // Quién compró
    private LocalDateTime fecha;
    private Double total;
    
    @OneToMany(cascade = CascadeType.ALL) // Al guardar boleta, se guardan los detalles
    @JoinColumn(name = "boleta_id") // Crea la FK en la tabla detalle
    private List<DetalleBoleta> detalles;
}
