package com.shashi.taskmanagement.Task.Management.System.apiConfig;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class OpenAPIConfig {
    @Bean
    public OpenAPI productServiceAPI(){
        return new OpenAPI().
                info(new Info().title("Task Management System")
                        .description("This is the REST API for Task Management System Service")
                        .version("v0.01.01")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Inventory Service Wiki Documentation")
                        .url("https://Order-service-dummy-url.com/docs"));

    }
}
