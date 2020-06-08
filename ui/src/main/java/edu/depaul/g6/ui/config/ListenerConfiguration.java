package edu.depaul.g6.ui.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author nardos
 *
 * This class defines beans for HTTP Session Listeners
 */
@Configuration
@Slf4j
public class ListenerConfiguration {

    @Bean
    public HttpSessionListener httpSessionListener() {
        
        return new HttpSessionListener() {

            @Override
            public void sessionCreated(HttpSessionEvent se) {
                log.info(String.format("Session with ID %s created.", se.getSession().getId()));
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent se) {
                log.info(String.format("Session with ID %s destroyed.", se.getSession().getId()));
            }
        };
    }
}
