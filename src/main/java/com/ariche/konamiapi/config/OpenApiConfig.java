package com.ariche.konamiapi.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI (Swagger), config to document/poke at the endpoints
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("com.ariche.konamiapi.controller")
                .build();
    }
}
