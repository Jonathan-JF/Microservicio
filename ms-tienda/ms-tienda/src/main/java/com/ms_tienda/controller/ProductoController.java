package com.ms_tienda.controller;

import com.ms_tienda.Repository.ProductoRepository;
import com.ms_tienda.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

@Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> listar() {
        return productoRepository.findAll();
    }
    
    // Endpoint para cargar datos de prueba (útil para la demo)
    @GetMapping("/cargar-demo")
    public String cargarDatos() {
        Producto p1 = new Producto();
        p1.setNombre("Robot Demo");
        p1.setPrecio(15000.0);
        p1.setDestacado(true);
        p1.setImagen("https://via.placeholder.com/150");
        productoRepository.save(p1);
        return "Datos cargados";
    }
}