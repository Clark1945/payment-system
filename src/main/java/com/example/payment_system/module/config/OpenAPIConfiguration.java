package com.example.payment_system.module.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Clark Liu");
        myContact.setEmail("ziegler7359@gmail.com");

        Info information = new Info()
                .title("Payment System API")
                .version("1.0")
                .description("這是會員系統與支付系統的API")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
