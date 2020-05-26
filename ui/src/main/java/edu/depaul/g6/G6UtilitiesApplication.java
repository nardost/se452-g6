package edu.depaul.g6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class G6UtilitiesApplication {
    public static void main(String[] args) {
        SpringApplication.run(G6UtilitiesApplication.class, args);
    }
}
