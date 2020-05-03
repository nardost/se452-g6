package edu.depaul.g6.facilities.service;

import edu.depaul.g6.facilities.domain.Subscription;
import edu.depaul.g6.facilities.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    @Autowired
    SubscriptionService(SubscriptionRepository repository) {
        this.subscriptionRepository = repository;
    }

    Subscription getSubscription(String accountNumber) {
        return subscriptionRepository.findById(accountNumber).orElse(null);
    }

    List<Subscription> getAllSubscriptions() {
        return StreamSupport.stream(subscriptionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
