package com.uniquindio.software.demoproyectosalondebelleza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoProyectoSalonDeBellezaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProyectoSalonDeBellezaApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createPasswordsCommand(){
        return args -> {
            System.out.println(passwordEncoder.encode("12345"));
            System.out.println(passwordEncoder.encode("admin_secure"));
        };
    }
}
