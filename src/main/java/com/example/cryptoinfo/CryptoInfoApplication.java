package com.example.cryptoinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class CryptoInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoInfoApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
