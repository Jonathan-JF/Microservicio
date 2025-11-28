package com.ms_tienda.controller;

import com.ms_tienda.Repository.ProductoRepository;
import com.ms_tienda.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> listar() {
        return productoRepository.findAll();
    }
    
    // Endpoint modificado para cargar BEBIDAS
    @GetMapping("/cargar-demo")
    public String cargarDatos() {
        // Limpiamos la base de datos previa para no duplicar si recargas
        productoRepository.deleteAll();

        Producto p1 = new Producto();
        p1.setNombre("Coca Cola 3L");
        p1.setPrecio(3500.0);
        p1.setDestacado(true);
        p1.setDescripcion("Bebida gaseosa sabor original, formato familiar.");
        p1.setImagen("https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&w=500&q=60");

        Producto p2 = new Producto();
        p2.setNombre("Cerveza Lager Pack 6");
        p2.setPrecio(5990.0);
        p2.setDestacado(true);
        p2.setDescripcion("Pack de 6 latas de cerveza lager premium.");
        p2.setImagen("https://images.unsplash.com/photo-1608270586620-25fd19478f0e?auto=format&fit=crop&w=500&q=60");

        Producto p3 = new Producto();
        p3.setNombre("Jugo de Naranja Natural");
        p3.setPrecio(2000.0);
        p3.setDestacado(false);
        p3.setDescripcion("Jugo 100% natural sin azúcar añadida.");
        p3.setImagen("https://images.unsplash.com/photo-1600271886742-f049cd451bba?auto=format&fit=crop&w=500&q=60");

        // Guardamos todos de una vez
        productoRepository.saveAll(Arrays.asList(p1, p2, p3));
        
        return "¡Bebidas de prueba cargadas exitosamente!";
    }
}