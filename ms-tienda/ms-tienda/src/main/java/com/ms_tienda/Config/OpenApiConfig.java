package com.ms_tienda.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "MS-TIENDA API",
        version = "1.0",
        description = "Documentación del catálogo de productos.",
        contact = @Contact(name = "Soporte", email = "soporte@tienda.cl")
    ),
    // IMPORTANTE: Esto le dice a Swagger que las peticiones se hagan al Gateway (8080)
    servers = {
        @Server(url = "http://localhost:8080", description = "API Gateway Server"),
        @Server(url = "http://localhost:8082", description = "Servidor Local Directo")
    }
)
public class OpenApiConfig {
}