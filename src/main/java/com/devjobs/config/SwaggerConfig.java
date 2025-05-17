package com.devjobs.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI devJobsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DevJobs REST API")
                        .description("Job platform built with Java 17, Spring Boot 3, Rich DDD, and Hexagonal Architecture.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Zuma")
                                .url("https://github.com/zuamirgoliveira")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Project Repository")
                        .url("https://github.com/zuamirgoliveira/dev-jobs-backend"));
    }
}
