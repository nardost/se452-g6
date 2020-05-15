package edu.depaul.g6.ui.service;

import edu.depaul.g6.commons.IdGenerator;
import edu.depaul.g6.facilities.domain.Location;
import edu.depaul.g6.facilities.domain.ServiceCategory;
import edu.depaul.g6.facilities.domain.Subscriber;
import edu.depaul.g6.facilities.domain.Subscription;
import edu.depaul.g6.facilities.service.Facilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class FacilitiesService {

    private final Facilities facilities;
    private IdGenerator idGenerator;

    @Autowired
    public FacilitiesService(Facilities facilities) {
        this.facilities = facilities;
    }

    @Autowired
    public void setIdGenerator(IdGenerator generator) {
        this.idGenerator = generator;
    }

    public void activateService(Subscriber subscriber) {
        facilities.activateService(subscriber);
    }

    public List<ServiceCategory> getAllCategories() {
        return facilities.getAllCategories();
    }

    public List<Location> getLocations(Integer zipCode) {
        return (zipCode == null) ? facilities.getAllLocations() : facilities.getAllLocationsInZipCode(zipCode);
    }

    public Subscription getSubscription(String accountNumber) {
        return facilities.getSubscription(accountNumber);
    }

    public List<Subscription> getAllSubscriptions() {
        return facilities.getAllSubscriptions();
    }

    public List<String> getServiceStates() {
        return facilities.getStatesServed();
    }

    public void saveSubscriber(Subscriber subscriber) throws NoSuchAlgorithmException {
        subscriber.setId(idGenerator.generateAccountNumber(subscriber.getEmail()));
        facilities.saveSubscriber(subscriber);
    }
}
