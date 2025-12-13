package com.ms_tienda.config.deprecated;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

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
    name = "BearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    description = "Token JWT (simulado) para acceder a recursos protegidos. Requiere el prefijo 'Bearer '."
)
public class OpenApiConfigOld {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                    .title("MS-TIENDA API (Microservicio de Tienda/Catálogo)")
                    .version("1.0")
                    .description("API para gestionar el catálogo de productos y el historial de boletas (compras).")
                    .contact(new io.swagger.v3.oas.models.info.Contact().name("Equipo de Desarrollo").email("dev@tienda.cl"))
                );
    }
}