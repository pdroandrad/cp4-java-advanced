package com.example.MiniMercado.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI miniMercadoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MiniMercado API")
                        .description("Documentação da API do MiniMercado")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repo")
                        .url("https://github.com/pdroandrad/cp4-java-advanced"));
    }
}
