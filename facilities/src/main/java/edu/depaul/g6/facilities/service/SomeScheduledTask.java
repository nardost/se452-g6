package edu.depaul.g6.facilities.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SomeScheduledTask {

    @Scheduled(fixedDelay = 1000)
    public void scheduledTask() {
        log.info("Doing scheduled task");
    }
}
