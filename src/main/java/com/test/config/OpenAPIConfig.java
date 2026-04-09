package com.test.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI().info(new Info()
                .version("v1.0.0")
                .title("UserManagement API")
                .summary("This is a user management api")
                .contact(new Contact()
                        .email("jeee@gmail.com")));
    }

}
