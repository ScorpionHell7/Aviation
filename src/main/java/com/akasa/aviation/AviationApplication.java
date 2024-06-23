package com.akasa.aviation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
@EnableJpaRepositories("com.akasa.aviation.repository")
@EntityScan("com.akasa.aviation.model")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AviationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AviationApplication.class, args);
    }

}
