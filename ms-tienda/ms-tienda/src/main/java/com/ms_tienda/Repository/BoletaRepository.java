package com.ms_tienda.Repository;

import com.ms_tienda.model.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoletaRepository extends JpaRepository<Boleta, Long> {
    // Método útil para que un usuario vea SU historial de compras
    List<Boleta> findByUsuarioCorreo(String usuarioCorreo);
}