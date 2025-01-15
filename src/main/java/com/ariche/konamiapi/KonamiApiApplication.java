package com.ariche.konamiapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * API for Konami Gaming.
 */
@SpringBootApplication
@EnableAsync
public class KonamiApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KonamiApiApplication.class, args);
    }

}
