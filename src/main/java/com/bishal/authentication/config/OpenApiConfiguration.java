package com.bishal.authentication.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server()
                .url("http://localhost:8080")
                .description("Local server");

        Contact contact = new Contact()
                .name("Bishal")
                .email("giribsaal@gmail.com");

        Info information = new Info()
                .title("Authentication APIs")
                .version("1.0")
                .description("Authentication in Spring Boot")
                .contact(contact);

        return new OpenAPI()
                .info(information)
                .servers(List.of(server))
                .components(
                        new Components()
                        .addSecuritySchemes(
                                "bearer-key",
                                new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }
}
