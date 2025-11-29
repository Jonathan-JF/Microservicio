package com.ms_auth;

import com.ms_auth.model.Usuario;
import com.ms_auth.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MsAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAuthApplication.class, args);
	}


 @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                System.out.println("Cargando usuarios de demostración...");

                // Usuario Administrador: para probar restricciones (IE3.3.3)
                Usuario admin = new Usuario();
                admin.setNombre("Admin Tienda");
                admin.setEmail("admin@tienda.cl");
                admin.setPassword(passwordEncoder.encode("password")); // Usar PasswordEncoder
                admin.setRol("ADMIN");
                usuarioRepository.save(admin);
                
                // Usuario Cliente: para probar el flujo normal
                Usuario cliente = new Usuario();
                cliente.setNombre("Cliente Demo");
                cliente.setEmail("cliente@demo.cl");
                cliente.setPassword(passwordEncoder.encode("password")); // Usar PasswordEncoder
                cliente.setRol("CLIENTE");
                usuarioRepository.save(cliente);

                System.out.println("Usuarios de demostración cargados (admin@tienda.cl, cliente@demo.cl).");
            }
        };
    }
}