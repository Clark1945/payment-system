package com.example.payment_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.example.payment_system.orm")
@EnableJpaRepositories(basePackages = {"com.example.payment_system.repository"})
@SpringBootApplication(scanBasePackages = {"com.example.payment_system"})
public class PaymentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentSystemApplication.class, args);
    }

}
