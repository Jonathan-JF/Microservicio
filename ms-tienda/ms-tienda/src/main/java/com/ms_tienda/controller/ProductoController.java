package com.ms_tienda.controller;

import com.ms_tienda.Repository.ProductoRepository;
import com.ms_tienda.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Endpoint para listar todos los productos.
     * Mapeado a GET /api/productos (a través del Gateway)
     */
    @GetMapping
    public List<Producto> listar() {
        return productoRepository.findAll();
    }
    
    // NOTA: Se eliminó el método /cargar-demo, ya que la carga inicial se hace
    // automáticamente en MsTiendaApplication.java usando CommandLineRunner.
}