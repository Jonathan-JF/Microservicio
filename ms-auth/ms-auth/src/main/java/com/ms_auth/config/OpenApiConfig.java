package com.ms_auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "MS-AUTH API (Microservicio de Autenticación)",
        version = "1.0",
        description = "API para manejar el registro, inicio de sesión (login) y autenticación de usuarios. Implementa autenticación basada en tokens JWT.",
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
    description = "Token JWT (simulado) para acceder a recursos protegidos."
)
public class OpenApiConfig {

    // Not registering a GroupedOpenApi bean to avoid runtime dependency issues.

}