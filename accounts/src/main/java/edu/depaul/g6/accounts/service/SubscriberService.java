package edu.depaul.g6.accounts.service;

import edu.depaul.g6.accounts.domain.Account;
import edu.depaul.g6.accounts.domain.Role;
import edu.depaul.g6.accounts.domain.Subscriber;
import edu.depaul.g6.accounts.repository.AccountRepository;
import edu.depaul.g6.accounts.repository.SubscriberRepository;
import edu.depaul.g6.commons.Utilities;
import edu.depaul.g6.facilities.domain.Location;
import edu.depaul.g6.facilities.service.Facilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class SubscriberService {

    private Facilities facilities;

    @Autowired
    public void setFacilities(Facilities f) {
        this.facilities = f;
    }

    private final SubscriberRepository subscriberRepository;
    private AccountRepository accountRepository;
    private Utilities utilities;

    @Autowired
    public SubscriberService(SubscriberRepository repository) {
        this.subscriberRepository = repository;
    }

    @Autowired
    public void setAccountRepository(AccountRepository repository) {
        this.accountRepository = repository;
    }

    @Autowired
    public void setUtilities(Utilities generator) {
        this.utilities = generator;
    }

    /**
     * Persists both a subscriber and an account objects.
     * @param subscriber
     */
    public String saveSubscriber(Subscriber subscriber) throws NoSuchAlgorithmException {
        /*
         * Generate account number, set id of the
         * subscriber, and persist the subscriber.
         */
        final String accountNumber = utilities.generateAccountNumber(subscriber.getEmail());
        subscriber.setId(accountNumber);
        subscriberRepository.save(subscriber);
        log.info("Subscriber " + subscriber.getId() + " saved to database.");
        /*
         * Create a new account and persist it.
         */
        Account account = new Account();
        account.setId(subscriber.getId());
        account.setEmail(subscriber.getEmail());
        final String encodedPassword = utilities.encodePassword(subscriber.getId());
        account.setPassword(encodedPassword);
        account.setRole(Role.ROLE_USER);
        accountRepository.save(account);
        log.info("Account for " + account.getEmail() + " saved into database.");
        /*
         * Send subscription to facilities for activation.
         */
        Location location = new Location();
        location.setStreetAddress(subscriber.getStreetAddress());
        location.setUnit(subscriber.getUnit());
        location.setCity(subscriber.getCity());
        location.setState(subscriber.getState());
        location.setZipCode(subscriber.getZipCode());
        facilities.activateService(accountNumber, location, subscriber.getServiceType());
        log.info("Activation request for " + subscriber.getId() + " sent to facilities.");
        return accountNumber;
    }
}
