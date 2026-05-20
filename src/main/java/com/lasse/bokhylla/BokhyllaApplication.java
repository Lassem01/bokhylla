package com.lasse.bokhylla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BokhyllaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BokhyllaApplication.class, args);
    }

    // Denne kjører ETTER at appen er ferdig startet, og skriver ut lenka.
    // ApplicationReadyEvent betyr "alt er klart, serveren tar imot trafikk".
    @Bean
    public ApplicationListener<ApplicationReadyEvent> printUrl(Environment env) {
        return event -> {
            // Henter porten fra konfigurasjonen (8080 hvis ikke annet er satt)
            String port = env.getProperty("server.port", "8080");
            String url = "http://localhost:" + port;
            System.out.println("\n  📚  Bokhylla er klar!  Åpne:  " + url + "\n");
        };
    }
}