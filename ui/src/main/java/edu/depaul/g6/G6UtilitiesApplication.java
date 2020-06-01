package edu.depaul.g6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;


@SpringBootApplication
@EnableScheduling
@EnableRedisHttpSession
public class G6UtilitiesApplication {
    public static void main(String[] args) {
        SpringApplication.run(G6UtilitiesApplication.class, args);
    }

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
}
