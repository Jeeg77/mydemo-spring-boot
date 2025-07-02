package com.example.demo.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/*
 * In teoria questa classe è facoltativa ma è utile per aggiungere una serie di informazioni allo swagger dell'applicazione.
 * Informazioni che poi sono anche visibili una volta che da browser si accede allo swagger.
 */
@Configuration
public class OpenApiConfig {
	
	@Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("MyDemo spring-boot").version("1.0"))
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new Components()
                .addSecuritySchemes("bearerAuth",
                    new SecurityScheme()
                        .name("Authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
    }
	
}

