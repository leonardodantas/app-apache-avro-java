package com.application.java.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(getInfo());
    }

    private Info getInfo() {
        return new Info().title(title.toUpperCase()).description(description).version(version);
    }

}
