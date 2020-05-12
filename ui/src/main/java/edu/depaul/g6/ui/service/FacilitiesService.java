package edu.depaul.g6.ui.service;

import edu.depaul.g6.facilities.domain.Location;
import edu.depaul.g6.facilities.domain.ServiceCategory;
import edu.depaul.g6.facilities.domain.Subscription;
import edu.depaul.g6.facilities.service.Facilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilitiesService {

    private Facilities facilities;

    @Autowired
    public FacilitiesService(Facilities facilities) {
        this.facilities = facilities;
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
}
