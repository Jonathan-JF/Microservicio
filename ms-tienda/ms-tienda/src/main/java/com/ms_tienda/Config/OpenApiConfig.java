package com.ms_tienda.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "MS-TIENDA API (Microservicio de Tienda/Catálogo)",
        version = "1.0",
        description = "API para gestionar el catálogo de productos y el historial de boletas (compras).",
        contact = @Contact(name = "Equipo de Desarrollo", email = "dev@tienda.cl")
    ),
    security = {
        @SecurityRequirement(name = "BearerAuth")
    }
)
@SecurityScheme(
    name = "BearerAuth", // Nombre de referencia para las operaciones
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    description = "Token JWT (simulado) para acceder a recursos protegidos. Requiere el prefijo 'Bearer '."
)
public class OpenApiConfig {
}