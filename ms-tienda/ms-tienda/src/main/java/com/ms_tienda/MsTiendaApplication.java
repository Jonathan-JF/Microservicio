package com.ms_tienda;

import com.ms_tienda.Repository.CategoriaRepository;
import com.ms_tienda.Repository.ProductoRepository;
import com.ms_tienda.model.Categoria;
import com.ms_tienda.model.Producto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MsTiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTiendaApplication.class, args);
	}

    /**
     * Bean para cargar categorías y productos de demostración al iniciar.
     */
    @Bean
    public CommandLineRunner initData(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        return args -> {
            // Solo cargar si no hay productos
            if (productoRepository.count() == 0) {
                System.out.println("Cargando categorías y productos de demostración...");

                // 1. Creación de Categorías
                Categoria bebidas = new Categoria();
                bebidas.setNombre("Bebidas");
                categoriaRepository.save(bebidas);
                
                Categoria alcohol = new Categoria();
                alcohol.setNombre("Alcohol");
                categoriaRepository.save(alcohol);
                
                // 2. Creación de Productos
                Producto p1 = new Producto();
                p1.setNombre("Coca Cola 3L");
                p1.setPrecio(3500.0);
                p1.setDestacado(true);
                p1.setDescripcion("Bebida gaseosa sabor original, formato familiar.");
                p1.setImagen("https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&w=500&q=60");
                p1.setCategoria(bebidas);

                Producto p2 = new Producto();
                p2.setNombre("Cerveza Lager Pack 6");
                p2.setPrecio(5990.0);
                p2.setDestacado(true);
                p2.setDescripcion("Pack de 6 latas de cerveza lager premium.");
                p2.setImagen("https://images.unsplash.com/photo-1608270586620-25fd19478f0e?auto=format&fit=crop&w=500&q=60");
                p2.setCategoria(alcohol);

                Producto p3 = new Producto();
                p3.setNombre("Jugo de Naranja Natural");
                p3.setPrecio(2000.0);
                p3.setDestacado(false);
                p3.setDescripcion("Jugo 100% natural sin azúcar añadida.");
                p3.setImagen("https://images.unsplash.com/photo-1600271886742-f049cd451bba?auto=format&fit=crop&w=500&q=60");
                p3.setCategoria(bebidas);

                productoRepository.saveAll(List.of(p1, p2, p3));
                System.out.println("Productos de demostración cargados exitosamente.");
            }
        };
    }
}