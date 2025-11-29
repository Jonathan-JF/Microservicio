package com.ms_tienda.service;

import com.ms_tienda.Repository.ProductoRepository;
import com.ms_tienda.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }
    
    // Método para crear o actualizar un producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // Nuevo método para eliminar un producto por ID (Necesario para el CRUD completo)
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}