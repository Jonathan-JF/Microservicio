package com.ms_tienda.Repository;

import com.ms_tienda.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Método extra para filtrar por destacados si lo necesitas
    List<Producto> findByDestacadoTrue();
}

