package com.ms_auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Autenticación (Microservicio Auth)",
                version = "1.0",
                description = "Endpoints para registro, login y validación de tokens JWT.",
                contact = @Contact(
                        name = "Tu Nombre",
                        email = "tu.email@duocuc.cl"
                )
        )
)
public class OpenApiConfig {
}