package edu.depaul.g6.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "edu.depaul.g6.service", "edu.depaul.g6.ui" })
@EntityScan(basePackages = { "edu.depaul.g6.domain" })
@EnableJpaRepositories(basePackages = { "edu.depaul.g6.repository" })
public class G6UtilitiesApplication {
    public static void main(String[] args) {
        SpringApplication.run(G6UtilitiesApplication.class, args);
    }
}
