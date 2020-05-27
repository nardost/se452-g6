package edu.depaul.g6.opms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartMeterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartMeterApplication.class, args);

    }
}
