package com.ms_tienda.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Tienda Online (Microservicio Productos)",
                version = "1.0",
                description = "Documentación oficial para el examen final. Gestiona productos, stock y boletas.",
                contact = @Contact(
                        name = "Tu Nombre",
                        email = "tu.email@duocuc.cl"
                ),
                license = @License(
                        name = "Standard License",
                        url = "http://duoc.cl"
                )
        )
)
public class OpenApiConfig {
}