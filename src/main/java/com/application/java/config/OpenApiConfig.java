package com.application.java.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private final String title;
    private final String description;
    private final String version;

    public OpenApiConfig(
            @Value("${application.swagger.title}")
            final String title,
            @Value("${application.swagger.description}")
            final String description,
            @Value("${application.swagger.version}")
            final String version) {
        this.title = title;
        this.description = description;
        this.version = version;
    }

    public OpenAPI customOpenAPI(){
        return new OpenAPI();
    }

}
