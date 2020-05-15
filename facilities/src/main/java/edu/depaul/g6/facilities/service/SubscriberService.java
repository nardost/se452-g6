package edu.depaul.g6.facilities.service;

import edu.depaul.g6.facilities.domain.Subscriber;
import edu.depaul.g6.facilities.repository.SubscriberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SubscriberService {

    private final SubscriberRepository repository;
    private Facilities facilities;

    @Autowired
    SubscriberService(SubscriberRepository repository) {
        this.repository = repository;
    }

    @Autowired
    void setFacilities(Facilities f) {
        this.facilities = f;
    }

    void saveSubscriber(Subscriber subscriber) {
        /*
         * Persist the incoming subscriber.
         */
        repository.save(subscriber);
        log.info("Subscriber " + subscriber.getId() + " saved to database.");
        /*
         * Send subscription to facilities for activation.
         */
        facilities.activateService(subscriber);
        log.info("Activation request for " + subscriber.getId() + " sent to facilities.");
    }
}
