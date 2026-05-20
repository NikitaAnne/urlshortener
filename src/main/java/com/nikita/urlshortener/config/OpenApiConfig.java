package com.nikita.urlshortener.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI urlShortenerOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("URL Shortener API")
                        .description("Spring Boot URL Shortener with Redis Caching")
                        .version("1.0")

                        .contact(new Contact()
                                .name("Nikita Jacob")
                                .email("nikiannejacob1298@gmail.com"))

                        .license(new License()
                                .name("MIT License")))

                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation"));
    }
}
