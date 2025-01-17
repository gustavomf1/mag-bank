package com.gustavo.mag_bank.config;


import com.gustavo.mag_bank.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public CommandLineRunner instanciaDB() {
        return args -> {
            System.out.println("Valor de spring.jpa.hibernate.ddl-auto: " + value);
            if ("create".equals(value)) {
                dbService.instanciaDB();
            }
        };
    }
}
