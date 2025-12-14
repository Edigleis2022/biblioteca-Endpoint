package br.ifm.edu.biblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Biblioteca_EndPoint API")
                .version("1.0")
                .description("Criando Biblioteca com Spring Boot. Bruno e Edigleis."));
    }
}
    