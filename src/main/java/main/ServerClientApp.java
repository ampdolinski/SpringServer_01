package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Adam Doliński
 * 08.04.2020
 */

@SpringBootApplication
@EnableAsync
public class ServerClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerClientApp.class, args);
    }
}